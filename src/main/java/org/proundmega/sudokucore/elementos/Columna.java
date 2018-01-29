package org.proundmega.sudokucore.elementos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import org.proundmega.sudokucore.Posicion;

public enum Columna implements Posicionable {
    _1(1),
    _2(2),
    _3(3),
    _4(4),
    _5(5),
    _6(6),
    _7(7),
    _8(8),
    _9(9);

    private static final Map<Integer, Columna> mapa = new HashMap<>();

    static {
        for (Columna columna : values()) {
            mapa.put(columna.indiceColumna, columna);
        }
    }

    public static Columna toColumna(int columna) {
        return mapa.get(columna);
    }

    private int indiceColumna;

    private Columna(int fila) {
        this.indiceColumna = fila;
    }

    public int getIndiceColumnaParaArray() {
        return indiceColumna - 1;
    }

    public int getIndice() {
        return indiceColumna;
    }

    public Valor[] getColumna(Valor[][] celdas) {
        return Arrays.stream(celdas)
                .map(tupla -> tupla[getIndiceColumnaParaArray()])
                .toArray(Valor[]::new);
    }

    public List<Posicion> agregarSi(Valor[][] celdas, Predicate<Valor> predicado) {
        List<Posicion> posiciones = new ArrayList<>();

        for (int fila = 0; fila < celdas[0].length; fila++) {
            Valor celdaTrabajo = celdas[fila][getIndiceColumnaParaArray()];
            if (predicado.test(celdaTrabajo)) {
                Posicion posicion = new Posicion(fila + 1, indiceColumna, celdaTrabajo);
                posiciones.add(posicion);
            }
        }

        return Collections.unmodifiableList(posiciones);
    }

    @Override
    public List<Posicion> getPosiciones(Valor[][] celdas) {
        return agregarSi(celdas, valor -> true);
    }

    @Override
    public String toString() {
        return "Columna{" + indiceColumna + '}';
    }

    @Override
    public List<Posicion> getPosicionesVacias(Valor[][] celdas) {
        return agregarSi(celdas, Valor::estaVacia);
    }

    @Override
    public List<Posicion> getPosicionesConValor(Valor[][] celdas) {
        return agregarSi(celdas, celda -> !celda.estaVacia());
    }

}
