/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.proundmega.sudokucore;

import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.proundmega.sudokucore.data.GridFactory;
import org.proundmega.sudokucore.elementos.Columna;
import org.proundmega.sudokucore.elementos.Fila;

/**
 *
 * @author vansi
 */
public class GridTest {
    
    public GridTest() {
    }

    @Test(expected = NullPointerException.class)
    public void crearNuevoSudokuNulo() {
        Celda[][] celdasErroneas = null;
        Grid grid = new Grid(celdasErroneas);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void crearNuevoSudokuSinDimensionesApropiadas() {
        Celda[][] celdasErroneas = new Celda[0][4];
        Grid grid = new Grid(celdasErroneas);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void crearNuevoSudokuSinDimensionesApropiadas2() {
        Celda[][] celdasErroneas = new Celda[9][8];
        Grid grid = new Grid(celdasErroneas);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void crearNuevoSudokuSinDimensionesApropiadas3() {
        Celda[][] celdasErroneas = new Celda[1][10];
        Grid grid = new Grid(celdasErroneas);
    }
    
    @Test
    public void crearNuevoSudokuVacioValido() {
        Celda[][] celdasErroneas = new Celda[9][9];
        Grid grid = new Grid(celdasErroneas);
        assertTrue(true);
    }
    
    @Test
    public void reemplazarElementoEnGrid() {
        Celda[][] celdas = GridFactory.getSudokuResueltoValido1();
        Grid grid = new Grid(celdas);
        
        Celda[][] copia = GridFactory.getSudokuResueltoValido1();
        copia[2][3] = new Celda(Valor.VACIA);
        Grid esperado = new Grid(copia);
        
        assertNotEquals(celdas, copia);
        
        Grid obtenido = grid.reemplazarCasilla(Fila._3, Columna._4, Valor.VACIA);
        
        assertEquals(esperado, obtenido);
    }
}
