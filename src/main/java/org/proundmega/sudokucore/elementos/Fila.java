package org.proundmega.sudokucore.elementos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.proundmega.sudokucore.Posicion;

public enum Fila {
    _1(1),
    _2(2),
    _3(3),
    _4(4),
    _5(5),
    _6(6),
    _7(7),
    _8(8),
    _9(9);

    private static final Map<Integer, Fila> mapa = new HashMap<>();

    static {
        for (Fila fila : values()) {
            mapa.put(fila.indiceFila, fila);
        }
    }
    
    /**
     * Este metodo devuelve la fila en numero a su equivalente en objeto Fila
     *
     * @param fila
     * @return
     */
    public static Fila toFila(int fila) {
        return mapa.get(fila);
    }

    private int indiceFila;

    private Fila(int fila) {
        this.indiceFila = fila;
    }

    public int getIndiceFilaParaArray() {
        return indiceFila - 1;
    }

    public Celda[] getFila(Celda[][] celdas) {
        return celdas[getIndiceFilaParaArray()];
    }

    public List<Posicion> getfilaAsList(Celda[][] celdas) {
        List<Posicion> posiciones = new ArrayList<>();

        for (int columna = 0; columna < celdas[0].length; columna++) {
            Posicion posicion = new Posicion(indiceFila, columna + 1, celdas[getIndiceFilaParaArray()][columna]);
            posiciones.add(posicion);
        }

        return Collections.unmodifiableList(posiciones);
    }

}
