package org.proundmega.sudokucore.solver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Este es un pipeline hecho de creadores y consumidores que procesa una entrada
 * y saca otra entrada del mismo tipo ejecutanto una lista de funciones
 * definidas por el usuario. El plan es algo asi:
 *
 * - Obtengo un valor inicial I. Al ejecutar algo en el, obtengo un valor O - Al
 * ejecutar un intermediario, convierto O en I - Prosigo hasta donde yo desee
 *
 * @author vansi
 * @param <I> Input - la clase que recibo
 * @param <O> Output - la clases que voy a retornar en un paso intermedio
 * @param <P> La clases a usar como base de la funcion, los pasos que debe
 * seguir
 */
public class Pipeline<I, O, P extends Function<I, O>> {

    private PipelineIntermediate<I, O, P> datos;

    private Pipeline(PipelineIntermediate<I, O, P> intermediate) {
        this.datos = intermediate;
    }

    public O get() {
        List<Retorno> retornoList = new ArrayList<>();
        Consumer<Retorno> nulo = retorno -> {};
        Consumer<Retorno> guardar = retorno -> retornoList.add(retorno);
        realizarPipeline(nulo, guardar);

        return retornoList.get(0).getSalida();
    }

    private void realizarPipeline(Consumer<Retorno> alFinalizarPaso, Consumer<Retorno> alFinalizarIteracion) {
        I temp = datos.input;
        Retorno respuesta = null;
        for (int i = 0; i < datos.maxIteraciones; i++) {
            respuesta = iterar(temp);
            if (respuesta.isTerminado()) {
                alFinalizarIteracion.accept(respuesta);
            } else {
                temp = datos.pasoIntercambio.apply(respuesta.getSalida());
                alFinalizarPaso.accept(respuesta);
            }
        }
        alFinalizarIteracion.accept(respuesta);
    }

    private Retorno iterar(I input) {
        I temp = input;
        O salida = null;

        for (Function<I, O> pipelineStep : datos.pasos) {
            salida = pipelineStep.apply(temp);

            if (haTerminado(salida)) {
                return new Retorno((P) pipelineStep, salida, true);
            } else if (tieneQueReiniciar(salida)) {
                return new Retorno((P) pipelineStep, salida, false);
            } else {
                temp = datos.pasoIntercambio.apply(salida);
            }
        }

        return new Retorno(null, salida, false);
    }

    private boolean haTerminado(O resultado) {
        return datos.finalizadores.stream()
                .map(funcion -> funcion.test(resultado))
                .allMatch(respuesta -> respuesta == true);
    }

    private boolean tieneQueReiniciar(O resultado) {
        return datos.reiniciadores.stream()
                .map(funcion -> funcion.test(resultado))
                .allMatch(respuesta -> respuesta == true);
    }

    public static <I, O, P extends Function<I, O>> PipelineIntermediate<I, O, P> crear(I input, Class<P> clazz) {
        return new PipelineIntermediate(input);
    }

    public static <I, O, P extends Function<I, O>> PipelineIntermediate<I, O, P> crear(I input, Set<P> funciones) {
        return new PipelineIntermediate<>(input, funciones);
    }

    public List<O> getPorPasos() {
        List<Retorno> retornoList = new ArrayList<>();

        Consumer<Retorno> guardar = retorno -> retornoList.add(retorno);
        realizarPipeline(guardar, guardar);

        return retornoList.stream()
                .map(valor -> valor.getSalida())
                .collect(Collectors.toList());
    }

    public static class PipelineIntermediate<I, O, P extends Function<I, O>> {

        private I input;
        private Set<P> pasos = new HashSet<>();
        private Function<O, I> pasoIntercambio;
        private List<Predicate> reiniciadores = new ArrayList<>();
        private List<Predicate> finalizadores = new ArrayList<>();
        private int maxIteraciones = 10000;

        private PipelineIntermediate(I input, Set<P> steps) {
            this.pasos = steps;
            this.input = input;
        }

        private PipelineIntermediate(I input) {
            this.input = input;
        }

        public PipelineIntermediate<I, O, P> addStep(P step) {
            pasos.add(step);
            return this;
        }

        public PipelineIntermediate<I, O, P> afterStep(Function<O, I> step) {
            this.pasoIntercambio = step;
            return this;
        }

        public PipelineIntermediate<I, O, P> restartPipelineIf(Predicate<O> consumer) {
            reiniciadores.add(consumer);
            return this;
        }

        public PipelineIntermediate<I, O, P> finishIf(Predicate<O> consumer) {
            finalizadores.add(consumer);
            return this;
        }

        public PipelineIntermediate<I, O, P> maxIterations(int maxIterations) {
            this.maxIteraciones = maxIterations;
            return this;
        }

        public Pipeline build() {
            return new Pipeline(this);
        }
    }

    @Data
    @AllArgsConstructor
    private class Retorno {
        private P metodo;
        private O salida;
        private boolean terminado;
    }
}
