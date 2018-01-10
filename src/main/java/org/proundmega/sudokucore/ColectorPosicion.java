package org.proundmega.sudokucore;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.proundmega.sudokucore.elementos.Celda;
import org.proundmega.sudokucore.elementos.Columna;
import org.proundmega.sudokucore.elementos.Fila;

public class ColectorPosicion {
    private Celda[][] celdas;

    private ColectorPosicion(Celda[][] celdas) {
        this.celdas = celdas;
    }
    
    public static ColectorPosicion of(Celda[][] celdas) {
        return new ColectorPosicion(celdas);
    }
    
    public Stream<Posicion> collectPosiciones(Fila filaInicio, Fila filaFin, Columna columnaInicio, Columna columnaFin) {
        List<Posicion> posiciones = new ArrayList<>();
        
        for(int fila = 0; fila < 9; fila++) {
            for(int columna = 0; columna < 9; columna++) {
                posiciones.add(new Posicion(fila + 1, columna + 1, celdas[fila][columna]));
            }
        }
        
        return posiciones.stream()
                .filter(posicion -> posicion.getFilaAsNumero() >= filaInicio.getIndice())
                .filter(posicion -> posicion.getFilaAsNumero() <= filaFin.getIndice())
                .filter(posicion -> posicion.getColumnaAsNumero() >=columnaInicio.getIndice())
                .filter(posicion -> posicion.getColumnaAsNumero() <=columnaFin.getIndice())
                .sorted();
    }
    
}
