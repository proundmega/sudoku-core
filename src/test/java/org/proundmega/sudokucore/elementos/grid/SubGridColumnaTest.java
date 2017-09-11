package org.proundmega.sudokucore.elementos.grid;

import org.junit.Test;
import static org.junit.Assert.*;
import org.proundmega.sudokucore.data.GridFactory;
import org.proundmega.sudokucore.elementos.Columna;

public class SubGridColumnaTest {
    
    @Test
    public void soloFaltaUnEspacioCorrecto() {
        SubGrid subgrid = new SubGridColumna(GridFactory.getSudokuIncompleto1(), Columna._9);
        assertTrue(subgrid.soloFaltaUnEspacio());
    }
    
    @Test
    public void soloFaltaUnEspacioIncorrecto() {
        SubGrid subgrid = new SubGridColumna(GridFactory.getSudokuIncompleto1(), Columna._1);
        assertFalse(subgrid.soloFaltaUnEspacio());
    }
    
}
