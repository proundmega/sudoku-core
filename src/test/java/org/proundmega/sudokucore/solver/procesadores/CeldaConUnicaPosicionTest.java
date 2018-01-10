package org.proundmega.sudokucore.solver.procesadores;

import java.util.List;
import java.util.Optional;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.proundmega.sudokucore.MetadataSolver;
import org.proundmega.sudokucore.Posicion;
import org.proundmega.sudokucore.data.GridFactory;
import org.proundmega.sudokucore.elementos.Celda;
import org.proundmega.sudokucore.elementos.Columna;
import org.proundmega.sudokucore.elementos.Cuadrante;
import org.proundmega.sudokucore.elementos.Fila;
import org.proundmega.sudokucore.elementos.Valor;
import org.proundmega.sudokucore.elementos.grid.SubGridCuadrante;
import org.proundmega.sudokucore.elementos.anotador.Anotador;

public class CeldaConUnicaPosicionTest {
    
    @Test
    public void hayUnaCeldaConUnPosibleValorCorrecto() {
        List<Posicion> posiciones = getPosicionesVaciasDeCuadrante(Cuadrante.SUPERIOR_DERECHO)
                .getPosicionesConAnotacionesRemovidas();
        
        CeldaConUnicaPosicion metodo = new CeldaConUnicaPosicion();
        assertTrue(metodo.hayUnaCasillaConUnSoloPosibleValor(posiciones));
    }
    
    private Anotador getPosicionesVaciasDeCuadrante(Cuadrante cuadrante) {
        Celda[][] celda = GridFactory.getSudokuFacil1();
        SubGridCuadrante subgrid = new SubGridCuadrante(celda, cuadrante);
        
        return subgrid.getAnotador();
    }
    
    @Test
    public void hayUnaCeldaConUnPosibleValorIncorrecto() {
        List<Posicion> posiciones = getPosicionesVaciasDeCuadrante(Cuadrante.CENTRAL_DERECHO)
                .getPosicionesConAnotacionesRemovidas();
        
        CeldaConUnicaPosicion metodo = new CeldaConUnicaPosicion();
        assertFalse(metodo.hayUnaCasillaConUnSoloPosibleValor(posiciones));
    }
    
    @Test
    public void getRespuestaCorrecta() {
        Anotador anotador = getPosicionesVaciasDeCuadrante(Cuadrante.SUPERIOR_DERECHO);
        
        CeldaConUnicaPosicion posicion = new CeldaConUnicaPosicion();
        
        Posicion esperado = new Posicion(Fila._2, Columna._8, new Celda(Valor._2));
        
        Optional<MetadataSolver> obtenido = posicion.apply(anotador);
        
        assertTrue(obtenido.isPresent());
        assertEquals(esperado, obtenido.get().getPosicionResuelta());
    }
    
    @Test
    public void getRespuestaIncorrecta() {
        Anotador anotador = getPosicionesVaciasDeCuadrante(Cuadrante.SUPERIOR_DERECHO);
        CeldaConUnicaPosicion posicion = new CeldaConUnicaPosicion();
        
        Posicion esperado = new Posicion(Fila._2, Columna._8, new Celda(Valor._2));
        
        Optional<MetadataSolver> obtenido = posicion.apply(anotador);
        
        assertTrue(obtenido.isPresent());
        assertEquals(esperado, obtenido.get().getPosicionResuelta());
    }
}
