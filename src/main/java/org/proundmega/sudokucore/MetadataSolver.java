package org.proundmega.sudokucore;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.proundmega.sudokucore.elementos.grid.Grid;

@Data
@AllArgsConstructor
public class MetadataSolver {
    private Grid gridResuelta;
    private Posicion posicionResuelta;
    private List<Posicion> celdasQueLimitanValor;

    public MetadataSolver(Grid gridResuelta, Posicion posicionResuelta) {
        this.gridResuelta = gridResuelta;
        this.posicionResuelta = posicionResuelta;
        this.celdasQueLimitanValor = new ArrayList<>();
    }

    public MetadataSolver() {
    }
}
