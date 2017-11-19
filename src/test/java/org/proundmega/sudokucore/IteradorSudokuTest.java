package org.proundmega.sudokucore;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.proundmega.sudokucore.data.GridFactory;

public class IteradorSudokuTest {
    
    @Test
    public void noHayNextEnIteradorVacio() {
        IteradorSudoku iterador = new IteradorSudoku();
        assertFalse(iterador.hasNext());
    }
    
    @Test
    public void hayNextEnIteradorCorrecto() {
        IteradorSudoku iterador = new IteradorSudoku(getMockRespuestas());
        assertTrue(iterador.hasNext());
    }
    
    private List<Respuesta> getMockRespuestas() {
        Sudoku sudoku = new Sudoku(GridFactory.getSudokuFacil1());
        return sudoku.solvePorPasos();
    }
    
    @Test
    public void noHayValorPresenteEnIteradorVacio() {
        IteradorSudoku iterador = new IteradorSudoku();
        assertFalse(iterador.isExisteValor());
    }
    
    @Test
    public void hayValorPresenteEnIteradorCorrecto() {
        IteradorSudoku iterador = new IteradorSudoku(getMockRespuestas());
        assertTrue(iterador.isExisteValor());
    }
    
    @Test
    public void seDevuelveSudokuPrimeroEnIteradorCorrecto() {
        List<Respuesta> mockRespuestas = getMockRespuestas();
        IteradorSudoku iterador = new IteradorSudoku(mockRespuestas);
        Sudoku sudokuObtenido = new Sudoku(iterador.getActual().getGridRespuesta());
        
        assertEquals(new Sudoku(mockRespuestas.get(0).getGridRespuesta()), sudokuObtenido);
    }
}
