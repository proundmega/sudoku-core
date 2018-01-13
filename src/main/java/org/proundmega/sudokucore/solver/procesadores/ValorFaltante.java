package org.proundmega.sudokucore.solver.procesadores;

import java.util.Collections;
import java.util.List;
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
            
            List<Posicion> posicionesBloque = anotador.getPosicionesDeBloque();
            MetadataSolver metadata = new MetadataSolver(respuesta, Collections.EMPTY_LIST, posicionesBloque);
            
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
