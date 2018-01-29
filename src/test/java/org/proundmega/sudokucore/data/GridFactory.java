package org.proundmega.sudokucore.data;

import java.io.IOException;
import org.proundmega.sudokucore.elementos.Valor;
import org.proundmega.sudokucore.io.FileToSudoku;
import org.proundmega.sudokucore.io.TestUtils;

public class GridFactory {
    
    public static Valor[][] getSudokuFacil1Resuelto() {
        return loadSudokuValor("1_resuelto.txt");
    }
    
    private static Valor[][] loadSudokuValor(String nombre) {
        try {
            return new FileToSudoku().crearSudokuAsValor(TestUtils.getTestResource("test", nombre));
        } catch (IOException e) {
            throw new RuntimeException("No se pudo leer el sudoku" +  nombre);
        }
    }
    
    private static Valor[][] loadSudoku(String nombre) {
        try {
            return new FileToSudoku().crearSudokuAsValor(TestUtils.getTestResource("test", nombre));
        } catch (IOException e) {
            throw new RuntimeException("No se pudo leer el sudoku" +  nombre);
        }
    }
    
    public static Valor[][] getSudokuIncompleto1() {
        return loadSudokuValor("1_incompleto_v1.txt");
    }
    
    public static Valor[][] getSudokuIncompleto2() {
        return loadSudokuValor("1_incompleto_v2.txt");
    }
    
    public static Valor[][] getSudokuInvalido1() {
        return loadSudokuValor("1_invalido_v1.txt");
    }
    
    public static Valor[][] getSudokuFacil1() {
        return loadSudokuValor("1.txt");
    }
    
    public static Valor[][] getSudokuFacil2() {
        return loadSudokuValor("2.txt");
    }
    
}
