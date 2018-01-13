package org.proundmega.sudokucore.io;

public class InvalidSudokuFormatException extends RuntimeException {

    public InvalidSudokuFormatException(String message) {
        super(message);
    }

    public InvalidSudokuFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
