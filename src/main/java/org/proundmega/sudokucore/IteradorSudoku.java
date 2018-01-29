package org.proundmega.sudokucore;

import org.proundmega.sudokucore.elementos.Grid;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.proundmega.sudokucore.solver.NullSolver;

@Data
public class IteradorSudoku {

    @Setter(AccessLevel.NONE)
    private List<Respuesta> pasos;

    @Setter(AccessLevel.NONE)
    private boolean existeValor;

    @Setter(AccessLevel.NONE)
    private Respuesta actual;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private int posicionActual;

    public IteradorSudoku(List<Respuesta> pasos) {
        this.pasos = pasos;
        this.existeValor = true;
        this.actual = pasos.get(0);
        this.posicionActual = 0;
    }

    public IteradorSudoku() {
        this.pasos = new ArrayList<>();
        this.pasos.add(new Respuesta(new Grid(), false, new NullSolver(), new MetadataSolver()));
        this.existeValor = false;
        this.actual = pasos.get(0);
        this.posicionActual = 0;
    }

    public boolean haySiguiente() {
        return posicionActual < pasos.size() - 1;
    }

    public void siguiente() {
        if(haySiguiente()) {
            posicionActual++;
            actual = pasos.get(posicionActual);
        }
    }

    public boolean hayPrevio() {
        return posicionActual > 0;
    }

    public void anterior() {
        if(hayPrevio()) {
            posicionActual--;
            actual = pasos.get(posicionActual);
        }
    }
    
    public void inicio() {
        posicionActual = 0;
        actual = pasos.get(posicionActual);
    }
    
    public void ultimo() {
        posicionActual = pasos.size() - 1;
        actual = pasos.get(posicionActual);
    }
}
