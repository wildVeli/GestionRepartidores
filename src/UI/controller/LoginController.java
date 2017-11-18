/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.controller;

import control.AreaManager;
import control.PedidosManager;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Sergio López
 */
public class LoginController{
    
    private static final Logger logger= Logger.getLogger("UI");
    private Stage stage;
    @FXML
    private TextField user;
    @FXML
    private TextField password;
    @FXML
    private Button login;
    private PedidosManager pedidosManager;
    private AreaManager areaManager;

    public void setStage(Stage stage){
        this.stage=stage;
    }
    void setPedidosManager(PedidosManager bussinesslogicController) {
        this.pedidosManager=bussinesslogicController;
    }

    void setAreaManager(AreaManager areaManagerController) {
        this.areaManager=areaManagerController;
    }
    @FXML
    
    public void initStage(Parent root){
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Inicio sesión");
        stage.show();
        
    }
    @FXML      
    private void handleLoginButtonAction(ActionEvent event) throws IOException {
        if(!user.getText().trim().equals("") && !password.getText().trim().equals("")){
           //Comprobar en base de datos;
           if(user.getText().equals("admin")){
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/UI/view/GestionPedidos.fxml"));
            Parent root=(Parent)loader.load();
            
            GestionPedidosController gestionPedidos=loader.getController();
            gestionPedidos.setPedidosManager(pedidosManager);
            gestionPedidos.setAreaManager(areaManager);
            gestionPedidos.initStage(root);
        }else{
               
        }


        
       }else{
           Alert alert = new Alert(Alert.AlertType.ERROR, "Introduce un usuario y contraseña");
           DialogPane dialogPane = alert.getDialogPane();
          // dialogPane.getStylesheets().add(getClass().getResource("Custom.css").toExternalForm());
           alert.showAndWait();
       }
    }


}
