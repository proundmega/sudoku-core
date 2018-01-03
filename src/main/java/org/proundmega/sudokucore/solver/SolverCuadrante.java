package org.proundmega.sudokucore.solver;

import java.util.Optional;
import java.util.function.Function;
import org.proundmega.sudokucore.MetadataSolver;
import org.proundmega.sudokucore.Respuesta;
import org.proundmega.sudokucore.elementos.Cuadrante;
import org.proundmega.sudokucore.elementos.grid.Grid;
import org.proundmega.sudokucore.elementos.grid.SubGridCuadrante;
import org.proundmega.sudokucore.elementos.grid.anotador.Anotador;
import org.proundmega.sudokucore.solver.procesadores.PipelineProcesadores;
import org.proundmega.sudokucore.solver.procesadores.ProcesadorAnotaciones;

public class SolverCuadrante implements Solver {
    private Function<Anotador, Pipeline<Anotador, Optional<MetadataSolver>>> pipeline;

    public SolverCuadrante(Function<Anotador, Pipeline<Anotador, Optional<MetadataSolver>>> pipeline) {
        this.pipeline = pipeline;
    }

    @Override
    public Respuesta apply(Grid gridOriginal) {
        for (Cuadrante cuadrante : Cuadrante.values()) {
            Optional<MetadataSolver> respuesta = resolverCuadrante(gridOriginal, cuadrante);

            if (respuesta.isPresent()) {
                MetadataSolver metadata = respuesta.get();
                Grid gridRespuesta = gridOriginal.reemplazarCasilla(metadata.getPosicionResuelta());
                return new Respuesta(gridRespuesta, true, this, respuesta.get());
            }
        }

        return new Respuesta(gridOriginal, false, this);
    }

    private Optional<MetadataSolver> resolverCuadrante(Grid grid, Cuadrante cuadrante) {
        SubGridCuadrante subGrid = grid.getSubGrid(cuadrante);
        Anotador anotador = subGrid.getAnotador();

        Pipeline<Anotador, Optional<MetadataSolver>> pipelineProcesadoresSimples
                = pipeline.apply(anotador);

        Optional<MetadataSolver> respuesta = pipelineProcesadoresSimples.get();
        return respuesta;
    }

    @Override
    public String getMetodoUsado() {
        return "Pronto elimino esto";
    }
    
}
