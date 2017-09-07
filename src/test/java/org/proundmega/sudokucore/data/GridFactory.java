package org.proundmega.sudokucore.data;

import java.util.ArrayList;
import java.util.List;
import org.proundmega.sudokucore.Celda;
import org.proundmega.sudokucore.Valor;
import static org.proundmega.sudokucore.Valor.*;

public class GridFactory {
    
    public static Celda[][] getSudokuResueltoValido1() {
        return new Celda[][] {
            crearCeldas( _8,    _7,    _1,      _2,    _3,    _9,       _4,   _5,   _6),
            crearCeldas( _4,    _5,    _9,      _6,    _1,    _7,       _8,   _2,   _3),
            crearCeldas( _6,    _2,    _3,      _4,    _5,    _8,       _7,   _9,   _1),
            
            crearCeldas( _2,    _4,    _6,      _3,    _7,    _1,      _9,   _8,   _5),
            crearCeldas( _9,    _1,    _8,      _5,    _4,    _6,      _2,   _3,   _7),
            crearCeldas( _5,    _3,    _7,      _8,    _9,    _2,      _1,   _6,   _4),
            
            crearCeldas( _7,    _8,    _4,      _9,    _6,    _5,       _3,   _1,   _2),
            crearCeldas( _1,    _6,    _2,      _7,    _8,    _3,       _5,   _4,   _9),
            crearCeldas( _3,    _9,    _5,      _1,    _2,    _4,       _6,   _7,   _8)
        };
    }
    
    public static Celda[] crearCeldas(Valor... valores) {
        List<Celda> celdas = new ArrayList<>();
        for (Valor valor : valores) {
            celdas.add(new Celda(valor));
        }
        
        return celdas.toArray(new Celda[9]);
    }
    
    public static Celda[][] getSudokuIncompleto1() {
        return new Celda[][] {
            crearCeldas( _8,    _7,    _1,      _2,    _3,    _9,       _4,   _5,   _6),
            crearCeldas( _4,    _5,    _9,      _6,    _1,    _7,       _8,   _2,   _3),
            crearCeldas( _6,    _2,    _3,      _4,    _5,    _8,       _7,   _9,   _1),
            
            crearCeldas( _2,    _4,    _6,      _3,    _7,    _1,      _9,   _8,   _5),
            crearCeldas( _9,    _1,    _8,      _5,    _4,    _6,      _2,   _3,   _7),
            crearCeldas( _5,    _3,    _7,      _8,    _9,    _2,      _1,   _6,   _4),
            
            crearCeldas( _7,    _8,    _4,      _9,    _6,    _5,       _3,   _1,   _2),
            crearCeldas( _1,    _6,    _2,      _7,    _8,    _3,       _5,   _4,   _9),
            crearCeldas( _3,    _9,    _5,      _1,    _2,    _4,       _6,   _7,   VACIA)
        };
    }
    
    public static Celda[][] getSudokuInvalido1() {
        return new Celda[][] {
            crearCeldas( _8,    _7,    _5,      _2,    _3,    _9,       _4,   _5,   _6),
            crearCeldas( _4,    _5,    _9,      _6,    _1,    _7,       _8,   _2,   _3),
            crearCeldas( _6,    _2,    _3,      _4,    _5,    _8,       _7,   _9,   _1),
            
            crearCeldas( _2,    _4,    _6,      _3,    _7,    _1,      _9,   _8,   _5),
            crearCeldas( _9,    _1,    _8,      _5,    _4,    _6,      _2,   _3,   _7),
            crearCeldas( _5,    _3,    _9,      _8,    _9,    _2,      _1,   _6,   _4),
            
            crearCeldas( _7,    _8,    _4,      _9,    _6,    _5,       _3,   _1,   _2),
            crearCeldas( _1,    _6,    _2,      _7,    _8,    _3,       _5,   _4,   _9),
            crearCeldas( _3,    _9,    _5,      _1,    _2,    _4,       _6,   _7,   _8)
        };
    }
}
