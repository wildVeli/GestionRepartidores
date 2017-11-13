/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.controller;

import control.PedidoBean;
import control.PedidosManager;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private PedidosManager pedidosManager;
    
    
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
    El botón de nuevo pedido se habilita
    La tabla de datos se carga con todos los pedidos
    El botón de detalles se deshabilita
    El botón eliminar se deshabilita
    El botón de salir se habilita

    La ComboBox se búsqueda avanzada se carga con todas las áreas almacenadas en la base de datos.
    Los campos de fecha de entrada y de salida se habilitan
    El botón de buscar de la búsqueda avanzada se habilita
    */
    private void handleWindowShowing(WindowEvent event){
        
        
        logger.info("Beginning LoginController::handleWindowShowing");
        detalles.setDisable(true);
        eliminar.setDisable(true);
        tbcolnSeguimiento.setCellValueFactory(new PropertyValueFactory<>("nSeguimiento"));
        tbcolAlbaran.setCellValueFactory(new PropertyValueFactory<>("albaran"));
        tbcolnFechaEntrada.setCellValueFactory(new PropertyValueFactory<>("fechaEntrada"));
        tbcolDireccion.setCellValueFactory(new PropertyValueFactory<>("destino"));
        tbcolnRepartidor.setCellValueFactory(new PropertyValueFactory<>("repartidor"));
       
        ObservableList pedidosData = null;
        try{
            pedidosData = FXCollections.observableArrayList(pedidosManager.getAllPedidos());
        }catch(Exception e){
            
        }     
        tablaPedidos.setItems(pedidosData);
        //Añade un listener para reaccinar a la selección de filas de una tabla
        tablaPedidos.getSelectionModel().selectedItemProperty().addListener(this::handlePeidosTableSelectionChanged);
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
        nuevoPedid.setPedidosManager(pedidosManager);
        nuevoPedid.initStage(root);    

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
            tablaPedidos.getItems().remove(tablaPedidos.getSelectionModel().getSelectedItem());
        }
        
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