/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.controller;

import control.PedidoTestDataGenerator;
import control.PedidosManager;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Sergio
 */
public class GestionRepartidores extends Application {
    
    
    @Override
    public void start(Stage stage) throws Exception {
        
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/UI/view/login.fxml"));
        Parent root=(Parent)loader.load();
        
        PedidosManager bussinesslogicController=new PedidoTestDataGenerator();
        
        
        LoginController login =loader.getController();
        login.setPedidosManager(bussinesslogicController);
        login.setStage(stage);
        login.initStage(root);
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
