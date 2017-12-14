package org.proundmega.sudokucore.solver;

import org.proundmega.sudokucore.MetadataSolver;
import org.proundmega.sudokucore.Posicion;
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
                MetadataSolver respuesta = subgrid.completarSubGrid();
                return new Respuesta(respuesta.getGridResuelta(), true, this, respuesta);
            }
        }
        
        return new Respuesta(gridOriginal, false, this);
    }

    @Override
    public String getMetodoUsado() {
        return "Completar columna";
    }

}
