package org.proundmega.sudokucore.elementos.anotador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.proundmega.sudokucore.Posicion;
import org.proundmega.sudokucore.elementos.Celda;
import org.proundmega.sudokucore.elementos.Celdas;
import org.proundmega.sudokucore.elementos.Posicionable;
import org.proundmega.sudokucore.elementos.Valor;

public class AnotadorGeneral implements Anotador {
    private Celda[][] celdas;
    private List<Posicion> posicionesVacias;
    private List<Posicion> cachePosicionesConAnotacionesRemovidas;
    private Posicionable posicionable;
    
    public AnotadorGeneral(Celda[][] celdas, Posicionable posicionable) {
        this.celdas = celdas;
        this.posicionable = posicionable;
        this.posicionesVacias = posicionable.getPosicionesVacias(celdas);
        anotarPosiciones();
    }
    
    private void anotarPosiciones() {
        Set<Valor> valoresFaltantes = getValoresFaltantes();

        posicionesVacias = posicionesVacias.stream()
                .map(posicion -> posicion.addAnotaciones(valoresFaltantes))
                .collect(Collectors.toList());
    }
    
    public Set<Valor> getValoresFaltantes() {
        Set<Valor> valoresUsados = posicionable.getPosicionesConValor(celdas)
                .stream()
                .map(posicion -> posicion.getValorActual())
                .collect(Collectors.toSet());

        return Arrays.stream(Valor.values())
                .filter(valor -> valor != Valor.VACIA)
                .filter(valor -> !valoresUsados.contains(valor))
                .collect(Collectors.toSet());
    }
    
    public List<Posicion> crearPosicionesConAnotacionesRemovidas() {
        List<Posicion> posiciones = removerNotacionesFilaA(posicionesVacias);
        posiciones = removerNotacionesColumnaA(posiciones);
        return removerNotacionesCuadranteA(posiciones);
    }

    @Override
    public List<Posicion> getPosicionesConAnotacionesRemovidas() {
        synchronized(this) {
            if(cachePosicionesConAnotacionesRemovidas == null) {
                cachePosicionesConAnotacionesRemovidas = crearPosicionesConAnotacionesRemovidas();
            }
        }
        return cachePosicionesConAnotacionesRemovidas;
    }
    
    public List<Posicion> getPosicionesFilasRemovidas() {
        return removerNotacionesFilaA(posicionesVacias);
    }
    
    private List<Posicion> removerNotacionesFilaA (List<Posicion> posiciones) {
        return removerNotacionesEnBaseAValorEnComun(posiciones, celdas, Posicion::getFila);
    }
    
    private static <T> List<Posicion> removerNotacionesEnBaseAValorEnComun (List<Posicion> posiciones, Celda[][] celdas, Function<Posicion, T> mapper) {
        Map<T, Set<Valor>> valoresUsados = AnotadorMapper.getPosicionesEnBaseAFuncionTomandoComoBase(posiciones, celdas, mapper).stream()
                .collect(Collectors.groupingBy(mapper, Collectors.mapping(Posicion::getValorActual, Collectors.toSet())));
        
        Set<Valor> vacio = Collections.EMPTY_SET;
        
        return posiciones.stream()
                .map(posicion -> posicion.removeAnotaciones(valoresUsados.getOrDefault(mapper.apply(posicion), vacio)))
                .sorted()
                .collect(Collectors.toList());
    }
    
    public List<Posicion> getPosicionesColumnasRemovidas() {
        return removerNotacionesColumnaA(posicionesVacias);
    }
    
    private List<Posicion> removerNotacionesColumnaA(List<Posicion> posiciones) {
        return removerNotacionesEnBaseAValorEnComun(posiciones, celdas, Posicion::getColumna);
    }
    
    public List<Posicion> getPosicionesCuadranteRemovido() {
        return removerNotacionesCuadranteA(posicionesVacias);
    }
    
    private List<Posicion> removerNotacionesCuadranteA(List<Posicion> posicionesVacias) {
        return removerNotacionesEnBaseAValorEnComun(posicionesVacias, celdas, Posicion::getCuadrante);
    }

    @Override
    public List<Posicion> getPosicionesQueLimitanElValor(Valor valor) {
        if (valor == Valor.VACIA) return Collections.EMPTY_LIST;
        
        AnotadorMapper mapper = new AnotadorMapper(celdas, posicionable.getPosicionesVacias(celdas));
        List<Posicion> posiciones = mapper.groupByFilaColumnaYCuadrante();
        
        List<Posicion> posicionesYaEnSolver = posicionable.getPosicionesConValor(celdas);
        
        return posiciones.stream()
                .filter(posicionConValor -> posicionConValor.getValorActual() == valor)
                .filter(posicion -> !posicionesYaEnSolver.contains(posicion))
                .sorted()
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Posicion> getPosicionesLimitadoras(Posicion filtro) {
        List<Posicion> posiciones = getPosicionesLimitadorasConRepetidos(filtro);
        return eliminarRepetidosConMismoValor(posiciones);
    }
    
    public List<Posicion> getPosicionesLimitadorasConRepetidos(Posicion filtro) {
        return Celdas.asPosiciones(celdas)
                .stream()
                .filter(posicion -> !posicion.getCelda().estaVacia())
                .filter(posicion -> poseenFilaOColumnaSimilaresOSonDelMismoCuadrante(filtro, posicion))
                .sorted()
                .collect(Collectors.toList());
    }

    private static boolean poseenFilaOColumnaSimilaresOSonDelMismoCuadrante(Posicion filtro, Posicion posicion) {
        return posicion.getFila() == filtro.getFila() || posicion.getColumna() == filtro.getColumna()
                || posicion.getCuadrante() == filtro.getCuadrante();
    }
    
    private List<Posicion> eliminarRepetidosConMismoValor(List<Posicion> posiciones) {
        Map<Valor, List<Posicion>> posicionesRepetidas = posiciones.stream()
                .collect(Collectors.groupingBy(Posicion::getValorActual));
        
        return posicionesRepetidas.entrySet().stream()
                .flatMap(entrada -> Stream.of(entrada.getValue().get(0)))
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public List<Posicion> getPosicionesDeBloque() {
        return posicionable.getPosiciones(celdas);
    }

}
