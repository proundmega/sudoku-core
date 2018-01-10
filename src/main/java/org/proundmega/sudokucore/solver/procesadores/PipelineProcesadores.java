package org.proundmega.sudokucore.solver.procesadores;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import org.proundmega.sudokucore.MetadataSolver;
import org.proundmega.sudokucore.elementos.anotador.Anotador;
import org.proundmega.sudokucore.solver.Pipeline;

public class PipelineProcesadores {
    
    public static Pipeline<Anotador, Optional<MetadataSolver>> getPipelineProcesadoresSimples(
            Anotador anotador) {
        return Pipeline.crear(anotador, getProcesadores())
                .afterStep(intercambio -> anotador)
                .finishIf(Optional::isPresent)
                .maxIterations(1)
                .build();
    }
    
    private static Set<Function<Anotador, Optional<MetadataSolver>>> getProcesadores() {
        Set<Function<Anotador, Optional<MetadataSolver>>> procesadores = new HashSet<>();
        procesadores.add(new ValorFaltante());
        procesadores.add(new CeldaConUnicaPosicion());
        procesadores.add(new ValorConUnicaPosicion());
        
        return procesadores;
    }
}
