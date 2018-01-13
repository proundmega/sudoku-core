package org.proundmega.sudokucore.solver.procesadores;

import java.util.List;
import java.util.Optional;
import org.proundmega.sudokucore.MetadataSolver;
import org.proundmega.sudokucore.Posicion;
import org.proundmega.sudokucore.elementos.Valor;
import org.proundmega.sudokucore.elementos.anotador.Anotador;

public class CeldaConUnicaPosicion implements ProcesadorAnotaciones {

    public CeldaConUnicaPosicion() {
    }
    
    @Override
    public Optional<MetadataSolver> apply(Anotador anotador) {
        List<Posicion> posiciones = anotador.getPosicionesConAnotacionesRemovidas();
        if (hayUnaCasillaConUnSoloPosibleValor(posiciones)) {
            return Optional.ofNullable(resolverCasillaConUnSoloPosibleValor(anotador));
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
    
    private MetadataSolver resolverCasillaConUnSoloPosibleValor(Anotador anotador) {
        List<Posicion> posicionesVaciasAnotadas = anotador.getPosicionesConAnotacionesRemovidas();
        
        Posicion posicionACambiar = posicionesVaciasAnotadas.stream()
                .filter(posicion -> posicion.getAnotaciones().size() == 1)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        
        Valor valorAPoner = posicionACambiar.getAnotaciones().stream()
                .findFirst().orElseThrow(NullPointerException::new);
        
        return crearMetadata(posicionACambiar, valorAPoner, anotador);
    }

    private MetadataSolver crearMetadata(Posicion posicionACambiar, Valor valorAPoner, Anotador anotador) {
        Posicion posicionReemplazo = new Posicion(posicionACambiar.getFila(), posicionACambiar.getColumna(), valorAPoner);
        List<Posicion> posicionesLimitantes = anotador.getPosicionesLimitadoras(posicionACambiar);
        List<Posicion> posicionesBloque = anotador.getPosicionesDeBloque();
        return new MetadataSolver(posicionReemplazo, posicionesLimitantes, posicionesBloque);
    }
    
}
