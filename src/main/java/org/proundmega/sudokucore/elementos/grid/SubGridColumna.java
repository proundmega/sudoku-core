package org.proundmega.sudokucore.elementos.grid;

import java.util.List;
import org.proundmega.sudokucore.Posicion;
import org.proundmega.sudokucore.elementos.Celda;
import org.proundmega.sudokucore.elementos.Columna;
import org.proundmega.sudokucore.elementos.ValidadorSudoku;
import org.proundmega.sudokucore.solver.SolverHelper;

public class SubGridColumna extends AbstractSubGrid {
    private Columna columnaActual;
    
    public SubGridColumna(Celda[][] celdas, Columna columnaActual) {
        this.celdas = celdas;
        this.columnaActual = columnaActual;
    }
    
    @Override
    protected List<Posicion> getPosiciones() {
        return columnaActual.getColumnaAsList(celdas);
    }

    @Override
    public boolean estaCompleta() {
        return ValidadorSudoku.esColumnaCompleta(celdas, columnaActual);
    }

    @Override
    public boolean soloFaltaUnEspacio() {
        return SolverHelper.soloFaltaUnEspacio(columnaActual.getColumna(celdas));
    }
    
}
