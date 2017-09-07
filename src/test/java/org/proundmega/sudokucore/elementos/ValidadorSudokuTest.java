package org.proundmega.sudokucore.elementos;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.proundmega.sudokucore.Celda;
import org.proundmega.sudokucore.Grid;
import org.proundmega.sudokucore.InvalidSudokuException;
import org.proundmega.sudokucore.Valor;
import static org.proundmega.sudokucore.Valor.*;
import org.proundmega.sudokucore.data.GridFactory;

public class ValidadorSudokuTest {
    private static Celda[][] celdas;

    @BeforeClass
    public static void crearSudoku() {
        celdas = new Celda[][] {
            crearCeldas( _1,    _2,    _3,   _4,    _5,    _6,    _7,   _8,     _9),
            crearCeldas( _2, VACIA, VACIA, VACIA, VACIA, VACIA, VACIA, VACIA, VACIA),
            crearCeldas( _3, VACIA, VACIA, VACIA, VACIA, VACIA, VACIA, VACIA, VACIA),
            crearCeldas( _4, VACIA, VACIA, VACIA, VACIA, VACIA, VACIA, VACIA, VACIA),
            crearCeldas( _5, VACIA, VACIA,   _5,    _7,    _3,    _5, VACIA, VACIA),
            crearCeldas( _6, VACIA, VACIA,   _5,    _7,    _3,  VACIA, VACIA, VACIA),
            crearCeldas( _7, VACIA, VACIA,   _6,    _4,    _8,    _9,    _1,    _2),
            crearCeldas( _8, VACIA, VACIA, VACIA, VACIA, VACIA,    _5,    _7,    _3),
            crearCeldas( _9, VACIA, VACIA, VACIA, VACIA, VACIA,    _6,    _4,    _8)
        };
        
    }
    
    private static Celda[] crearCeldas(Valor... valores) {
        List<Celda> celdas = new ArrayList<>();
        for (Valor valor : valores) {
            celdas.add(new Celda(valor));
        }
        
        return celdas.toArray(new Celda[9]);
    }
    
    @Test
    public void filaCompleta() {
        assertTrue(ValidadorSudoku.esFilaCompleta(celdas, Fila._1));
    }
    
    @Test
    public void filaIncompleta() {
        assertFalse(ValidadorSudoku.esFilaCompleta(celdas, Fila._2));
    }
    
    @Test
    public void columnaCompleta() {
        assertTrue(ValidadorSudoku.esColumnaCompleta(celdas, Columna._1));
    }
    
    @Test
    public void columnaIncompleta() {
        assertFalse(ValidadorSudoku.esColumnaCompleta(celdas, Columna._2));
    }
    
    @Test
    public void filaValidaCompleta() {
        assertTrue(ValidadorSudoku.esFilaValida(celdas, Fila._1));
    }
    
    @Test
    public void filaValidaIncompleta() {
        assertTrue(ValidadorSudoku.esFilaValida(celdas, Fila._2));
    }
    
    @Test
    public void filaInvalidaRepetido() {
        assertFalse(ValidadorSudoku.esFilaValida(celdas, Fila._5));
    }
    
    @Test
    public void columnaValidaCompleta() {
        assertTrue(ValidadorSudoku.esColumnaValida(celdas, Columna._1));
    }
    
    @Test
    public void columnaValidaIncompleta() {
        assertTrue(ValidadorSudoku.esColumnaValida(celdas, Columna._9));
    }
    
    @Test
    public void columnaInvalidaRepetido() {
        assertFalse(ValidadorSudoku.esColumnaValida(celdas, Columna._7));
    }
    
    @Test
    public void cuadranteCompleto() {
        assertTrue(ValidadorSudoku.esCuadranteCompleto(celdas, Cuadrante.INFERIOR_DERECHO));
    }
    
    @Test
    public void cuadranteIncompleto() {
        assertFalse(ValidadorSudoku.esCuadranteCompleto(celdas, Cuadrante.INFERIOR_IZQUIERO));
    }
    
    @Test
    public void cuadranteValido() {
        assertTrue(ValidadorSudoku.esCuadranteValido(celdas, Cuadrante.INFERIOR_DERECHO));
    }
    
    @Test
    public void cuadranteInvalido() {
        assertFalse(ValidadorSudoku.esCuadranteValido(celdas, Cuadrante.CENTRAL_CENTRAL));
    }
    
    @Test
    public void isResueltaQueEstaResuelta() {
        assertTrue(ValidadorSudoku.esCeldasValidasYCompletas(GridFactory.getSudokuResueltoValido1()));
    }
    
    @Test
    public void isResueltaConFilaIncompleta() {
        assertFalse(ValidadorSudoku.esCeldasValidasYCompletas(GridFactory.getSudokuIncompleto1()));
    }
    
    @Test(expected = InvalidSudokuException.class) 
    public void isResueltaConValoresInvalidos1() {
        ValidadorSudoku.esCeldasValidasYCompletas(GridFactory.getSudokuInvalido1());
    }
    
    @Test(expected = InvalidSudokuException.class) 
    public void isResueltaConValoresInvalidos2() {
        ValidadorSudoku.esCeldasValidasYCompletas(celdas);
    }
}
