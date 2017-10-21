package org.proundmega.sudokucore.elementos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.proundmega.sudokucore.Posicion;
import org.proundmega.sudokucore.data.GridFactory;
import static org.proundmega.sudokucore.elementos.Valor.*;
import static org.proundmega.sudokucore.data.GridFactory.crearCeldas;

public class CuadranteTest {
    private static Celda[][] celdas;
    
    @BeforeClass
    public static void crearGridPrueba() {
        celdas = GridFactory.getSudokuResueltoValido1();
    }
    
    @Test
    public void gcSuperiorIzquierdo() {
        Cuadrante cuadrante = Cuadrante.SUPERIOR_IZQUIERO;
        
        Celda[][] esperado = new Celda[][] {
            crearCeldas(_8,    _7,    _1),
            crearCeldas(_4,    _5,    _9),
            crearCeldas(_6,    _2,    _3)
        };
        
        assertArrayEquals(esperado, cuadrante.getCuadrante(celdas));
    }
    
    @Test
    public void gcSuperiorCentral() {
        Cuadrante cuadrante = Cuadrante.SUPERIOR_CENTRAL;
        
        Celda[][] esperado = new Celda[][] {
            crearCeldas(_2,    _3,    _9),
            crearCeldas(_6,    _1,    _7),
            crearCeldas(_4,    _5,    _8)
        };
        
        assertArrayEquals(esperado, cuadrante.getCuadrante(celdas));
    }

    @Test
    public void gcSuperiorDerecho() {
        Cuadrante cuadrante = Cuadrante.SUPERIOR_DERECHO;
        
        Celda[][] esperado = new Celda[][] {
            crearCeldas(_4,   _5,   _6),
            crearCeldas(_8,   _2,   _3),
            crearCeldas(_7,   _9,   _1)
        };
        
        assertArrayEquals(esperado, cuadrante.getCuadrante(celdas));
    }
    
    @Test
    public void gcCentralIzquierdo() {
        Cuadrante cuadrante = Cuadrante.CENTRAL_IZQUIERO;
        
        Celda[][] esperado = new Celda[][] {
            crearCeldas(_2,    _4,    _6),
            crearCeldas(_9,    _1,    _8),
            crearCeldas(_5,    _3,    _7)
        };
        
        assertArrayEquals(esperado, cuadrante.getCuadrante(celdas));
    }
    
    @Test
    public void gcCentralCentral() {
        Cuadrante cuadrante = Cuadrante.CENTRAL_CENTRAL;
        
        Celda[][] esperado = new Celda[][] {
            crearCeldas(_3,    _7,    _1),
            crearCeldas(_5,    _4,    _6),
            crearCeldas(_8,    _9,    _2)
        };
        
        assertArrayEquals(esperado, cuadrante.getCuadrante(celdas));
    }
    
    @Test
    public void gcCentralDerecho() {
        Cuadrante cuadrante = Cuadrante.CENTRAL_DERECHO;
        
        Celda[][] esperado = new Celda[][] {
            crearCeldas(_9,   _8,   _5),
            crearCeldas(_2,   _3,   _7),
            crearCeldas(_1,   _6,   _4)
        };
        
        assertArrayEquals(esperado, cuadrante.getCuadrante(celdas));
    }
    
    @Test
    public void gcInferiorIzquierdo() {
        Cuadrante cuadrante = Cuadrante.INFERIOR_IZQUIERO;
        
        Celda[][] esperado = new Celda[][] {
            crearCeldas(_7,    _8,    _4),
            crearCeldas(_1,    _6,    _2),
            crearCeldas(_3,    _9,    _5)
        };
        
        assertArrayEquals(esperado, cuadrante.getCuadrante(celdas));
    }
    
    @Test
    public void gcInferiorCentral() {
        Cuadrante cuadrante = Cuadrante.INFERIOR_CENTRAL;
        
        Celda[][] esperado = new Celda[][] {
            crearCeldas(_9,    _6,    _5),
            crearCeldas(_7,    _8,    _3),
            crearCeldas(_1,    _2,    _4)
        };
        
        assertArrayEquals(esperado, cuadrante.getCuadrante(celdas));
    }
    
    @Test
    public void gcInferiorDerecho() {
        Cuadrante cuadrante = Cuadrante.INFERIOR_DERECHO;
        
        Celda[][] esperado = new Celda[][] {
            crearCeldas(_3,   _1,   _2),
            crearCeldas(_5,   _4,   _9),
            crearCeldas(_6,   _7,   _8)
        };
        
        assertArrayEquals(esperado, cuadrante.getCuadrante(celdas));
    }
    
    @Test
    public void gcInferiorDerechoAsList() {
        Cuadrante cuadrante = Cuadrante.INFERIOR_DERECHO;
        
        List<Posicion> esperado = getPosicionesInferiorDerecho(celdas);
        List<Posicion> obtenido = cuadrante.getCuadranteAsList(celdas);
        
        assertEquals(esperado, obtenido);
    }
    
    private List<Posicion> getPosicionesInferiorDerecho(Celda[][] celdas) {
        List<Posicion> posiciones = new ArrayList<>();
        
        posiciones.add(new Posicion(7, 7, celdas[6][6]));
        posiciones.add(new Posicion(7, 8, celdas[6][7]));
        posiciones.add(new Posicion(7, 9, celdas[6][8]));
        
        posiciones.add(new Posicion(8, 7, celdas[7][6]));
        posiciones.add(new Posicion(8, 8, celdas[7][7]));
        posiciones.add(new Posicion(8, 9, celdas[7][8]));
        
        posiciones.add(new Posicion(9, 7, celdas[8][6]));
        posiciones.add(new Posicion(9, 8, celdas[8][7]));
        posiciones.add(new Posicion(9, 9, celdas[8][8]));
        
        return posiciones;
    }
    
    @Test
    public void gcInferiorDerechoVacias() {
        Celda[][] celdas = GridFactory.getSudokuIncompleto2();
        
        Cuadrante cuadrante = Cuadrante.INFERIOR_DERECHO;
        
        List<Posicion> esperado = getPosicionesInferiorDerechoVacias(celdas);
        List<Posicion> obtenido = cuadrante.getCeldasVacias(celdas);
        
        assertEquals(esperado, obtenido);
    }

    private List<Posicion> getPosicionesInferiorDerechoVacias(Celda[][] celdas) {
        List<Posicion> posiciones = new ArrayList<>();
        
        posiciones.add(new Posicion(8, 8, celdas[7][7]));
        posiciones.add(new Posicion(9, 9, celdas[8][8]));
        
        return posiciones;
    }
    
    @Test
    public void getValoresHorizontalesCorrectos1() {
        Celda[][] celdas = GridFactory.getSudokuResueltoValido1();
        
        Cuadrante cuadrante = Cuadrante.CENTRAL_CENTRAL;
        
        List<Posicion> esperado = new ArrayList<>();
        esperado.addAll(Cuadrante.CENTRAL_IZQUIERO.getCuadranteAsList(celdas));
        esperado.addAll(Cuadrante.CENTRAL_DERECHO.getCuadranteAsList(celdas));
        
        List<Posicion> obtenidos = cuadrante.getCeldasHorizontalesConValor(celdas);
        
        assertEquals(esperado, obtenidos);
    }
    
    @Test
    public void getValoresHorizontalesCorrectos2() {
        Celda[][] celdas = GridFactory.getSudokuResueltoValido1();
        
        Cuadrante cuadrante = Cuadrante.INFERIOR_DERECHO;
        
        List<Posicion> esperado = new ArrayList<>();
        esperado.addAll(Cuadrante.INFERIOR_IZQUIERO.getCuadranteAsList(celdas));
        esperado.addAll(Cuadrante.INFERIOR_CENTRAL.getCuadranteAsList(celdas));
        
        List<Posicion> obtenidos = cuadrante.getCeldasHorizontalesConValor(celdas);
        
        assertEquals(esperado, obtenidos);
    }
    
    @Test
    public void getValoresVerticalesCorrectos1() {
        Celda[][] celdas = GridFactory.getSudokuResueltoValido1();
        
        Cuadrante cuadrante = Cuadrante.CENTRAL_CENTRAL;
        
        List<Posicion> esperado = new ArrayList<>();
        esperado.addAll(Cuadrante.SUPERIOR_CENTRAL.getCuadranteAsList(celdas));
        esperado.addAll(Cuadrante.INFERIOR_CENTRAL.getCuadranteAsList(celdas));
        
        List<Posicion> obtenidos = cuadrante.getCeldasVerticalesConValor(celdas);
        
        assertEquals(esperado, obtenidos);
    }
    
    @Test
    public void getValoresVerticalesCorrectos2() {
        Celda[][] celdas = GridFactory.getSudokuResueltoValido1();
        
        Cuadrante cuadrante = Cuadrante.INFERIOR_DERECHO;
        
        List<Posicion> esperado = new ArrayList<>();
        esperado.addAll(Cuadrante.SUPERIOR_DERECHO.getCuadranteAsList(celdas));
        esperado.addAll(Cuadrante.CENTRAL_DERECHO.getCuadranteAsList(celdas));
        
        List<Posicion> obtenidos = cuadrante.getCeldasVerticalesConValor(celdas);
        
        assertEquals(esperado, obtenidos);
    }
 }
