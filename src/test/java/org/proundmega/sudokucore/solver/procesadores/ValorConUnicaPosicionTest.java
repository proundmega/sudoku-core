package org.proundmega.sudokucore.solver.procesadores;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.proundmega.sudokucore.Posicion;
import org.proundmega.sudokucore.data.GridFactory;
import org.proundmega.sudokucore.elementos.Celda;
import org.proundmega.sudokucore.elementos.Columna;
import org.proundmega.sudokucore.elementos.Cuadrante;
import org.proundmega.sudokucore.elementos.Fila;
import org.proundmega.sudokucore.elementos.Valor;
import org.proundmega.sudokucore.elementos.grid.SubGridCuadrante;
import org.proundmega.sudokucore.elementos.grid.anotador.Anotador;

public class ValorConUnicaPosicionTest {
    
    @Test
    public void unValorSoloPuedeEstarEnUnLugarCorrecto() {
        List<Posicion> posiciones = crearAnotaciones(Cuadrante.SUPERIOR_DERECHO)
                .getPosicionesConAnotacionesRemovidas();
        
        ValorConUnicaPosicion pa = new ValorConUnicaPosicion();
        
        assertTrue(pa.unValorSoloPuedeEstarEnUnLugar(posiciones));
    }

    private Anotador crearAnotaciones(Cuadrante cuadranteAResolver) {
        Celda[][] celdas = GridFactory.getSudokuFacil1();
        SubGridCuadrante subgrid = new SubGridCuadrante(celdas, cuadranteAResolver);
        List<Posicion> posiciones = subgrid.getPosicionesVaciasAnotadas();
        
        Anotador mock = mock(Anotador.class);
        when(mock.getPosicionesConAnotacionesRemovidas())
                .thenReturn(posiciones);
        
        return mock;
    }
    
    @Test
    public void unValorSoloPuedeEstarEnUnLugarIncorrecto() {
        List<Posicion> posiciones = crearAnotaciones(Cuadrante.CENTRAL_CENTRAL)
                .getPosicionesConAnotacionesRemovidas();
        
        ValorConUnicaPosicion pa = new ValorConUnicaPosicion();
        
        assertFalse(pa.unValorSoloPuedeEstarEnUnLugar(posiciones));
    }
    
    @Test
    public void getValorASustituirCorrecto() {
        Anotador anotador = crearAnotaciones(Cuadrante.SUPERIOR_DERECHO);
        
        Posicion esperado = new Posicion(Fila._2, Columna._9, new Celda(Valor._3));
        ValorConUnicaPosicion pa = new ValorConUnicaPosicion();
        
        Posicion obtenido = pa.apply(anotador).get().getPosicionResuelta();
        
        assertEquals(esperado, obtenido);
    }
    
}
