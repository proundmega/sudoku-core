package org.proundmega.sudokucore.solver.procesadores;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import org.proundmega.sudokucore.MetadataSolver;
import org.proundmega.sudokucore.elementos.anotador.Anotador;
import org.proundmega.sudokucore.solver.Pipeline;

public class PipelineProcesadores {
    
    public static Pipeline<Anotador, Optional<MetadataSolver>> getPipelineProcesadoresSimples(
            Anotador anotador) {
        return crearPipeline(anotador, getProcesadoresSimples());
    }
    
    private static Pipeline<Anotador, Optional<MetadataSolver>> crearPipeline(Anotador anotador, List<Function<Anotador, Optional<MetadataSolver>>> procesadores) {
        return Pipeline.crear(anotador, procesadores)
                .afterStep(intercambio -> anotador)
                .finishIf(Optional::isPresent)
                .maxIterations(1)
                .build();
    }
    
    private static List<Function<Anotador, Optional<MetadataSolver>>> getProcesadoresSimples() {
        List<Function<Anotador, Optional<MetadataSolver>>> procesadores = new ArrayList<>();
        procesadores.add(new ValorFaltante());
        procesadores.add(new ValorConUnicaPosicion());
        
        return procesadores;
    }
    
    public static Pipeline<Anotador, Optional<MetadataSolver>> getPipelineProcesadoresIntermedios(
            Anotador anotador) {
        return crearPipeline(anotador, getProcesadoresIntermedios());
    }
    
    private static List<Function<Anotador, Optional<MetadataSolver>>> getProcesadoresIntermedios() {
        List<Function<Anotador, Optional<MetadataSolver>>> procesadores = new ArrayList<>();
        procesadores.add(new CeldaConUnicaPosicion());
        
        return procesadores;
    }
}
