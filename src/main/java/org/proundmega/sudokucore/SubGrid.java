package org.proundmega.sudokucore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.proundmega.sudokucore.elementos.Columna;
import org.proundmega.sudokucore.elementos.Cuadrante;
import org.proundmega.sudokucore.elementos.Fila;
import org.proundmega.sudokucore.elementos.ValidadorSudoku;

public class SubGrid {
    private Celda[][] celdas;
    private Cuadrante cuadranteObjetivo;
    private Celda[][] solve;

    public SubGrid(Celda[][] celdas, Cuadrante cuadrante) {
        this.celdas = celdas;
        this.cuadranteObjetivo = cuadrante;
        this.solve = cuadrante.getCuadrante(celdas);
    }
    
    public boolean estaCompleta() {
        return ValidadorSudoku.esCuadranteCompleto(solve);
    }

    public boolean soloFaltaUnEspacio() {
        return Arrays.stream(solve)
                .flatMap(tupla -> Arrays.stream(tupla))
                .filter(Celda::estaVacia)
                .count() == 1;
    }

    public Grid completarCuadrante() {
        List<Posicion> posiciones = cuadranteObjetivo.getCuadranteAsList(celdas);
        
        Posicion posicionVacia = getPosicionVacia(posiciones);
        Valor valorFaltante = getPosicionFaltante(posiciones);
        
        return new Grid(celdas).reemplazarCasilla(
                Fila.toFila(posicionVacia.getFila()),
                Columna.toColumna(posicionVacia.getColumna()),
                valorFaltante
        );
    }

    private Posicion getPosicionVacia(List<Posicion> posiciones) {
        return posiciones.stream()
            .filter(posicion -> posicion.getCelda().estaVacia())
            .findFirst()
            .orElseThrow(NullPointerException::new);
    }

    private Valor getPosicionFaltante(List<Posicion> posiciones) {
        Set<Valor> valores = posiciones.stream()
                .map(posicion -> posicion.getCelda().getValorActual())
                .collect(Collectors.toSet());
        
        return Arrays.stream(Valor.values())
                .filter(valor -> valor != Valor.VACIA)
                .filter(valor -> !valores.contains(valor))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }
}
