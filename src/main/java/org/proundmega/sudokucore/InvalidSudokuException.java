package org.proundmega.sudokucore;

public class InvalidSudokuException extends RuntimeException {

    public InvalidSudokuException(String message) {
        super(message);
    }

    public InvalidSudokuException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
