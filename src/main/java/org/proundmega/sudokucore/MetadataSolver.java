package org.proundmega.sudokucore;

import java.text.MessageFormat;
import java.util.Locale;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MetadataSolver {
    private PosicionBundle posicionBundle;
    private ExplicacionBundle explicacionBundle;
    
    public MetadataSolver() {
    }
    
    public String getExplicacion(Locale locale) {
        return explicacionBundle.getMensaje(locale);
    }

    public Posicion getPosicionResuelta() {
        return posicionBundle.getPosicionResuelta();
    }
}
