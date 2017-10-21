package org.proundmega.sudokucore;

import org.proundmega.sudokucore.elementos.grid.Grid;
import org.proundmega.sudokucore.elementos.Celda;
import java.util.ArrayList;
import java.util.List;
import org.proundmega.sudokucore.solver.Respuesta;
import org.proundmega.sudokucore.solver.Pipeline;
import org.proundmega.sudokucore.solver.PipelineSolver;
import org.proundmega.sudokucore.solver.Solver;

public class Sudoku {
    private Grid gridActual;
    private List<Grid> gridsHistoricos;
    
    public Sudoku(Celda[][] celdas) {
        this.gridActual = new Grid(celdas);
        this.gridsHistoricos = new ArrayList<>();
    }
    
    public Grid solve() {
        Pipeline<Grid, Respuesta, Solver> pipeline = PipelineSolver.getPipeline(gridActual);
        
        return pipeline.get().getGridRespuesta();
    }

}
