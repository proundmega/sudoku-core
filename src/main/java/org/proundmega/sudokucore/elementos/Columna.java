package org.proundmega.sudokucore.elementos;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.proundmega.sudokucore.Celda;

public enum Columna {
    _1(1),
    _2(2),
    _3(3),
    _4(4),
    _5(5),
    _6(6),
    _7(7),
    _8(8),
    _9(9);
    
    private static final Map<Integer, Columna> mapa = new HashMap<>();
    
    static {
        for(Columna columna : values()) {
            mapa.put(columna.indiceColumna, columna);
        }
    }
    

    public static Columna toColumna(int columna) {
        return mapa.get(columna);
    }
    
    private int indiceColumna;

    private Columna(int fila) {
        this.indiceColumna = fila;
    }

    public int getIndiceColumna() {
        return indiceColumna - 1;
    }
    
    public Celda[] getColumnas(Celda[][] celdas) {
        return Arrays.stream(celdas)
                .map(tupla -> tupla[getIndiceColumna()])
                .toArray(Celda[]::new);
    }
}
