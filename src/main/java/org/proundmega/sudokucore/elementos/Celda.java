package org.proundmega.sudokucore.elementos;

import java.util.Objects;

/**
 *
 * @author vansi
 */
public class Celda {
    private final Valor valorActual;

    public Celda() {
        valorActual = Valor.VACIA;
    }
    
    public Celda(Valor valorActual) {
        this.valorActual = valorActual;
    }
    
    public boolean estaVacia() {
        return valorActual == Valor.VACIA;
    }

    public Valor getValorActual() {
        return valorActual;
    }

    public Celda cambiarValor(Valor valor) {
        return new Celda(valor);
    }

    @Override
    public String toString() {
        return "[" + valorActual + "]";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.valorActual);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Celda other = (Celda) obj;
        if (this.valorActual != other.valorActual) {
            return false;
        }
        return true;
    }
    
}
