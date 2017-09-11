package org.proundmega.sudokucore.solver;

import org.proundmega.sudokucore.elementos.grid.Grid;

public interface Solver {
    Respuesta solveCasilla(Grid gridOriginal);
    String getMetodoUsado();
}
