package org.proundmega.sudokucore.solver;

import org.proundmega.sudokucore.Respuesta;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import org.proundmega.sudokucore.elementos.grid.Grid;

public class PipelineSolver {
    
    public static Pipeline<Grid, Respuesta> getPipeline(Grid gridInicial) {
        return Pipeline.crear(gridInicial, PipelineSolver.getSolvers())
                .afterStep(Respuesta::getGridRespuesta)
                .finishIf(esGridRespuesta())
                .restartPipelineIf(Respuesta::isAvanceEnResolver)
                .maxIterations(100)
                .build();
    }

    private static Predicate<Respuesta> esGridRespuesta() {
        return respuesta -> respuesta.getGridRespuesta().isGridResuelta();
    }
    
    private static Set<Function<Grid, Respuesta>> getSolvers() {
        Set<Function<Grid, Respuesta>> solvers = new HashSet<>();
        solvers.add(FactorySolver.crearSolverCuadranteSimple());
        solvers.add(FactorySolver.crearSolverFilaSimple());
        
        return solvers;
    }
}
