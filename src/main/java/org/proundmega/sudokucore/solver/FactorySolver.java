package org.proundmega.sudokucore.solver;

import java.util.Arrays;
import org.proundmega.sudokucore.elementos.Columna;
import org.proundmega.sudokucore.elementos.Cuadrante;
import org.proundmega.sudokucore.elementos.Fila;
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
    
    public static Solver crearSolverColumnaSimple() {
        return new SolverCuadrante(PipelineProcesadores::getPipelineProcesadoresSimples
                , Arrays.asList(Columna.values()));
    }
    
    public static Solver crearSolverCuadranteIntermedio() {
        return new SolverCuadrante(PipelineProcesadores::getPipelineProcesadoresIntermedios
                , Arrays.asList(Cuadrante.values()));
    }
    
    public static Solver crearSolverFilaIntermedio() {
        return new SolverCuadrante(PipelineProcesadores::getPipelineProcesadoresIntermedios
                , Arrays.asList(Fila.values()));
    }
    
    public static Solver crearSolverColumnaIntermedio() {
        return new SolverCuadrante(PipelineProcesadores::getPipelineProcesadoresIntermedios
                , Arrays.asList(Columna.values()));
    }
}
