package org.proundmega.sudokucore;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.proundmega.sudokucore.data.GridFactory;

public class IteradorSudokuTest {
    
    @Test
    public void noHayNextEnIteradorVacio() {
        IteradorSudoku iterador = new IteradorSudoku();
        assertFalse(iterador.haySiguiente());
    }
    
    @Test
    public void hayNextEnIteradorCorrecto() {
        IteradorSudoku iterador = new IteradorSudoku(getMockRespuestas());
        assertTrue(iterador.haySiguiente());
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
        
        Sudoku esperado = mockRespuestas.get(0).getSudoku();
        Sudoku sudokuObtenido = iterador.getActual().getSudoku();
        
        assertEquals(esperado, sudokuObtenido);
    }
    
    @Test
    public void noHayAnteriorEnIteradorVacio() {
        IteradorSudoku iterador = new IteradorSudoku();
        assertFalse(iterador.hayPrevio());
    }
    
    @Test
    public void noHayAnteriorEnIteradorRecienCreado() {
        List<Respuesta> mockRespuestas = getMockRespuestas();
        IteradorSudoku iterador = new IteradorSudoku(mockRespuestas);
        assertFalse(iterador.hayPrevio());
    }
    
    @Test
    public void avanzaCorrectamenteAlDarNext() {
        List<Respuesta> mockRespuestas = getMockRespuestas();
        IteradorSudoku iterador = new IteradorSudoku(mockRespuestas);
        iterador.siguiente();
        
        Sudoku esperado = mockRespuestas.get(1).getSudoku();
        Sudoku obtenido = iterador.getActual().getSudoku();
        
        assertEquals(esperado, obtenido);
    }
    
    @Test
    public void avanzaYRetrocedeCorrectamente() {
        List<Respuesta> mockRespuestas = getMockRespuestas();
        IteradorSudoku iterador = new IteradorSudoku(mockRespuestas);
        iterador.siguiente();
        
        Sudoku primerSudoku = mockRespuestas.get(1).getSudoku();
        Sudoku segundoSudoku = mockRespuestas.get(0).getSudoku();
        
        Sudoku obtenido = iterador.getActual().getSudoku();
        assertEquals(primerSudoku, obtenido);
        
        iterador.anterior();
        
        obtenido = iterador.getActual().getSudoku();
        assertEquals(segundoSudoku, obtenido);
    }
    
    @Test
    public void avanzarRetrocederYAvanzarCorrecto() {
        List<Respuesta> mockRespuestas = getMockRespuestas();
        IteradorSudoku iterador = new IteradorSudoku(mockRespuestas);
        
        // Debe estar en 2
        iterador.siguiente();
        iterador.siguiente();
        
        Sudoku primerSudoku = mockRespuestas.get(2).getSudoku();
        Sudoku obtenido = iterador.getActual().getSudoku();
        
        assertEquals(primerSudoku, obtenido);
        
        // Debe estar en 0
        iterador.anterior();
        iterador.anterior();
        
        // Debe estar en 1
        iterador.siguiente();
        
        Sudoku segundoSudoku = mockRespuestas.get(1).getSudoku();
        obtenido = iterador.getActual().getSudoku();
        assertEquals(segundoSudoku, obtenido);
    }
    
    @Test
    public void volverAlInicioCorrecto() {
        List<Respuesta> mockRespuestas = getMockRespuestas();
        IteradorSudoku iterador = new IteradorSudoku(mockRespuestas);
        
        iterador.siguiente();
        iterador.siguiente();
        iterador.siguiente();
        iterador.anterior();
        
        iterador.inicio();
        
        Sudoku esperado = mockRespuestas.get(0).getSudoku();
        Sudoku obtenido = iterador.getActual().getSudoku();
        
        assertEquals(esperado, obtenido);
    }
    
    @Test
    public void irAlUltimoCorrecto() {
        List<Respuesta> mockRespuestas = getMockRespuestas();
        IteradorSudoku iterador = new IteradorSudoku(mockRespuestas);
        
        iterador.ultimo();
        
        Sudoku esperado = mockRespuestas.get(mockRespuestas.size() - 1).getSudoku();
        Sudoku obtenido = iterador.getActual().getSudoku();
        
        assertEquals(esperado, obtenido);
    }
    
    @Test
    public void irAlUltimoYNoHaySiguiente() {
        List<Respuesta> mockRespuestas = getMockRespuestas();
        IteradorSudoku iterador = new IteradorSudoku(mockRespuestas);
        
        iterador.ultimo();
        assertFalse(iterador.haySiguiente());
    }
}
