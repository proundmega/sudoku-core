package org.proundmega.sudokucore.solver.procesadores;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.proundmega.sudokucore.ExplicacionBundle;
import org.proundmega.sudokucore.MetadataSolver;
import org.proundmega.sudokucore.Posicion;
import org.proundmega.sudokucore.PosicionBundle;
import org.proundmega.sudokucore.elementos.Valor;
import org.proundmega.sudokucore.elementos.anotador.Anotador;
import org.proundmega.sudokucore.internacionalization.I18NUtils;

public class ValorFaltante implements ProcesadorAnotaciones {

    @Override
    public Optional<MetadataSolver> apply(Anotador anotador) {
        if(soloPoseeUnEspacioVacio(anotador)) {
            Posicion posicionUnica = anotador.getPosicionesConAnotacionesRemovidas().get(0);
            
            Valor valorUnico = posicionUnica.getAnotaciones().stream().findFirst().orElseThrow(NullPointerException::new);
            Posicion respuesta = new Posicion(posicionUnica, valorUnico);
            
            List<Posicion> posicionesBloque = anotador.getPosicionesDeBloque();
            MetadataSolver metadata = crearMetadataSolver(respuesta, posicionesBloque, anotador);
            
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
    
    private MetadataSolver crearMetadataSolver(Posicion respuesta, List<Posicion> posicionesBloque, Anotador anotador) {
        ExplicacionBundle explicacionBundle = new ExplicacionBundle(getClass());
        explicacionBundle.addParametro(locale -> respuesta.getValorActual().toString());
        explicacionBundle.addParametro(locale -> I18NUtils.getInternationalizationString(
                anotador.getPosicionable().getClass(), locale, "nombre_posesivo")
        );
        
        PosicionBundle posicionBundle = new PosicionBundle(respuesta, Collections.emptyList(), posicionesBloque);
        MetadataSolver metadata = new MetadataSolver(posicionBundle, explicacionBundle);
        return metadata;
    }
}
