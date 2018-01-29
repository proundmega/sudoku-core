package org.proundmega.sudokucore.elementos;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.proundmega.sudokucore.Posicion;
import org.proundmega.sudokucore.data.GridFactory;

public class FilaTest {
    
    @Test
    public void filaRetornadaCorrectamente() {
        int fila = 3;
        Fila esperado = Fila._3;
        
        Fila obtenido = Fila.toFila(fila);
        
        assertEquals(esperado, obtenido);
    }
    
    @Test
    public void obtenerFilaAsListCorrectamente() {
        Fila fila = Fila._7;
        
        Valor[][] celda = GridFactory.getSudokuFacil1Resuelto();
        List<Posicion> posiciones = getListaFila7(celda);
        List<Posicion> obtenidos = fila.getPosiciones(celda);
        
        assertEquals(posiciones, obtenidos);
    }
    
    private List<Posicion> getListaFila7(Valor[][] celdas) {
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
    public void getPosicionesVaciasCorrectas() {
        Valor[][] celdas = GridFactory.getSudokuFacil1();
        Fila filaObjetivo = Fila._1;
        
        List<Posicion> esperado = getPosicionesVaciasFila1();
        List<Posicion> obtenido = filaObjetivo.getPosicionesVacias(celdas);
        
        assertEquals(esperado, obtenido);
    }
    
    private List<Posicion> getPosicionesVaciasFila1() {
        List<Posicion> posicionesVacias = new ArrayList<>();
        posicionesVacias.add(new Posicion(Fila._1, Columna._1, Valor.VACIA));
        posicionesVacias.add(new Posicion(Fila._1, Columna._3, Valor.VACIA));
        posicionesVacias.add(new Posicion(Fila._1, Columna._4, Valor.VACIA));
        posicionesVacias.add(new Posicion(Fila._1, Columna._6, Valor.VACIA));
        posicionesVacias.add(new Posicion(Fila._1, Columna._7, Valor.VACIA));
        posicionesVacias.add(new Posicion(Fila._1, Columna._8, Valor.VACIA));
        
        return posicionesVacias;
    }
    
    @Test
    public void getPosicionesLlenasCorrectas() {
        Valor[][] celdas = GridFactory.getSudokuFacil1();
        Fila filaObjetivo = Fila._6;
        
        List<Posicion> esperado = getPosicionesLlenasFila6();
        List<Posicion> obtenido = filaObjetivo.getPosicionesConValor(celdas);
        
        assertEquals(esperado, obtenido);
    }
    
    private List<Posicion> getPosicionesLlenasFila6() {
        List<Posicion> posicionesLlenas = new ArrayList<>();
        posicionesLlenas.add(new Posicion(Fila._6, Columna._7, Valor._1));
        
        return posicionesLlenas;
    }
}
