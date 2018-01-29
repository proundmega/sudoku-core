package org.proundmega.sudokucore.elementos;

import java.util.List;
import org.proundmega.sudokucore.Posicion;

public interface Posicionable {
    List<Posicion> getPosiciones(Valor[][] celdas);
    List<Posicion> getPosicionesVacias(Valor[][] celdas);
    List<Posicion> getPosicionesConValor(Valor[][] celdas);
    
}
