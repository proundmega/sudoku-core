package org.proundmega.sudokucore;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.proundmega.sudokucore.data.GridFactory;
import org.proundmega.sudokucore.elementos.Celda;

public class SudokuTest {
    
    @Test
    public void solveSudoku1() {
        Celda[][] celdas = GridFactory.getSudokuFacil1();
        Sudoku sudoku = new Sudoku(celdas);
        
        Sudoku esperado = new Sudoku(GridFactory.getSudokuFacil1Resuelto());
        Sudoku obtenido = sudoku.solve();
        
        assertEquals(esperado, obtenido);
    }
    
    @Test
    @Ignore
    public void todasLasFases() {
        Celda[][] celdas = GridFactory.getSudokuFacil1();
        Sudoku sudoku = new Sudoku(celdas);
        
        for(Respuesta respuesta : sudoku.solvePorPasos()) {
            System.out.println(respuesta.getGridRespuesta());
            System.out.println("");
        }
    }
    
    @Test
    public void solveSudoku2() {
        Celda[][] celdas = GridFactory.getSudokuFacil2();
        Sudoku sudoku = new Sudoku(celdas);
        Sudoku obtenido = sudoku.solve();
        assertTrue(obtenido.estaResuelto());
    }
}
