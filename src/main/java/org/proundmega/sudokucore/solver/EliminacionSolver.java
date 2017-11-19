package org.proundmega.sudokucore.solver;

import org.proundmega.sudokucore.Respuesta;
import java.util.List;
import java.util.Optional;
import org.proundmega.sudokucore.solver.procesadores.Intercambio;
import org.proundmega.sudokucore.Posicion;
import org.proundmega.sudokucore.elementos.Columna;
import org.proundmega.sudokucore.elementos.Cuadrante;
import org.proundmega.sudokucore.elementos.Valor;
import org.proundmega.sudokucore.elementos.grid.Grid;
import org.proundmega.sudokucore.elementos.grid.SubGridCuadrante;
import org.proundmega.sudokucore.solver.procesadores.PipelineProcesadores;
import org.proundmega.sudokucore.solver.procesadores.ProcesadorAnotaciones;
import org.proundmega.sudokucore.solver.procesadores.ValorConUnicaPosicion;

public class EliminacionSolver implements Solver {

    @Override
    public Respuesta apply(Grid gridOriginal) {
        for(Cuadrante cuadrante: Cuadrante.values()) {
            Grid respuesta = resolverCuadrante(gridOriginal, cuadrante);
            
            if(seResolvioUnaCasilla(gridOriginal, respuesta)) {
                return new Respuesta(respuesta, true, this);
            }
        }
        
        return new Respuesta(gridOriginal, false, this);
    }

    private static boolean seResolvioUnaCasilla(Grid gridOriginal, Grid respuesta) {
        return !gridOriginal.equals(respuesta);
    }

    private Grid resolverCuadrante(Grid grid, Cuadrante cuadrante) {
        SubGridCuadrante subGrid = grid.getSubGrid(cuadrante);
        List<Posicion> valoresVacios = subGrid.getPosicionesVaciasAnotadas();

        Pipeline<List<Posicion>, Optional<Intercambio>, ProcesadorAnotaciones> pipelineProcesadoresSimples 
                = PipelineProcesadores.getPipelineProcesadoresSimples(valoresVacios);
        
        Optional<Intercambio> respuesta = pipelineProcesadoresSimples.get();
        
        if(respuesta.isPresent()) {
            Intercambio valor = respuesta.get();
            return grid.reemplazarCasilla(valor.getPosicionVacia().getFila()
                    , valor.getPosicionVacia().getColumna()
                    , valor.getNuevoValor());
        }
        else {
            return grid;
        }
    }
    
    @Override
    public String getMetodoUsado() {
        return "Deduccion simple";
    }
    
}
