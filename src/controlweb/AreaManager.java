/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlweb;

import control.AreaBean;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import javax.ws.rs.core.GenericType;
import rest.AreaRest;

/**
 *
 * @author ubuntu
 */
public class AreaManager implements InterfaceAreaManager {

    private static final Logger LOGGER= Logger.getLogger("controlweb");
    private AreaRest areaREST;
    
    public AreaManager (){
        areaREST = new AreaRest();
    }
    
    @Override
    public Collection getAllAreas() {
       LOGGER.info("Getting all areas");
       List <AreaBean> areas = areaREST.findAll_XML(new GenericType <List<AreaBean>>() {});
       return areas;
    }
    
}
