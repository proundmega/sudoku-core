package org.proundmega.sudokucore;

import org.proundmega.sudokucore.elementos.Grid;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.proundmega.sudokucore.elementos.Valor;
import org.proundmega.sudokucore.solver.Pipeline;
import org.proundmega.sudokucore.solver.PipelineSolver;

@ToString
@EqualsAndHashCode
public class Sudoku {
    private Grid gridActual;
    
    public Sudoku(Valor[][] valores) {
        this.gridActual = new Grid(valores);
    }
    
    public Sudoku(Grid grid) {
        this.gridActual = grid;
    }
    
    public Sudoku(String[][] grid) {
        this.gridActual = new Grid(grid);
    }
    
    public Sudoku solve() {
        Pipeline<Grid, Respuesta> pipeline = PipelineSolver.getPipeline(gridActual);
        
        return new Sudoku(pipeline.get().getGridRespuesta());
    }
    
    public List<Respuesta> solvePorPasos() {
        Pipeline<Grid, Respuesta> pipeline = PipelineSolver.getPipeline(gridActual);
        
        return pipeline.getPorPasos();
    }
    
    public IteradorSudoku solveAsIterador() {
        return new IteradorSudoku(solvePorPasos());
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
