package org.proundmega.sudokucore;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.proundmega.sudokucore.elementos.grid.Grid;

@Data
@AllArgsConstructor
public class MetadataSolver {
    private Posicion posicionResuelta;
    private List<Posicion> celdasQueLimitanValor = new ArrayList<>();
    private List<Posicion> celdasLimitantesEnCuadrante = new ArrayList<>();

    public MetadataSolver(Posicion posicionResuelta) {
        this.posicionResuelta = posicionResuelta;
    }

    public MetadataSolver() {
    }
    
    public MetadataSolver(Posicion posicionResuela, List<Posicion> celdasQueLimitanValor) {
        this.posicionResuelta = posicionResuela;
        this.celdasQueLimitanValor = celdasQueLimitanValor;
    }
}
