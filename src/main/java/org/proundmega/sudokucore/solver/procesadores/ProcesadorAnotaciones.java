package org.proundmega.sudokucore.solver.procesadores;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import org.proundmega.sudokucore.Posicion;

public interface ProcesadorAnotaciones extends Function<List<Posicion>, Optional<Intercambio>> {
    @Override
    public Optional<Intercambio> apply(List<Posicion> t);
}
