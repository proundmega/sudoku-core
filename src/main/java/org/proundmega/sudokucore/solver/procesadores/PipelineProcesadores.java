package org.proundmega.sudokucore.solver.procesadores;

import java.util.List;
import java.util.Optional;
import org.proundmega.sudokucore.MetadataSolver;
import org.proundmega.sudokucore.Posicion;
import org.proundmega.sudokucore.elementos.grid.anotador.Anotador;
import org.proundmega.sudokucore.solver.Pipeline;

public class PipelineProcesadores {
    
    public static Pipeline<Anotador, Optional<MetadataSolver>, ProcesadorAnotaciones> getPipelineProcesadoresSimples(
            Anotador anotador) {
        return Pipeline.crear(anotador, ProcesadorAnotaciones.class)
                .addStep(new SoloUnaCasilla())
                .addStep(new CeldaConUnicaPosicion())
                .addStep(new ValorConUnicaPosicion())
                .afterStep(intercambio -> anotador)
                .finishIf(Optional::isPresent)
                .maxIterations(1)
                .build();
    }
}
