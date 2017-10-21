package org.proundmega.sudokucore;

import org.junit.Test;
import static org.junit.Assert.*;
import org.proundmega.sudokucore.data.GridFactory;
import org.proundmega.sudokucore.elementos.Celda;
import org.proundmega.sudokucore.elementos.grid.Grid;

public class SudokuTest {
    
    @Test
    public void testSolve() {
        Celda[][] celdas = GridFactory.getSudokuFacil1();
        Sudoku sudoku = new Sudoku(celdas);
        
        Grid esperado = new Grid(GridFactory.getSudokuResueltoValido1());
        Grid obtenido = sudoku.solve();
        
        assertEquals(esperado, obtenido);
    }
    
}
