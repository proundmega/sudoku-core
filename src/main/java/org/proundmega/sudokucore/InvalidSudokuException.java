package org.proundmega.sudokucore;

import java.util.List;

public class InvalidSudokuException extends RuntimeException {
    private List<Posicion> posiciones;
    
    public InvalidSudokuException(String message, List<Posicion> posiciones) {
        super(message + "\nPosiciones repetidas: " + posiciones);
        this.posiciones = posiciones;
    }

    public InvalidSudokuException(String message, List<Posicion> posiciones, Throwable cause) {
        super(message + "\nPosiciones repetidas: " + posiciones);
        this.posiciones = posiciones;
    }
    
}
