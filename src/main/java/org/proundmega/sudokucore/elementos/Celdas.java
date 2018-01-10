package org.proundmega.sudokucore.elementos;

import java.util.ArrayList;
import java.util.List;
import org.proundmega.sudokucore.Posicion;

public class Celdas {

    public static List<Posicion> asPosiciones(Celda[][] celdas) {
        List<Posicion> posiciones = new ArrayList<>();

        for (int fila = 0; fila < 9; fila++) {
            for (int columna = 0; columna < 9; columna++) {
                Posicion posicion = new Posicion(fila + 1, columna + 1, celdas[fila][columna]);
                posiciones.add(posicion);
            }
        }
        
        return posiciones;
    }
    
    public static Posicion getPosicion(Celda[][] celdas, Fila fila, Columna columna) {
        return new Posicion(fila, columna, celdas[fila.getIndiceFilaParaArray()][columna.getIndiceColumnaParaArray()]);
    }
}
