package org.proundmega.sudokucore.elementos.grid;

import java.util.ArrayList;
import java.util.List;
import org.proundmega.sudokucore.Posicion;
import org.proundmega.sudokucore.elementos.Celda;
import org.proundmega.sudokucore.elementos.Columna;
import org.proundmega.sudokucore.elementos.Fila;

public class GridAPosiciones {
    public static Posicion getPosicion(Celda[][] celdas, Fila fila, Columna columna) {
        return new Posicion(fila, columna, celdas[fila.getIndiceFilaParaArray()][columna.getIndiceColumnaParaArray()]);
    }

    public static List<Posicion> getPosiciones(Celda[][] celdas) {
        List<Posicion> posiciones = new ArrayList<>();
        
        for(Fila fila : Fila.values()) {
            for(Columna columna : Columna.values()) {
                posiciones.add(getPosicion(celdas, fila, columna));
            }
        }
        
        return posiciones;
    }
}
