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

public class CuadranteTest {
    private static Valor[][] valores;
    
    @BeforeClass
    public static void crearGridPrueba() {
        valores = GridFactory.getSudokuFacil1Resuelto();
    }
    
    @Test
    public void gcSuperiorIzquierdo() {
        Cuadrante cuadrante = Cuadrante.SUPERIOR_IZQUIERO;
        
        Valor[][] esperado = new Valor[][] {
            new Valor[]{_8,    _7,    _1},
            new Valor[]{_4,    _5,    _9},
            new Valor[]{_6,    _2,    _3}
        };
        
        assertArrayEquals(esperado, cuadrante.getCuadrante(valores));
    }
    
    @Test
    public void gcSuperiorCentral() {
        Cuadrante cuadrante = Cuadrante.SUPERIOR_CENTRAL;
        
        Valor[][] esperado = new Valor[][] {
            new Valor[]{_2,    _3,    _9},
            new Valor[]{_6,    _1,    _7},
            new Valor[]{_4,    _5,    _8}
        };
        
        assertArrayEquals(esperado, cuadrante.getCuadrante(valores));
    }

    @Test
    public void gcSuperiorDerecho() {
        Cuadrante cuadrante = Cuadrante.SUPERIOR_DERECHO;
        
        Valor[][] esperado = new Valor[][] {
            new Valor[]{_4,   _5,   _6},
            new Valor[]{_8,   _2,   _3},
            new Valor[]{_7,   _9,   _1}
        };
        
        assertArrayEquals(esperado, cuadrante.getCuadrante(valores));
    }
    
    @Test
    public void gcCentralIzquierdo() {
        Cuadrante cuadrante = Cuadrante.CENTRAL_IZQUIERO;
        
        Valor[][] esperado = new Valor[][] {
            new Valor[]{_2,    _4,    _6},
            new Valor[]{_9,    _1,    _8},
            new Valor[]{_5,    _3,    _7}
        };
        
        assertArrayEquals(esperado, cuadrante.getCuadrante(valores));
    }
    
    @Test
    public void gcCentralCentral() {
        Cuadrante cuadrante = Cuadrante.CENTRAL_CENTRAL;
        
        Valor[][] esperado = new Valor[][] {
            new Valor[]{_3,    _7,    _1},
            new Valor[]{_5,    _4,    _6},
            new Valor[]{_8,    _9,    _2}
        };
        
        assertArrayEquals(esperado, cuadrante.getCuadrante(valores));
    }
    
    @Test
    public void gcCentralDerecho() {
        Cuadrante cuadrante = Cuadrante.CENTRAL_DERECHO;
        
        Valor[][] esperado = new Valor[][] {
            new Valor[]{_9,   _8,   _5},
            new Valor[]{_2,   _3,   _7},
            new Valor[]{_1,   _6,   _4}
        };
        
        assertArrayEquals(esperado, cuadrante.getCuadrante(valores));
    }
    
    @Test
    public void gcInferiorIzquierdo() {
        Cuadrante cuadrante = Cuadrante.INFERIOR_IZQUIERO;
        
        Valor[][] esperado = new Valor[][] {
            new Valor[]{_7,    _8,    _4},
            new Valor[]{_1,    _6,    _2},
            new Valor[]{_3,    _9,    _5}
        };
        
        assertArrayEquals(esperado, cuadrante.getCuadrante(valores));
    }
    
    @Test
    public void gcInferiorCentral() {
        Cuadrante cuadrante = Cuadrante.INFERIOR_CENTRAL;
        
        Valor[][] esperado = new Valor[][] {
            new Valor[]{_9,    _6,    _5},
            new Valor[]{_7,    _8,    _3},
            new Valor[]{_1,    _2,    _4}
        };
        
        assertArrayEquals(esperado, cuadrante.getCuadrante(valores));
    }
    
    @Test
    public void gcInferiorDerecho() {
        Cuadrante cuadrante = Cuadrante.INFERIOR_DERECHO;
        
        Valor[][] esperado = new Valor[][] {
            new Valor[]{_3,   _1,   _2},
            new Valor[]{_5,   _4,   _9},
            new Valor[]{_6,   _7,   _8}
        };
        
        assertArrayEquals(esperado, cuadrante.getCuadrante(valores));
    }
    
    @Test
    public void gcInferiorDerechoAsList() {
        Cuadrante cuadrante = Cuadrante.INFERIOR_DERECHO;
        
        List<Posicion> esperado = getPosicionesInferiorDerecho(valores);
        List<Posicion> obtenido = cuadrante.getCuadranteAsList(valores);
        
        assertEquals(esperado, obtenido);
    }
    
    private List<Posicion> getPosicionesInferiorDerecho(Valor[][] celdas) {
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
        Valor[][] celdas = GridFactory.getSudokuIncompleto2();
        
        Cuadrante cuadrante = Cuadrante.INFERIOR_DERECHO;
        
        List<Posicion> esperado = getPosicionesInferiorDerechoVacias(celdas);
        List<Posicion> obtenido = cuadrante.getPosicionesVacias(celdas);
        
        assertEquals(esperado, obtenido);
    }

    private List<Posicion> getPosicionesInferiorDerechoVacias(Valor[][] celdas) {
        List<Posicion> posiciones = new ArrayList<>();
        
        posiciones.add(new Posicion(8, 8, celdas[7][7]));
        posiciones.add(new Posicion(9, 9, celdas[8][8]));
        
        return posiciones;
    }
    
 }
