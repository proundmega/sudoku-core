package org.proundmega.sudokucore.solver.procesadores;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.proundmega.sudokucore.MetadataSolver;
import org.proundmega.sudokucore.Posicion;
import org.proundmega.sudokucore.elementos.Valor;

@Value
@AllArgsConstructor
@EqualsAndHashCode(of = {"posicionVacia", "nuevoValor"})
public class Intercambio {
    private Posicion posicionVacia;
    private Valor nuevoValor;
    private MetadataSolver metadatosProducidos;

    public Intercambio(Posicion posicionVacia, Valor nuevoValor) {
        this.posicionVacia = posicionVacia;
        this.nuevoValor = nuevoValor;
        this.metadatosProducidos = new MetadataSolver();
    }
}
