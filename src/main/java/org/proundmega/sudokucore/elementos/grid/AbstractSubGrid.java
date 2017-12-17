package org.proundmega.sudokucore.elementos.grid;

import org.proundmega.sudokucore.elementos.Valor;
import org.proundmega.sudokucore.elementos.Celda;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.proundmega.sudokucore.MetadataSolver;
import org.proundmega.sudokucore.Posicion;
import org.proundmega.sudokucore.elementos.Columna;
import org.proundmega.sudokucore.elementos.Fila;

public abstract class AbstractSubGrid implements SubGrid {
    protected Celda[][] celdas;

    public AbstractSubGrid() {
    }

    @Override
    public MetadataSolver completarSubGrid() {
        List<Posicion> posiciones = getPosiciones();
        
        Posicion posicionVacia = getPosicionVacia(posiciones);
        Valor valorFaltante = getValorFaltante(posiciones);
        
        Posicion posicionReemplazo = new Posicion(posicionVacia.getFila(), posicionVacia.getColumna(), new Celda(valorFaltante));
        Grid gridResuelta = new Grid(celdas).reemplazarCasilla(posicionReemplazo);
        
        return new MetadataSolver(posicionReemplazo);
    }
    
    protected abstract List<Posicion> getPosiciones();
    
    protected Posicion getPosicionVacia(List<Posicion> posiciones) {
        return posiciones.stream()
                .filter((Posicion posicion) -> posicion.getCelda().estaVacia())
                .findFirst()
                .orElseThrow(NullPointerException::new);
    }

    protected Valor getValorFaltante(List<Posicion> posiciones) {
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
