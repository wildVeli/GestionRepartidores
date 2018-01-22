///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
//*/
////Version without server
//
//package control;
//
//
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Date;
//import java.util.logging.Logger;
//import java.util.stream.Collectors;
//*/
///**
// *
// * @author Sergio López
// * Maneja los datos de pedidos, simulación de la capa de datos
// * 
// */
///*
//public class PedidoTestDataGenerator implements PedidosManager{
//    
//    private static final Logger LOGGER=Logger.getLogger("control");
//    private final ArrayList<PedidoBean>  pedidos;
//    private String formato = "dd/MM/yyyy";
//    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato);
//
//
//    /**
//     * Genera unos pedidos de prueba
//     */
//    public PedidoTestDataGenerator() {
//        pedidos=new ArrayList();
//        for (int i = 10; i < 31; i++) {
//            pedidos.add(new PedidoBean(i,i,i+"/01/2017",i+1+"/01/2017","Bilbao"+i,"A",i,i));
//            LOGGER.info("Generando pedidos de prueba");
//        }
//    }
//    
//    /**
//     * Añade un pedido a los existentes
//     * @param pedidoBean pedido que se añadirá a la colección
//     */
//    @Override
//    public void addPedido(PedidoBean pedidoBean){
//        pedidos.add(pedidoBean);
//        LOGGER.info("Nuevo pedido añadido a los datos");
//    }
//    /**
//     * Devuelve todos los pedidos
//     * @return colleción con todos los pedidos
//     */
//    @Override
//    public Collection getAllPedidos() {
//        LOGGER.info("Getting all pedidos");
//        return pedidos;
//    }
//    
//
//    /**
//     * Remplaza cierta información de un pedido
//     * @param pedidoBean pedido que contiene la nueva información modificada
//     */
//    @Override
//    public void updatePedido(PedidoBean pedidoBean) {
//        for (PedidoBean pedido : pedidos) {
//            if(pedido.getNSeguimiento().equals(pedidoBean.getNSeguimiento())){
//                pedidos.remove(pedido);
//                pedidos.add(pedidoBean);
//                LOGGER.info("Pedido modificado en los datos");
//                break;
//            }
//        }
//    }
//
//    /**
//     * Efectúa una selección de ciertos pedidos según unos parámetros
//     * @param selectedItem contiene porque parámetro se filtrará
//     * @param tfBuscarSimple texto del usuario para filtrar la búsqueda
//     * @return colección de pedidos que cuadren con los parámetros de búsqueda
//     */
//    @Override
//    public Collection getPedidosBusquedaSimple(String selectedItem, String tfBuscarSimple) {
//        Collection busqueda = null;
//        switch(selectedItem){
//            case ("N.Seguimiento"):
//                 busqueda = pedidos.stream().filter(e -> e.getNSeguimiento().toString().equals(tfBuscarSimple)).collect(Collectors.toList());
//                break;
//            case ("Destino"):
//                busqueda = pedidos.stream().filter(e -> e.getDestino().equals(tfBuscarSimple)).collect(Collectors.toList());
//                break;
//            case ("Albarán"):
//                busqueda = pedidos.stream().filter(e -> e.getAlbaran().toString().equals(tfBuscarSimple)).collect(Collectors.toList());
//                break;
//            case ("Fecha Entrada"):
//                busqueda = pedidos.stream().filter(e ->e.getFechaEntrada().equals(tfBuscarSimple)).collect(Collectors.toList());
//                break;
//        }
//        return busqueda;
//    }
//
//    /**
//     * Efectúa una selección de ciertos pedidos según unos parámetros
//     * @param selectedItem  área seleccionada por la que se filtrará
//     * @param dpfechaEntrada contiene la fecha de inicio con la que se filtrará
//     * @param dpfechaSalida contiene la fecha final con la que se filtrará
//     * @return colección de pedidos que cuadren con los parámetros de búsqueda
//     */
//    @Override
//    public Collection getPedidosBusquedaAvanzada(String selectedItem, LocalDate dpfechaEntrada, LocalDate dpfechaSalida,AreaManager areaManager) {
//        
//        Collection<PedidoBean> busqueda = null;
//
//        int numeroArea=areaManager.getNumeroArea(selectedItem);
//
//        
//        if(dpfechaEntrada!=null){
//            
//             busqueda = pedidos.stream().filter(c -> LocalDate.parse(c.getFechaEntrada(), formatter).compareTo(dpfechaEntrada)>=0)
//                               .collect(Collectors.toList());
//        }
//        if(dpfechaSalida!=null){
//             busqueda = busqueda.stream().filter(c -> LocalDate.parse(c.getFechaSalida(), formatter).compareTo(dpfechaSalida)<=0)
//                               .collect(Collectors.toList());
//        }
//        if(!selectedItem.equals("Todas las áreas")){
//            busqueda = busqueda.stream().filter(c ->c.getArea().equals(numeroArea)).collect(Collectors.toList());
//        }
//        LOGGER.info("Búsqueda finalizada "+busqueda.size()+" resultados");
//
//        return busqueda;
//    }
//
//    /**
//     * Elimina un pedido
//     * @param nSeguimiento número de seguimiento del pedido a eliminar
//     */
//    @Override
//    public void removePedido(Integer nSeguimiento) {
//        for (PedidoBean pedido : pedidos) {
//            if(nSeguimiento==pedido.getNSeguimiento()){
//                pedidos.remove(pedido);
//                LOGGER.info("pedido eliminado de los datos");
//                break;
//            }
//            
//        }
//    }
//
//    /**
//     * Crea los datos para un nuevo pedido
//     * @return devuelve un nuevo pedido con los datos basicos generados en base a los pedidos existentes
//     */
//    @Override
//    public PedidoBean getDatosNuevoPedido(){
//        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        Date date = new Date();
//        PedidoBean pedido=new PedidoBean(pedidos.get(pedidos.size()-1).getNSeguimiento()+1
//                ,pedidos.get(pedidos.size()-1).getAlbaran()+1,dateFormat.format(date),"","","",0,0);
//        return pedido;
//    }
//
//
//}
