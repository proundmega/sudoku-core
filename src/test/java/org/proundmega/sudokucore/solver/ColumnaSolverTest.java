package org.proundmega.sudokucore.solver;

import org.proundmega.sudokucore.Respuesta;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.proundmega.sudokucore.Posicion;
import org.proundmega.sudokucore.data.GridFactory;
import org.proundmega.sudokucore.elementos.Celda;
import org.proundmega.sudokucore.elementos.Columna;
import org.proundmega.sudokucore.elementos.Fila;
import org.proundmega.sudokucore.elementos.Valor;
import org.proundmega.sudokucore.elementos.grid.Grid;

@Ignore
public class ColumnaSolverTest {
    
    @Test
    public void resolver1() {
        Grid gridIncompleta = new Grid(GridFactory.getSudokuIncompleto1());
        Grid esperado = new Grid(GridFactory.getSudokuFacil1Resuelto());
        
        Solver solver = new ColumnaVaciaSolver();
        Respuesta obtenido = solver.apply(gridIncompleta);
        
        assertEquals(esperado, obtenido.getGridRespuesta());
    }
    
    @Test
    public void resolver2() {
        Grid gridIncompleta = new Grid(GridFactory.getSudokuIncompleto2());
        Grid esperado = new Grid(GridFactory.getSudokuIncompleto1());
        
        Solver solver = new ColumnaVaciaSolver();
        Respuesta obtenido = solver.apply(gridIncompleta);
        
        assertEquals(esperado, obtenido.getGridRespuesta());
    }
    
    @Test
    public void getPosicionResueltaCorrecta() {
        Grid gridIncompleta = new Grid(GridFactory.getSudokuIncompleto1());
        Posicion esperado = new Posicion(Fila._9, Columna._9, new Celda(Valor._8));
        
        Solver solver = new ColumnaVaciaSolver();
        Respuesta obtenido = solver.apply(gridIncompleta);
        
        assertEquals(esperado, obtenido.getMetadatos().getPosicionResuelta());
    }
}
