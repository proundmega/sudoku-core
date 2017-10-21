package org.proundmega.sudokucore.solver;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import org.proundmega.sudokucore.elementos.Celda;
import org.proundmega.sudokucore.elementos.Cuadrante;
import org.proundmega.sudokucore.elementos.Valor;

public class SolverHelper {
    
    public static boolean soloFaltaUnEspacio(Celda[] celdas) {
        return Arrays.stream(celdas)
                .filter(Celda::estaVacia)
                .count() == 1;
    }
    
    public static Set<Valor> getValoresFaltantes(Celda[][] celdas, Cuadrante cuadrante) {
        Set<Valor> valoresUsados = cuadrante.getCeldasConValor(celdas)
                .stream()
                .map(posicion -> posicion.getCelda().getValorActual())
                .collect(Collectors.toSet());

        return Arrays.stream(Valor.values())
                .filter(valor -> valor != Valor.VACIA)
                .filter(valor -> !valoresUsados.contains(valor))
                .collect(Collectors.toSet());
    }
    
}
