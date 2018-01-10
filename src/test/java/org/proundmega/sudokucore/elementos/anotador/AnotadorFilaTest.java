package org.proundmega.sudokucore.elementos.anotador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Ignore;
import org.proundmega.sudokucore.Posicion;
import org.proundmega.sudokucore.data.GridFactory;
import org.proundmega.sudokucore.elementos.Celda;
import org.proundmega.sudokucore.elementos.Columna;
import org.proundmega.sudokucore.elementos.Fila;
import org.proundmega.sudokucore.elementos.Valor;
import static org.proundmega.sudokucore.elementos.Valor.*;

public class AnotadorFilaTest {
    private Celda[][] celdas;
    // TODO pasar pruebas de AnotadorCuadrante para aca
    // TODO crear implementacion de anotador de fila
    
    @Before
    public void crearSudoku() {
        celdas = GridFactory.getSudokuFacil1();
    }
    
    @Test
    public void anotacionHorizontalNoAnotaNada() {
        Fila filaObjetivo = Fila._4;
        AnotadorGeneral anotador = new AnotadorGeneral(celdas, filaObjetivo);
        List<Posicion> esperado = getPosicionesConFilasRemovidasFila4(celdas);
        List<Posicion> obtenido = anotador.getPosicionesFilasRemovidas();
        
        assertEquals(esperado, obtenido);
    }
    
    private List<Posicion> getPosicionesConFilasRemovidasFila4(Celda[][] celdas) {
        List<Posicion> posicionesVacias = Fila._4.getPosicionesVacias(celdas);
        Set<Valor> notacionesEsperadas = new HashSet<>(Arrays.asList(
                Valor._1, Valor._2, Valor._3, Valor._4, Valor._5, Valor._7, Valor._8, Valor._9));
        
        return posicionesVacias.stream()
                .map(posicion -> posicion.addAnotaciones(notacionesEsperadas))
                .collect((Collectors.toList()));
    }
    
    @Test
    public void anotacionesCorrectas1() {
        Fila filaObjetivo = Fila._3;
        AnotadorGeneral anotador = new AnotadorGeneral(celdas, filaObjetivo);
        
        List<Posicion> esperado = getPosicionesAnotadasFila3();
        List<Posicion> obtenido = anotador.getPosicionesConAnotacionesRemovidas();
        
        assertEquals(esperado, obtenido);
    }
    
    private List<Posicion> getPosicionesAnotadasFila3() {
        List<Posicion> posiciones = new ArrayList<>();
        
        Posicion posicion1 = new Posicion(Fila._3, Columna._1, Valor.VACIA);
        posicion1 = posicion1.addAnotaciones(new TreeSet<>(Arrays.asList(_1, _2, _6, _8)));
        
        Posicion posicion2 = new Posicion(Fila._3, Columna._2, Valor.VACIA);
        posicion2 = posicion2.addAnotaciones(new TreeSet<>(Arrays.asList(_2, _6)));
        
        Posicion posicion3 = new Posicion(Fila._3, Columna._5, Valor.VACIA);
        posicion3 = posicion3.addAnotaciones(new TreeSet<>(Arrays.asList(_5, _6, _7)));
        
        Posicion posicion4 = new Posicion(Fila._3, Columna._6, Valor.VACIA);
        posicion4 = posicion4.addAnotaciones(new TreeSet<>(Arrays.asList(_2, _6, _7, _8)));
        
        Posicion posicion5 = new Posicion(Fila._3, Columna._7, Valor.VACIA);
        posicion5 = posicion5.addAnotaciones(new TreeSet<>(Arrays.asList(_2, _5, _7)));
        
        Posicion posicion6 = new Posicion(Fila._3, Columna._9, Valor.VACIA);
        posicion6 = posicion6.addAnotaciones(new TreeSet<>(Arrays.asList(_1, _2, _5)));
        
        posiciones.add(posicion1);
        posiciones.add(posicion2);
        posiciones.add(posicion3);
        posiciones.add(posicion4);
        posiciones.add(posicion5);
        posiciones.add(posicion6);
        
        return posiciones;
    }
}
