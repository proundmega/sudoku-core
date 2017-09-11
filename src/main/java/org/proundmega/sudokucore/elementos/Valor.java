package org.proundmega.sudokucore.elementos;

/**
 *
 * @author vansi
 */
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
    
    private String valor;

    private Valor(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return valor;
    }
    
}
