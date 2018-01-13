package org.proundmega.sudokucore.solver.procesadores;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.proundmega.sudokucore.MetadataSolver;
import org.proundmega.sudokucore.Posicion;
import org.proundmega.sudokucore.elementos.Valor;
import org.proundmega.sudokucore.elementos.anotador.Anotador;

public class ValorConUnicaPosicion implements ProcesadorAnotaciones {
    
    public ValorConUnicaPosicion() {
    }
    
    @Override
    public Optional<MetadataSolver> apply(Anotador anotador) {
        List<Posicion> posiciones = anotador.getPosicionesConAnotacionesRemovidas();
        
        if (unValorSoloPuedeEstarEnUnLugar(posiciones)) {
            return Optional.of(getIntercambioCalculado(anotador));
        } else {
            return Optional.ofNullable(null);
        }
    }

    public boolean unValorSoloPuedeEstarEnUnLugar(List<Posicion> posicionesVaciasAnotadas) {
        Map<Valor, Long> valores = getValoresConSusConteos(posicionesVaciasAnotadas);

        return valores.values().stream()
                .filter(conteoValor -> conteoValor == 1)
                .count() >= 1;
    }

    private Map<Valor, Long> getValoresConSusConteos(List<Posicion> posicionesVaciasAnotadas) {
        Map<Valor, Long> valores = posicionesVaciasAnotadas.stream()
                .flatMap(posicion -> posicion.getAnotaciones().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return valores;
    }

    private MetadataSolver getIntercambioCalculado(Anotador anotador) {
        List<Posicion> posicionesVaciasAnotadas = anotador.getPosicionesConAnotacionesRemovidas();
        
        Map<Valor, Long> valores = getValoresConSusConteos(posicionesVaciasAnotadas);
        Map.Entry<Valor, Long> otro = getEntradaConConteoDeUno(valores);
        Posicion elegida = getPosicionConLaEntradaDeseada(posicionesVaciasAnotadas, otro);
        
        return crearMetadata(elegida, otro.getKey(), anotador);
    }

    private Map.Entry<Valor, Long> getEntradaConConteoDeUno(Map<Valor, Long> valores) {
        Map.Entry<Valor, Long> otro = valores.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .filter(valor -> valor.getValue() == 1)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        return otro;
    }

    private Posicion getPosicionConLaEntradaDeseada(List<Posicion> posicionesVaciasAnotadas, Map.Entry<Valor, Long> otro) {
        Posicion elegida = posicionesVaciasAnotadas.stream()
                .filter(posicion -> posicion.getAnotaciones().contains(otro.getKey()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        return elegida;
    }
    
    private MetadataSolver crearMetadata(Posicion posicionACambiar, Valor valorAPoner, Anotador anotador) {
        Posicion posicionReemplazo = new Posicion(posicionACambiar.getFila(), posicionACambiar.getColumna(), valorAPoner);
        List<Posicion> posicionesLimitantes = anotador.getPosicionesQueLimitanElValor(valorAPoner);
        List<Posicion> posicionesBloque = anotador.getPosicionesDeBloque();
        return new MetadataSolver(posicionReemplazo, posicionesLimitantes, posicionesBloque);
    }

}
