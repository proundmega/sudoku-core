package org.proundmega.sudokucore.elementos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.proundmega.sudokucore.Posicion;

public enum Cuadrante implements Posicionable {
    SUPERIOR_IZQUIERO(0, 0, 1),
    SUPERIOR_CENTRAL(0, 3, 2),
    SUPERIOR_DERECHO(0, 6, 3),
    CENTRAL_IZQUIERO(3, 0, 4),
    CENTRAL_CENTRAL(3, 3, 5),
    CENTRAL_DERECHO(3, 6, 6),
    INFERIOR_IZQUIERO(6, 0, 7),
    INFERIOR_CENTRAL(6, 3, 8),
    INFERIOR_DERECHO(6, 6, 9);

    private final int offsetFila;
    private final int offsetColumna;
    private final int id;

    private Cuadrante(int offsetFila, int offsetColumna, int id) {
        this.offsetFila = offsetFila;
        this.offsetColumna = offsetColumna;
        this.id = id;
    }

    public int getOffsetFila() {
        return offsetFila + 1;
    }

    public int getOffsetColumna() {
        return offsetColumna + 1;
    }

    public Valor[][] getCuadrante(Valor[][] valores) {
        return Arrays.stream(valores, offsetFila, offsetFila + 3)
                .map(tupla -> Arrays.copyOfRange(tupla, offsetColumna, offsetColumna + 3))
                .toArray(Valor[][]::new);
    }

    public List<Posicion> getCuadranteAsList(Valor[][] celdas) {
        return agregarSi(celdas, celda -> true);
    }

    private List<Posicion> agregarSi(Valor[][] celdas, Predicate<Valor> predicado) {
        List<Posicion> posiciones = new ArrayList<>();

        for (int fila = offsetFila; fila < offsetFila + 3; fila++) {
            for (int columna = offsetColumna; columna < offsetColumna + 3; columna++) {
                if (predicado.test(celdas[fila][columna])) {
                    Posicion posicion = new Posicion(fila + 1, columna + 1, celdas[fila][columna]);
                    posiciones.add(posicion);
                }
            }
        }

        return Collections.unmodifiableList(posiciones);
    }

    @Override
    public List<Posicion> getPosicionesVacias(Valor[][] celdas) {
        return agregarSi(celdas, Valor::estaVacia);
    }

    @Override
    public List<Posicion> getPosicionesConValor(Valor[][] celdas) {
        return agregarSi(celdas, celda -> !celda.estaVacia());
    }

    @Override
    public List<Posicion> getPosiciones(Valor[][] celdas) {
        return getCuadranteAsList(celdas);
    }

    @Override
    public int getIdEnSudoku() {
        return id;
    }

}
