package org.proundmega.sudokucore.solver;

import org.junit.Test;
import static org.junit.Assert.*;
import org.proundmega.sudokucore.Posicion;
import org.proundmega.sudokucore.Respuesta;
import org.proundmega.sudokucore.data.GridFactory;
import org.proundmega.sudokucore.elementos.Celda;
import org.proundmega.sudokucore.elementos.Columna;
import org.proundmega.sudokucore.elementos.Fila;
import org.proundmega.sudokucore.elementos.Valor;
import org.proundmega.sudokucore.elementos.grid.Grid;

public class EliminacionCuadranteSolverTest {
    
    @Test
    public void devuelveCorrectamenteLaPosicionResuelta() {
        Grid sudokuAResolver = new Grid(GridFactory.getSudokuFacil1());
        Solver solver = new EliminacionCuadranteSolver();
        Posicion esperada = new Posicion(Fila._2, Columna._3, new Celda(Valor._9));
        
        Respuesta respuesta = solver.apply(sudokuAResolver);
        
        assertEquals(esperada, respuesta.getMetadatos().getPosicionResuelta());
    }
    
}
