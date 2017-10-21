package org.proundmega.sudokucore.solver.procesadores;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.proundmega.sudokucore.Posicion;
import org.proundmega.sudokucore.elementos.Valor;

public class ValorConUnicaPosicion implements ProcesadorAnotaciones {

    public ValorConUnicaPosicion() {
    }
    
    @Override
    public Optional<Intercambio> apply(List<Posicion> posiciones) {
        if (unValorSoloPuedeEstarEnUnLugar(posiciones)) {
            return Optional.ofNullable(getIntercambioCalculado(posiciones));
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

    private Intercambio getIntercambioCalculado(List<Posicion> posicionesVaciasAnotadas) {
        Map<Valor, Long> valores = getValoresConSusConteos(posicionesVaciasAnotadas);
        Map.Entry<Valor, Long> otro = getEntradaConConteoDeUno(valores);
        Posicion elegida = getPosicionConLaEntradaDeseada(posicionesVaciasAnotadas, otro);
        return new Intercambio(elegida, otro.getKey());

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

}
