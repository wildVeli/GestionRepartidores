/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.controller;

import control.PedidoBean;
import control.PedidosManager;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private void handleWindowShowing(WindowEvent event){
        logger.info("Beginning LoginController::handleWindowShowing");
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
        
    }
    
}
