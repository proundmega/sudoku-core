package org.proundmega.sudokucore.elementos.grid;

public interface SubGrid {
    Grid completarSubGrid();
    boolean estaCompleta();
    boolean soloFaltaUnEspacio();
}
