package org.proundmega.sudokucore.elementos.grid;

import org.proundmega.sudokucore.elementos.Celda;
import java.util.Arrays;
import java.util.List;
import org.proundmega.sudokucore.Posicion;
import org.proundmega.sudokucore.elementos.Cuadrante;
import org.proundmega.sudokucore.elementos.ValidadorSudoku;
import org.proundmega.sudokucore.elementos.Valor;
import org.proundmega.sudokucore.elementos.grid.anotador.AnotadorCuadrante;
import org.proundmega.sudokucore.solver.SolverHelper;

public class SubGridCuadrante extends AbstractSubGrid {

    private Cuadrante cuadranteObjetivo;
    private Celda[][] solve;

    public SubGridCuadrante(Celda[][] celdas, Cuadrante cuadrante) {
        this.celdas = celdas;
        this.cuadranteObjetivo = cuadrante;
        this.solve = cuadrante.getCuadrante(celdas);
    }

    @Override
    public boolean estaCompleta() {
        return ValidadorSudoku.esCuadranteCompleto(solve);
    }

    @Override
    public boolean soloFaltaUnEspacio() {
        Celda[] arreglo = Arrays.stream(solve)
                .flatMap(tupla -> Arrays.stream(tupla))
                .toArray(Celda[]::new);
        return SolverHelper.soloFaltaUnEspacio(arreglo);
    }

    @Override
    protected List<Posicion> getPosiciones() {
        return cuadranteObjetivo.getCuadranteAsList(celdas);
    }

    public List<Posicion> getPosicionesVaciasAnotadas() {
        AnotadorCuadrante anotador = new AnotadorCuadrante(celdas, cuadranteObjetivo);
        List<Posicion> posicionesVaciasAnotadas = anotador.getPosicionesConAnotacionesRemovidas();
        
        return posicionesVaciasAnotadas;
    }
    
}
