package org.proundmega.sudokucore.utils;

import java.util.List;
import java.util.stream.Collectors;
import org.proundmega.sudokucore.Posicion;

public class Posiciones {

    public static String joinPosicionesAsCoordenadasSinValor(List<Posicion> posiciones) {
        return posiciones.stream()
                    .map(posicion -> "(" + posicion.getFila().getIndice() + ", " + posicion.getColumna().getIndice() + ")")
                    .collect(Collectors.joining(", "));
    }
}
