package org.proundmega.sudokucore.solver;

import org.junit.Test;
import static org.junit.Assert.*;
import org.proundmega.sudokucore.elementos.grid.Grid;
import org.proundmega.sudokucore.data.GridFactory;

public class CuadranteSolverTest {
    
    @Test
    public void llenarUnaCasilla() {
        Solver solver = new CuadranteSolver();
        Grid gridIncompleta = new Grid(GridFactory.getSudokuIncompleto1());
        
        Grid esperado = new Grid(GridFactory.getSudokuResueltoValido1());
        Grid obtenido = solver.apply(gridIncompleta).getGridRespuesta();
        
        assertEquals(esperado, obtenido);
    }
    
    @Test
    public void solverYaResuelto() {
        Solver solver = new CuadranteSolver();
        
        Grid gridCompleta = new Grid(GridFactory.getSudokuResueltoValido1());
        
        Respuesta obtenido = solver.apply(gridCompleta);
        
        assertFalse(obtenido.isAvanceEnResolver());
    }
    
}
