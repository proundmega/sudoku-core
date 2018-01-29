package org.proundmega.sudokucore.solver.procesadores;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.proundmega.sudokucore.Posicion;
import org.proundmega.sudokucore.data.GridFactory;
import org.proundmega.sudokucore.elementos.Valor;
import org.proundmega.sudokucore.elementos.Columna;
import org.proundmega.sudokucore.elementos.Cuadrante;
import org.proundmega.sudokucore.elementos.Fila;
import org.proundmega.sudokucore.elementos.Grid;
import org.proundmega.sudokucore.elementos.Valor;
import org.proundmega.sudokucore.elementos.anotador.Anotador;

public class SolucionSimpleTest {
    
    @Test
    public void unValorSoloPuedeEstarEnUnLugarCorrecto() {
        List<Posicion> posiciones = crearAnotaciones(Cuadrante.SUPERIOR_DERECHO)
                .getPosicionesConAnotacionesRemovidas();
        
        SolucionSimple pa = new SolucionSimple();
        
        assertTrue(pa.unValorSoloPuedeEstarEnUnLugar(posiciones));
    }

    private Anotador crearAnotaciones(Cuadrante cuadranteAResolver) {
        Valor[][] celdas = GridFactory.getSudokuFacil1();
        Grid grid = new Grid(celdas);
        List<Posicion> posiciones = grid.getAnotador(cuadranteAResolver).getPosicionesConAnotacionesRemovidas();
        
        Anotador mock = mock(Anotador.class);
        when(mock.getPosicionesConAnotacionesRemovidas())
                .thenReturn(posiciones);
        
        return mock;
    }
    
    @Test
    public void unValorSoloPuedeEstarEnUnLugarIncorrecto() {
        List<Posicion> posiciones = crearAnotaciones(Cuadrante.CENTRAL_CENTRAL)
                .getPosicionesConAnotacionesRemovidas();
        
        SolucionSimple pa = new SolucionSimple();
        
        assertFalse(pa.unValorSoloPuedeEstarEnUnLugar(posiciones));
    }
    
    @Test
    public void getValorASustituirCorrecto() {
        Anotador anotador = crearAnotaciones(Cuadrante.SUPERIOR_DERECHO);
        
        Posicion esperado = new Posicion(Fila._2, Columna._9, Valor._3);
        SolucionSimple pa = new SolucionSimple();
        
        Posicion obtenido = pa.apply(anotador).get().getPosicionResuelta();
        
        assertEquals(esperado, obtenido);
    }
    
}
