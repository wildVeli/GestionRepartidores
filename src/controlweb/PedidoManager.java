/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlweb;

import control.PedidoBean;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import javax.ws.rs.core.GenericType;
import rest.PedidoRest;

/**
 *
 * @author ubuntu
 */
public class PedidoManager implements PedidosManager{
    
    private static final Logger LOGGER= Logger.getLogger("controlweb");
    private PedidoRest pedidoREST;
    
    public PedidoManager (){
        pedidoREST = new PedidoRest();
    }
    /**
     * Añade un pedido a los existentes
     * @param pedidoBean pedido que se añadirá a la colección
     */
    @Override
    public void addPedido(PedidoBean pedidoBean){
        LOGGER.info("Nuevo pedido añadido a los datos");
        pedidoREST.create_XML(pedidoBean);
    }
    /**
     * Devuelve todos los pedidos
     * @return colleción con todos los pedidos
     */
    @Override
    public Collection<PedidoBean> getAllPedidos() {
        LOGGER.info("Getting all pedidos");
        List<PedidoBean> pedidos = pedidoREST.findAll_XML(new GenericType<List<PedidoBean>>() {});
        return pedidos;
    }
    

    /**
     * Remplaza cierta información de un pedido
     * @param pedidoBean pedido que contiene la nueva información modificada
     */
    @Override
    public void updatePedido(PedidoBean pedidoBean) {
        LOGGER.info("Updating pedido");
        pedidoREST.update_XML(pedidoBean);
    }

    /**
     * Efectúa una selección de ciertos pedidos según unos parámetros
     * @param selectedItem contiene porque parámetro se filtrará
     * @param tfBuscarSimple texto del usuario para filtrar la búsqueda
     * @return colección de pedidos que cuadren con los parámetros de búsqueda
     */
    @Override
    public Collection getPedidosBusquedaSimple(String selectedItem, String tfBuscarSimple) {
        LOGGER.info("Búsqueda simple");
        List<PedidoBean> pedidos = pedidoREST.busquedaSimple_XML(new GenericType<List<PedidoBean>>() {},selectedItem,tfBuscarSimple);
        return pedidos;
    }

    /**
     * Efectúa una selección de ciertos pedidos según unos parámetros
     * @param selectedItem  área seleccionada por la que se filtrará
     * @param dpfechaEntrada contiene la fecha de inicio con la que se filtrará
     * @param dpfechaSalida contiene la fecha final con la que se filtrará
     * @return colección de pedidos que cuadren con los parámetros de búsqueda
     */
    @Override
    public Collection getPedidosBusquedaAvanzada(String selectedItem, String dpfechaEntrada, String dpfechaSalida) {
        LOGGER.info("Búsqueda avanzada");
        List<PedidoBean> pedidos = pedidoREST.busquedaAvanzada_XML(new GenericType<List<PedidoBean>>() {},selectedItem,dpfechaEntrada,dpfechaSalida);
        return pedidos;
    }

    /**
     * Elimina un pedido
     * @param nSeguimiento número de seguimiento del pedido a eliminar
     */
    @Override
    public void removePedido(String nSeguimiento) {
        LOGGER.info("Removing pedido");
        pedidoREST.remove(nSeguimiento);
    }

    /**
     * Crea los datos para un nuevo pedido
     * @return devuelve un nuevo pedido con los datos basicos generados en base a los pedidos existentes
     */
    @Override
    public PedidoBean getDatosNuevoPedido(){
        LOGGER.info("Getting new Pedido data");
        PedidoBean pedido = pedidoREST.getDatosNuevoPedido_XML(PedidoBean.class);
        return pedido;
    }

   


}
