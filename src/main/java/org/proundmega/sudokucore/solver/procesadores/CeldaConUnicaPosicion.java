package org.proundmega.sudokucore.solver.procesadores;

import java.util.List;
import java.util.Optional;
import org.proundmega.sudokucore.Posicion;
import org.proundmega.sudokucore.elementos.Valor;

public class CeldaConUnicaPosicion implements ProcesadorAnotaciones {

    public CeldaConUnicaPosicion() {
    }
    
    @Override
    public Optional<Intercambio> apply(List<Posicion> posiciones) {
        if (hayUnaCasillaConUnSoloPosibleValor(posiciones)) {
            return Optional.ofNullable(resolverCasillaConUnSoloPosibleValor(posiciones));
        }
        else {
            return Optional.ofNullable(null);
        }
    }
    
    public boolean hayUnaCasillaConUnSoloPosibleValor(List<Posicion> posicionesVaciasAnotadas) {
        return posicionesVaciasAnotadas.stream()
                .filter(posicion -> posicion.getAnotaciones().size() == 1)
                .count() >= 1;
    }
    
    private Intercambio resolverCasillaConUnSoloPosibleValor(List<Posicion> posicionesVaciasAnotadas) {
        Posicion posicionACambiar = posicionesVaciasAnotadas.stream()
                .filter(posicion -> posicion.getAnotaciones().size() == 1)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        
        Valor valorAPoner = posicionACambiar.getAnotaciones().stream()
                .findFirst().orElseThrow(NullPointerException::new);
        
        return new Intercambio(posicionACambiar, valorAPoner);
    }
}
