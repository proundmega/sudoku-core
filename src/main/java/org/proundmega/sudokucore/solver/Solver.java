package org.proundmega.sudokucore.solver;

import java.util.function.Function;
import org.proundmega.sudokucore.elementos.grid.Grid;

public interface Solver extends Function<Grid, Respuesta>{
    Respuesta apply(Grid gridOriginal);
    String getMetodoUsado();
}
