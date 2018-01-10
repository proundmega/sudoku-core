package org.proundmega.sudokucore.elementos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import org.proundmega.sudokucore.Posicion;

public enum Fila implements Posicionable {
    _1(1),
    _2(2),
    _3(3),
    _4(4),
    _5(5),
    _6(6),
    _7(7),
    _8(8),
    _9(9);

    private static final Map<Integer, Fila> mapa = new HashMap<>();

    static {
        for (Fila fila : values()) {
            mapa.put(fila.indiceFila, fila);
        }
    }

    /**
     * Este metodo devuelve la fila en numero a su equivalente en objeto Fila
     *
     * @param fila
     * @return
     */
    public static Fila toFila(int fila) {
        return mapa.get(fila);
    }

    private int indiceFila;

    private Fila(int fila) {
        this.indiceFila = fila;
    }

    public int getIndiceFilaParaArray() {
        return indiceFila - 1;
    }

    public int getIndice() {
        return indiceFila;
    }

    public Celda[] getFila(Celda[][] celdas) {
        return celdas[getIndiceFilaParaArray()];
    }

    @Override
    public List<Posicion> getPosiciones(Celda[][] celdas) {
        return agregarSi(celdas, celda -> true);
    }

    private List<Posicion> agregarSi(Celda[][] celdas, Predicate<Celda> predicado) {
        List<Posicion> posiciones = new ArrayList<>();

        for (int columna = 0; columna < celdas[0].length; columna++) {
            Celda celdaTrabajo = celdas[getIndiceFilaParaArray()][columna];
            if (predicado.test(celdaTrabajo)) {
                Posicion posicion = new Posicion(indiceFila, columna + 1, celdaTrabajo);
                posiciones.add(posicion);
            }

        }

        return Collections.unmodifiableList(posiciones);
    }
    
    @Override
    public List<Posicion> getPosicionesVacias(Celda[][] celdas) {
        return agregarSi(celdas, Celda::estaVacia);
    }
    
    @Override
    public List<Posicion> getPosicionesConValor(Celda[][] celdas) {
        return agregarSi(celdas, celda -> !celda.estaVacia());
    }

    @Override
    public String toString() {
        return "Fila{" + indiceFila + '}';
    }
}
