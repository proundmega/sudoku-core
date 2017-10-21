package org.proundmega.sudokucore.solver.procesadores;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.proundmega.sudokucore.Posicion;
import org.proundmega.sudokucore.data.GridFactory;
import org.proundmega.sudokucore.elementos.Celda;
import org.proundmega.sudokucore.elementos.Columna;
import org.proundmega.sudokucore.elementos.Cuadrante;
import org.proundmega.sudokucore.elementos.Fila;
import org.proundmega.sudokucore.elementos.Valor;
import org.proundmega.sudokucore.elementos.grid.SubGridCuadrante;

public class ValorConUnicaPosicionTest {
    
    @Test
    public void unValorSoloPuedeEstarEnUnLugarCorrecto() {
        Celda[][] celdas = celdas = GridFactory.getSudokuFacil1();
        Cuadrante cuadranteAResolver = Cuadrante.SUPERIOR_DERECHO;
        SubGridCuadrante subgrid = new SubGridCuadrante(celdas, cuadranteAResolver);
        List<Posicion> posiciones = subgrid.getPosicionesVaciasAnotadas();
        
        ValorConUnicaPosicion pa = new ValorConUnicaPosicion();
        
        assertTrue(pa.unValorSoloPuedeEstarEnUnLugar(posiciones));
    }
    
    @Test
    public void unValorSoloPuedeEstarEnUnLugarIncorrecto() {
        Celda[][] celdas = celdas = GridFactory.getSudokuFacil1();
        Cuadrante cuadranteAResolver = Cuadrante.CENTRAL_CENTRAL;
        SubGridCuadrante subgrid = new SubGridCuadrante(celdas, cuadranteAResolver);
        List<Posicion> posiciones = subgrid.getPosicionesVaciasAnotadas();
        
        ValorConUnicaPosicion pa = new ValorConUnicaPosicion();
        
        assertFalse(pa.unValorSoloPuedeEstarEnUnLugar(posiciones));
    }
    
    @Test
    public void getValorASustituirCorrecto() {
        Celda[][] celdas = celdas = GridFactory.getSudokuFacil1();
        Cuadrante cuadranteAResolver = Cuadrante.SUPERIOR_DERECHO;
        SubGridCuadrante subgrid = new SubGridCuadrante(celdas, cuadranteAResolver);
        List<Posicion> posiciones = subgrid.getPosicionesVaciasAnotadas();
        
        Posicion esperado = new Posicion(Fila._2, Columna._9, new Celda(Valor._3));
        
        ValorConUnicaPosicion pa = new ValorConUnicaPosicion();
        
        Intercambio respuesta = pa.apply(posiciones).get();
        Posicion obtenido = new Posicion(respuesta.getPosicionVacia().getFila()
                , respuesta.getPosicionVacia().getColumna()
                , new Celda(respuesta.getNuevoValor()));
        
        assertEquals(esperado, obtenido);
    }
    
}
