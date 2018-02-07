package org.proundmega.sudokucore.internacionalization;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18NUtils {
    
    /** Toda la internacionalizacion es basada usando este metodo. La idea es en lo
     *  posible abstraer todo lo relacionado a internacionalizacion usando la clase
     *  que posee el dato de internacionalizacion y la propiedad a obtener.
     *  
     *  Para que esto funcione, cada .properties debe ser mapeado con el nombre de la clase.
     *  Por ejemplo si la clase es org.proundmega.Hola, el .properties debe ir como
     *  org.proundmega.Hola.properties
     * 
     * @param clase
     * @param locale
     * @param propiedad
     * @return 
     */
    public static String getInternationalizationString(Class<?> clase, Locale locale, String propiedad) {
        return ResourceBundle.getBundle(clase.getCanonicalName(), locale)
                .getString(propiedad);
    }
}
