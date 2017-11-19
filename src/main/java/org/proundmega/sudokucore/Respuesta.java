package org.proundmega.sudokucore;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.proundmega.sudokucore.elementos.grid.Grid;
import org.proundmega.sudokucore.solver.Solver;

@Value
@AllArgsConstructor
public class Respuesta {
    private Grid gridRespuesta;
    private boolean avanceEnResolver;
    private Solver solverUsado;
}
