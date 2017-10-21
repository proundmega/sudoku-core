package org.proundmega.sudokucore.elementos.grid;

import org.proundmega.sudokucore.elementos.Valor;
import org.proundmega.sudokucore.elementos.Celda;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.proundmega.sudokucore.Posicion;
import org.proundmega.sudokucore.elementos.Columna;
import org.proundmega.sudokucore.elementos.Fila;

public abstract class AbstractSubGrid implements SubGrid {
    protected Celda[][] celdas;

    public AbstractSubGrid() {
    }

    @Override
    public Grid completarSubGrid() {
        List<Posicion> posiciones = getPosiciones();
        
        Posicion posicionVacia = getPosicionVacia(posiciones);
        Valor valorFaltante = getPosicionFaltante(posiciones);
        
        return new Grid(celdas).reemplazarCasilla(
                posicionVacia.getFila(),
                posicionVacia.getColumna(),
                valorFaltante
        );
    }
    
    protected abstract List<Posicion> getPosiciones();
    
    protected Posicion getPosicionVacia(List<Posicion> posiciones) {
        return posiciones.stream()
                .filter((Posicion posicion) -> posicion.getCelda().estaVacia())
                .findFirst()
                .orElseThrow(NullPointerException::new);
    }

    protected Valor getPosicionFaltante(List<Posicion> posiciones) {
        Set<Valor> valores = posiciones.stream()
                .map((Posicion posicion) -> posicion.getCelda().getValorActual())
                .collect(Collectors.toSet());
        
        return Arrays.stream(Valor.values())
                .filter((Valor valor) -> valor != Valor.VACIA)
                .filter((Valor valor) -> !valores.contains(valor))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }
}
