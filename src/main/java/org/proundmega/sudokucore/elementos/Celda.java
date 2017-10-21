package org.proundmega.sudokucore.elementos;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.proundmega.sudokucore.Posicion;

@EqualsAndHashCode
@AllArgsConstructor
public class Celda {
    @Getter
    private final Valor valorActual;

    public Celda() {
        valorActual = Valor.VACIA;
    }
    
    public boolean estaVacia() {
        return valorActual == Valor.VACIA;
    }

    public Celda cambiarValor(Valor valor) {
        return new Celda(valor);
    }

    @Override
    public String toString() {
        return "[" + valorActual + "]";
    }
    
}
