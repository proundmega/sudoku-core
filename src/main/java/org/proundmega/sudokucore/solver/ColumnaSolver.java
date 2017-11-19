package org.proundmega.sudokucore.solver;

import org.proundmega.sudokucore.Respuesta;
import org.proundmega.sudokucore.elementos.Columna;
import org.proundmega.sudokucore.elementos.grid.Grid;
import org.proundmega.sudokucore.elementos.grid.SubGrid;

public class ColumnaSolver implements Solver {

    @Override
    public Respuesta apply(Grid gridOriginal) {
        for(Columna columna: Columna.values()) {
            SubGrid subgrid = gridOriginal.getSubGrid(columna);
            
            if(subgrid.soloFaltaUnEspacio()) {
                Grid respuesta = subgrid.completarSubGrid();
                return new Respuesta(respuesta, true, this);
            }
        }
        
        return new Respuesta(gridOriginal, false, this);
    }

    @Override
    public String getMetodoUsado() {
        return "Completar columna";
    }
    
}
