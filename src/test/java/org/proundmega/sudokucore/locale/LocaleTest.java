package org.proundmega.sudokucore.locale;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import org.junit.Test;
import static org.junit.Assert.*;
import org.proundmega.sudokucore.MetadataSolver;
import org.proundmega.sudokucore.Respuesta;
import org.proundmega.sudokucore.Sudoku;
import org.proundmega.sudokucore.data.GridFactory;
import org.proundmega.sudokucore.elementos.Cuadrante;
import org.proundmega.sudokucore.elementos.Grid;
import org.proundmega.sudokucore.solver.procesadores.ProcesadorAnotaciones;
import org.proundmega.sudokucore.solver.procesadores.SolucionSimple;

public class LocaleTest {
    
    @Test
    public void probarInternacionalizacionEN() {
        Locale locale = new Locale("en");
        verificarTextoLocale(locale, "this case");
    }

    private void verificarTextoLocale(Locale locale, String textoEnLocale) {
        Grid grid = new Grid(GridFactory.getSudokuFacil1());
        Cuadrante bloque = Cuadrante.SUPERIOR_DERECHO;
        
        ProcesadorAnotaciones procesador = new SolucionSimple();
        Optional<MetadataSolver> apply = procesador.apply(grid.getAnotador(bloque));
        
        assertTrue(apply.isPresent());
        apply.ifPresent(respuesta -> {
            assertTrue(respuesta.getExplicacion(locale).contains(textoEnLocale));
        });
    }
    
    @Test
    public void probarInternacionalizacionES() {
        Locale locale = new Locale("es");
        verificarTextoLocale(locale, "este caso");
    }
    
    @Test
    public void iterarYObtenerValoresInternacionalizadosCorrectos() {
        Locale locale = new Locale("es");
        Grid grid = new Grid(GridFactory.getSudokuFacil1());
        Sudoku sudoku = new Sudoku(grid);
        
        List<Respuesta> solvePorPasos = sudoku.solvePorPasos();
        solvePorPasos.forEach(respuesta -> respuesta.getMetadatos().getExplicacionBundle().getMensaje(locale));
        assertTrue(true);
    }
}
