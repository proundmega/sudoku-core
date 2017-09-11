package org.proundmega.sudokucore.elementos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
    
    public Celda[][] getCuadrante(Celda[][] celdas) {
        return Arrays.stream(celdas, offsetFila , offsetFila + 3)
                    .map(tupla -> Arrays.copyOfRange(tupla, offsetColumna, offsetColumna + 3))
                    .toArray(Celda[][]::new);
    }
    
    public List<Posicion> getCuadranteAsList(Celda[][] celdas) {
        List<Posicion> posiciones = new ArrayList<>();
        
        for(int fila = offsetFila; fila < offsetFila + 3; fila++) {
            for(int columna = offsetColumna; columna < offsetColumna + 3; columna++) {
                Posicion posicion = new Posicion(fila + 1, columna + 1, celdas[fila][columna]);
                posiciones.add(posicion);
            }
        }
        
        return Collections.unmodifiableList(posiciones);
    }

    public int getOffsetFila() {
        return offsetFila;
    }

    public int getOffsetColumna() {
        return offsetColumna;
    }
    
}
