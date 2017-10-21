package org.proundmega.sudokucore.solver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.proundmega.contrib.OptionalConsumer;

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
 * @param <P> La clases a usar como base de la funcion, los pasos que debe seguir
 */
public class Pipeline<I, O, P extends Function<I, O>> {

    private PipelineIntermediate<I, O, P> intermediate;

    private Pipeline(PipelineIntermediate<I, O, P> intermediate) {
        this.intermediate = intermediate;
    }

    public O get() {
        I temp = intermediate.input;
        Return<O> answer = null;
        for (int i = 0; i < intermediate.maxIterations; i++) {
            answer = iterate(temp);
            if (answer.isFinished()) {
                return answer.getOutput();
            }
            else {
                temp = intermediate.afterStep.apply(answer.getOutput());
            }
        }
        return answer.getOutput();
    }

    private Return<O> iterate(I input) {
        I temp = input;
        O output = null;
        
        for (Function<I, O> pipelineStep : intermediate.steps) {
            output = pipelineStep.apply(temp);
            //System.out.println("Input: \n" + temp);
            //System.out.println("Output: \n" + output);
            
            if (finished(output)) {
                return new Return<>(output, true);
            } else if (haveToRestart(output)) {
                return new Return<>(output, false);
            } else {
                temp = intermediate.afterStep.apply(output);
            }
        }

        return new Return<>(output, false);
    }

    private boolean finished(O result) {
        return intermediate.finishers.stream()
                .map(function -> function.test(result))
                .allMatch(answer -> answer == true);
    }

    private boolean haveToRestart(O result) {
        return intermediate.restarters.stream()
                .map(function -> function.test(result))
                .allMatch(answer -> answer == true);
    }
    
    public static <I, O, P extends Function<I, O>> PipelineIntermediate<I, O, P> create(I input, Class<P> clazz) {
        return new PipelineIntermediate<I, O, P>(input);
    }
    
    public static <I, O, P extends Function<I, O>> PipelineIntermediate<I, O, P> create(I input, Set<P> funciones) {
        return new PipelineIntermediate<>(input, funciones);
    }
    
    public static class PipelineIntermediate<I, O, P extends Function<I, O>> {

        private I input;
        private Set<P> steps = new HashSet<>();
        private Function<O, I> afterStep;
        private List<Predicate> restarters = new ArrayList<>();
        private List<Predicate> finishers = new ArrayList<>();
        private int maxIterations = 10000;

        private PipelineIntermediate(I input, Set<P> steps) {
            this.steps = steps;
            this.input = input;
        }
        
        private PipelineIntermediate(I input) {
            this.input = input;
        }
        
        public PipelineIntermediate<I, O, P> addStep(P step) {
            steps.add(step);
            return this;
        }

        public PipelineIntermediate<I, O, P> afterStep(Function<O, I> step) {
            this.afterStep = step;
            return this;
        }

        public PipelineIntermediate<I, O, P> restartPipelineIf(Predicate<O> consumer) {
            restarters.add(consumer);
            return this;
        }

        public PipelineIntermediate<I, O, P> finishIf(Predicate<O> consumer) {
            finishers.add(consumer);
            return this;
        }

        public PipelineIntermediate<I, O, P> maxIterations(int maxIterations) {
            this.maxIterations = maxIterations;
            return this;
        }

        public Pipeline build() {
            return new Pipeline(this);
        }
    }

    @Data
    @AllArgsConstructor
    private static class Return<O> {

        private O output;
        private boolean finished;
    }
}
