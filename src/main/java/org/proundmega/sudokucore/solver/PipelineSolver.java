package org.proundmega.sudokucore.solver;

import java.util.ArrayList;
import org.proundmega.sudokucore.Respuesta;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import org.proundmega.sudokucore.elementos.Grid;

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
    
    private static List<Function<Grid, Respuesta>> getSolvers() {
        List<Function<Grid, Respuesta>> solvers = new ArrayList<>();
        solvers.add(FactorySolver.crearSolverCuadranteSimple());
        solvers.add(FactorySolver.crearSolverFilaSimple());
        solvers.add(FactorySolver.crearSolverColumnaSimple());
        
        solvers.add(FactorySolver.crearSolverCuadranteIntermedio());
        solvers.add(FactorySolver.crearSolverFilaIntermedio());
        solvers.add(FactorySolver.crearSolverColumnaIntermedio());
        return solvers;
    }
}
