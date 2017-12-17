package org.proundmega.sudokucore.solver.procesadores;

import java.util.Optional;
import org.junit.Test;
import static org.junit.Assert.*;
import org.proundmega.sudokucore.MetadataSolver;
import org.proundmega.sudokucore.Posicion;
import org.proundmega.sudokucore.data.GridFactory;
import org.proundmega.sudokucore.elementos.Columna;
import org.proundmega.sudokucore.elementos.Cuadrante;
import org.proundmega.sudokucore.elementos.Fila;
import org.proundmega.sudokucore.elementos.Valor;
import org.proundmega.sudokucore.elementos.grid.anotador.AnotadorCuadrante;

public class SoloUnaCasillaTest {
    
    public SoloUnaCasillaTest() {
    }

    @Test
    public void obtengoMetadatoEnPosicionUnica() {
        AnotadorCuadrante anotador = new AnotadorCuadrante(GridFactory.getSudokuIncompleto1(), Cuadrante.INFERIOR_DERECHO);
        ProcesadorAnotaciones procesador = new SoloUnaCasilla();
        
        Posicion posicionEsperada = new Posicion(Fila._9, Columna._9, Valor._8);
        Optional<MetadataSolver> respuesta = procesador.apply(anotador);
        
        assertTrue(respuesta.isPresent());
        assertEquals(posicionEsperada, respuesta.get().getPosicionResuelta());
    }
    
    @Test
    public void noObtengoMetadatoEnCuadranteLleno() {
        AnotadorCuadrante anotador = new AnotadorCuadrante(GridFactory.getSudokuIncompleto1(), Cuadrante.SUPERIOR_CENTRAL);
        ProcesadorAnotaciones procesador = new SoloUnaCasilla();
        
        Optional<MetadataSolver> respuesta = procesador.apply(anotador);
        
        assertFalse(respuesta.isPresent());
    }
    
    @Test
    public void noObtengoMetadatoEnCuadranteConMasDeUnEspacio() {
        AnotadorCuadrante anotador = new AnotadorCuadrante(GridFactory.getSudokuFacil1(), Cuadrante.SUPERIOR_CENTRAL);
        ProcesadorAnotaciones procesador = new SoloUnaCasilla();
        
        Optional<MetadataSolver> respuesta = procesador.apply(anotador);
        
        assertFalse(respuesta.isPresent());
    }
}
