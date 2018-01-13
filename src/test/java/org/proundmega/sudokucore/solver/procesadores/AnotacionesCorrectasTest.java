package org.proundmega.sudokucore.solver.procesadores;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import static org.junit.Assert.*;
import org.proundmega.sudokucore.MetadataSolver;
import org.proundmega.sudokucore.Posicion;
import org.proundmega.sudokucore.Sudoku;
import org.proundmega.sudokucore.elementos.Columna;
import org.proundmega.sudokucore.elementos.Cuadrante;
import org.proundmega.sudokucore.elementos.Fila;
import org.proundmega.sudokucore.elementos.Valor;
import org.proundmega.sudokucore.elementos.anotador.Anotador;
import org.proundmega.sudokucore.elementos.anotador.AnotadorGeneral;
import org.proundmega.sudokucore.io.FileToSudoku;
import org.proundmega.sudokucore.io.TestUtils;

public class AnotacionesCorrectasTest {
    
    @Test
    public void correccionBug1() throws IOException {
        File archivo = TestUtils.getTestResource("data", "bug1.txt");
        Sudoku sudoku = new FileToSudoku().crearSudoku(archivo);
        Cuadrante cuadranteObjetivo = Cuadrante.INFERIOR_IZQUIERO;
        Anotador anotador = sudoku.getGrid().getAnotador(cuadranteObjetivo);
        
        ProcesadorAnotaciones solucion = new ValorConUnicaPosicion();
        Optional<MetadataSolver> respuesta = solucion.apply(anotador);
        
        assertTrue(respuesta.isPresent());
        MetadataSolver datos = respuesta.get();
        
        List<Posicion> esperado = getPosicionesLimitadorasBug1();
        List<Posicion> obtenido = datos.getCeldasQueLimitanValor();
        
        assertEquals(esperado, obtenido);
    }
    
    private List<Posicion> getPosicionesLimitadorasBug1() {
        List<Posicion> posiciones = new ArrayList<>();
        posiciones.add(new Posicion(Fila._5, Columna._3, Valor._3));
        posiciones.add(new Posicion(Fila._8, Columna._8, Valor._3));
        posiciones.add(new Posicion(Fila._9, Columna._5, Valor._3));
        
        return posiciones;
    }
    
    @Test
    public void correccionBug2() throws IOException {
        File archivo = TestUtils.getTestResource("data", "bug2.txt");
        Sudoku sudoku = new FileToSudoku().crearSudoku(archivo);
        Cuadrante cuadranteObjetivo = Cuadrante.SUPERIOR_CENTRAL;
        Anotador anotador = sudoku.getGrid().getAnotador(cuadranteObjetivo);
        
        ProcesadorAnotaciones solucion = new ValorConUnicaPosicion();
        Optional<MetadataSolver> respuesta = solucion.apply(anotador);
        
        assertTrue(respuesta.isPresent());
        MetadataSolver datos = respuesta.get();
        
        List<Posicion> esperado = getPosicionesLimitadorasBug2();
        List<Posicion> obtenido = datos.getCeldasQueLimitanValor();
        
        assertEquals(esperado, obtenido);
    }
    
    private List<Posicion> getPosicionesLimitadorasBug2() {
        List<Posicion> posiciones = new ArrayList<>();
        posiciones.add(new Posicion(Fila._2, Columna._2, Valor._1));
        posiciones.add(new Posicion(Fila._3, Columna._8, Valor._1));
        
        return posiciones;
    }
}
