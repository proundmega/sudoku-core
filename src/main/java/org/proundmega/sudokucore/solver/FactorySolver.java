package org.proundmega.sudokucore.solver;

import java.util.Arrays;
import org.proundmega.sudokucore.elementos.Cuadrante;
import org.proundmega.sudokucore.elementos.Fila;
import org.proundmega.sudokucore.elementos.grid.Grid;
import org.proundmega.sudokucore.solver.procesadores.PipelineProcesadores;

public class FactorySolver {
    
    public static Solver crearSolverCuadranteSimple() {
        return new SolverCuadrante(PipelineProcesadores::getPipelineProcesadoresSimples
                , Arrays.asList(Cuadrante.values()));
    }
    
    public static Solver crearSolverFilaSimple() {
        return new SolverCuadrante(PipelineProcesadores::getPipelineProcesadoresSimples
                , Arrays.asList(Fila.values()));
    }
}
