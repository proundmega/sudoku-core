package org.proundmega.sudokucore.elementos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.proundmega.sudokucore.Celda;
import org.proundmega.sudokucore.Valor;
import static org.proundmega.sudokucore.Valor.*;
import org.proundmega.sudokucore.data.GridFactory;

public class CuadranteTest {
    private static Celda[][] celdas;
    
    @BeforeClass
    public static void crearGridPrueba() {
        celdas = GridFactory.getSudokuResueltoValido1();
    }
    
    @Test
    public void gcSuperiorIzquierdo() {
        Cuadrante cuadrante = Cuadrante.SUPERIOR_IZQUIERO;
        Celda[] esperado = GridFactory.crearCeldas(
                _8,    _7,    _1,
                _4,    _5,    _9,
                _6,    _2,    _3
        );
        
        assertArrayEquals(esperado, cuadrante.getCuadrante(celdas));
    }
    
    @Test
    public void gcSuperiorCentral() {
        Cuadrante cuadrante = Cuadrante.SUPERIOR_CENTRAL;
        Celda[] esperado = GridFactory.crearCeldas(
                _2,    _3,    _9, 
                _6,    _1,    _7, 
                _4,    _5,    _8
        );
        
        assertArrayEquals(esperado, cuadrante.getCuadrante(celdas));
    }

    @Test
    public void gcSuperiorDerecho() {
        Cuadrante cuadrante = Cuadrante.SUPERIOR_DERECHO;
        Celda[] esperado = GridFactory.crearCeldas(
                _4,   _5,   _6,
                _8,   _2,   _3,
                _7,   _9,   _1
        );
        
        assertArrayEquals(esperado, cuadrante.getCuadrante(celdas));
    }
    
    @Test
    public void gcCentralIzquierdo() {
        Cuadrante cuadrante = Cuadrante.CENTRAL_IZQUIERO;
        Celda[] esperado = GridFactory.crearCeldas(
                _2,    _4,    _6,
                _9,    _1,    _8, 
                _5,    _3,    _7
        );
        
        assertArrayEquals(esperado, cuadrante.getCuadrante(celdas));
    }
    
    @Test
    public void gcCentralCentral() {
        Cuadrante cuadrante = Cuadrante.CENTRAL_CENTRAL;
        Celda[] esperado = GridFactory.crearCeldas(
                _3,    _7,    _1,
                _5,    _4,    _6, 
                _8,    _9,    _2
        );
        
        assertArrayEquals(esperado, cuadrante.getCuadrante(celdas));
    }
    
    @Test
    public void gcCentralDerecho() {
        Cuadrante cuadrante = Cuadrante.CENTRAL_DERECHO;
        Celda[] esperado = GridFactory.crearCeldas(
                _9,   _8,   _5,
                _2,   _3,   _7,
                _1,   _6,   _4
        );
        
        assertArrayEquals(esperado, cuadrante.getCuadrante(celdas));
    }
    
    @Test
    public void gcInferiorIzquierdo() {
        Cuadrante cuadrante = Cuadrante.INFERIOR_IZQUIERO;
        Celda[] esperado = GridFactory.crearCeldas(
                _7,    _8,    _4,
                _1,    _6,    _2,
                _3,    _9,    _5
        );
        
        assertArrayEquals(esperado, cuadrante.getCuadrante(celdas));
    }
    
    @Test
    public void gcInferiorCentral() {
        Cuadrante cuadrante = Cuadrante.INFERIOR_CENTRAL;
        Celda[] esperado = GridFactory.crearCeldas(
                _9,    _6,    _5,
                _7,    _8,    _3, 
                _1,    _2,    _4
        );
        
        assertArrayEquals(esperado, cuadrante.getCuadrante(celdas));
    }
    
    @Test
    public void gcInferiorDerecho() {
        Cuadrante cuadrante = Cuadrante.INFERIOR_DERECHO;
        Celda[] esperado = GridFactory.crearCeldas(
                _3,   _1,   _2,
                _5,   _4,   _9,
                _6,   _7,   _8
        );
        
        assertArrayEquals(esperado, cuadrante.getCuadrante(celdas));
    }
}
