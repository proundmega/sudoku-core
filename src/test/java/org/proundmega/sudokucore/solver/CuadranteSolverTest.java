package org.proundmega.sudokucore.solver;

import org.junit.Test;
import static org.junit.Assert.*;
import org.proundmega.sudokucore.Grid;
import org.proundmega.sudokucore.data.GridFactory;

public class CuadranteSolverTest {
    
    @Test
    public void llenarUnaCasilla() {
        Solver solver = new CuadranteSolver();
        Grid gridIncompleta = new Grid(GridFactory.getSudokuIncompleto1());
        
        Grid esperado = new Grid(GridFactory.getSudokuResueltoValido1());
        Grid obtenido = solver.solveCasilla(gridIncompleta);
        
        assertEquals(esperado, obtenido);
    }
    
}
