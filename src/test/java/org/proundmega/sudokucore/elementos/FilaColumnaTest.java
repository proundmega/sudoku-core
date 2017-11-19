package org.proundmega.sudokucore.elementos;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.proundmega.sudokucore.Posicion;
import org.proundmega.sudokucore.data.GridFactory;
import org.proundmega.sudokucore.elementos.grid.Grid;

public class FilaColumnaTest {
    
    @Test
    public void filaRetornadaCorrectamente() {
        int fila = 3;
        Fila esperado = Fila._3;
        
        Fila obtenido = Fila.toFila(fila);
        
        assertEquals(esperado, obtenido);
    }
    
    @Test
    public void columnaRetornadaCorrectamente() {
        int columna = 5;
        Columna esperado = Columna._5;
        
        Columna obtenido = Columna.toColumna(columna);
        
        assertEquals(esperado, obtenido);
    }
    
    @Test
    public void obtenerFilaAsListCorrectamente() {
        Fila fila = Fila._7;
        
        Celda[][] celda = GridFactory.getSudokuFacil1Resuelto();
        List<Posicion> posiciones = getListaFila7(celda);
        List<Posicion> obtenidos = fila.getfilaAsList(celda);
        
        assertEquals(posiciones, obtenidos);
    }
    
    private List<Posicion> getListaFila7(Celda[][] celdas) {
        List<Posicion> posiciones = new ArrayList<>();
        
        posiciones.add(new Posicion(7, 1, celdas[6][0]));
        posiciones.add(new Posicion(7, 2, celdas[6][1]));
        posiciones.add(new Posicion(7, 3, celdas[6][2]));
        
        posiciones.add(new Posicion(7, 4, celdas[6][3]));
        posiciones.add(new Posicion(7, 5, celdas[6][4]));
        posiciones.add(new Posicion(7, 6, celdas[6][5]));
        
        posiciones.add(new Posicion(7, 7, celdas[6][6]));
        posiciones.add(new Posicion(7, 8, celdas[6][7]));
        posiciones.add(new Posicion(7, 9, celdas[6][8]));
        
        return posiciones;
    }
    
    @Test
    public void obtenerColumnaAsListCorrectamente() {
        Columna columna = Columna._3;
        
        Celda[][] celda = GridFactory.getSudokuFacil1Resuelto();
        List<Posicion> posiciones = getListaColumna3(celda);
        List<Posicion> obtenidos = columna.getColumnaAsList(celda);
        
        assertEquals(posiciones, obtenidos);
    }
    
    private List<Posicion> getListaColumna3(Celda[][] celdas) {
        List<Posicion> posiciones = new ArrayList<>();
        
        posiciones.add(new Posicion(1, 3, celdas[0][2]));
        posiciones.add(new Posicion(2, 3, celdas[1][2]));
        posiciones.add(new Posicion(3, 3, celdas[2][2]));
        
        posiciones.add(new Posicion(4, 3, celdas[3][2]));
        posiciones.add(new Posicion(5, 3, celdas[4][2]));
        posiciones.add(new Posicion(6, 3, celdas[5][2]));
        
        posiciones.add(new Posicion(7, 3, celdas[6][2]));
        posiciones.add(new Posicion(8, 3, celdas[7][2]));
        posiciones.add(new Posicion(9, 3, celdas[8][2]));
        
        return posiciones;
    }
}
