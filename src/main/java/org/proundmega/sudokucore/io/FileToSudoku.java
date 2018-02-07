package org.proundmega.sudokucore.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.AllArgsConstructor;
import org.proundmega.sudokucore.Sudoku;
import org.proundmega.sudokucore.utils.Valores;
import org.proundmega.sudokucore.elementos.Valor;

@AllArgsConstructor
public class FileToSudoku implements Serializable {

    private static final Pattern PATRON_SUDOKU = Pattern.compile(Pattern.quote("[") + "(.*?){1}" + Pattern.quote("]"));

    public Sudoku crearSudoku(File archivo) throws IOException {
        return readArchivoYParsearlo(new FileReader(archivo));
    }
    
    public Sudoku crearSudoku(InputStreamReader reader) throws IOException {
        return readArchivoYParsearlo(reader);
    }

    private Sudoku readArchivoYParsearlo(Reader lector) throws IOException {
        try (BufferedReader reader = new BufferedReader(lector)) {
            String[][] valores = extraerValores(reader);

            return new Sudoku(valores);
        } catch (IllegalArgumentException ex) {
            throw new InvalidSudokuFormatException("El archivo no cumple con el formato parseable de un sudoku", ex);
        }
    }

    private static String[][] extraerValores(final BufferedReader reader) {
        String[][] valores = reader.lines()
                .filter(linea -> !linea.trim().isEmpty())
                .map(FileToSudoku::getValores)
                .toArray(String[][]::new);
        return valores;
    }

    private static String[] getValores(String linea) {
        Matcher matcher = PATRON_SUDOKU.matcher(linea);
        String[] lineas = new String[9];

        for (int fila = 0; matcher.find(); fila++) {
            lineas[fila] = matcher.group(1);
        }
        return lineas;
    }

    public static Valor[][] crearSudokuAsValor(File testResource) throws FileNotFoundException, IOException {
        return procesarArchivo(new FileReader(testResource), Valores::copyOf);
    }
    
    private static <T> T procesarArchivo(Reader reader, Function<String[][], T> funcion) throws IOException {
        try(BufferedReader bufferedReader = new BufferedReader(reader)) {
            String[][] valores = extraerValores(bufferedReader);
            return funcion.apply(valores);
        }
    }
}