package org.proundmega.sudokucore;

import org.junit.Test;
import static org.junit.Assert.*;
import org.proundmega.sudokucore.elementos.Columna;
import org.proundmega.sudokucore.elementos.Cuadrante;
import org.proundmega.sudokucore.elementos.Fila;
import org.proundmega.sudokucore.elementos.Valor;

public class PosicionTest {
    
    public PosicionTest() {
    }

    @Test
    public void probarComparacionDePosicionNula() {
        Posicion posicion = Posicion.posicionNula();
        assertTrue(Posicion.esNula(posicion));
    }
    
    @Test
    public void probarComparacionDePosicionNulaEnNoNulo() {
        Posicion posicion = new Posicion(Fila._1, Columna._1, Valor.VACIA);
        assertFalse(Posicion.esNula(posicion));
    }
    
    @Test
    public void getCuadranteFuncionaCorrectamente1() {
        Posicion posicion = new Posicion(Fila._1, Columna._3, Valor.VACIA);
        Cuadrante esperado = Cuadrante.SUPERIOR_IZQUIERO;
        Cuadrante obtenido = posicion.getCuadrante();
        
        assertEquals(esperado, obtenido);
    }
    
    @Test
    public void getCuadranteFuncionaCorrectamente2() {
        Posicion posicion = new Posicion(Fila._5, Columna._9, Valor.VACIA);
        Cuadrante esperado = Cuadrante.CENTRAL_DERECHO;
        Cuadrante obtenido = posicion.getCuadrante();
        
        assertEquals(esperado, obtenido);
    }
    
    @Test
    public void getCuadranteFuncionaCorrectamente3() {
        Posicion posicion = new Posicion(Fila._7, Columna._3, Valor.VACIA);
        Cuadrante esperado = Cuadrante.INFERIOR_IZQUIERO;
        Cuadrante obtenido = posicion.getCuadrante();
        
        assertEquals(esperado, obtenido);
    }
}
