package org.proundmega.sudokucore.elementos.grid;

import org.proundmega.sudokucore.elementos.Valor;
import org.proundmega.sudokucore.elementos.Celda;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.proundmega.sudokucore.Posicion;
import org.proundmega.sudokucore.elementos.Celdas;
import org.proundmega.sudokucore.elementos.Columna;
import org.proundmega.sudokucore.elementos.Cuadrante;
import org.proundmega.sudokucore.elementos.Fila;
import org.proundmega.sudokucore.elementos.Posicionable;
import org.proundmega.sudokucore.elementos.ValidadorSudoku;
import org.proundmega.sudokucore.elementos.anotador.Anotador;
import org.proundmega.sudokucore.elementos.anotador.AnotadorGeneral;

@EqualsAndHashCode
public class Grid {
    private Celda[][] celdas = new Celda[9][9];
    
    public Grid() {
        crearCeldasVacias();
    }

    private void crearCeldasVacias() {
        for (int fila = 0; fila < 9; fila++) {
            for (int columna = 0; columna < 9; columna++) {
                celdas[fila][columna] = new Celda();
            }
        }
    }
    
    public Grid(Celda[][] celdas) {
        verificarCeldasEnviadas(celdas); 
        this.celdas = copiarCeldas(celdas);
    }
    
    public Grid(String[][] gridAsString) {
        verificarCeldasEnviadas(gridAsString);
        pasarLosValoresACelda(gridAsString);
    }

    private void pasarLosValoresACelda(String[][] gridAsString) {
        for(int fila = 0; fila < 9; fila++) {
            for(int columna = 0; columna < 9; columna++) {
                Valor valor = Valor.toValor(gridAsString[fila][columna]);
                this.celdas[fila][columna] = new Celda(valor);
            }
        }
    }
    
    private static <T> void verificarCeldasEnviadas(@NonNull T[][] celdasEnviadas) throws IllegalArgumentException {
        if (celdasEnviadas.length == 0) { 
            throw new IllegalArgumentException("Matriz de sudoku con numero de filas 0...");
        }
        if (celdasEnviadas.length != 9 || celdasEnviadas[0].length != 9) {
            throw new IllegalArgumentException("Las celdas enviadas poseen dimensiones " + celdasEnviadas.length + "x" + celdasEnviadas[0].length + " por lo que no es un " + "Sudoku valido");
        }
    }

    private Celda[][] copiarCeldas(Celda[][] celdasACopiar) {
        Celda[][] celdas = new Celda[9][9];
        for (int fila = 0; fila < 9; fila++) {
            for (int columna = 0; columna < 9; columna++) {
                celdas[fila][columna] = celdasACopiar[fila][columna];
            }
        }
        
        return celdas;
    }

    @Override
    public String toString() {
        StringBuilder print = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            Celda[] fila = celdas[i];
            print.append(printFila(fila));
            
            if (i == 2 || i == 5) {
                print.append("-------------------------------\n");
            }
        }
        
        return print.toString();
    }

    private String printFila(Celda[] fila) {
        StringBuilder print = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            Celda celda = fila[i];
            print.append(celda.toString());
            
            if (i == 2 || i == 5) {
                print.append("  ");
            }
        }
        print.append("\n");
        return print.toString();
    }
    
    public Grid reemplazarCasilla(@NonNull Fila fila, @NonNull Columna columna, @NonNull Valor valor) {
        Celda[][] copia = copiarCeldas(celdas);
        copia[fila.getIndiceFilaParaArray()][columna.getIndiceColumnaParaArray()] = new Celda(valor);
        
        return new Grid(copia);
    }
    
    public Grid reemplazarCasilla(Posicion posicion) {
        return reemplazarCasilla(posicion.getFila(), posicion.getColumna(), posicion.getCelda().getValorActual());
    }
    
    public SubGridCuadrante getSubGrid(Cuadrante cuadrante) {
        return new SubGridCuadrante(celdas, cuadrante);
    }
    
    public boolean isGridResuelta() {
        return ValidadorSudoku.esCeldasValidasYCompletas(celdas);
    }
    
    public Posicion getPosicion(Fila fila, Columna columna) {
        return Celdas.getPosicion(celdas, fila, columna);
    }

    public List<Posicion> getPosiciones() {
        return Celdas.asPosiciones(celdas);
    }
    
    public String[][] getValoresAsString() {
        String[][] gridAsString = new String[9][9];
        
        for(int fila = 0; fila < 9; fila++) {
            for(int columna = 0; columna < 9; columna++) {
                gridAsString[fila][columna] = celdas[fila][columna].getValorActual().toString();
            }
        }
        
        return gridAsString;
    }
    
    public Anotador getAnotador(Posicionable posicionable) {
        return new AnotadorGeneral(celdas, posicionable);
    }
}
