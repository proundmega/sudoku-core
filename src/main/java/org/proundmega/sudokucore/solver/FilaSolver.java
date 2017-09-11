package org.proundmega.sudokucore.solver;

import org.proundmega.sudokucore.elementos.grid.Grid;
import org.proundmega.sudokucore.elementos.Fila;
import org.proundmega.sudokucore.elementos.grid.SubGrid;

public class FilaSolver implements Solver {

    @Override
    public Respuesta solveCasilla(Grid gridOriginal) {
        for(Fila fila: Fila.values()) {
            SubGrid subgrid = gridOriginal.getSubGrid(fila);
            
            if(subgrid.soloFaltaUnEspacio()) {
                Grid respuesta = subgrid.completarSubGrid();
                return new Respuesta(respuesta, true, this);
            }
        }
        
        return new Respuesta(gridOriginal, false, this);
    }

    @Override
    public String getMetodoUsado() {
        return "Completar fila";
    }
    
}
