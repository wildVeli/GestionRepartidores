/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.text.SimpleDateFormat;
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
    public Collection getPedidosBusquedaAvanzada(String selectedItem, DatePicker dpfechaEntrada, DatePicker dpfechaSalida) {
        
        Collection busqueda = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            
           // pedidos.stream().filter(e -> sdf.parse(e.getFechaEntrada()).after(sdf.parse(dpfechaEntrada.getEditor().getText())));
            
            boolean bEntrada = true;
            boolean bSalida = true;
            boolean guardar;
            for (PedidoBean pedido : pedidos) {
                guardar = true;
                System.out.println("datePedido "+sdf.parse(pedido.getFechaEntrada()));
               if(bEntrada && sdf.parse(pedido.getFechaEntrada()).before(sdf.parse(dpfechaEntrada.getEditor().getText()))){
                   guardar=false;
               }
               if(bSalida && sdf.parse(pedido.getFechaEntrada()).after(sdf.parse(dpfechaSalida.getEditor().getText()))){
                    guardar=false;
                }
                if(guardar){
                    busqueda.add(pedido);
                }
            }
            
        }catch(Exception e){
            
        }   
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
          /*  
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            boolean bEntrada = true;
            boolean bSalida = true;
            if(dpfechaEntrada.getEditor().getText().isEmpty()){
                bEntrada = false;
            }
            if(dpfechaSalida.getEditor().getText().isEmpty()){
                bSalida = false;
            }
            
            DateTimeFormatter format= DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate entrada = dpfechaEntrada.getValue();
            entrada.format(format);
            LocalDate salida = dpfechaSalida.getValue();
            salida.format(format);
            
            System.out.println("salida "+salida);
            System.out.println("entrada "+entrada);
            boolean guardar;
            for (PedidoBean pedido : pedidos) {
                guardar = true;
                LocalDate datePedido=LocalDate.parse(pedido.getFechaEntrada(),format);
                System.out.println("datePedido "+datePedido);
                if(bEntrada&&(datePedido.compareTo(entrada)<0)){
                    guardar=false;
                    
                    
                }
                if(bSalida&&(datePedido.compareTo(salida)>0)){
                    guardar=false;
                }
                if(guardar){
                    busqueda.add(pedido);
                }
            }
            System.out.println(busqueda.size());
            return busqueda;
        } catch (ParseException ex) {
            Logger.getLogger(PedidoTestDataGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
*/
    
    

