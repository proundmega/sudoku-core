package org.proundmega.sudokucore;

import org.proundmega.sudokucore.elementos.grid.Grid;
import org.proundmega.sudokucore.elementos.Celda;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.proundmega.sudokucore.solver.Pipeline;
import org.proundmega.sudokucore.solver.PipelineSolver;
import org.proundmega.sudokucore.solver.Solver;

@ToString
@EqualsAndHashCode
public class Sudoku {
    private Grid gridActual;
    
    public Sudoku(Celda[][] celdas) {
        this.gridActual = new Grid(celdas);
    }
    
    public Sudoku(Grid grid) {
        this.gridActual = grid;
    }
    
    public Sudoku(String[][] grid) {
        this.gridActual = new Grid(grid);
    }
    
    public Sudoku solve() {
        Pipeline<Grid, Respuesta, Solver> pipeline = PipelineSolver.getPipeline(gridActual);
        
        return new Sudoku(pipeline.get().getGridRespuesta());
    }
    
    public List<Respuesta> solvePorPasos() {
        Pipeline<Grid, Respuesta, Solver> pipeline = PipelineSolver.getPipeline(gridActual);
        
        return pipeline.getPorPasos();
    }
    
    public Grid getGrid() {
        return gridActual;
    }
    
    public String[][] getGridAsString() {
        return gridActual.getValoresAsString();
    }
    
    public boolean estaResuelto() {
        return gridActual.isGridResuelta();
    }
}
