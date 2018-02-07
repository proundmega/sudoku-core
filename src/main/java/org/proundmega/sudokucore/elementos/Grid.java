package org.proundmega.sudokucore.elementos;

import org.proundmega.sudokucore.utils.ValidadorSudoku;
import org.proundmega.sudokucore.utils.Valores;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.proundmega.sudokucore.Posicion;
import org.proundmega.sudokucore.elementos.anotador.Anotador;
import org.proundmega.sudokucore.elementos.anotador.AnotadorGeneral;

@EqualsAndHashCode
public class Grid {
    private Valor[][] valores = new Valor[9][9];
    
    public Grid() {
        crearValoresVacios();
    }

    private void crearValoresVacios() {
        for (int fila = 0; fila < 9; fila++) {
            for (int columna = 0; columna < 9; columna++) {
                valores[fila][columna] = Valor.VACIA;
            }
        }
    }
    
    public Grid(Valor[][] valores) {
        verificarValoresEnviadas(valores);
        this.valores = copiarValores(valores);
    }
    
    private Valor[][] copiarValores(Valor[][] valores) {
        Valor[][] copia = new Valor[9][9];
        for(int fila = 0; fila < 9; fila++) {
            for(int columna = 0; columna < 9; columna++) {
                copia[fila][columna] = valores[fila][columna];
            }
        }
        return copia;
    }
    
    public Grid(String[][] gridAsString) {
        verificarValoresEnviadas(gridAsString);
        pasarLosValoresAValor(gridAsString);
    }

    private void pasarLosValoresAValor(String[][] gridAsString) {
        for(int fila = 0; fila < 9; fila++) {
            for(int columna = 0; columna < 9; columna++) {
                Valor valor = Valor.toValor(gridAsString[fila][columna]);
                this.valores[fila][columna] = valor;
            }
        }
    }
    
    private static <T> void verificarValoresEnviadas(@NonNull T[][] celdasEnviadas) throws IllegalArgumentException {
        if (celdasEnviadas.length == 0) { 
            throw new IllegalArgumentException("Matriz de sudoku con numero de filas 0...");
        }
        if (celdasEnviadas.length != 9 || celdasEnviadas[0].length != 9) {
            throw new IllegalArgumentException("Las celdas enviadas poseen dimensiones " + celdasEnviadas.length + "x" + celdasEnviadas[0].length + " por lo que no es un " + "Sudoku valido");
        }
    }

    @Override
    public String toString() {
        StringBuilder print = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            Valor[] fila = valores[i];
            print.append(printFila(fila));
            
            if (i == 2 || i == 5) {
                print.append("-------------------------------\n");
            }
        }
        
        return print.toString();
    }

    private String printFila(Valor[] fila) {
        StringBuilder print = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            Valor celda = fila[i];
            print.append("[").append(celda.toString()).append("]");
            
            if (i == 2 || i == 5) {
                print.append("  ");
            }
        }
        print.append("\n");
        return print.toString();
    }
    
    public Grid reemplazarCasilla(@NonNull Fila fila, @NonNull Columna columna, @NonNull Valor valor) {
        Valor[][] copia = copiarValores(valores);
        copia[fila.getIndiceFilaParaArray()][columna.getIndiceColumnaParaArray()] = valor;
        
        return new Grid(copia);
    }
    
    public Grid reemplazarCasilla(Posicion posicion) {
        return reemplazarCasilla(posicion.getFila(), posicion.getColumna(), posicion.getValorActual());
    }
    
    public boolean isGridResuelta() {
        return ValidadorSudoku.esValorsValidasYCompletas(valores);
    }
    
    public Posicion getPosicion(Fila fila, Columna columna) {
        return Valores.getPosicion(valores, fila, columna);
    }

    public List<Posicion> getPosiciones() {
        return Valores.asPosiciones(valores);
    }
    
    public String[][] getValoresAsString() {
        String[][] gridAsString = new String[9][9];
        
        for(int fila = 0; fila < 9; fila++) {
            for(int columna = 0; columna < 9; columna++) {
                gridAsString[fila][columna] = valores[fila][columna].toString();
            }
        }
        
        return gridAsString;
    }
    
    public Anotador getAnotador(Posicionable posicionable) {
        return new AnotadorGeneral(valores, posicionable);
    }
}
