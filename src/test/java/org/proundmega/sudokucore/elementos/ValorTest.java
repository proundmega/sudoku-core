package org.proundmega.sudokucore.elementos;

import org.junit.Test;
import static org.junit.Assert.*;

public class ValorTest {
    
    @Test
    public void convertirStringEnValor1() {
        String valor = "1";
        Valor esperado = Valor._1;
        
        Valor obtenido = Valor.toValor(valor);
        assertEquals(esperado, obtenido);
    }
    
    @Test
    public void convertirStringEnValor5() {
        String valor = "5";
        Valor esperado = Valor._5;
        
        Valor obtenido = Valor.toValor(valor);
        assertEquals(esperado, obtenido);
    }
    
    @Test
    public void convertirStringEnValor9() {
        String valor = "9";
        Valor esperado = Valor._9;
        
        Valor obtenido = Valor.toValor(valor);
        assertEquals(esperado, obtenido);
    }
    
    @Test
    public void convertirStringEnValorVacio() {
        String valor = " ";
        Valor esperado = Valor.VACIA;
        
        Valor obtenido = Valor.toValor(valor);
        assertEquals(esperado, obtenido);
    }
    
    @Test
    public void convertirStringEnValorVacio2() {
        String valor = "";
        Valor esperado = Valor.VACIA;
        
        Valor obtenido = Valor.toValor(valor);
        assertEquals(esperado, obtenido);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void convertirStringEnValorInvalido() {
        String valor = "Juela wej";
        
        Valor obtenido = Valor.toValor(valor);
    }
}
