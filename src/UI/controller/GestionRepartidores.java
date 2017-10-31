/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.controller;

import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author ubuntu
 */
public class GestionRepartidores extends Application {
    
    private static final Logger logger= Logger.getLogger("UI");
    @Override
    public void start(Stage stage) throws Exception {
        
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/UI/view/login.fxml"));
        Parent root=(Parent)loader.load();
        
        LoginController login =new LoginController();
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
