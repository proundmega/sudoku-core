/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.proundmega.sudokucore.solver;

import org.junit.Test;
import static org.junit.Assert.*;
import org.proundmega.sudokucore.data.GridFactory;
import org.proundmega.sudokucore.elementos.grid.Grid;

/**
 *
 * @author vansi
 */
public class ColumnaSolverTest {
    
    @Test
    public void resolver1() {
        Grid gridIncompleta = new Grid(GridFactory.getSudokuIncompleto1());
        Grid esperado = new Grid(GridFactory.getSudokuResueltoValido1());
        
        Solver solver = new ColumnaSolver();
        Respuesta obtenido = solver.apply(gridIncompleta);
        
        assertEquals(esperado, obtenido.getGridRespuesta());
    }
    
    @Test
    public void resolver2() {
        Grid gridIncompleta = new Grid(GridFactory.getSudokuIncompleto2());
        Grid esperado = new Grid(GridFactory.getSudokuIncompleto1());
        
        Solver solver = new ColumnaSolver();
        Respuesta obtenido = solver.apply(gridIncompleta);
        
        assertEquals(esperado, obtenido.getGridRespuesta());
    }
    
}
