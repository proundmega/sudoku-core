package org.proundmega.sudokucore.elementos;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.proundmega.sudokucore.Posicion;
import org.proundmega.sudokucore.data.GridFactory;

public class ColumnaTest {
    
    @Test
    public void columnaRetornadaCorrectamente() {
        int columna = 5;
        Columna esperado = Columna._5;
        
        Columna obtenido = Columna.toColumna(columna);
        
        assertEquals(esperado, obtenido);
    }
    
    @Test
    public void obtenerColumnaAsListCorrectamente() {
        Columna columna = Columna._3;
        
        Valor[][] celda = GridFactory.getSudokuFacil1Resuelto();
        List<Posicion> posiciones = getListaColumna3(celda);
        List<Posicion> obtenidos = columna.getPosiciones(celda);
        
        assertEquals(posiciones, obtenidos);
    }
    
    private List<Posicion> getListaColumna3(Valor[][] celdas) {
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
    
    @Test
    public void getValoresVaciosCorrectos1() {
        Columna columnaObjetivo = Columna._7;
        
        Valor[][] celdas = GridFactory.getSudokuFacil1();
        List<Posicion> esperado = getPosicionesVaciasColumna7();
        List<Posicion> obtenido = columnaObjetivo.getPosicionesVacias(celdas);
        
        assertEquals(esperado, obtenido);
    }
    
    private List<Posicion> getPosicionesVaciasColumna7() {
        List<Posicion> posiciones = new ArrayList<>();
        posiciones.add(new Posicion(Fila._1, Columna._7, Valor.VACIA));
        posiciones.add(new Posicion(Fila._3, Columna._7, Valor.VACIA));
        posiciones.add(new Posicion(Fila._4, Columna._7, Valor.VACIA));
        posiciones.add(new Posicion(Fila._5, Columna._7, Valor.VACIA));
        posiciones.add(new Posicion(Fila._8, Columna._7, Valor.VACIA));
        posiciones.add(new Posicion(Fila._9, Columna._7, Valor.VACIA));
        
        return posiciones;
    }
    
    @Test
    public void getPosicionesConValorCorrectos1() {
        Columna columnaObjetivo = Columna._7;
        
        Valor[][] celdas = GridFactory.getSudokuFacil1();
        List<Posicion> esperado = getPosicionesConValorColumna7();
        List<Posicion> obtenido = columnaObjetivo.getPosicionesConValor(celdas);
        
        assertEquals(esperado, obtenido);
    }
    
    private List<Posicion> getPosicionesConValorColumna7() {
        List<Posicion> posiciones = new ArrayList<>();
        posiciones.add(new Posicion(Fila._2, Columna._7, Valor._8));
        posiciones.add(new Posicion(Fila._6, Columna._7, Valor._1));
        posiciones.add(new Posicion(Fila._7, Columna._7, Valor._3));
        
        return posiciones;
    }
}
