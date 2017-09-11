/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.proundmega.sudokucore.elementos.grid;

import org.proundmega.sudokucore.elementos.grid.SubGridCuadrante;
import org.proundmega.sudokucore.elementos.grid.Grid;
import org.junit.Test;
import static org.junit.Assert.*;
import org.proundmega.sudokucore.data.GridFactory;
import org.proundmega.sudokucore.elementos.Cuadrante;

/**
 *
 * @author vansi
 */
public class SubGridTest {
    
    public SubGridTest() {
    }

    @Test
    public void estaCasiCompleto() {
        Grid gridCasiCompleto = new Grid(GridFactory.getSudokuIncompleto1());
        SubGridCuadrante subgrid = gridCasiCompleto.getSubGrid(Cuadrante.INFERIOR_DERECHO);
        
        assertTrue(subgrid.soloFaltaUnEspacio());
    }
    
    @Test
    public void estaCasiCompletoPeroEstaCompleto() {
        Grid gridCasiCompleto = new Grid(GridFactory.getSudokuIncompleto1());
        SubGridCuadrante subgrid = gridCasiCompleto.getSubGrid(Cuadrante.INFERIOR_IZQUIERO);
        
        assertFalse(subgrid.soloFaltaUnEspacio());
    }
    
    @Test
    public void estaCasiCompletoPeroEstaMuyIncompleto() {
        Grid gridCasiCompleto = new Grid(GridFactory.getSudokuIncompleto2());
        SubGridCuadrante subgrid = gridCasiCompleto.getSubGrid(Cuadrante.INFERIOR_DERECHO);
        
        assertFalse(subgrid.soloFaltaUnEspacio());
    }
}
