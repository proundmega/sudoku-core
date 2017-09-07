package org.proundmega.sudokucore.elementos;

import java.util.Arrays;
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
