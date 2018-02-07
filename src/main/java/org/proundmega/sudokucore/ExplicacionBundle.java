package org.proundmega.sudokucore;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.Value;
import org.proundmega.sudokucore.internacionalization.I18NUtils;

/** Para añadir mas mensajes, se debe crear el respectivo archivo .properties, añadirle 
 *  las propiedades usando el formato {0}, {1}, etc y asi.
 * 
 * @author vansi
 */
@Data
public class ExplicacionBundle {
    private Class<?> clase;
    private List<Function<Locale, String>> parametrosInternacionalizados;

    public ExplicacionBundle(Class<?> clase) {
        this.clase = clase;
        this.parametrosInternacionalizados = new ArrayList<>();
    }
    
    public void addParametro(Function<Locale, String> funcion) {
        parametrosInternacionalizados.add(funcion);
    }
    
    public String getMensaje(Locale locale) {
        String mensaje = I18NUtils.getInternationalizationString(clase, locale, "mensaje");
        MessageFormat formatter = new MessageFormat(mensaje, locale);
        
        Object[] textos = parametrosInternacionalizados.stream()
                .map(funcion -> funcion.apply(locale))
                .toArray();
                
        return formatter.format(textos);
    }
}
