/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.controller;

import controlweb.InterfaceAreaManager;
import controlweb.InterfacePedidoManager;
import java.io.IOException;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Sergio López Fuentefría
 */
public class LoginController{
    
    private static final Logger LOGGER= Logger.getLogger("UI");
    private Stage stage;
    @FXML
    private TextField user;
    @FXML
    private TextField password;
    @FXML
    private Button login;
    private InterfacePedidoManager pedidoManager;
    private InterfaceAreaManager areaManager;
    
    /*without server
    private PedidosManager pedidosManager;
    private AreaManager areaManager;
    */

    public void setStage(Stage stage){
        this.stage=stage;
    }
    
    void setPedidoManager(InterfacePedidoManager bussinesslogicController) {
        this.pedidoManager=bussinesslogicController;
    }

    void setAreaManager(InterfaceAreaManager areaManagerController) {
        this.areaManager=areaManagerController;
    }
    @FXML
    
    public void initStage(Parent root){
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Inicio sesión");
        stage.show();
        
    }
    /*
    Comprueba los datos de inicio de sesión
    */
    @FXML      
    private void handleLoginButtonAction(ActionEvent event) throws IOException {
        if(!user.getText().trim().equals("") && !password.getText().trim().equals("")){
           //Comprobar en base de datos;
           if(user.getText().equals("admin")&&password.getText().equals("admin")){
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/UI/view/G006UI:GestionPedidos.fxml"));
            Parent root=(Parent)loader.load();
            
            GestionPedidosController gestionPedidos=loader.getController();
            gestionPedidos.setPedidoManager(pedidoManager);
            gestionPedidos.setAreaManager(areaManager);
            gestionPedidos.initStage(root);
            LOGGER.info("usuario admin inicio sesión");
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR, "Introduce un usuario y contraseña");
                DialogPane dialogPane = alert.getDialogPane();
               // dialogPane.getStylesheets().add(getClass().getResource("Custom.css").toExternalForm());
                alert.showAndWait();
                LOGGER.info("Intento de inicio de sesión erroneo con los siguientes datos :    USUARIO: "
                        +user.getText()+" CONTRASEÑA: "+password.getText());
            }
        
       }else{

       }
    }


}
