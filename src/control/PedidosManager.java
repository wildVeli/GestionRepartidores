/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.time.LocalDate;
import java.util.Collection;


/**
 *
 * @author Sergio López
 */
public interface PedidosManager {
    
     /**
     * Devuelve todos los pedidos
     * @return colleción con todos los pedidos
     */
    public Collection getAllPedidos();
    /**
     * Añade un pedido a los existentes
     * @param pedidoBean pedido que se añadirá a la colección
     */
    public void addPedido(PedidoBean pedidoBean);
     /**
     * Remplaza cierta información de un pedido
     * @param pedidoBean pedido que contiene la nueva información modificada
     */
    public void updatePedido(PedidoBean pedidoBean);
     /**
     * Efectúa una selección de ciertos pedidos según unos parámetros
     * @param selectedItem contiene porque parámetro se filtrará
     * @param tfBuscarSimple texto del usuario para filtrar la búsqueda
     * @return colección de pedidos que cuadren con los parámetros de búsqueda
     */
    public Collection getPedidosBusquedaSimple(String selectedItem, String tfBuscarSimple);
      /**
     * Efectúa una selección de ciertos pedidos según unos parámetros
     * @param selectedItem  área seleccionada por la que se filtrará
     * @param dpfechaEntrada contiene la fecha de inicio con la que se filtrará
     * @param dpfechaSalida contiene la fecha final con la que se filtrará
     * @return colección de pedidos que cuadren con los parámetros de búsqueda
     */
    public Collection getPedidosBusquedaAvanzada(String selectedItem, LocalDate dpfechaEntrada, LocalDate dpfechaSalida,AreaManager areaManager);
        /**
     * Elimina un pedido
     * @param nSeguimiento número de seguimiento del pedido a eliminar
     */
    public void removePedido(Integer nSeguimiento);
    /**
     * Crea los datos para un nuevo pedido
     * @return devuelve un nuevo pedido con los datos basicos generados en base a los pedidos existentes
     */
    public PedidoBean getDatosNuevoPedido();
}
