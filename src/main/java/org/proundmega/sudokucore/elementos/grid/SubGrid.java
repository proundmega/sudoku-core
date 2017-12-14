package org.proundmega.sudokucore.elementos.grid;

import org.proundmega.sudokucore.MetadataSolver;

public interface SubGrid {
    MetadataSolver completarSubGrid();
    boolean estaCompleta();
    boolean soloFaltaUnEspacio();
}
