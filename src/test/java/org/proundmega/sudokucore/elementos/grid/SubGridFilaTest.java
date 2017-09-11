package org.proundmega.sudokucore.elementos.grid;

import org.junit.Test;
import static org.junit.Assert.*;
import org.proundmega.sudokucore.data.GridFactory;
import org.proundmega.sudokucore.elementos.Fila;

public class SubGridFilaTest {
    
    @Test
    public void soloFaltaUnEspacioCorrecto() {
        Grid grid = new Grid(GridFactory.getSudokuIncompleto1());
        SubGrid subgrid = grid.getSubGrid(Fila._9);
        
        assertTrue(subgrid.soloFaltaUnEspacio());
    }
    
}
