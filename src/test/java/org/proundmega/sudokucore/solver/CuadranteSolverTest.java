package org.proundmega.sudokucore.solver;

import org.proundmega.sudokucore.Respuesta;
import org.junit.Test;
import static org.junit.Assert.*;
import org.proundmega.sudokucore.Posicion;
import org.proundmega.sudokucore.elementos.grid.Grid;
import org.proundmega.sudokucore.data.GridFactory;
import org.proundmega.sudokucore.elementos.Celda;
import org.proundmega.sudokucore.elementos.Columna;
import org.proundmega.sudokucore.elementos.Fila;
import org.proundmega.sudokucore.elementos.Valor;

public class CuadranteSolverTest {
    
    @Test
    public void llenarUnaCasilla() {
        Solver solver = new CuadranteSolver();
        Grid gridIncompleta = new Grid(GridFactory.getSudokuIncompleto1());
        
        Grid esperado = new Grid(GridFactory.getSudokuFacil1Resuelto());
        Grid obtenido = solver.apply(gridIncompleta).getGridRespuesta();
        
        assertEquals(esperado, obtenido);
    }
    
    @Test
    public void solverYaResuelto() {
        Solver solver = new CuadranteSolver();
        
        Grid gridCompleta = new Grid(GridFactory.getSudokuFacil1Resuelto());
        
        Respuesta obtenido = solver.apply(gridCompleta);
        
        assertFalse(obtenido.isAvanceEnResolver());
    }
    
    @Test
    public void getPosicionResueltaCorrecta() {
        Grid gridIncompleta = new Grid(GridFactory.getSudokuIncompleto1());
        Posicion esperado = new Posicion(Fila._9, Columna._9, new Celda(Valor._8));
        
        Solver solver = new CuadranteSolver();
        Respuesta obtenido = solver.apply(gridIncompleta);
        
        assertEquals(esperado, obtenido.getMetadatos().getPosicionResuelta());
    }
    
}
