package org.proundmega.sudokucore;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import org.proundmega.sudokucore.elementos.Celda;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.proundmega.sudokucore.elementos.Columna;
import org.proundmega.sudokucore.elementos.Fila;
import org.proundmega.sudokucore.elementos.Valor;

@Value
@AllArgsConstructor
public class Posicion implements Comparable<Posicion>{
    private Fila fila;
    private Columna columna;
    private Celda celda;
    private Set<Valor> anotaciones;
    
    private static Posicion POSICION_NULA = new Posicion(null, null, Valor.VACIA);
    
    public Posicion(int fila, int columna, Celda celda) {
        this.fila = Fila.toFila(fila);
        this.columna = Columna.toColumna(columna);
        this.celda = celda;
        this.anotaciones = new TreeSet<>();
    }

    public Posicion(Fila fila, Columna columna, Celda celda) {
        this.fila = fila;
        this.columna = columna;
        this.celda = celda;
        this.anotaciones = new TreeSet<>();
    }
    
    public Posicion(Fila fila, Columna columna, Valor valor) {
        this.fila = fila;
        this.columna = columna;
        this.celda = new Celda(valor);
        this.anotaciones = new TreeSet<>();
    }

    public Posicion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int getFilaAsNumero() {
        return fila.getIndice();
    }
    
    public int getColumnaAsNumero() {
        return columna.getIndice();
    }
    
    public Posicion addAnotacion(Valor anotacion) {
        Set<Valor> anotacionesCopia = anotaciones.stream()
                .collect(Collectors.toCollection(TreeSet::new));
        anotacionesCopia.add(anotacion);
        return new Posicion(fila, columna, celda, anotacionesCopia);
    }
    
    public Posicion addAnotaciones(Set<Valor> anotaciones) {
        return new Posicion(fila, columna, celda, anotaciones);
    }
    
    public Posicion removeAnotaciones(Set<Valor> anotacionesARemover) {
        Set<Valor> anotacionesConRemociones = this.anotaciones.stream()
                .filter(anotacion -> !anotacionesARemover.contains(anotacion))
                .collect(Collectors.toCollection(TreeSet::new));
        
        return new Posicion(fila, columna, celda, anotacionesConRemociones);
    }

    @Override
    public int compareTo(Posicion o) {
        int compareFila = Integer.compare(this.fila.getIndice(), o.fila.getIndice());
        if(compareFila != 0) {
            return compareFila;
        }
        
        return Integer.compare(this.columna.getIndice(), o.columna.getIndice());
    }
    
    public static Posicion posicionNula() {
        return POSICION_NULA;
    }
    
    public static boolean esNula(Posicion posicion) {
        return posicion == POSICION_NULA;
    }
}
