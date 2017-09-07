package org.proundmega.sudokucore.elementos;

import org.proundmega.sudokucore.Celda;

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
    
    private int indiceFila;

    private Fila(int fila) {
        this.indiceFila = fila;
    }

    public int getIndiceFila() {
        return indiceFila - 1;
    }
    
    public Celda[] getFila(Celda[][] celdas) {
        return celdas[getIndiceFila()];
    }
}
