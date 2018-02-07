package org.proundmega.sudokucore.elementos;

import org.proundmega.sudokucore.utils.ValidadorSudoku;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.proundmega.sudokucore.InvalidSudokuException;
import static org.proundmega.sudokucore.elementos.Valor.*;
import org.proundmega.sudokucore.data.GridFactory;

public class ValidadorSudokuTest {
    private static Valor[][] celdas;

    @BeforeClass
    public static void crearSudoku() {
        celdas = new Valor[][] {
            new Valor[]{ _1,    _2,    _3,   _4,    _5,    _6,    _7,   _8,     _9},
            new Valor[]{ _2, VACIA, VACIA, VACIA, VACIA, VACIA, VACIA, VACIA, VACIA},
            new Valor[]{ _3, VACIA, VACIA, VACIA, VACIA, VACIA, VACIA, VACIA, VACIA},
            new Valor[]{ _4, VACIA, VACIA, VACIA, VACIA, VACIA, VACIA, VACIA, VACIA},
            new Valor[]{ _5, VACIA, VACIA,   _5,    _7,    _3,    _5, VACIA, VACIA},
            new Valor[]{ _6, VACIA, VACIA,   _5,    _7,    _3,  VACIA, VACIA, VACIA},
            new Valor[]{ _7, VACIA, VACIA,   _6,    _4,    _8,    _9,    _1,    _2},
            new Valor[]{ _8, VACIA, VACIA, VACIA, VACIA, VACIA,    _5,    _7,    _3},
            new Valor[]{ _9, VACIA, VACIA, VACIA, VACIA, VACIA,    _6,    _4,    _8}
        };
        
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
        assertTrue(ValidadorSudoku.esValorsValidasYCompletas(GridFactory.getSudokuFacil1Resuelto()));
    }
    
    @Test
    public void isResueltaConFilaIncompleta() {
        assertFalse(ValidadorSudoku.esValorsValidasYCompletas(GridFactory.getSudokuIncompleto1()));
    }
    
    @Test(expected = InvalidSudokuException.class) 
    public void isResueltaConValoresInvalidos1() {
        ValidadorSudoku.esValorsValidasYCompletas(GridFactory.getSudokuInvalido1());
    }
    
    @Test(expected = InvalidSudokuException.class) 
    public void isResueltaConValoresInvalidos2() {
        ValidadorSudoku.esValorsValidasYCompletas(celdas);
    }
    
    public void isCuadranteCompletoValido() {
        Valor[][] cuadranteCompleto = new Valor[][] {
            new Valor[]{_1, _2, _3},
            new Valor[]{_4, _5, _6},
            new Valor[]{_7, _8, _9}
        };
        
        assertTrue(ValidadorSudoku.esCuadranteCompleto(cuadranteCompleto));
    }
}
