package org.proundmega.sudokucore.solver.procesadores;

import java.util.List;
import java.util.Optional;
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

public class CeldaConUnicaPosicionTest {
    
    @Test
    public void hayUnaCeldaConUnPosibleValorCorrecto() {
        List<Posicion> posiciones = getPosicionesVaciasDeCuadrante(Cuadrante.SUPERIOR_DERECHO);
        
        CeldaConUnicaPosicion metodo = new CeldaConUnicaPosicion();
        assertTrue(metodo.hayUnaCasillaConUnSoloPosibleValor(posiciones));
    }
    
    private List<Posicion> getPosicionesVaciasDeCuadrante(Cuadrante cuadrante) {
        Celda[][] celda = GridFactory.getSudokuFacil1();
        SubGridCuadrante subgrid = new SubGridCuadrante(celda, cuadrante);
        return subgrid.getPosicionesVaciasAnotadas();
    }
    
    @Test
    public void hayUnaCeldaConUnPosibleValorIncorrecto() {
        List<Posicion> posiciones = getPosicionesVaciasDeCuadrante(Cuadrante.CENTRAL_DERECHO);
        
        CeldaConUnicaPosicion metodo = new CeldaConUnicaPosicion();
        assertFalse(metodo.hayUnaCasillaConUnSoloPosibleValor(posiciones));
    }
    
    @Test
    public void getRespuestaCorrecta() {
        List<Posicion> posiciones = getPosicionesVaciasDeCuadrante(Cuadrante.SUPERIOR_DERECHO);
        CeldaConUnicaPosicion posicion = new CeldaConUnicaPosicion();
        
        Intercambio esperado = new Intercambio(new Posicion(Fila._2, Columna._8, new Celda()).addAnotacion(Valor._2), Valor._2);
        
        Optional<Intercambio> obtenido = posicion.apply(posiciones);
        
        assertTrue(obtenido.isPresent());
        assertEquals(esperado, obtenido.get());
    }
    
    @Test
    public void getRespuestaIncorrecta() {
        List<Posicion> posiciones = getPosicionesVaciasDeCuadrante(Cuadrante.SUPERIOR_DERECHO);
        CeldaConUnicaPosicion posicion = new CeldaConUnicaPosicion();
        
        Intercambio esperado = new Intercambio(new Posicion(Fila._2, Columna._8, new Celda()).addAnotacion(Valor._2), Valor._2);
        
        Optional<Intercambio> obtenido = posicion.apply(posiciones);
        
        assertTrue(obtenido.isPresent());
        assertEquals(esperado, obtenido.get());
    }
}
