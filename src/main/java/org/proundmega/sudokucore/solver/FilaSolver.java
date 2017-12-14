package org.proundmega.sudokucore.solver;

import org.proundmega.sudokucore.MetadataSolver;
import org.proundmega.sudokucore.Respuesta;
import org.proundmega.sudokucore.elementos.grid.Grid;
import org.proundmega.sudokucore.elementos.Fila;
import org.proundmega.sudokucore.elementos.grid.SubGrid;

public class FilaSolver implements Solver {

    @Override
    public Respuesta apply(Grid gridOriginal) {
        for(Fila fila: Fila.values()) {
            SubGrid subgrid = gridOriginal.getSubGrid(fila);
            
            if(subgrid.soloFaltaUnEspacio()) {
                MetadataSolver respuesta = subgrid.completarSubGrid();
                return new Respuesta(respuesta.getGridResuelta(), true, this, respuesta);
            }
        }
        
        return new Respuesta(gridOriginal, false, this);
    }

    @Override
    public String getMetodoUsado() {
        return "Completar fila";
    }
    
}
