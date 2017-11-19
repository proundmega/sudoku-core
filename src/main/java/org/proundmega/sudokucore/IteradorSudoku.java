package org.proundmega.sudokucore;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;
import org.proundmega.sudokucore.elementos.grid.Grid;
import org.proundmega.sudokucore.solver.NullSolver;

@Value
public class IteradorSudoku {
    private List<Respuesta> pasos;
    private boolean existeValor;
    private Respuesta actual;

    @Getter(AccessLevel.NONE)
    private ListIterator<Respuesta> iterador;

    public IteradorSudoku(List<Respuesta> pasos) {
        this.pasos = pasos;
        this.existeValor = true;
        this.iterador = pasos.listIterator();
        this.actual = iterador.next();
    }

    public IteradorSudoku() {
        this.pasos = new ArrayList<>();
        this.pasos.add(new Respuesta(new Grid(), false, new NullSolver()));
        this.existeValor = false;
        this.iterador = pasos.listIterator();
        this.actual = iterador.next();
    }

    public boolean hasNext() {
        return iterador.hasNext();
    }
}
