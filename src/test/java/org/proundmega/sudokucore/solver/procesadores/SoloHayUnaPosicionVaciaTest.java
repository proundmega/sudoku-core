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
import org.proundmega.sudokucore.elementos.anotador.Anotador;
import org.proundmega.sudokucore.elementos.anotador.AnotadorGeneral;

public class SoloHayUnaPosicionVaciaTest {
    
    public SoloHayUnaPosicionVaciaTest() {
    }

    @Test
    public void obtengoMetadatoEnPosicionUnica() {
        Anotador anotador = new AnotadorGeneral(GridFactory.getSudokuIncompleto1(), Cuadrante.INFERIOR_DERECHO);
        ProcesadorAnotaciones procesador = new ValorFaltante();
        
        Posicion posicionEsperada = new Posicion(Fila._9, Columna._9, Valor._8);
        Optional<MetadataSolver> respuesta = procesador.apply(anotador);
        
        assertTrue(respuesta.isPresent());
        assertEquals(posicionEsperada, respuesta.get().getPosicionResuelta());
    }
    
    @Test
    public void noObtengoMetadatoEnCuadranteLleno() {
        Anotador anotador = new AnotadorGeneral(GridFactory.getSudokuIncompleto1(), Cuadrante.SUPERIOR_CENTRAL);
        ProcesadorAnotaciones procesador = new ValorFaltante();
        
        Optional<MetadataSolver> respuesta = procesador.apply(anotador);
        
        assertFalse(respuesta.isPresent());
    }
    
    @Test
    public void noObtengoMetadatoEnCuadranteConMasDeUnEspacio() {
        Anotador anotador = new AnotadorGeneral(GridFactory.getSudokuFacil1(), Cuadrante.SUPERIOR_CENTRAL);
        ProcesadorAnotaciones procesador = new ValorFaltante();
        
        Optional<MetadataSolver> respuesta = procesador.apply(anotador);
        
        assertFalse(respuesta.isPresent());
    }
}
