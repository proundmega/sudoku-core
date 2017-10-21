package org.proundmega.sudokucore.solver.procesadores;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.proundmega.sudokucore.Posicion;
import org.proundmega.sudokucore.elementos.Valor;

@Value
@AllArgsConstructor
public class Intercambio {
    private Posicion posicionVacia;
    private Valor nuevoValor;
}
