package org.proundmega.sudokucore;

import java.util.List;
import java.util.stream.Collectors;
import org.junit.Test;
import static org.junit.Assert.*;
import org.proundmega.sudokucore.data.GridFactory;
import org.proundmega.sudokucore.elementos.Celda;
import org.proundmega.sudokucore.elementos.Columna;
import org.proundmega.sudokucore.elementos.Cuadrante;
import org.proundmega.sudokucore.elementos.Fila;

public class ColectorPosicionTest {
    
    @Test
    public void compararColectorConCuadranteSuperior() {
        Celda[][] celdas = GridFactory.getSudokuFacil1();
        Cuadrante cuadrante = Cuadrante.SUPERIOR_CENTRAL;
        
        List<Posicion> esperado = cuadrante.getPosiciones(celdas);
        List<Posicion> obtenidos = ColectorPosicion.of(celdas)
                .collectPosiciones(Fila._1, Fila._3, Columna._4, Columna._6)
                .collect(Collectors.toList());
        
        assertEquals(esperado, obtenidos);
    }
    
}
