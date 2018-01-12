/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlweb;

import control.AreaManager;
import control.PedidoBean;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import rest.PedidoRest;

/**
 *
 * @author ubuntu
 */
public class PedidoManager implements PedidosManager{
    
    private static final Logger LOGGER= Logger.getLogger("controlweb");
    PedidoRest pedidoREST;
    /**
     * Añade un pedido a los existentes
     * @param pedidoBean pedido que se añadirá a la colección
     */
    public void addPedido(PedidoBean pedidoBean){
        LOGGER.info("Nuevo pedido añadido a los datos");
        pedidoREST.create_XML(pedidoBean);
    }
    /**
     * Devuelve todos los pedidos
     * @return colleción con todos los pedidos
     */
    @Override
    public Collection getAllPedidos() {
        LOGGER.info("Getting all pedidos");
        return null;
    }
    

    /**
     * Remplaza cierta información de un pedido
     * @param pedidoBean pedido que contiene la nueva información modificada
     */
    @Override
    public void updatePedido(PedidoBean pedidoBean) {

    }

    /**
     * Efectúa una selección de ciertos pedidos según unos parámetros
     * @param selectedItem contiene porque parámetro se filtrará
     * @param tfBuscarSimple texto del usuario para filtrar la búsqueda
     * @return colección de pedidos que cuadren con los parámetros de búsqueda
     */
    @Override
    public Collection getPedidosBusquedaSimple(String selectedItem, String tfBuscarSimple) {

        return null;
    }

    /**
     * Efectúa una selección de ciertos pedidos según unos parámetros
     * @param selectedItem  área seleccionada por la que se filtrará
     * @param dpfechaEntrada contiene la fecha de inicio con la que se filtrará
     * @param dpfechaSalida contiene la fecha final con la que se filtrará
     * @return colección de pedidos que cuadren con los parámetros de búsqueda
     */
    @Override
    public Collection getPedidosBusquedaAvanzada(String selectedItem, LocalDate dpfechaEntrada, LocalDate dpfechaSalida,AreaManager areaManager) {
        return null;
    }

    /**
     * Elimina un pedido
     * @param nSeguimiento número de seguimiento del pedido a eliminar
     */
    @Override
    public void removePedido(Integer nSeguimiento) {
    }

    /**
     * Crea los datos para un nuevo pedido
     * @return devuelve un nuevo pedido con los datos basicos generados en base a los pedidos existentes
     */
    @Override
    public PedidoBean getDatosNuevoPedido(){
        return null;
    }


}
