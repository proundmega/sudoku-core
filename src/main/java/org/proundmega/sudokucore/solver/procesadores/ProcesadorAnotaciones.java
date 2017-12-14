package org.proundmega.sudokucore.solver.procesadores;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import org.proundmega.sudokucore.MetadataSolver;
import org.proundmega.sudokucore.Posicion;
import org.proundmega.sudokucore.elementos.grid.anotador.Anotador;

public interface ProcesadorAnotaciones extends Function<Anotador, Optional<MetadataSolver>> {
    @Override
    public Optional<MetadataSolver> apply(Anotador t);
}
