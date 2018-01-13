package org.proundmega.sudokucore;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MetadataSolver {
    private Posicion posicionResuelta;
    private List<Posicion> celdasQueLimitanValor = new ArrayList<>();
    private List<Posicion> celdasLimitantesEnBloque = new ArrayList<>();

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
