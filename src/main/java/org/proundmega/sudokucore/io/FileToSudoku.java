package org.proundmega.sudokucore.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.proundmega.sudokucore.Sudoku;

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
            String[][] valores = reader.lines()
                    .filter(linea -> !linea.trim().isEmpty())
                    .map(this::getValores)
                    .toArray(String[][]::new);

            return new Sudoku(valores);
        } catch (IllegalArgumentException ex) {
            throw new InvalidSudokuFormatException("El archivo no cumple con el formato parseable de un sudoku", ex);
        }
    }

    private String[] getValores(String linea) {
        Matcher matcher = PATRON_SUDOKU.matcher(linea);
        String[] lineas = new String[9];

        for (int fila = 0; matcher.find(); fila++) {
            lineas[fila] = matcher.group(1);
        }
        return lineas;
    }
}
