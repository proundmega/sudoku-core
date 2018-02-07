package org.proundmega.sudokucore.elementos;

import java.util.List;
import java.util.Locale;
import org.proundmega.sudokucore.Posicion;
import org.proundmega.sudokucore.internacionalization.I18NUtils;

public interface Posicionable {
    List<Posicion> getPosiciones(Valor[][] celdas);
    List<Posicion> getPosicionesVacias(Valor[][] celdas);
    List<Posicion> getPosicionesConValor(Valor[][] celdas);
    int getIdEnSudoku();
}
