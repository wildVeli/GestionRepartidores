/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.controller;

import java.net.URL;
import java.util.ResourceBundle;
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
 * @author Sergio
 */
public class LoginController {
    

    private Stage stage;
    @FXML
    private TextField user;
    @FXML
    private TextField password;
    @FXML
    private Button login;

    public void setStage(Stage stage){
        this.stage=stage;
    }
    @FXML
    
    public void initStage(Parent root){
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Inicio sesión");
        stage.show();
        
    }
    @FXML      
    private void handleLoginButtonAction(ActionEvent event) {
        if(!user.getText().trim().equals("") && !password.getText().trim().equals("")){
           //Comprobar en base de datos;


        
       }else{
           Alert alert = new Alert(Alert.AlertType.ERROR, "Introduce un usuario y contraseña");
           DialogPane dialogPane = alert.getDialogPane();
          // dialogPane.getStylesheets().add(getClass().getResource("Custom.css").toExternalForm());
           alert.showAndWait();
       }
    }
    
}