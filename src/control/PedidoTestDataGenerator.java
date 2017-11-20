/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.scene.control.DatePicker;

/**
 *
 * @author Sergio López
 * Maneja los datos de pedidos, simulación de la capa de datos
 * 
 */
public class PedidoTestDataGenerator implements PedidosManager{
    
    private static final Logger LOGGER=Logger.getLogger("control");
    private ArrayList<PedidoBean>  pedidos;

    /**
     * Genera unos pedidos de prueba
     */
    public PedidoTestDataGenerator() {
        pedidos=new ArrayList();
        for (int i = 0; i < 25; i++) {
            pedidos.add(new PedidoBean(i,i,i+"/1/2017",i+"/2/2017","Bilbao"+i,"A",i,i));
            LOGGER.info("Generando pedidos de prueba");
        }
    }
    
    /**
     * Añade un pedido a los existentes
     * @param pedidoBean pedido que se añadirá a la colección
     */
    @Override
    public void addPedido(PedidoBean pedidoBean){
        pedidos.add(pedidoBean);
        LOGGER.info("Nuevo pedido añadido a los datos");
    }
    /**
     * Devuelve todos los pedidos
     * @return colleción con todos los pedidos
     */
    @Override
    public Collection getAllPedidos() {
        LOGGER.info("Getting all pedidos");
        return pedidos;
    }
    /**
     * 
     * @param nSeguimiento 
     */
    @Override
    public void pedidoExiste(Integer nSeguimiento){
        LOGGER.info("Valida nSeguimiento existance");
        /*for
        if(){
            LOGGER.severe("nSeguimiento already exist.");
            //excepion
        }
        */
    }

    /**
     * Remplaza cierta información de un pedido
     * @param pedidoBean pedido que contiene la nueva información modificada
     */
    @Override
    public void updatePedido(PedidoBean pedidoBean) {
        for (PedidoBean pedido : pedidos) {
            if(pedido.getNSeguimiento().equals(pedidoBean.getNSeguimiento())){
                pedidos.remove(pedido);
                pedidos.add(pedidoBean);
                LOGGER.info("Pedido modificado en los datos");
                break;
            }
        }
    }

    /**
     * Efectua una selección de ciertos pedidos según unos parámetros
     * @param selectedItem contiene porque parámetro se filtrará
     * @param tfBuscarSimple texto del usuario para filtrar la búsqueda
     * @return colección de pedidos que cuadren con los parámetros de búsqueda
     */
    @Override
    public Collection getPedidosBusquedaSimple(String selectedItem, String tfBuscarSimple) {
        Collection busqueda = null;
        switch(selectedItem){
            case ("N.Seguimiento"):
                 busqueda = pedidos.stream().filter(e -> e.getNSeguimiento().toString().equals(tfBuscarSimple)).collect(Collectors.toList());
                break;
            case ("Destino"):
                busqueda = pedidos.stream().filter(e -> e.getDestino().equals(tfBuscarSimple)).collect(Collectors.toList());
                break;
            case ("Albarán"):
                busqueda = pedidos.stream().filter(e -> e.getAlbaran().toString().equals(tfBuscarSimple)).collect(Collectors.toList());
                break;
            case ("Fecha Entrada"):
                busqueda = pedidos.stream().filter(e ->e.getFechaEntrada().equals(tfBuscarSimple)).collect(Collectors.toList());
                break;
        }
        return busqueda;
    }

    /**
     * Efectua una selección de ciertos pedidos según unos parámetros
     * @param selectedItem  área seleccionada por la que se filtrará
     * @param dpfechaEntrada contiene la fecha de inicio con la que se filtrará
     * @param dpfechaSalida contiene la fecha final con la que se filtrará
     * @return colección de pedidos que cuadren con los parámetros de búsqueda
     */
    @Override
    public Collection getPedidosBusquedaAvanzada(String selectedItem, LocalDate dpfechaEntrada, LocalDate dpfechaSalida) {
        
        Collection busqueda = pedidos;
        /*
       // SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       
        LOGGER.info(dpfechaEntrada.toString());
        String x = pedidos.get(1).getFechaEntrada();
        x=LocalDate.parse(x, formatter).toString();
        String y= dpfechaEntrada.format(formatter);
        dpfechaEntrada.format(formatter);
        dpfechaSalida.format(formatter);
        LOGGER.info(dpfechaEntrada.toString());

        LOGGER.info(LocalDate.parse(x, formatter).toString());
        */
        //this.pedidos=(ArrayList<PedidoBean>) busqueda;
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        //NO SALE EN EL LOGGER!
        //NO SE PARA EL DEBUG
        LOGGER.severe("fecha clase :" +LocalDate.parse(pedidos.get(1).getFechaEntrada(), formatter));
        LOGGER.severe("hola "+dpfechaEntrada);
        
        if(dpfechaEntrada!=null){
             busqueda = pedidos.stream().filter(c -> LocalDate.parse(c.getFechaEntrada(), formatter).compareTo(dpfechaEntrada)>=0)
                               .collect(Collectors.toList());
        }
        if(dpfechaSalida!=null){
             busqueda = pedidos.stream().filter(c -> LocalDate.parse(c.getFechaSalida(), formatter).compareTo(dpfechaSalida)<=0)
                               .collect(Collectors.toList());
        }
        if(!selectedItem.equals("Todas las áreas")){
            busqueda = pedidos.stream().filter(c ->c.getArea().equals(selectedItem)).collect(Collectors.toList());
        }
        LOGGER.info("Búsqueda finalizada "+busqueda.size()+" resultados");

        return busqueda;
    }

    /**
     * Elimina un pedido
     * @param nSeguimiento número de seguimiento del pedido a eliminar
     */
    @Override
    public void removePedido(Integer nSeguimiento) {
        for (PedidoBean pedido : pedidos) {
            if(nSeguimiento==pedido.getNSeguimiento()){
                pedidos.remove(pedido);
                LOGGER.info("pedido eliminado de los datos");
                break;
            }
            
        }
    }

}
