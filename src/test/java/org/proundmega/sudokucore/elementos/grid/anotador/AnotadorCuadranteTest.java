/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.proundmega.sudokucore.elementos.grid.anotador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.proundmega.sudokucore.Posicion;
import org.proundmega.sudokucore.data.GridFactory;
import org.proundmega.sudokucore.elementos.Celda;
import org.proundmega.sudokucore.elementos.Cuadrante;
import org.proundmega.sudokucore.elementos.Valor;
import static org.proundmega.sudokucore.elementos.Valor.*;

/**
 *
 * @author vansi
 */
public class AnotadorCuadranteTest {
    private static Celda[][] celdas;
    
    @BeforeClass
    public static void crearGridParaAnotar() {
        celdas = GridFactory.getSudokuFacil1();
    }

    @Test
    public void getAnotacionesConCuadrantesHorizontalesRemovidos() {
        Cuadrante cuadrante = Cuadrante.CENTRAL_IZQUIERO;
        
        AnotadorCuadrante anotador = new AnotadorCuadrante(celdas, cuadrante);
        
        Posicion posicionAfectada = new Posicion(5, 3, new Celda(Valor.VACIA));
        posicionAfectada = crearAnotaciones(posicionAfectada, _2, _4, _5, _8);
        
        assertTrue(anotador.getPosicionesFilasRemovidas().contains(posicionAfectada));
    }

    @Test
    public void getAnotacionesConCuadrantesVerticalesRemovidos() {
        Cuadrante cuadrante = Cuadrante.CENTRAL_IZQUIERO;
        
        AnotadorCuadrante anotador = new AnotadorCuadrante(celdas, cuadrante);
        
        List<Posicion> posicionesCambiadas = getPosicionesAlteradasCuadranteCentralIzquierdo();
        
        for (Posicion posicionAfectada : posicionesCambiadas) {
            assertTrue(anotador.getPosicionesColumnasRemovidas().contains(posicionAfectada));
        }
    }

    private List<Posicion> getPosicionesAlteradasCuadranteCentralIzquierdo() {
        List<Posicion> posiciones = new ArrayList<>();
        
        Posicion posicionAfectada1 = new Posicion(4, 2, new Celda(Valor.VACIA));
        posicionAfectada1 = crearAnotaciones(posicionAfectada1, _2, _3, _4);
        
        posiciones.add(posicionAfectada1);
        
        Posicion posicionAfectada2 = new Posicion(6, 2, new Celda(Valor.VACIA));
        posicionAfectada2 = crearAnotaciones(posicionAfectada2, _2, _3, _4);
        
        posiciones.add(posicionAfectada2);
        
        return posiciones;
    }
    
    @Test
    public void getAnotacionesCSDHorizontales() {
        Cuadrante cuadrante = Cuadrante.SUPERIOR_DERECHO;
        
        AnotadorCuadrante anotador = new AnotadorCuadrante(celdas, cuadrante);
        
        List<Posicion> posicionesCambiadas = getPosicionesCSDHorizontales();
        assertEquals(posicionesCambiadas, anotador.getPosicionesFilasRemovidas());
    }
    
    private List<Posicion> getPosicionesCSDHorizontales() {
        List<Posicion> posiciones = new ArrayList<>();
        
        Posicion posicionAfectada1 = new Posicion(1, 7, new Celda(Valor.VACIA));
        posicionAfectada1 = crearAnotaciones(posicionAfectada1, _1, _2, _4, _5);
        posiciones.add(posicionAfectada1);
        
        Posicion posicionAfectada2 = new Posicion(1, 8, new Celda(Valor.VACIA));
        posicionAfectada2 = crearAnotaciones(posicionAfectada2, _1, _2, _4, _5);
        posiciones.add(posicionAfectada2);
        
        Posicion posicionAfectada3 = new Posicion(2, 8, new Celda(Valor.VACIA));
        posicionAfectada3 = crearAnotaciones(posicionAfectada3, _2, _3, _7);
        posiciones.add(posicionAfectada3);
        
        Posicion posicionAfectada4 = new Posicion(2, 9, new Celda(Valor.VACIA));
        posicionAfectada4 = crearAnotaciones(posicionAfectada4, _2, _3, _7);
        posiciones.add(posicionAfectada4);
        
        Posicion posicionAfectada5 = new Posicion(3, 7, new Celda(Valor.VACIA));
        posicionAfectada5 = crearAnotaciones(posicionAfectada5, _1, _2, _5, _7);
        posiciones.add(posicionAfectada5);
        
        Posicion posicionAfectada6 = new Posicion(3, 9, new Celda(Valor.VACIA));
        posicionAfectada6 = crearAnotaciones(posicionAfectada6, _1, _2, _5, _7);
        posiciones.add(posicionAfectada6);
        
        return posiciones;
    }
    
    private Posicion crearAnotaciones(Posicion posicion, Valor... anotaciones) {
        Set<Valor> anotacionesAsSet = new TreeSet<>(Arrays.asList(anotaciones));
        Posicion nuevaPosicion = posicion.addAnotaciones(anotacionesAsSet);
        
        return nuevaPosicion;
    }
    
    @Test
    public void getAnotacionesCSDVerticales() {
        Cuadrante cuadrante = Cuadrante.SUPERIOR_DERECHO;
        
        AnotadorCuadrante anotador = new AnotadorCuadrante(celdas, cuadrante);
        
        List<Posicion> posicionesCambiadas = getPosicionesCSDVericales();
        
        assertEquals(posicionesCambiadas, anotador.getPosicionesColumnasRemovidas());
    }
    
    private List<Posicion> getPosicionesCSDVericales() {
        List<Posicion> posiciones = new ArrayList<>();
        
        Posicion posicionAfectada1 = new Posicion(1, 7, new Celda(Valor.VACIA));
        posicionAfectada1 = crearAnotaciones(posicionAfectada1, _2, _4, _5, _7);
        posiciones.add(posicionAfectada1);
        
        Posicion posicionAfectada2 = new Posicion(1, 8, new Celda(Valor.VACIA));
        posicionAfectada2 = crearAnotaciones(posicionAfectada2, _1, _2, _5);
        posiciones.add(posicionAfectada2);
        
        Posicion posicionAfectada3 = new Posicion(2, 8, new Celda(Valor.VACIA));
        posicionAfectada3 = crearAnotaciones(posicionAfectada3, _1, _2, _5);
        posiciones.add(posicionAfectada3);
        
        Posicion posicionAfectada4 = new Posicion(2, 9, new Celda(Valor.VACIA));
        posicionAfectada4 = crearAnotaciones(posicionAfectada4, _1, _2, _3, _4, _5);
        posiciones.add(posicionAfectada4);
        
        Posicion posicionAfectada5 = new Posicion(3, 7, new Celda(Valor.VACIA));
        posicionAfectada5 = crearAnotaciones(posicionAfectada5, _2, _4, _5, _7);
        posiciones.add(posicionAfectada5);
        
        Posicion posicionAfectada6 = new Posicion(3, 9, new Celda(Valor.VACIA));
        posicionAfectada6 = crearAnotaciones(posicionAfectada6, _1, _2, _3, _4, _5);
        posiciones.add(posicionAfectada6);
        
        return posiciones;
    }
    
}
