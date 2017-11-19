package org.proundmega.sudokucore.elementos;

import org.junit.Test;
import static org.junit.Assert.*;

public class CeldaTest {
    
    @Test
    public void estaVacio() {
        Celda celda = new Celda();
        assertTrue(celda.estaVacia());
    }
    
    @Test
    public void noEstaVacio() {
        Celda celda = new Celda();
        celda = celda.cambiarValor(Valor._1);
        assertFalse(celda.estaVacia());
    }

}
