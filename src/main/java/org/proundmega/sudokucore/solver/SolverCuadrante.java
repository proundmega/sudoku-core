package org.proundmega.sudokucore.solver;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import org.proundmega.sudokucore.MetadataSolver;
import org.proundmega.sudokucore.Respuesta;
import org.proundmega.sudokucore.elementos.Cuadrante;
import org.proundmega.sudokucore.elementos.Posicionable;
import org.proundmega.sudokucore.elementos.grid.Grid;
import org.proundmega.sudokucore.elementos.anotador.Anotador;

public class SolverCuadrante implements Solver {
    private List<Posicionable> posicionables;
    private Function<Anotador, Pipeline<Anotador, Optional<MetadataSolver>>> pipeline;

    public SolverCuadrante(Function<Anotador, Pipeline<Anotador, Optional<MetadataSolver>>> pipeline, List<Posicionable> posicionables) {
        this.pipeline = pipeline;
        this.posicionables = posicionables;
    }

    @Override
    public Respuesta apply(Grid gridOriginal) {
        for (Posicionable posicionable : posicionables) {
            Optional<MetadataSolver> respuesta = resolverCuadrante(gridOriginal, posicionable);

            if (respuesta.isPresent()) {
                MetadataSolver metadata = respuesta.get();
                Grid gridRespuesta = gridOriginal.reemplazarCasilla(metadata.getPosicionResuelta());
                return new Respuesta(gridRespuesta, true, this, respuesta.get());
            }
        }

        return new Respuesta(gridOriginal, false, this);
    }

    private Optional<MetadataSolver> resolverCuadrante(Grid grid, Posicionable posicionable) {
        Anotador anotador = grid.getAnotador(posicionable);

        Pipeline<Anotador, Optional<MetadataSolver>> pipelineObtenido
                = pipeline.apply(anotador);

        Optional<MetadataSolver> respuesta = pipelineObtenido.get();
        return respuesta;
    }

}
