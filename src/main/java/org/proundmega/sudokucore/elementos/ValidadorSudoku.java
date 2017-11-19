package org.proundmega.sudokucore.elementos;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.proundmega.sudokucore.InvalidSudokuException;
import org.proundmega.sudokucore.Posicion;

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
        return getPosicionesRepetidasDePosicionable(celdas, fila).isEmpty();
    }
    
    private static List<Posicion> getPosicionesRepetidasDePosicionable(Celda[][] celdas, Posicionable posicionable) {
        List<Posicion> posiciones = posicionable.getPosiciones(celdas);
        Map<Valor, Long> conteo = getConteoPorValorList(posiciones);
        
        Set<Valor> celdasRepetidas = conteo.entrySet().stream()
                .filter(tupla -> tupla.getValue() != 1)
                .map(tupla -> tupla.getKey())
                .collect(Collectors.toSet());
        
        return posiciones.stream()
                .filter(posicion -> celdasRepetidas.contains(posicion.getCelda().getValorActual()))
                .collect(Collectors.toList());
                
    }
    
    private static Map<Valor, Long> getConteoPorValorList(List<Posicion> posiciones) {
        Map<Valor, Long> elementosContados = posiciones.stream()
                .map(Posicion::getCelda)
                .filter(celda -> !celda.estaVacia())
                .map(Celda::getValorActual)
                .collect(
                        Collectors.groupingBy(Function.identity(), Collectors.counting()
                        )
                );
        return elementosContados;
    }
    
    public static boolean esColumnaValida(Celda[][] celdas, Columna columna) {
        return getPosicionesRepetidasDePosicionable(celdas, columna).isEmpty();
    }
    
    public static boolean esCuadranteValido(Celda[][] celdas, Cuadrante cuadrante) {
        return getPosicionesRepetidasDePosicionable(celdas, cuadrante).isEmpty();
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
                manejarExcepcionDeElementosRepetidos(celdas, fila);
            }
            if(!esFilaCompleta(celdas, fila)) {
                return false;
            }
        }
        return true;
    }

    private static void manejarExcepcionDeElementosRepetidos(Celda[][] celdas, Posicionable posicionable) throws InvalidSudokuException {
        List<Posicion> posicionesRepetidas = getPosicionesRepetidasDePosicionable(celdas, posicionable);
        throw new InvalidSudokuException("Sudoku no valido en la fila " + posicionable.toString() +  ": entradas duplicadas ", posicionesRepetidas);
    }
    
    private static boolean columnasValidasYCompletas(Celda[][] celdas) {
        for(Columna columna : Columna.values()) {
            if (!esColumnaValida(celdas, columna)) {
                manejarExcepcionDeElementosRepetidos(celdas, columna);
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
                manejarExcepcionDeElementosRepetidos(celdas, cuadrante);
            }
            if(!esCuadranteCompleto(celdas, cuadrante)) {
                return false;
            }
        }
        return true;
    }

}
