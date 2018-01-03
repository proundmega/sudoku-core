package org.proundmega.sudokucore.solver;

import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;
import org.proundmega.sudokucore.data.GridFactory;
import org.proundmega.sudokucore.elementos.Celda;
import org.proundmega.sudokucore.elementos.Cuadrante;
import org.proundmega.sudokucore.elementos.Valor;
import static org.proundmega.sudokucore.elementos.Valor.*;

public class SolverHelperTest {

    @Test
    public void testSoloFaltaUnEspacioCorrecto() {
        Celda[] celdas = GridFactory.crearCeldas(_1, _2, VACIA);
        
        assertTrue(SolverHelper.soloFaltaUnEspacio(celdas));
    }
    
    @Test
    public void testSoloFaltaUnEspacioFaltaMasDeUnEspacio() {
        Celda[] celdas = GridFactory.crearCeldas(_1, VACIA, VACIA);
        
        assertFalse(SolverHelper.soloFaltaUnEspacio(celdas));
    }
    
    @Test
    public void testSoloFaltaUnEspacioNoFaltanEspacios() {
        Celda[] celdas = GridFactory.crearCeldas(_1, _2, _3);
        
        assertFalse(SolverHelper.soloFaltaUnEspacio(celdas));
    }

}
