package org.proundmega.sudokucore.solver;

import java.util.Arrays;
import org.proundmega.sudokucore.elementos.Celda;

public class SolverHelper {
    
    public static boolean soloFaltaUnEspacio(Celda[] celdas) {
        return Arrays.stream(celdas)
                .filter(Celda::estaVacia)
                .count() == 1;
    }
}
