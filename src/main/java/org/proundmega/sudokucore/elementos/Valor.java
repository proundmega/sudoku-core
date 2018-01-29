package org.proundmega.sudokucore.elementos;

import java.util.HashMap;
import java.util.Map;

public enum Valor {
    _1("1"),
    _2("2"),
    _3("3"),
    _4("4"),
    _5("5"),
    _6("6"),
    _7("7"),
    _8("8"),
    _9("9"),
    VACIA(" ");
    
    private static Map<String, Valor> valores;
    
    static {
        valores = new HashMap<>();
        for(Valor valor : Valor.values()) {
            valores.put(valor.valorAsString.trim(), valor);
        }
    }

    public static Valor toValor(String valor) {
        valor = valor.trim();
        if(valores.containsKey(valor)) {
            return valores.get(valor);
        }
        else {
            throw new IllegalArgumentException("El elemento ingresado es un valor invalido: " + valor);
        }
    }
    
    private String valorAsString;

    private Valor(String valor) {
        this.valorAsString = valor;
    }

    @Override
    public String toString() {
        return valorAsString;
    }
    
    public boolean estaVacia() {
        return this == Valor.VACIA;
    }
}
