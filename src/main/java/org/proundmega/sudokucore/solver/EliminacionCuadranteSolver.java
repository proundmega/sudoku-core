package org.proundmega.sudokucore.solver;

import org.proundmega.sudokucore.Respuesta;
import java.util.Optional;
import org.proundmega.sudokucore.MetadataSolver;
import org.proundmega.sudokucore.elementos.Cuadrante;
import org.proundmega.sudokucore.elementos.grid.Grid;
import org.proundmega.sudokucore.elementos.grid.SubGridCuadrante;
import org.proundmega.sudokucore.elementos.grid.anotador.Anotador;
import org.proundmega.sudokucore.solver.procesadores.PipelineProcesadores;
import org.proundmega.sudokucore.solver.procesadores.ProcesadorAnotaciones;

public class EliminacionCuadranteSolver extends AbstractSolverCuadrante {

    @Override
    protected Pipeline<Anotador, Optional<MetadataSolver>, ProcesadorAnotaciones> getPipeline(Anotador anotador) {
        return PipelineProcesadores.getPipelineProcesadoresSimples(anotador);
    }

    @Override
    public String getMetodoUsado() {
        return "Reductor";
    }
}
