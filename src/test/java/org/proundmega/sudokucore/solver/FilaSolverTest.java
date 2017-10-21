package org.proundmega.sudokucore.solver;

import org.junit.Test;
import static org.junit.Assert.*;
import org.proundmega.sudokucore.data.GridFactory;
import org.proundmega.sudokucore.elementos.grid.Grid;

public class FilaSolverTest {
    
    @Test
    public void resolver1() {
        Grid gridIncompleta = new Grid(GridFactory.getSudokuIncompleto1());
        Grid esperado = new Grid(GridFactory.getSudokuResueltoValido1());
        
        Solver solver = new FilaSolver();
        Respuesta obtenido = solver.apply(gridIncompleta);
        
        assertEquals(esperado, obtenido.getGridRespuesta());
    }
    
    @Test
    public void resolver2() {
        Grid gridIncompleta = new Grid(GridFactory.getSudokuIncompleto2());
        Grid esperado = new Grid(GridFactory.getSudokuIncompleto1());
        
        Solver solver = new FilaSolver();
        Respuesta obtenido = solver.apply(gridIncompleta);
        
        assertEquals(esperado, obtenido.getGridRespuesta());
    }
}
