package org.proundmega.sudokucore.solver;

import org.proundmega.sudokucore.Grid;
import org.proundmega.sudokucore.SubGrid;
import org.proundmega.sudokucore.elementos.Cuadrante;

public class CuadranteSolver implements Solver{

    @Override
    public Grid solveCasilla(Grid gridOriginal) {
        for(Cuadrante cuadrante : Cuadrante.values()) {
            SubGrid subgrid = gridOriginal.getSubGrid(cuadrante);
            
            if(subgrid.soloFaltaUnEspacio()) {
                return subgrid.completarCuadrante();
            }
        }
        
        // TODO Testea esho
        return new Grid();
    }
    
    

    @Override
    public String getMetodoUsado() {
        return "Deduccion simple a nivel de casilla";
    }
}
