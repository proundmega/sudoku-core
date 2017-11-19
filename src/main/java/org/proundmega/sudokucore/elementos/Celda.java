package org.proundmega.sudokucore.elementos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

@Value
@AllArgsConstructor
public class Celda {
    private Valor valorActual;

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
