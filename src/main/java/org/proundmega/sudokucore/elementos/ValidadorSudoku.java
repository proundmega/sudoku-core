package org.proundmega.sudokucore.elementos;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.proundmega.sudokucore.InvalidSudokuException;
import org.proundmega.sudokucore.elementos.grid.SubGridCuadrante;

public class ValidadorSudoku {
    
    public static boolean esFilaCompleta(Celda[][] celdas, Fila fila) {
        return esBloqueCompleto(fila.getFila(celdas));
    }

    private static boolean esBloqueCompleto(Celda[] bloque) {
        return !Arrays.stream(bloque)
                .anyMatch(Celda::estaVacia);
    }
    
    public static boolean esColumnaCompleta(Celda[][] celdas, Columna columna) {
        return esBloqueCompleto(columna.getColumna(celdas));
    }
    
    public static boolean esCuadranteCompleto(Celda[][] celdas, Cuadrante cuadrante) {
        return esBloqueCompleto(
                Arrays.stream(cuadrante.getCuadrante(celdas))
                        .flatMap(tupla -> Arrays.stream(tupla))
                        .toArray(Celda[]::new)
        );
    }
    
    public static boolean esCuadranteCompleto(Celda[][] cuadrante) {
        return esBloqueCompleto(
                Arrays.stream(cuadrante)
                        .flatMap(tupla -> Arrays.stream(tupla))
                        .toArray(Celda[]::new)
        );
    }
    
    public static boolean esFilaValida(Celda[][] celdas, Fila fila) {
        return esBloqueValido(fila.getFila(celdas));
    }
    
    private static boolean esBloqueValido(Celda[] celdas) {
        Map<Celda, Long> elementosContados = Arrays.stream(celdas).filter(celda -> !celda.estaVacia())
                .collect(
                        Collectors.groupingBy(Function.identity(), Collectors.counting()
                        )
                );
        
        return elementosContados.values().stream()
                .allMatch(conteo -> conteo == 1);
    }
    
    public static boolean esColumnaValida(Celda[][] celdas, Columna columna) {
        return esBloqueValido(columna.getColumna(celdas));
    }
    
    public static boolean esCuadranteValido(Celda[][] celdas, Cuadrante cuadrante) {
        return esBloqueValido(Arrays.stream(cuadrante.getCuadrante(celdas))
                        .flatMap(tupla -> Arrays.stream(tupla))
                        .toArray(Celda[]::new)
        );
    }
    
    public static boolean esCeldasValidasYCompletas(Celda[][] celdas) {
        boolean filas = filasValidasYCompletas(celdas);
        boolean columnas = columnasValidasYCompletas(celdas);
        boolean cuadrantes = cuadrantesValidosYCompletos(celdas);
        
        return filas && columnas && cuadrantes;
    }
    
    private static boolean filasValidasYCompletas(Celda[][] celdas) {
        for(Fila fila : Fila.values()) {
            if (!esFilaValida(celdas, fila)) {
                throw new InvalidSudokuException("Sudoku no valido en la fila " + fila.getIndiceFilaParaArray() +  ": entradas duplicadas " );
            }
            if(!esFilaCompleta(celdas, fila)) {
                return false;
            }
        }
        return true;
    }
    
    private static boolean columnasValidasYCompletas(Celda[][] celdas) {
        for(Columna columna : Columna.values()) {
            if (!esColumnaValida(celdas, columna)) {
                throw new InvalidSudokuException("Sudoku no valido en la columna " + columna.getIndiceColumnaParaArray()+  ": entradas duplicadas " );
            }
            if(!esColumnaCompleta(celdas, columna)) {
                return false;
            }
        }
        return true;
    }
    
    private static boolean cuadrantesValidosYCompletos(Celda[][] celdas) {
        for(Cuadrante cuadrante : Cuadrante.values()) {
            if (!esCuadranteValido(celdas, cuadrante)) {
                throw new InvalidSudokuException("Sudoku no valido en el cuadrante " + cuadrante.toString() +  ": entradas duplicadas " );
            }
            if(!esCuadranteCompleto(celdas, cuadrante)) {
                return false;
            }
        }
        return true;
    }
}
