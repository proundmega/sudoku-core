package org.proundmega.sudokucore;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.proundmega.sudokucore.elementos.Columna;
import org.proundmega.sudokucore.elementos.Cuadrante;
import org.proundmega.sudokucore.elementos.Fila;
import org.proundmega.sudokucore.elementos.Valor;

@Value
@AllArgsConstructor
public class Posicion implements Comparable<Posicion>{
    private Fila fila;
    private Columna columna;
    private Valor valorActual;
    private Set<Valor> anotaciones;
    
    private static Posicion POSICION_NULA = new Posicion(null, null, Valor.VACIA);
    
    public Posicion(int fila, int columna, Valor celda) {
        this.fila = Fila.toFila(fila);
        this.columna = Columna.toColumna(columna);
        this.valorActual = celda;
        this.anotaciones = new TreeSet<>();
    }
    
    public Posicion(Fila fila, Columna columna, Valor celda) {
        this.fila = fila;
        this.columna = columna;
        this.valorActual = celda;
        this.anotaciones = new TreeSet<>();
    }
    
    public Posicion(Posicion coordenadas, Valor nuevoValor) {
        this.fila = coordenadas.fila;
        this.columna = coordenadas.columna;
        this.anotaciones = new TreeSet<>();
        this.valorActual = nuevoValor;
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
        return new Posicion(fila, columna, valorActual, anotacionesCopia);
    }
    
    public Posicion addAnotaciones(Set<Valor> anotaciones) {
        return new Posicion(fila, columna, valorActual, anotaciones);
    }
    
    public Posicion removeAnotaciones(Set<Valor> anotacionesARemover) {
        Set<Valor> anotacionesConRemociones = this.anotaciones.stream()
                .filter(anotacion -> !anotacionesARemover.contains(anotacion))
                .collect(Collectors.toCollection(TreeSet::new));
        
        return new Posicion(fila, columna, valorActual, anotacionesConRemociones);
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
    
    public Cuadrante getCuadrante() {
        return Arrays.stream(Cuadrante.values())
                .filter(estaEnElRangoDeFilas())
                .filter(estaEnElRangoDeColumnas())
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
                
                
    }

    private Predicate<Cuadrante> estaEnElRangoDeColumnas() {
        return cuadrante -> cuadrante.getOffsetColumna() <= getColumnaAsNumero() && cuadrante.getOffsetColumna() + 3 > getColumnaAsNumero();
    }

    private Predicate<Cuadrante> estaEnElRangoDeFilas() {
        return cuadrante -> cuadrante.getOffsetFila() <= getFilaAsNumero() && cuadrante.getOffsetFila() + 3 > getFilaAsNumero();
    }
}
