package org.proundmega.sudokucore;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PosicionBundle {
    private Posicion posicionResuelta;
    private List<Posicion> posicionesQueLimitanValor = new ArrayList<>();
    private List<Posicion> posicionesLimitantesEnBloque = new ArrayList<>();

    public PosicionBundle() {
    }
}
