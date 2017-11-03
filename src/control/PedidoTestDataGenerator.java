/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

/**
 *
 * @author ubuntu
 */
public class PedidoTestDataGenerator implements PedidosManager{
    private static final Logger logger=Logger.getLogger("control");
    private ArrayList<PedidoBean>  pedidos;

    public PedidoTestDataGenerator() {
        pedidos=new ArrayList();
        for (int i = 0; i < 25; i++) {
            pedidos.add(new PedidoBean(i,i,i+"/1/2017",i+"/2/2017","Bilbao"+i,"A",i,i));
        }
    }
    @Override
    public Collection getAllPedidos() {
        logger.info("Getting all pedidos");
        return pedidos;
    }
    @Override
    public void pedidoExiste(Integer nSeguimiento){
        logger.info("Valida nSeguimiento existance");
        /*for
        if(){
            logger.severe("nSeguimiento already exist.");
            //excepion
        }
        */
    }
    
}
