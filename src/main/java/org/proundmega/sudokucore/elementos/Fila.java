package org.proundmega.sudokucore.elementos;

import java.util.HashMap;
import java.util.Map;
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

    private static final Map<Integer, Fila> mapa = new HashMap<>();
    
    static {
        for(Fila fila : values()) {
            mapa.put(fila.indiceFila, fila);
        }
    }
    
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
    
    /** Este metodo devuelve la fila en numero a su equivalente en objeto Fila
     * 
     * @param fila
     * @return 
     */
    public static Fila toFila(int fila) {
        return mapa.get(fila);
    }
}
