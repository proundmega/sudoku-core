package org.proundmega.sudokucore.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.proundmega.sudokucore.InvalidSudokuException;
import org.proundmega.sudokucore.Posicion;
import org.proundmega.sudokucore.elementos.Columna;
import org.proundmega.sudokucore.elementos.Cuadrante;
import org.proundmega.sudokucore.elementos.Fila;
import org.proundmega.sudokucore.elementos.Posicionable;
import org.proundmega.sudokucore.elementos.Valor;

public class ValidadorSudoku {
    
    public static boolean esFilaCompleta(Valor[][] valores, Fila fila) {
        return esBloqueCompleto(fila.getFila(valores));
    }

    private static boolean esBloqueCompleto(Valor[] bloque) {
        return !Arrays.stream(bloque)
                .anyMatch(Valor::estaVacia);
    }
    
    public static boolean esColumnaCompleta(Valor[][] celdas, Columna columna) {
        return esBloqueCompleto(columna.getColumna(celdas));
    }
    
    public static boolean esCuadranteCompleto(Valor[][] valores, Cuadrante cuadrante) {
        return esBloqueCompleto(
                Arrays.stream(cuadrante.getCuadrante(valores))
                        .flatMap(tupla -> Arrays.stream(tupla))
                        .toArray(Valor[]::new)
        );
    }
    
    public static boolean esCuadranteCompleto(Valor[][] cuadrante) {
        return esBloqueCompleto(
                Arrays.stream(cuadrante)
                        .flatMap(tupla -> Arrays.stream(tupla))
                        .toArray(Valor[]::new)
        );
    }
    
    public static boolean esFilaValida(Valor[][] celdas, Fila fila) {
        return getPosicionesRepetidasDePosicionable(celdas, fila).isEmpty();
    }
    
    private static List<Posicion> getPosicionesRepetidasDePosicionable(Valor[][] celdas, Posicionable posicionable) {
        List<Posicion> posiciones = posicionable.getPosiciones(celdas);
        Map<Valor, Long> conteo = getConteoPorValorList(posiciones);
        
        Set<Valor> celdasRepetidas = conteo.entrySet().stream()
                .filter(tupla -> tupla.getValue() != 1)
                .map(tupla -> tupla.getKey())
                .collect(Collectors.toSet());
        
        return posiciones.stream()
                .filter(posicion -> celdasRepetidas.contains(posicion.getValorActual()))
                .collect(Collectors.toList());
                
    }
    
    private static Map<Valor, Long> getConteoPorValorList(List<Posicion> posiciones) {
        Map<Valor, Long> elementosContados = posiciones.stream()
                .map(Posicion::getValorActual)
                .filter(valor -> !valor.estaVacia())
                .collect(
                        Collectors.groupingBy(Function.identity(), Collectors.counting()
                        )
                );
        return elementosContados;
    }
    
    public static boolean esColumnaValida(Valor[][] celdas, Columna columna) {
        return getPosicionesRepetidasDePosicionable(celdas, columna).isEmpty();
    }
    
    public static boolean esCuadranteValido(Valor[][] celdas, Cuadrante cuadrante) {
        return getPosicionesRepetidasDePosicionable(celdas, cuadrante).isEmpty();
    }
    
    public static boolean esValorsValidasYCompletas(Valor[][] celdas) {
        boolean filas = filasValidasYCompletas(celdas);
        boolean columnas = columnasValidasYCompletas(celdas);
        boolean cuadrantes = cuadrantesValidosYCompletos(celdas);
        
        return filas && columnas && cuadrantes;
    }
    
    private static boolean filasValidasYCompletas(Valor[][] celdas) {
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

    private static void manejarExcepcionDeElementosRepetidos(Valor[][] celdas, Posicionable posicionable) throws InvalidSudokuException {
        List<Posicion> posicionesRepetidas = getPosicionesRepetidasDePosicionable(celdas, posicionable);
        throw new InvalidSudokuException("Sudoku no valido en la fila " + posicionable.toString() +  ": entradas duplicadas ", posicionesRepetidas);
    }
    
    private static boolean columnasValidasYCompletas(Valor[][] celdas) {
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
    
    private static boolean cuadrantesValidosYCompletos(Valor[][] celdas) {
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
