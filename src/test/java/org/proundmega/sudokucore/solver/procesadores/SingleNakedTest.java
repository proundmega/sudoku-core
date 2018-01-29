package org.proundmega.sudokucore.solver.procesadores;

import java.util.List;
import java.util.Optional;
import org.junit.Test;
import static org.junit.Assert.*;
import org.proundmega.sudokucore.MetadataSolver;
import org.proundmega.sudokucore.Posicion;
import org.proundmega.sudokucore.data.GridFactory;
import org.proundmega.sudokucore.elementos.*;
import org.proundmega.sudokucore.elementos.anotador.Anotador;

public class SingleNakedTest {
    
    @Test
    public void hayUnaValorConUnPosibleValorCorrecto() {
        List<Posicion> posiciones = getPosicionesVaciasDeCuadrante(Cuadrante.SUPERIOR_DERECHO)
                .getPosicionesConAnotacionesRemovidas();
        
        SingleNaked metodo = new SingleNaked();
        assertTrue(metodo.hayUnaCasillaConUnSoloPosibleValor(posiciones));
    }
    
    private Anotador getPosicionesVaciasDeCuadrante(Cuadrante cuadrante) {
        Valor[][] celda = GridFactory.getSudokuFacil1();
        Grid grid = new Grid(celda);
        
        return grid.getAnotador(cuadrante);
    }
    
    @Test
    public void hayUnaValorConUnPosibleValorIncorrecto() {
        List<Posicion> posiciones = getPosicionesVaciasDeCuadrante(Cuadrante.CENTRAL_DERECHO)
                .getPosicionesConAnotacionesRemovidas();
        
        SingleNaked metodo = new SingleNaked();
        assertFalse(metodo.hayUnaCasillaConUnSoloPosibleValor(posiciones));
    }
    
    @Test
    public void getRespuestaCorrecta() {
        Anotador anotador = getPosicionesVaciasDeCuadrante(Cuadrante.SUPERIOR_DERECHO);
        
        SingleNaked posicion = new SingleNaked();
        
        Posicion esperado = new Posicion(Fila._2, Columna._8, Valor._2);
        
        Optional<MetadataSolver> obtenido = posicion.apply(anotador);
        
        assertTrue(obtenido.isPresent());
        assertEquals(esperado, obtenido.get().getPosicionResuelta());
    }
    
    @Test
    public void getRespuestaIncorrecta() {
        Anotador anotador = getPosicionesVaciasDeCuadrante(Cuadrante.SUPERIOR_DERECHO);
        SingleNaked posicion = new SingleNaked();
        
        Posicion esperado = new Posicion(Fila._2, Columna._8, Valor._2);
        
        Optional<MetadataSolver> obtenido = posicion.apply(anotador);
        
        assertTrue(obtenido.isPresent());
        assertEquals(esperado, obtenido.get().getPosicionResuelta());
    }
}
