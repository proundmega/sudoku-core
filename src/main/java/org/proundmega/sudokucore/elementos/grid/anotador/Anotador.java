package org.proundmega.sudokucore.elementos.grid.anotador;

import java.util.List;
import org.proundmega.sudokucore.Posicion;
import org.proundmega.sudokucore.elementos.Valor;

public interface Anotador {
    List<Posicion> getPosicionesConAnotacionesRemovidas();
    List<Posicion> getPosicionesQueRemuevenValoresDeValor(Valor valor);
}
