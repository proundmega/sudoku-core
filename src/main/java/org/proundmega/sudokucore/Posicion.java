package org.proundmega.sudokucore;

import org.proundmega.sudokucore.elementos.Celda;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class Posicion {
    private int fila;
    private int columna;
    private Celda celda;
}
