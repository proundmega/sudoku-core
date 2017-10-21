package org.proundmega.sudokucore.solver;

import org.proundmega.sudokucore.elementos.grid.Grid;
import org.proundmega.sudokucore.elementos.grid.SubGridCuadrante;
import org.proundmega.sudokucore.elementos.Cuadrante;

public class CuadranteSolver implements Solver{

    @Override
    public Respuesta apply(Grid gridOriginal) {
        for(Cuadrante cuadrante : Cuadrante.values()) {
            SubGridCuadrante subgrid = gridOriginal.getSubGrid(cuadrante);
            
            if(subgrid.soloFaltaUnEspacio()) {
                Grid respuesta = subgrid.completarSubGrid();
                return new Respuesta(respuesta, true, this);
            }
        }
        // Si estoy aqui no resolvi nada
        return new Respuesta(gridOriginal, false, this);
    }
    
    

    @Override
    public String getMetodoUsado() {
        return "Deduccion simple a nivel de casilla";
    }
}
