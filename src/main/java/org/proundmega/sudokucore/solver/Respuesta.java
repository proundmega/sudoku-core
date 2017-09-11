package org.proundmega.sudokucore.solver;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.proundmega.sudokucore.elementos.grid.Grid;

@Value
@AllArgsConstructor
public class Respuesta {
    private Grid gridRespuesta;
    private boolean avanceEnResolver;
    private Solver solverUsado;
}
