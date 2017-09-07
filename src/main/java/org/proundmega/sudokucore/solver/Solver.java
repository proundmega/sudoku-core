package org.proundmega.sudokucore.solver;

import org.proundmega.sudokucore.Grid;

public interface Solver {
    Grid solveCasilla(Grid gridOriginal);
    String getMetodoUsado();
}
