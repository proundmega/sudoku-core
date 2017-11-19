package org.proundmega.sudokucore.solver.procesadores;

import java.util.List;
import java.util.Optional;
import org.proundmega.sudokucore.Posicion;
import org.proundmega.sudokucore.solver.Pipeline;

public class PipelineProcesadores {
    
    public static Pipeline<List<Posicion>, Optional<Intercambio>, ProcesadorAnotaciones> getPipelineProcesadoresSimples(
            List<Posicion> posicionesVacias) {
        return Pipeline.crear(posicionesVacias, ProcesadorAnotaciones.class)
                .addStep(new CeldaConUnicaPosicion())
                .addStep(new ValorConUnicaPosicion())
                .afterStep(intercambio -> posicionesVacias)
                .finishIf(intercambio -> intercambio.isPresent())
                .maxIterations(10)
                .build();
    }
    
}
