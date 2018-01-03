package org.proundmega.sudokucore.solver;

import org.proundmega.sudokucore.Respuesta;
import java.util.Optional;
import org.proundmega.sudokucore.MetadataSolver;
import org.proundmega.sudokucore.elementos.grid.Grid;
import org.proundmega.sudokucore.elementos.grid.anotador.Anotador;
import org.proundmega.sudokucore.solver.procesadores.PipelineProcesadores;

public class EliminacionCuadranteSolver implements Solver {

    @Override
    public Respuesta apply(Grid gridOriginal) {
        return new SolverCuadrante(PipelineProcesadores::getPipelineProcesadoresSimples)
                .apply(gridOriginal);
    }

    @Override
    public String getMetodoUsado() {
        return "Reductor";
    }
}
