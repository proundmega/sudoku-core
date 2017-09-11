package org.proundmega.sudokucore.elementos.grid;

import java.util.List;
import org.proundmega.sudokucore.Posicion;
import org.proundmega.sudokucore.elementos.Celda;
import org.proundmega.sudokucore.elementos.Fila;
import org.proundmega.sudokucore.elementos.ValidadorSudoku;
import org.proundmega.sudokucore.solver.SolverHelper;

public class SubGridFila extends AbstractSubGrid {
    private Fila filaActual;
    
    public SubGridFila(Celda[][] celdas, Fila filaActual) {
        this.celdas = celdas;
        this.filaActual = filaActual;
    }
    
    @Override
    protected List<Posicion> getPosiciones() {
        return filaActual.getfilaAsList(celdas);
    }

    @Override
    public boolean estaCompleta() {
        return ValidadorSudoku.esFilaCompleta(celdas, filaActual);
    }

    @Override
    public boolean soloFaltaUnEspacio() {
        return SolverHelper.soloFaltaUnEspacio(filaActual.getFila(celdas));
    }
    
}
