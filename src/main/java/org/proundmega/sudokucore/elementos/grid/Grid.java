package org.proundmega.sudokucore.elementos.grid;

import org.proundmega.sudokucore.elementos.Valor;
import org.proundmega.sudokucore.elementos.Celda;
import java.util.Arrays;
import lombok.NonNull;
import org.proundmega.sudokucore.elementos.Columna;
import org.proundmega.sudokucore.elementos.Cuadrante;
import org.proundmega.sudokucore.elementos.Fila;
import org.proundmega.sudokucore.elementos.ValidadorSudoku;

public class Grid {
    private Celda[][] celdas = new Celda[9][9];
    
    public Grid() {
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
    
    private void verificarCeldasEnviadas(@NonNull Celda[][] celdasEnviadas) throws IllegalArgumentException {
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
        String print = "";
        for (int i = 0; i < 9; i++) {
            Celda[] fila = celdas[i];
            print += printFila(fila);
            
            if (i == 2 || i == 5) {
                print += "-------------------------------\n";
            }
        }
        
        return print;
    }

    private String printFila(Celda[] fila) {
        String print = "";
        for (int i = 0; i < 9; i++) {
            Celda celda = fila[i];
            print += celda.toString();
            
            if (i == 2 || i == 5) {
                print += "  ";
            }
        }
        print += "\n";
        return print;
    }
    
    @Deprecated
    public Grid resolverCasilla() {
        return new Grid(celdas);
    }
    
    public Grid reemplazarCasilla(@NonNull Fila fila, @NonNull Columna columna, @NonNull Valor valor) {
        Celda[][] copia = copiarCeldas(celdas);
        copia[fila.getIndiceFilaParaArray()][columna.getIndiceColumnaParaArray()] = new Celda(valor);
        
        return new Grid(copia);
    }
    
    public SubGridCuadrante getSubGrid(Cuadrante cuadrante) {
        return new SubGridCuadrante(celdas, cuadrante);
    }
    
    public SubGridFila getSubGrid(Fila fila) {
        return new SubGridFila(celdas, fila);
    }
    
    public SubGridColumna getSubGrid(Columna columna) {
        return new SubGridColumna(celdas, columna);
    }
    
    public boolean isGridResuelta() {
        return ValidadorSudoku.esCeldasValidasYCompletas(celdas);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Arrays.deepHashCode(this.celdas);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Grid other = (Grid) obj;
        if (!Arrays.deepEquals(this.celdas, other.celdas)) {
            return false;
        }
        return true;
    }
    
}
