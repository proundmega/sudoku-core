package org.proundmega.sudokucore.solver;

import org.proundmega.sudokucore.Respuesta;
import org.proundmega.sudokucore.elementos.grid.Grid;

public class NullSolver implements Solver {

    @Override
    public Respuesta apply(Grid gridOriginal) {
        return new Respuesta(gridOriginal, false, new NullSolver());
    }

    @Override
    public String getMetodoUsado() {
        return "Solver vacio";
    }
    
}
