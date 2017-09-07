package org.proundmega.sudokucore.elementos;

import java.util.Arrays;
import org.proundmega.sudokucore.Celda;

public enum Cuadrante {
    SUPERIOR_IZQUIERO {
        @Override
        public Celda[] getCuadrante(Celda[][] celdas) {
            return Arrays.stream(celdas, 0 , 3)
                    .flatMap(tupla -> Arrays.stream(tupla, 0, 3))
                    .toArray(Celda[]::new);
        }
    },
    SUPERIOR_CENTRAL {
        @Override
        public Celda[] getCuadrante(Celda[][] celdas) {
            return Arrays.stream(celdas, 0 , 3)
                    .flatMap(tupla -> Arrays.stream(tupla, 3, 6))
                    .toArray(Celda[]::new);
        }
    },
    SUPERIOR_DERECHO {
        @Override
        public Celda[] getCuadrante(Celda[][] celdas) {
            return Arrays.stream(celdas, 0 , 3)
                    .flatMap(tupla -> Arrays.stream(tupla, 6, 9))
                    .toArray(Celda[]::new);
        }
    },
    CENTRAL_IZQUIERO {
        @Override
        public Celda[] getCuadrante(Celda[][] celdas) {
            return Arrays.stream(celdas, 3 , 6)
                    .flatMap(tupla -> Arrays.stream(tupla, 0, 3))
                    .toArray(Celda[]::new);
        }
    },
    CENTRAL_CENTRAL {
        @Override
        public Celda[] getCuadrante(Celda[][] celdas) {
            return Arrays.stream(celdas, 3 , 6)
                    .flatMap(tupla -> Arrays.stream(tupla, 3, 6))
                    .toArray(Celda[]::new);
        }
    },
    CENTRAL_DERECHO {
        @Override
        public Celda[] getCuadrante(Celda[][] celdas) {
            return Arrays.stream(celdas, 3 , 6)
                    .flatMap(tupla -> Arrays.stream(tupla, 6, 9))
                    .toArray(Celda[]::new);
        }
    },
    INFERIOR_IZQUIERO {
        @Override
        public Celda[] getCuadrante(Celda[][] celdas) {
            return Arrays.stream(celdas, 6 , 9)
                    .flatMap(tupla -> Arrays.stream(tupla, 0, 3))
                    .toArray(Celda[]::new);
        }
    },
    INFERIOR_CENTRAL {
        @Override
        public Celda[] getCuadrante(Celda[][] celdas) {
            return Arrays.stream(celdas, 6 , 9)
                    .flatMap(tupla -> Arrays.stream(tupla, 3, 6))
                    .toArray(Celda[]::new);
        }
    },
    INFERIOR_DERECHO {
        @Override
        public Celda[] getCuadrante(Celda[][] celdas) {
            return Arrays.stream(celdas, 6 , 9)
                    .flatMap(tupla -> Arrays.stream(tupla, 6, 9))
                    .toArray(Celda[]::new);
        }
    };
    
    public abstract Celda[] getCuadrante(Celda[][] celdas);
    
}
