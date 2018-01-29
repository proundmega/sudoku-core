package org.proundmega.sudokucore.solver;

import org.proundmega.sudokucore.Posicion;
import org.proundmega.sudokucore.Respuesta;
import org.proundmega.sudokucore.elementos.Columna;
import org.proundmega.sudokucore.elementos.Fila;
import org.proundmega.sudokucore.elementos.Grid;

public class NullSolver implements Solver {

    @Override
    public Respuesta apply(Grid gridOriginal) {
        return new Respuesta(gridOriginal, false, new NullSolver());
    }

}
