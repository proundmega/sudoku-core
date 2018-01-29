package org.proundmega.sudokucore.elementos;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.proundmega.sudokucore.data.GridFactory;

public class GridTest {
    
    public GridTest() {
    }

    @Test(expected = NullPointerException.class)
    public void crearNuevoSudokuNulo() {
        Valor[][] celdasErroneas = null;
        Grid grid = new Grid(celdasErroneas);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void crearNuevoSudokuSinDimensionesApropiadas() {
        Valor[][] celdasErroneas = new Valor[0][4];
        Grid grid = new Grid(celdasErroneas);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void crearNuevoSudokuSinDimensionesApropiadas2() {
        Valor[][] celdasErroneas = new Valor[9][8];
        Grid grid = new Grid(celdasErroneas);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void crearNuevoSudokuSinDimensionesApropiadas3() {
        Valor[][] celdasErroneas = new Valor[1][10];
        Grid grid = new Grid(celdasErroneas);
    }
    
    @Test
    public void crearNuevoSudokuVacioValido() {
        Valor[][] celdasErroneas = new Valor[9][9];
        Grid grid = new Grid(celdasErroneas);
        assertTrue(true);
    }
    
    @Test
    public void reemplazarElementoEnGrid() {
        Valor[][] celdas = GridFactory.getSudokuFacil1Resuelto();
        Grid grid = new Grid(celdas);
        
        Valor[][] copia = GridFactory.getSudokuFacil1Resuelto();
        copia[2][3] = Valor.VACIA;
        Grid esperado = new Grid(copia);
        
        assertNotEquals(celdas, copia);
        Grid obtenido = grid.reemplazarCasilla(Fila._3, Columna._4, Valor.VACIA);
        assertEquals(esperado, obtenido);
    }
    
    @Test
    public void validarTamañoDeContructorStringCorrecto() {
        String[][] bloque = getGridVacia();
        Grid grid = new Grid(bloque);
        assertTrue(true);
    }
    
    private String[][] getGridVacia() {
        String[][] grid = new String[9][9];
        for(int fila = 0; fila < 9; fila++) {
            for(int columna = 0; columna < 9; columna++) {
                grid[fila][columna] = "";
            }
        }
        return grid;
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void validarTamañoDeContructorStringIncorrectoDeFila() {
        String[][] bloque = new String[5][9];
        Grid grid = new Grid(bloque);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void validarTamañoDeContructorStringIncorrectoDeColumna() {
        String[][] bloque = new String[9][7];
        Grid grid = new Grid(bloque);
    }
    
    @Test
    public void contructorStringCopiaCorrectamente() {
        int filaPrueba = 1;
        int columnaPrueba = 5;
        String valor = "3";
        String[][] valores = getGridVacia();
        
        valores[filaPrueba][columnaPrueba] = valor;
                
        Grid grid = new Grid(valores);
        Valor esperado = Valor.toValor(valor);
        Valor obtenido = grid.getPosicion(Fila.toFila(filaPrueba + 1), Columna.toColumna(columnaPrueba + 1)).getValorActual();
        
        assertEquals(esperado, obtenido);
    }
}
