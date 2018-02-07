package org.proundmega.sudokucore.elementos.anotador;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.proundmega.sudokucore.Posicion;
import org.proundmega.sudokucore.elementos.Valor;
import org.proundmega.sudokucore.utils.Valores;

/** Esta clase busca filtrar las posiciones de todo un sudoku en base a 2 reglas:
 *  1) las posiciones no puede ser ninguna de las que estan en el filtro.
 *  2) las posiciones no pueden tener una propiedad en común especificada por una función (fila, columna, etc)
 * 
 *  Esta clase busca hacer ese tipo de filtrado facil de hacer 
 * 
 * @author vansi
 */
@AllArgsConstructor
public class AnotadorMapper {
    private Valor[][] celdas;
    private List<Posicion> filtros;
    
    
    public List<Posicion> groupByFila() {
        return getPosicionesEnBaseAFuncionTomandoComoBase(filtros, celdas, Posicion::getFila);
    }
    
    public List<Posicion> groupByColumna() {
        return getPosicionesEnBaseAFuncionTomandoComoBase(filtros, celdas, Posicion::getColumna);
    }
    
    public List<Posicion> groupByCuadrante() {
        return getPosicionesEnBaseAFuncionTomandoComoBase(filtros, celdas, Posicion::getCuadrante);
    }
    
    public List<Posicion> groupByFilaColumnaYCuadrante() {
        List<Posicion> posiciones = groupByFila();
        posiciones.addAll(groupByColumna());
        posiciones.addAll(groupByCuadrante());
        
        return posiciones;
    }
    
    /** Este metodo busca obtener todas las posiciones que cumplan con 2 criterios:
     *  1) las posiciones no puede ser ninguna de las que estan en el filtro.
     *  2) las posiciones poseen una propiedad en común con las usadas como base (fila, columna, etc)
     * 
     *  Imaginemos que tenemos la posicion (1, 2 : 5). Si deseamos obtener todas las posiciones que son de la misma fila ejecutamos
     * 
     *  <code>getPosicionesEnBaseAFuncionTomandoComoBase(Arrays.asList(posicion12), celdas, Posicion::getFila);</code>
     * 
     *  Si buscamos añadir ahora que se incluyan los valores que poseen el mismo cuadrante entre (3, 2 : 2) (5, 8 : 1) hacemos:
     * 
     *  <code>getPosicionesEnBaseAFuncionTomandoComoBase(Arrays.asList(posicion32, posicion58), celdas, Posicion::getCuadrante);</code>
     * 
     * @param <T> la propiedad en comun entre las posiciones
     * @param posicionesFiltro las posiciones que se usan como referencia para obtener su valor
     * @param celdas el sudoku de trabajo
     * @param mapper la funcion que define que propiedad en comun deben tener las posiciones para ser parte del grupo
     * @return todas las posiciones que comparten al mapper excluyendo las posiciones filtro
     */
    public static <T> List<Posicion> getPosicionesEnBaseAFuncionTomandoComoBase(List<Posicion> posicionesFiltro, Valor[][] celdas, Function<Posicion, T> mapper) {
        Set<T> valoresEnComun = posicionesFiltro.stream()
                .map(mapper)
                .collect(Collectors.toSet());
        
        return Valores.asPosiciones(celdas).stream()
                .filter(posicion -> valoresEnComun.contains(mapper.apply(posicion)))
                .filter(posicion -> !posicionesFiltro.contains(posicion))
                .collect(Collectors.toList());
    }
    
}
