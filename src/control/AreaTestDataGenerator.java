/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Sergio López Fuentefría
 * Maneja los datos de las areas, simulación de la capa de datos
 */
public class AreaTestDataGenerator implements AreaManager {
    
    private static final Logger LOGGER=Logger.getLogger("control");
    private ArrayList<AreaBean> areas;

    /**
     * Genera datos de prueba de las areas
     */
    @Override
    public void AreaTestDataGenerator(){
        areas=new ArrayList();
        for (int i = 0; i < 10; i++) {
            areas.add(new AreaBean(i,"Bilbao"+i,"Bonito",i+1));  
            LOGGER.info("Generando áreas de prueba");
        }
    }
    /**
     * Recoge todos los nombres de las áreas
     * @return una colección con los nombres de las áreas
     */
    @Override
    public Collection getAllAreaNames() {
        Collection areaNames= areas.stream().map(a -> a.getNombre()).collect(Collectors.toList());
        LOGGER.info("Devolviendo todas las áreas");
        return areaNames;
    }
    
    
    
}
