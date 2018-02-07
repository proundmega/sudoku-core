package org.proundmega.sudokucore.solver.procesadores;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.proundmega.sudokucore.ExplicacionBundle;
import org.proundmega.sudokucore.MetadataSolver;
import org.proundmega.sudokucore.Posicion;
import org.proundmega.sudokucore.PosicionBundle;
import org.proundmega.sudokucore.elementos.Valor;
import org.proundmega.sudokucore.elementos.anotador.Anotador;
import org.proundmega.sudokucore.internacionalization.I18NUtils;
import org.proundmega.sudokucore.utils.Posiciones;

public class SolucionSimple implements ProcesadorAnotaciones {

    public SolucionSimple() {
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

        return crearMetadataSolver(elegida, otro, anotador);
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

    private MetadataSolver crearMetadataSolver(Posicion elegida, Map.Entry<Valor, Long> otro, Anotador anotador) {
        PosicionBundle bundle = crearPosicionBundle(elegida, otro.getKey(), anotador);
        ExplicacionBundle explicacionBundle = crearBundleExplicacion(anotador, bundle);
        return new MetadataSolver(bundle, explicacionBundle);
    }

    private PosicionBundle crearPosicionBundle(Posicion posicionACambiar, Valor nuevoValor, Anotador anotadorContexto) {
        Posicion posicion = new Posicion(posicionACambiar.getFila(), posicionACambiar.getColumna(), nuevoValor);
        List<Posicion> posicionesQueLimitanElValor = anotadorContexto.getPosicionesQueLimitanElValor(nuevoValor);
        List<Posicion> posicionesDeBloque = anotadorContexto.getPosicionesDeBloque();

        return new PosicionBundle(posicion, posicionesQueLimitanElValor, posicionesDeBloque);
    }

    private ExplicacionBundle crearBundleExplicacion(Anotador anotador, PosicionBundle posicionBundle) {
        ExplicacionBundle bundle = new ExplicacionBundle(this.getClass());
        bundle.addParametro(locale -> posicionBundle.getPosicionResuelta().getValorActual().toString());
        bundle.addParametro(locale -> I18NUtils.getInternationalizationString(
                anotador.getPosicionable().getClass(), locale, "nombre_determinante")
        );
        bundle.addParametro(locale -> String.valueOf(anotador.getPosicionable().getIdEnSudoku()));
        bundle.addParametro(locale -> 
                Posiciones.joinPosicionesAsCoordenadasSinValor(posicionBundle.getPosicionesQueLimitanValor())
        );
        
        return bundle;
    }

}
