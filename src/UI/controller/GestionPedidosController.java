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

import control.PedidoBean;
import control.PedidosManager;
import control.AreaManager;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author Sergio
 */
public class GestionPedidosController  {

    private static final Logger logger= Logger.getLogger("UI");

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
    private TextField tfBuscarSimple;
    @FXML
    private DatePicker dpfechaEntrada;
    @FXML
    private DatePicker dpfechaSalida;
    private AreaManager areaManager;
    private PedidosManager pedidosManager;
    //Forma1 private ArrayList pedidosData;
    @FXML
    private Pagination pagination;
    private final int  lineasPorPagina=18;
    @FXML
    private VBox vbox;
    private ObservableList pedidosData;

    public void setAreaManager(AreaManager areaManager) {
        this.areaManager = areaManager;
    }
    
    
    void setPedidosManager(PedidosManager pedidosManager) {
        this.pedidosManager=pedidosManager;
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    void initStage(Parent root) {
        stage=new Stage();
        Scene scene=new Scene(root);
        
        stage.setScene(scene);
        stage.setOnShowing(this::handleWindowShowing);
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
    private void handleWindowShowing(WindowEvent event){
        
        
        logger.info("Beginning LoginController::handleWindowShowing");
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
        for (Object allAreaName : areaManager.getAllAreaNames()) {
            comboBoxAreas.getItems().add(allAreaName.toString());
        }
        comboBoxAreas.getSelectionModel().selectFirst();
        
        tbcolnSeguimiento.setCellValueFactory(new PropertyValueFactory<>("nSeguimiento"));
        tbcolAlbaran.setCellValueFactory(new PropertyValueFactory<>("albaran"));
        tbcolnFechaEntrada.setCellValueFactory(new PropertyValueFactory<>("fechaEntrada"));
        tbcolDireccion.setCellValueFactory(new PropertyValueFactory<>("destino"));
        tbcolnRepartidor.setCellValueFactory(new PropertyValueFactory<>("repartidor"));
       
        pedidosData = null;
        
        try{
            pedidosData = FXCollections.observableArrayList(pedidosManager.getAllPedidos());
        }catch(Exception e){
            
        }  
        
        
        //Forma1 pedidosData=(ArrayList) pedidosManager.getAllPedidos();
        pagination();
        
        //tablaPedidos.setItems(pedidosData);
        //Añade un listener para reaccinar a la selección de filas de una tabla
        tablaPedidos.getSelectionModel().selectedItemProperty().addListener(this::handlePeidosTableSelectionChanged);
       
    }
    
    //https://docs.oracle.com/javafx/2/ui_controls/pagination.htm
    //https://gist.github.com/timbuethe/7becdc4556225e7c5b7b
    private void pagination(){
        pagination = new Pagination((pedidosData.size() / lineasPorPagina + 1), 0);
        pagination.setPageFactory(this::createPage);
        
        vbox.getChildren().add(new BorderPane(pagination));
        
    }
    private Node createPage(int pageIndex) {

        
         try{
            pedidosData = FXCollections.observableArrayList(pedidosManager.getAllPedidos());
        }catch(Exception e){
            
        } 
        // pedidosData.sort(Comparator.comparingInt());
        
        int fromIndex = pageIndex * lineasPorPagina;
        int toIndex = Math.min(fromIndex + lineasPorPagina, pedidosData.size());
        tablaPedidos.setItems(FXCollections.observableArrayList(pedidosData.subList(fromIndex, toIndex)));

        return new BorderPane(tablaPedidos);
    }
 
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
    private void handlePeidosTableSelectionChanged(ObservableValue observable,Object oldValue,Object newValue){
        //Si se ha seleccionado una fila de la tabla,
        //Habilitar los botones de detalles y eliminar
        if(newValue!=null){
            PedidoBean pedido=(PedidoBean)newValue;
            detalles.setDisable(false);
            eliminar.setDisable(false);
        }
    }
    /*
        Botón de nuevo pedido...
    Pulsación
    Abre la ventana Nuevo pedido con un String "NuevoPedido" , refrescar la tabla al volver a la ventana anterior
    */
    @FXML
    private void handleBotonNuevoPedidoAction(ActionEvent event) throws IOException{
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/UI/view/NuevoPedido.fxml"));
        Parent root=(Parent)loader.load();
            
        NuevoPedidoController nuevoPedid=loader.getController();
        nuevoPedid.setTipoVentana("NuevoPedido");
        nuevoPedid.setTablaPedidos(tablaPedidos);
        //nuevoPedid.setPedidosData(pedidosData);
        nuevoPedid.setPedidosManager(pedidosManager);
        nuevoPedid.initStage(root);    
        System.out.println("SALGO");

    }
    /*
        Botón Salir
    Pulsación
    Muestra un diálogo de confirmación y cierra la ventana en caso afirmativo.
    */
    @FXML
    private void handleBotonSalirAction(ActionEvent event){
        stage.close();
    }
    /*
    Pulsación
    Abre la ventana Nuevo pedido con un String "Detalles" , cargando los detalles de la fila seleccionada.
    Refresca la tabla al volver si se han modificado los datos
    Si hay una fila seleccionada.
    
    */
    @FXML
    private void handleBotonDetallesAction(ActionEvent event) throws IOException{
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/UI/view/NuevoPedido.fxml"));
        Parent root=(Parent)loader.load();
            
        NuevoPedidoController nuevoPedid=loader.getController();
        nuevoPedid.setTipoVentana("Detalles");
        nuevoPedid.setTablaPedidos(tablaPedidos);
        nuevoPedid.setPedidosManager(pedidosManager);
        nuevoPedid.setPedidoDetalles((PedidoBean)tablaPedidos.getSelectionModel().getSelectedItem());
        nuevoPedid.initStage(root); 
    }
    /*
        Botón Eliminar
    Pulsación
    Muestra un cuadro de diálogo de confirmación de borrado del pedido seleccionado en la tabla
    Y si confirma borrado eliminar el pedido seleccionado de la colección de pedidos y refrescar la tabla.
    */
    @FXML
    private void handleBotonEliminarAction(ActionEvent event){
        Alert alert = new Alert(AlertType.CONFIRMATION,"¿Está seguro de que desea eliminar el pedido? ");
        DialogPane dialogPane = alert.getDialogPane();
        alert.setHeaderText("Confirmación");
        alert.setContentText("Este cambio será irreversible");
        //dialogPane.getStylesheets().add(getClass().getResource("Custom.css").toExternalForm());
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get()==ButtonType.OK){
            pedidosManager.removePedido(tablaPedidos.getSelectionModel().getSelectedItem().getNSeguimiento());
        //Forma1  for (Object pedido : pedidosData) {
         //Forma1      PedidoBean x=(PedidoBean)pedido;
        //Forma1      if(x.getNSeguimiento()==tablaPedidos.getSelectionModel().getSelectedItem().getNSeguimiento()){
         //Forma1          pedidosData.remove(pedido);
          //Forma1      }
         //Forma1  }
            tablaPedidos.getItems().remove(tablaPedidos.getSelectionModel().getSelectedItem());

            
        }
        
    }
    @FXML
    private void handleBotonBuscarSimple (ActionEvent event){
        
        ObservableList pedidosData = null;
        try{
            pedidosData = FXCollections.observableArrayList
        (pedidosManager.getPedidosBusquedaSimple(comboBoxBusquedaPedidos.getSelectionModel().getSelectedItem().toString(),tfBuscarSimple.getText()));
        }catch(Exception e){
            
        }     
        tablaPedidos.setItems(pedidosData);
    }
    
    @FXML
    private void handleBotonBuscarAvanzado (ActionEvent event){

        ObservableList pedidosData = null;
        try{
            pedidosData = FXCollections.observableArrayList(        
        pedidosManager.getPedidosBusquedaAvanzada(comboBoxAreas.getSelectionModel().getSelectedItem().toString()
                ,dpfechaEntrada
                ,dpfechaSalida));
           
        }catch(Exception e){
            
        }
        tablaPedidos.setItems(pedidosData);
    }
}


/*
ComBox de búsqueda de pedido
Selección
Se habilita el campo de texto de búsqueda de pedidos

Campo de texto de búsqueda de pedidos
Cambio de texto
El botón buscar se habilita

Botón buscar
Pulsación
Rellena la tabla aplicando los criterios de búsqueda seleccionados en la pestaña

Combobox de búsqueda avanzada
Selección
Habilita el botón buscar de la ventana correspondiente en el caso de no estarlo

Campo de fecha izquierdo
Selección de fecha
Habilita el botón buscar de la ventana correspondiente en el caso de no estarlo

Campo de fecha derecho
Selección de fecha
Habilita el botón buscar de la ventana correspondiente en el caso de no estarlo

*/