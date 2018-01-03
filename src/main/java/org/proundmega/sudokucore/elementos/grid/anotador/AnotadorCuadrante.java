package org.proundmega.sudokucore.elementos.grid.anotador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.proundmega.sudokucore.Posicion;
import org.proundmega.sudokucore.elementos.Celda;
import org.proundmega.sudokucore.elementos.Celdas;
import org.proundmega.sudokucore.elementos.Cuadrante;
import org.proundmega.sudokucore.elementos.Valor;
import org.proundmega.sudokucore.solver.SolverHelper;

public class AnotadorCuadrante implements Anotador {
    private Celda[][] celdas;
    private Cuadrante cuadranteObjetivo;
    private List<Posicion> posicionesVacias;
    private List<Posicion> cachePosicionesConAnotacionesRemovidas;
    
    public AnotadorCuadrante(Celda[][] celdas, Cuadrante cuadranteObjetivo) {
        this.celdas = celdas;
        this.cuadranteObjetivo = cuadranteObjetivo;
        this.posicionesVacias = cuadranteObjetivo.getCeldasVacias(celdas);
        anotarPosiciones();
    }
    
    private void anotarPosiciones() {
        Set<Valor> valoresFaltantes = getValoresFaltantes();

        posicionesVacias = posicionesVacias.stream()
                .map(posicion -> posicion.addAnotaciones(valoresFaltantes))
                .collect(Collectors.toList());
    }
    
    public Set<Valor> getValoresFaltantes() {
        Set<Valor> valoresUsados = cuadranteObjetivo.getCeldasConValor(celdas)
                .stream()
                .map(posicion -> posicion.getCelda().getValorActual())
                .collect(Collectors.toSet());

        return Arrays.stream(Valor.values())
                .filter(valor -> valor != Valor.VACIA)
                .filter(valor -> !valoresUsados.contains(valor))
                .collect(Collectors.toSet());
    }
    
    public List<Posicion> crearPosicionesConAnotacionesRemovidas() {
        List<Posicion> posiciones = removerNotacionesFilaA(posicionesVacias);
        return removerNotacionesColumnaA(posiciones);
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
        List<Posicion> posicionesHorizontales = cuadranteObjetivo.getCeldasHorizontalesConValor(celdas);
        
        int offset = cuadranteObjetivo.getOffsetFila();
        
        List<Posicion> valoresConNotacionesRemovidas = posiciones.stream()
                .collect(Collectors.toList());
        
        for (int actual = offset; actual < offset + 3; actual++) {
            int actualInmutable = actual;
            Set<Valor> valoresYaUsados = posicionesHorizontales.stream()
                    .filter(posicionActual -> posicionActual.getFilaAsNumero() == actualInmutable)
                    .map(posicionActual -> posicionActual.getCelda().getValorActual())
                    .collect(Collectors.toSet());
            
            List<Posicion> temp = valoresConNotacionesRemovidas.stream()
                    .filter(posicionActual -> posicionActual.getFilaAsNumero() == actualInmutable)
                    .map(posicionActual ->  posicionActual.removeAnotaciones(valoresYaUsados))
                    .collect(Collectors.toList());
            
            temp.addAll(
                    valoresConNotacionesRemovidas.stream()
                        .filter(posicionActual -> posicionActual.getFilaAsNumero() != actualInmutable)
                         .collect(Collectors.toList())
            );
            
            valoresConNotacionesRemovidas = temp;
            
        }
        
        Collections.sort(valoresConNotacionesRemovidas);
        return valoresConNotacionesRemovidas;
    }
    
    public List<Posicion> getPosicionesColumnasRemovidas() {
        return removerNotacionesColumnaA(posicionesVacias);
    }
    
    private List<Posicion> removerNotacionesColumnaA(List<Posicion> posiciones) {
        List<Posicion> posicionesVerticales = cuadranteObjetivo.getCeldasVerticalesConValor(celdas);
        int offset = cuadranteObjetivo.getOffsetColumna();
        
        List<Posicion> valoresConNotacionesRemovidas = posiciones.stream()
                .collect(Collectors.toList());
        
        for (int actual = offset; actual < offset + 3; actual++) {
            int actualInmutable = actual;
            Set<Valor> valoresYaUsados = posicionesVerticales.stream()
                    .filter(posicionActual -> posicionActual.getColumnaAsNumero() == actualInmutable)
                    .map(posicionActual -> posicionActual.getCelda().getValorActual())
                    .collect(Collectors.toSet());
            
            List<Posicion> temp = valoresConNotacionesRemovidas.stream()
                    .filter(posicionActual -> posicionActual.getColumnaAsNumero() == actualInmutable)
                    .map(posicionActual ->  posicionActual.removeAnotaciones(valoresYaUsados))
                    .collect(Collectors.toList());
            
            temp.addAll(
                    valoresConNotacionesRemovidas.stream()
                        .filter(posicionActual -> posicionActual.getColumnaAsNumero() != actualInmutable)
                         .collect(Collectors.toList())
            );
            
            valoresConNotacionesRemovidas = temp;
            
        }
        
        Collections.sort(valoresConNotacionesRemovidas);
        return valoresConNotacionesRemovidas;
    }

    @Override
    public List<Posicion> getPosicionesQueLimitanElValor(Valor valor) {
        List<Posicion> posiciones = new ArrayList<>();
        
        List<Posicion> valoresFilaLLenos = cuadranteObjetivo.getCeldasHorizontalesConValor(celdas);
        List<Posicion> valoresColumnaLLenos = cuadranteObjetivo.getCeldasVerticalesConValor(celdas);
        
        posiciones.addAll(valoresFilaLLenos);
        posiciones.addAll(valoresColumnaLLenos);
        
        return posiciones.stream()
                .filter(posicionConValor -> posicionConValor.getValorActual() == valor)
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
                .filter(posicion -> poseenFilaOColumnaSimilares(filtro, posicion))
                .sorted()
                .collect(Collectors.toList());
    }

    private static boolean poseenFilaOColumnaSimilares(Posicion filtro, Posicion posicion) {
        return posicion.getFila() == filtro.getFila() || posicion.getColumna() == filtro.getColumna();
    }
    
    private List<Posicion> eliminarRepetidosConMismoValor(List<Posicion> posiciones) {
        Map<Valor, List<Posicion>> posicionesRepetidas = posiciones.stream()
                .collect(Collectors.groupingBy(Posicion::getValorActual));
        
        return posicionesRepetidas.entrySet().stream()
                .flatMap(entrada -> Stream.of(entrada.getValue().get(0)))
                .sorted()
                .collect(Collectors.toList());
    }
    
}
