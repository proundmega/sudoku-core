package org.proundmega.sudokucore.elementos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.proundmega.sudokucore.Posicion;

public enum Cuadrante {
    SUPERIOR_IZQUIERO(0, 0),
    SUPERIOR_CENTRAL(0, 3),
    SUPERIOR_DERECHO(0, 6),
    CENTRAL_IZQUIERO(3, 0),
    CENTRAL_CENTRAL(3, 3),
    CENTRAL_DERECHO(3, 6),
    INFERIOR_IZQUIERO(6, 0),
    INFERIOR_CENTRAL(6, 3),
    INFERIOR_DERECHO(6, 6);

    private final int offsetFila;
    private final int offsetColumna;

    private Cuadrante(int offsetFila, int offsetColumna) {
        this.offsetFila = offsetFila;
        this.offsetColumna = offsetColumna;
    }
    
    public int getOffsetFila() {
        return offsetFila + 1;
    }

    public int getOffsetColumna() {
        return offsetColumna + 1;
    }

    public Celda[][] getCuadrante(Celda[][] celdas) {
        return Arrays.stream(celdas, offsetFila, offsetFila + 3)
                .map(tupla -> Arrays.copyOfRange(tupla, offsetColumna, offsetColumna + 3))
                .toArray(Celda[][]::new);
    }

    public List<Posicion> getCuadranteAsList(Celda[][] celdas) {
        return agregarSi(celdas, celda -> true);
    }

    private List<Posicion> agregarSi(Celda[][] celdas, Predicate<Celda> predicado) {
        List<Posicion> posiciones = new ArrayList<>();

        for (int fila = offsetFila; fila < offsetFila + 3; fila++) {
            for (int columna = offsetColumna; columna < offsetColumna + 3; columna++) {
                if (predicado.test(celdas[fila][columna])) {
                    Posicion posicion = new Posicion(fila + 1, columna + 1, celdas[fila][columna]);
                    posiciones.add(posicion);
                }
            }
        }

        return Collections.unmodifiableList(posiciones);
    }

    public List<Posicion> getCeldasVacias(Celda[][] celdas) {
        return agregarSi(celdas, Celda::estaVacia);
    }

    public List<Posicion> getCeldasConValor(Celda[][] celdas) {
        return agregarSi(celdas, celda -> !celda.estaVacia());
    }

    public List<Posicion> getCeldasHorizontalesConValor(Celda[][] celdas) {
        return Arrays.stream(Cuadrante.values())
                .filter(cuadrante -> cuadrante != this)
                .filter(cuadrante -> cuadrante.getOffsetFila() == this.getOffsetFila())
                .flatMap(cuadrante -> cuadrante.getCuadranteAsList(celdas).stream())
                .collect(Collectors.toList());
    }

    public List<Posicion> getCeldasVerticalesConValor(Celda[][] celdas) {
        return Arrays.stream(Cuadrante.values())
                .filter(cuadrante -> cuadrante != this)
                .filter(cuadrante -> cuadrante.getOffsetColumna()== this.getOffsetColumna())
                .flatMap(cuadrante -> cuadrante.getCuadranteAsList(celdas).stream())
                .collect(Collectors.toList());
    }

}
