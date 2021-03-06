package org.proundmega.sudokucore.solver;

import org.proundmega.sudokucore.Respuesta;
import java.util.function.Function;
import org.proundmega.sudokucore.MetadataSolver;
import org.proundmega.sudokucore.Posicion;
import org.proundmega.sudokucore.elementos.Grid;

public interface Solver extends Function<Grid, Respuesta> {
    Respuesta apply(Grid gridOriginal);
}
