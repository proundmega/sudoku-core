package org.proundmega.sudokucore;

import org.junit.Test;
import static org.junit.Assert.*;
import org.proundmega.sudokucore.elementos.Celda;
import org.proundmega.sudokucore.elementos.Columna;
import org.proundmega.sudokucore.elementos.Fila;

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
        Posicion posicion = new Posicion(Fila._1, Columna._1, new Celda());
        assertFalse(Posicion.esNula(posicion));
    }
}
