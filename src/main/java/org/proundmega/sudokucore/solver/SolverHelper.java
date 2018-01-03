package org.proundmega.sudokucore.solver;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import org.proundmega.sudokucore.elementos.Celda;
import org.proundmega.sudokucore.elementos.Cuadrante;
import org.proundmega.sudokucore.elementos.Valor;

public class SolverHelper {
    
    public static boolean soloFaltaUnEspacio(Celda[] celdas) {
        return Arrays.stream(celdas)
                .filter(Celda::estaVacia)
                .count() == 1;
    }
    
}
