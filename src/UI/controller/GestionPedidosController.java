/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
FORMA 1 DE LA TABLA
crea una página cada vez que se pulsa en una nueva página de la paginación
refresca el ArrayList que ha pedido 1 vez a los datos para refrescar la tabla
y manda las consultas a los datos
--No puedo ejecutar los cambios en la tabla


FORMA ACTUAL DE LA TABLA
crea una página cada vez que se pulsa en una nueva página de la paginación, recoge los datos de la capa de datos directamente,
cada vez que se vuelve a la tabla
--Funciona
--No se ordena bien





*/
package UI.controller;

import control.AreaBean;
import control.PedidoBean;
import controlweb.InterfaceAreaManager;
import controlweb.InterfacePedidoManager;
import controlweb.InterfaceRepartidorManager;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.PopupFeatures;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.*;
import net.sf.jasperreports.view.*;


/**
 * 
 *
 * @author Sergio López Fuentefría
 */
public class GestionPedidosController  {

    private static final Logger LOGGER= Logger.getLogger("UI");

    private Stage stage;
    @FXML
    private TableColumn tbcolnSeguimiento;
    @FXML
    private TableColumn tbcolAlbaran;
    @FXML
    private TableColumn tbcolnFechaEntrada;
    @FXML
    private TableColumn tbcolDireccion;
    @FXML
    private TableColumn tbcolnRepartidor;
    @FXML
    private TableView<PedidoBean> tablaPedidos;
    @FXML
    private Button detalles;
    @FXML
    private Button eliminar;
    @FXML
    private Button mapa;
    @FXML
    private Button salir;
    @FXML
    private Button nuevoPedido;
    @FXML
    private ComboBox comboBoxBusquedaPedidos;
    @FXML
    private ComboBox comboBoxAreas;
    @FXML
    private Button buscarSimple;
    @FXML
    private Button buscarAvanzado;
    @FXML
    private TextField tfBuscarSimple;
    @FXML
    private DatePicker dpfechaEntrada;
    @FXML
    private DatePicker dpfechaSalida;
    private InterfaceAreaManager areaManager;
    private InterfacePedidoManager pedidoManager;
    private InterfaceRepartidorManager repartidorManager;
    
    /*without server
    private AreaManager areaManager;
    private PedidosManager pedidosManager;
    */
    //Forma1 private ArrayList pedidosData;
    @FXML
    private Pagination pagination;
    private final int  lineasPorPagina=18;
    @FXML
    private VBox vbox;
    private ObservableList pedidosData;
    private String pattern="dd/MM/yyyy";
    private StringConverterDate converter=new StringConverterDate();

    public InterfaceRepartidorManager getRepartidorManager() {
        return repartidorManager;
    }

    public void setRepartidorManager(InterfaceRepartidorManager repartidorManager) {
        this.repartidorManager = repartidorManager;
    }

    
    
    public void setAreaManager(InterfaceAreaManager areaManager) {
        this.areaManager = areaManager;
    }
    
    
    void setPedidoManager(InterfacePedidoManager pedidoManager) {
        this.pedidoManager=pedidoManager;
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    /**
     * Primer método del controlador que iniciará todas las bases y mostrara el Stage
     * @param root 
     */
    void initStage(Parent root) {
        stage=new Stage();
        Scene scene=new Scene(root);
        
        stage.setScene(scene);
        stage.setOnShowing(this::handleWindowShowing);
        stage.setTitle("Gestión de pedidos");
        stage.show();
    }
    /*
        La ComboBox de búsqueda de pedidos se carga con los valores:
    Número de seguimiento
    Albarán
    Fecha de entrada
    Dirección de destino
    El campo de texto de búsqueda se deshabilita
    El botón de buscar se deshabilita
    La tabla de datos se carga con todos los pedidos
    El botón de detalles se deshabilita
    El botón eliminar se deshabilita

    La ComboBox se búsqueda avanzada se carga con todas las áreas almacenadas en la base de datos.
    */
    /**
     * Establece algunos parámetros iniciales de la ventana
     * @param event parámetro que nos permite controlar acciones sobre quien lanzo el evento
     */
    private void handleWindowShowing(WindowEvent event){
        
        
        LOGGER.info("Beginning LoginController::handleWindowShowing");
        detalles.setDisable(true);
        eliminar.setDisable(true);
        buscarSimple.setDisable(true);
        tfBuscarSimple.textProperty().addListener(this::handleTextFieldBuscarSimple);
        dpfechaEntrada.setPromptText("Fecha entrada");
        dpfechaSalida.setPromptText("Fecha salida");

        
        comboBoxBusquedaPedidos.getItems().add("N.Seguimiento");
        comboBoxBusquedaPedidos.getItems().add("Destino");
        comboBoxBusquedaPedidos.getItems().add("Albarán");
        comboBoxBusquedaPedidos.getItems().add("Fecha Entrada");

        comboBoxBusquedaPedidos.getSelectionModel().selectFirst();
        
        comboBoxAreas.getItems().add("Todas las áreas");

        Collection<AreaBean> areas = areaManager.getAllAreas();
        for (AreaBean area : areas) {
            comboBoxAreas.getItems().add(area.getNombre());
        }
        comboBoxAreas.getSelectionModel().selectFirst();
        
        tbcolnSeguimiento.setCellValueFactory(new PropertyValueFactory<>("nSeguimiento"));
        tbcolAlbaran.setCellValueFactory(new PropertyValueFactory<>("albaran"));
        tbcolnFechaEntrada.setCellValueFactory(new PropertyValueFactory<>("fechaEntrada"));
        tbcolDireccion.setCellValueFactory(new PropertyValueFactory<>("destino"));
        tbcolnRepartidor.setCellValueFactory(new PropertyValueFactory<>("repartidor"));
       
        pedidosData = null;
        
        try{
            pedidosData = FXCollections.observableArrayList(pedidoManager.getAllPedidos());
        }catch(Exception e){
            e.printStackTrace();
        }  
        
        
        //Forma1 pedidosData=(ArrayList) pedidosManager.getAllPedidos();
        pagination();
        
        //tablaPedidos.setItems(pedidosData);
        //Añade un listener para reaccinar a la selección de filas de una tabla
        tablaPedidos.getSelectionModel().selectedItemProperty().addListener(this::handlePeidosTableSelectionChanged);
       
            
        dpfechaEntrada.setConverter(converter.getConverter());
        dpfechaSalida.setConverter(converter.getConverter());
    }
    
    //https://docs.oracle.com/javafx/2/ui_controls/pagination.htm
    //https://gist.github.com/timbuethe/7becdc4556225e7c5b7b
    /**
     * Crea una paginación acorde a los datos necesitados
     */
    private void pagination(){
        if(pedidosData.size()!=0){
            pagination = new Pagination((pedidosData.size() / lineasPorPagina + 1), 0);
            pagination.setPageFactory(this::createPage);
        }else{
            pagination = new Pagination(0,0);
        }

        
        vbox.getChildren().add(new BorderPane(pagination));
        
    }
    /**
     * Crea una nueva página de la paginación con los datos comprendidos en el rango correspondiente a dicha página.
     * @param pageIndex numero de página de la paginación que se creará
     * @return devuelve un borderPane que contendrá una tabla con los datos que corresponden, segun la página actual.
     */
    private Node createPage(int pageIndex) {

        
        
         try{
            pedidosData = FXCollections.observableArrayList(pedidoManager.getAllPedidos());
        }catch(Exception e){
            e.printStackTrace();
        } 
        // pedidosData.sort(Comparator.comparingInt());
        
        int fromIndex = pageIndex * lineasPorPagina;
        int toIndex = Math.min(fromIndex + lineasPorPagina, pedidosData.size());
        tablaPedidos.setItems(FXCollections.observableArrayList(pedidosData.subList(fromIndex, toIndex)));

        return new BorderPane(tablaPedidos);
    }
 
    /*
       Campo de texto de búsqueda de pedidos
    Cambio de texto
    El botón buscar se habilita
    */
    /**
     * Controla cuando se informa el campo de texto BuscarSimple, para habilitar o deshabilitar el botón buscar
     * @param value campo que se ha modificado
     * @param oldValue valor que tenía el objeto que se acaba de modificar
     * @param newValue valor nuevo que tiene el objeto que se acaba de modificar
     */
    private void handleTextFieldBuscarSimple (Observable value,String oldValue,String newValue){
        
        if(!tfBuscarSimple.getText().isEmpty()){
            buscarSimple.setDisable(false);
        }else{
            buscarSimple.setDisable(true);
        }
    }

    /*
        Tabla
    Selección
    Habilita los botones Detalles y Eliminaral seleccionar una fila de la tabla
    */
    /**
     * Controla la selección de una fila de la tabla,activa los botones "detalles" y "eliminar"
     * @param observable campo que se ha modificado
     * @param oldValue valor que tenía el objeto que se acaba de modificar
     * @param newValue valor nuevo que tiene el objeto que se acaba de modificar
     */
    private void handlePeidosTableSelectionChanged(ObservableValue observable,Object oldValue,Object newValue){
        //Si se ha seleccionado una fila de la tabla,
        //Habilitar los botones de detalles y eliminar
        if(newValue!=null){
            PedidoBean pedido=(PedidoBean)newValue;
            detalles.setDisable(false);
            eliminar.setDisable(false);
        }
        LOGGER.info("Fila de la tabla seleccionada");
    }
    /*
        Botón de nuevo pedido...
    Pulsación
    Abre la ventana Nuevo pedido con un String "NuevoPedido" , refrescar la tabla al volver a la ventana anterior
    */
    /**
     * Carga la ventana G007UI:NuevoPedido con el parámetro "NuevoPedido" y su controlador
     */
    @FXML
    private void handleBotonNuevoPedidoAction(){
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/UI/view/G007UI:NuevoPedido.fxml"));
        Parent root = null;
        try {
            root = (Parent)loader.load();
        } catch (IOException ex) {
            Logger.getLogger(GestionPedidosController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        NuevoPedidoController nuevoPedid=loader.getController();
        nuevoPedid.setTipoVentana("NuevoPedido");
        nuevoPedid.setTablaPedidos(tablaPedidos);
        nuevoPedid.setAreaManager(areaManager);
        nuevoPedid.setRepartidorManager(repartidorManager);
        //nuevoPedid.setPedidosData(pedidosData);
        nuevoPedid.setPedidoManager(pedidoManager);
        nuevoPedid.initStage(root);  
        

    }
    /*
        Botón Salir
    Pulsación
    Muestra un diálogo de confirmación y cierra la ventana en caso afirmativo.
    */
    /**
     * Cierra la ventana
     */
    @FXML
    private void handleBotonSalirAction(){
        LOGGER.info("admin sale de la aplicación");
        stage.close();
        
    }
    /*
    Pulsación
    Abre la ventana Nuevo pedido con un String "Detalles" , cargando los detalles de la fila seleccionada.
    Refresca la tabla al volver si se han modificado los datos
    Si hay una fila seleccionada.
    
    */
    /**
     * Abre la ventana G007UI:NuevoPedido con el parámetro de "Detalles" y su controlador
     */
    @FXML
    private void handleBotonDetallesAction() {
       
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/UI/view/G007UI:NuevoPedido.fxml"));
        Parent root = null;
        try {
            root = (Parent)loader.load();
        } catch (IOException ex) {
            Logger.getLogger(GestionPedidosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        NuevoPedidoController nuevoPedid=loader.getController();
        nuevoPedid.setTipoVentana("Detalles");
        nuevoPedid.setTablaPedidos(tablaPedidos);
        nuevoPedid.setAreaManager(areaManager);
        nuevoPedid.setPedidoManager(pedidoManager);
        nuevoPedid.setRepartidorManager(repartidorManager);
        nuevoPedid.setPedidoDetalles((PedidoBean)tablaPedidos.getSelectionModel().getSelectedItem());
        nuevoPedid.initStage(root); 
        
        LOGGER.info("comprueba detalles de un pedido");
        
    }
    @FXML
    /**
     * Carga un navegador con google maps en una nueva ventana (utiliza WebView)
     */
    private void handleBotonMapa(){
        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();
        webEngine.load("https://www.google.es/maps/@43.3201152,-3.0324976,14z");
        webEngine.setCreatePopupHandler(new Callback<PopupFeatures, WebEngine>(){
            @Override 
            public WebEngine call(PopupFeatures config){
                return null;
        
            }
        });
        Scene web = new Scene(browser);
        stage = new Stage();
        stage.setScene(web);
        stage.show();
    }
    @FXML
    /**
     * Crea un informe de la tabla de pedidos (utiliza JasperReports)
     */
    private void handleBotonCrearInforme(){
        try {
            JasperReport report = JasperCompileManager.compileReport("src/UI/reports/pedidosReport.jrxml");
        List<Map<String,Object>> dataSource = new ArrayList<Map<String,Object>>();
        for(PedidoBean pedido: this.tablaPedidos.getItems()){
            Map<String,Object> row = new HashMap<String,Object>();
            row.put("nseguimiento", pedido.getNSeguimiento());
            row.put("albaran", pedido.getAlbaran());
            row.put("fechaEntrada", new SimpleDateFormat("dd/MM/yyyy").format(pedido.getFechaEntrada()));
            row.put("destino", pedido.getDestino());
            row.put("repartidor", pedido.getRepartidor().getNombre());
           dataSource.add(row);
        }
        JRDataSource jRDataSource = new JRBeanCollectionDataSource(dataSource);
        
        JasperPrint jasperPrint = JasperFillManager.fillReport(report,null,jRDataSource);
        //Evita que se cierre todo el programa con el booleano false en el constructor
        JasperViewer jasperViewer = new JasperViewer(jasperPrint,false);
        //Otra opción para hacer lo mismo
        //jasperViewer.viewReport(jasperPrint,flase);
        jasperViewer.setVisible(true);
//        
        } catch (JRException ex) {
            ex.printStackTrace();
           // Logger.getLogger(GestionPedidosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*
        Botón Eliminar
    Pulsación
    Muestra un cuadro de diálogo de confirmación de borrado del pedido seleccionado en la tabla
    Y si confirma borrado eliminar el pedido seleccionado de la colección de pedidos y refrescar la tabla.
    */
    /**
     * Elimina el pedido seleccionado
     */
    @FXML
    private void handleBotonEliminarAction(){
        Alert alert = new Alert(AlertType.CONFIRMATION,"¿Está seguro de que desea eliminar el pedido? ");
        DialogPane dialogPane = alert.getDialogPane();
        alert.setHeaderText("Confirmación");
        alert.setContentText("Este cambio será irreversible");
        //dialogPane.getStylesheets().add(getClass().getResource("Custom.css").toExternalForm());
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get()==ButtonType.OK){
            pedidoManager.removePedido(tablaPedidos.getSelectionModel().getSelectedItem().getNSeguimiento().toString());
        //Forma1  for (Object pedido : pedidosData) {
         //Forma1      PedidoBean x=(PedidoBean)pedido;
        //Forma1      if(x.getNSeguimiento()==tablaPedidos.getSelectionModel().getSelectedItem().getNSeguimiento()){
         //Forma1          pedidosData.remove(pedido);
          //Forma1      }
         //Forma1  }
            tablaPedidos.getItems().remove(tablaPedidos.getSelectionModel().getSelectedItem());
             LOGGER.info("El admin manda eliminar un pedido");
            
        }

        
    }
    @FXML
    /*
        Botón buscar simple
    Pulsación
    Rellena la tabla aplicando los criterios de búsqueda seleccionados en la pestaña
    */
    /**
     * Efectua una búsqueda simple por los criterios de "comboBoxBusquedaPedidos" y "tfBuscarSimple", mostrando el resultado en la tabla
     */
    private void handleBotonBuscarSimple (){
        
        ObservableList pedidosData = null;
        Boolean buscar = true;
        //Tipo de busqueda
        String tipoBusqueda = comboBoxBusquedaPedidos.getSelectionModel().getSelectedItem().toString().toLowerCase();
        LOGGER.info(tipoBusqueda);
        if(tipoBusqueda.equals("n.seguimiento")){
            tipoBusqueda = "nseguimiento";
        }else if (tipoBusqueda.equals("fecha entrada")){
            tipoBusqueda = "fechaentrada";
            if(!tfBuscarSimple.getText().toString().matches("\\d{2}-\\d{2}-\\d{4}")){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Formato: 01-02-2018(dd-mm-yyyy)");
                DialogPane dialogPane = alert.getDialogPane();
                        alert.setHeaderText("Formato fecha erroneo");
               // dialogPane.getStylesheets().add(getClass().getResource("Custom.css").toExternalForm());
                alert.showAndWait();
                buscar = false;
            }
        }else if (tipoBusqueda.equals("albarán")){
            tipoBusqueda = "albaran";
        }
        LOGGER.info(tipoBusqueda);
        try{
            if(buscar){
                      pedidosData = FXCollections.observableArrayList
            (pedidoManager.getPedidosBusquedaSimple(tipoBusqueda,tfBuscarSimple.getText().toLowerCase()));
                       tablaPedidos.setItems(pedidosData);
                  LOGGER.info("Búsqueda realizada por criterio :"+comboBoxBusquedaPedidos.getSelectionModel().getSelectedItem().toString()+" buscado: "+tfBuscarSimple.getText());
            }
      
        }catch(Exception e){
            e.printStackTrace();
        }     
       
    }
    
    @FXML
    /*
        Botón buscar avanzado
    Pulsación
    Rellena la tabla aplicando los criterios de búsqueda seleccionados en la pestaña
    */
    /**
     * Efectua una búsqueda por los criterios de "comboBoxAreas" y las fechas "dpfechaEntrada" y "dpfechaSalida", muestra el resultado en la tabla
     */
    private void handleBotonBuscarAvanzado (ActionEvent event){

        if(dpfechaSalida.getEditor().getText().isEmpty()||dpfechaEntrada.getEditor().getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Rellene los campos de fecha para buscar");
                DialogPane dialogPane = alert.getDialogPane();
                        alert.setHeaderText("Campos vacíos");
               // dialogPane.getStylesheets().add(getClass().getResource("Custom.css").toExternalForm());
                alert.showAndWait();
        }else{
            if(dpfechaSalida.getValue().compareTo(dpfechaEntrada.getValue())<0){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Fecha salida no debe ser anterior fecha entrada");
                DialogPane dialogPane = alert.getDialogPane();
                        alert.setHeaderText("Fecha salida erronea");
               // dialogPane.getStylesheets().add(getClass().getResource("Custom.css").toExternalForm());
                alert.showAndWait();
            }else{
                ObservableList pedidosData = null;
                try{
                    //Cambio string áreas seleccionadas
                    String areaSeleccionada = comboBoxAreas.getSelectionModel().getSelectedItem().toString();
                    if(areaSeleccionada.equals("Todas las áreas"))
                        areaSeleccionada = "todaslasareas";
                    
                      //String fsalida = new SimpleDateFormat("yyyy-MM-dd").format(dpfechaEntrada.getValue().toString());
        
                     // fechaSalida.setValue(LocalDate.parse(fsalida));
                    
                    //Reemplazar "/" por "-" para consultas http
                     String sfechaEntrada = dpfechaEntrada.getValue().toString().replace("/","-");
                     String sfechaSalida = dpfechaSalida.getValue().toString().replace("/","-");
                     LOGGER.info(sfechaEntrada);
                     
                    
                    pedidosData = FXCollections.observableArrayList(        
                pedidoManager.getPedidosBusquedaAvanzada(areaSeleccionada
                        ,sfechaEntrada
                        ,sfechaSalida));

                }catch(Exception e){

                }

                tablaPedidos.setItems(pedidosData);
                LOGGER.info("Búsqueda realizada área: "+comboBoxAreas.getSelectionModel().getSelectedItem().toString()+
                        " fecha inicio: "+dpfechaEntrada.getEditor().getText()+" fecha salida: "+dpfechaSalida.getEditor().getText());
            }
        }


    }
}
