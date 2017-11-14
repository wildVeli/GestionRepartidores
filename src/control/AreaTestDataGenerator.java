/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 *
 * @author Sergio López
 * Maneja los datos de las areas
 */
public class AreaTestDataGenerator implements AreaManager {
    
    private ArrayList<AreaBean> areas;

    @Override
    public void AreaTestDataGenerator(){
        areas=new ArrayList();
        for (int i = 0; i < 10; i++) {
            areas.add(new AreaBean(i,"Bilbao"+i,"Bonito",i+1));         
        }
    }
    @Override
    public Collection getAllAreaNames() {
        Collection areaNames= areas.stream().map(a -> a.getNombre()).collect(Collectors.toList());
        return areaNames;
    }
    
    
    
}
