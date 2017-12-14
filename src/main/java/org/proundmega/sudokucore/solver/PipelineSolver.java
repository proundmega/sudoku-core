package org.proundmega.sudokucore.solver;

import org.proundmega.sudokucore.Respuesta;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import org.proundmega.sudokucore.elementos.grid.Grid;

public class PipelineSolver {
    
    public static Pipeline<Grid, Respuesta, Solver> getPipeline(Grid gridInicial) {
        return Pipeline.crear(gridInicial, PipelineSolver.getSolvers())
                .afterStep(respuesta -> respuesta.getGridRespuesta())
                .finishIf(respuesta -> respuesta.getGridRespuesta().isGridResuelta())
                .restartPipelineIf(respuesta -> respuesta.isAvanceEnResolver())
                .maxIterations(100)
                .build();
    }
    
    private static Set<Function<Grid, Respuesta>> getSolvers() {
        Set<Function<Grid, Respuesta>> solvers = new HashSet<>();
        solvers.add(new CuadranteSolver());
        solvers.add(new FilaSolver());
        solvers.add(new ColumnaSolver());
        solvers.add(new EliminacionCuadranteSolver());
        
        return solvers;
    }
}
