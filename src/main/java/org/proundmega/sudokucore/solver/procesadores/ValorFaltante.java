package org.proundmega.sudokucore.solver.procesadores;

import java.util.Optional;
import org.proundmega.sudokucore.MetadataSolver;
import org.proundmega.sudokucore.Posicion;
import org.proundmega.sudokucore.elementos.Valor;
import org.proundmega.sudokucore.elementos.anotador.Anotador;

public class ValorFaltante implements ProcesadorAnotaciones {

    @Override
    public Optional<MetadataSolver> apply(Anotador anotador) {
        if(soloPoseeUnEspacioVacio(anotador)) {
            Posicion posicionUnica = anotador.getPosicionesConAnotacionesRemovidas().get(0);
            
            Valor valorUnico = posicionUnica.getAnotaciones().stream().findFirst().orElseThrow(NullPointerException::new);
            Posicion respuesta = new Posicion(posicionUnica, valorUnico);
            MetadataSolver metadata = new MetadataSolver(respuesta);
            
            // TODO falta añadir metadatos sobre posiciones en cuadrante
            
            return Optional.of(metadata);
        }
        else {
            return Optional.ofNullable(null);
        }
    }
    
    private boolean soloPoseeUnEspacioVacio(Anotador anotador) {
        return anotador.getPosicionesConAnotacionesRemovidas().stream()
                .count() == 1;
    }
}
