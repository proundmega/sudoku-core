package org.proundmega.sudokucore.elementos;

import java.util.List;
import org.proundmega.sudokucore.Posicion;

public interface Posicionable {
    List<Posicion> getPosiciones(Celda[][] celdas);
    List<Posicion> getPosicionesVacias(Celda[][] celdas);
    List<Posicion> getPosicionesConValor(Celda[][] celdas);
    
}
