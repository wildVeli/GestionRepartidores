/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlweb;

import control.Repartidor;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import javax.ws.rs.core.GenericType;
import rest.RepartidorRest;

/**
 *
 * @author ubuntu
 */
public class RepartidorManager implements InterfaceRepartidorManager {

    private static final Logger LOGGER= Logger.getLogger("controlweb");
    private RepartidorRest repartidorREST;
    
    public RepartidorManager (){
        repartidorREST = new RepartidorRest();
    }
    
    @Override
    public Collection getAllRepartidores() {
       LOGGER.info("Getting all repartidores");
       List <Repartidor> repartidores = repartidorREST.findAll_XML(new GenericType <List<Repartidor>>() {});
       return repartidores;
    }
    
}
