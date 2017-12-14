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
    private MetadataSolver metadatos;

    public Respuesta(Grid gridRespuesta, boolean avanceEnResolver, Solver solverUsado) {
        this.gridRespuesta = gridRespuesta;
        this.avanceEnResolver = avanceEnResolver;
        this.solverUsado = solverUsado;
        this.metadatos = new MetadataSolver();
    }
    
    public Sudoku getSudoku() {
        return new Sudoku(gridRespuesta);
    }
}
