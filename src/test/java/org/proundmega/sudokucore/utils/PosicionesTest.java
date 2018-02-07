package org.proundmega.sudokucore.utils;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.proundmega.sudokucore.Posicion;
import org.proundmega.sudokucore.elementos.Valor;

public class PosicionesTest {
    
    @Test
    public void testJoinPosicionesAsCoordenadasSinValor() {
        String esperado = "(1, 2), (3, 6), (7, 9)";
        List<Posicion> posicionesAHacerJoin = getPosicionesAHacerJoin();
        assertEquals(esperado, Posiciones.joinPosicionesAsCoordenadasSinValor(posicionesAHacerJoin));
    }

    private List<Posicion> getPosicionesAHacerJoin() {
        List<Posicion> posiciones = new ArrayList<>();
        posiciones.add(new Posicion(1, 2, Valor.VACIA));
        posiciones.add(new Posicion(3, 6, Valor.VACIA));
        posiciones.add(new Posicion(7, 9, Valor.VACIA));
        
        return posiciones;
    }
    
}
