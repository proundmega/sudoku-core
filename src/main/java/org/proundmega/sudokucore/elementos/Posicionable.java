package org.proundmega.sudokucore.elementos;

import java.util.List;
import org.proundmega.sudokucore.Posicion;

public interface Posicionable {
    List<Posicion> getPosiciones(Celda[][] celdas);
}
