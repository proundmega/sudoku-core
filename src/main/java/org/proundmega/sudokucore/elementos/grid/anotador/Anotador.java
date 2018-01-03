package org.proundmega.sudokucore.elementos.grid.anotador;

import java.util.List;
import org.proundmega.sudokucore.Posicion;
import org.proundmega.sudokucore.elementos.Valor;

public interface Anotador {
    List<Posicion> getPosicionesConAnotacionesRemovidas();
    
    /** Devuelve todos los numeros que eliminan espacios en un sudoku. Para entender esto mas
     *  facil es necesario un ejemplo. Digamos que tengo el siguiente sudoku, donde busco resolver
     *  3 en el cuadrante marcado con X:
     *      
     *  [ ][ ][ ] [ ][ ][ ]
     *  [.][X][.] [.][.][3]
     *  [ ][ ][ ] [ ][ ][ ]
     * 
     *  En este caso, el metodo devuelve las posiciones de todos los 3 que reducen las posiciones en las
     *  que puede ir 3 (marcadas con un punto)
     * 
     * @param valor el valor que se usa como base para la fila, columna, etc.
     * @return todas las posiciones que poseen este valor y limitan mi espacio.
     */
    List<Posicion> getPosicionesQueLimitanElValor(Valor valor);
    
    /** Devuelve todas las posiciones que limitan de alguna manera los numeros que 
     *  pueden ir en dicha posicion.
     * 
     *  Por ejemplo, deseo aplicar el metodo donde se encuentra la X con el numero 5 a nivel de cuandrantes:
     * 
     *  [1][ ][ ] [ ][ ][ ]
     *  [ ][X][4] [1][ ][7]
     *  [ ][3][ ] [ ][ ][ ]
     * 
     *  El cuadrante ya posee 1, 3 y 4 asi que se descartan. Como el unico numero que limita valores es 7,
     *  entonces se devuelve la posicion del 7
     * 
     * 
     * @param posicion la posicion de referencia
     * @return la posicion de todos los numeros que limitan en general los valores en la posicion enviada 
     *  como parametro
     */
    List<Posicion> getPosicionesLimitadoras(Posicion posicion);
}
