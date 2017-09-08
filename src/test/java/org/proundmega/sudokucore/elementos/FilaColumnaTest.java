package org.proundmega.sudokucore.elementos;

import org.junit.Test;
import static org.junit.Assert.*;

public class FilaColumnaTest {
    
    @Test
    public void filaRetornadaCorrectamente() {
        int fila = 3;
        Fila esperado = Fila._3;
        
        Fila obtenido = Fila.toFila(fila);
        
        assertEquals(esperado, obtenido);
    }
    
    @Test
    public void columnaRetornadaCorrectamente() {
        int columna = 5;
        Columna esperado = Columna._5;
        
        Columna obtenido = Columna.toColumna(columna);
        
        assertEquals(esperado, obtenido);
    }
}
