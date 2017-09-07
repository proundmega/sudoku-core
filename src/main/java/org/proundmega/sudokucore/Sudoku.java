package org.proundmega.sudokucore;

import java.util.ArrayList;
import java.util.List;

public class Sudoku {
    private Grid gridActual;
    private List<Grid> gridsHistoricos;
    
    public Sudoku(Celda[][] celdas) {
        this.gridActual = new Grid(celdas);
        this.gridsHistoricos = new ArrayList<>();
    }
    
    public void solve() {
        int intentos = 100;
        while(sigoIntentandoResolverGrid(intentos)) {
            Grid nuevaGrid = gridActual.resolverCasilla();
            gridsHistoricos.add(gridActual);
            gridActual = nuevaGrid;
            
            intentos--;
        }
    }

    private boolean sigoIntentandoResolverGrid(int intentos) {
        return intentos > 0 && !gridActual.isGridResuelta();
    }
}
