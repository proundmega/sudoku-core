package org.proundmega.sudokucore.solver;

import org.proundmega.sudokucore.Respuesta;
import java.util.function.Function;
import org.proundmega.sudokucore.elementos.grid.Grid;

public interface Solver extends Function<Grid, Respuesta>{
    Respuesta apply(Grid gridOriginal);
    String getMetodoUsado();
}
