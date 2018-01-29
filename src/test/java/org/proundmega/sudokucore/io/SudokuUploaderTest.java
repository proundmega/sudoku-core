package org.proundmega.sudokucore.io;

import java.io.File;
import org.junit.Test;
import static org.junit.Assert.*;
import org.proundmega.sudokucore.data.GridFactory;
import org.proundmega.sudokucore.Sudoku;

public class SudokuUploaderTest {
    
    public SudokuUploaderTest() {
    }

    @Test
    public void leerSudokuDeUnarchivoCorrectamenteYValidarlo() throws Exception {
        File archivo = TestUtils.getTestResource("data", "sudokuValido1.txt");
        Sudoku esperado = new Sudoku(GridFactory.getSudokuFacil1());
        
        FileToSudoku uploader = new FileToSudoku();
        Sudoku obtenido = uploader.crearSudoku(archivo);
        assertNotNull(obtenido);
        assertEquals(esperado, obtenido);
    }
    
    @Test(expected = InvalidSudokuFormatException.class)
    public void archivoConCeldasConValoresIncorrectos() throws Exception {
        File archivo = TestUtils.getTestResource("data", "sudokuInvalido1.txt");
        
        FileToSudoku uploader = new FileToSudoku();
        Sudoku obtenido = uploader.crearSudoku(archivo);
    }
    
    @Test(expected = InvalidSudokuFormatException.class)
    public void archivoConFilaExtraMala() throws Exception {
        File archivo = TestUtils.getTestResource("data", "sudokuInvalido2.txt");
        
        FileToSudoku uploader = new FileToSudoku();
        Sudoku obtenido = uploader.crearSudoku(archivo);
    }
    
    @Test(expected = InvalidSudokuFormatException.class)
    public void archivoConFilaFaltante() throws Exception {
        File archivo = TestUtils.getTestResource("data", "sudokuInvalido3.txt");
        
        FileToSudoku uploader = new FileToSudoku();
        Sudoku obtenido = uploader.crearSudoku(archivo);
    }
}
