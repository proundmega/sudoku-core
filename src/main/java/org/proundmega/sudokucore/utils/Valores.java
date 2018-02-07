package org.proundmega.sudokucore.utils;

import java.util.ArrayList;
import java.util.List;
import org.proundmega.sudokucore.Posicion;
import org.proundmega.sudokucore.elementos.Columna;
import org.proundmega.sudokucore.elementos.Fila;
import org.proundmega.sudokucore.elementos.Valor;

public class Valores {

    public static List<Posicion> asPosiciones(Valor[][] celdas) {
        List<Posicion> posiciones = new ArrayList<>();

        for (int fila = 0; fila < 9; fila++) {
            for (int columna = 0; columna < 9; columna++) {
                Posicion posicion = new Posicion(fila + 1, columna + 1, celdas[fila][columna]);
                posiciones.add(posicion);
            }
        }

        return posiciones;
    }

    public static Posicion getPosicion(Valor[][] celdas, Fila fila, Columna columna) {
        return new Posicion(fila, columna, celdas[fila.getIndiceFilaParaArray()][columna.getIndiceColumnaParaArray()]);
    }

    public static Valor[][] copyOf(String[][] gridAsString) {
        Valor[][] valores = new Valor[9][9];
        for (int fila = 0; fila < 9; fila++) {
            for (int columna = 0; columna < 9; columna++) {
                Valor valor = Valor.toValor(gridAsString[fila][columna]);
                valores[fila][columna] = valor;
            }
        }
        return valores;
    }

}
