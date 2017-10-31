/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ubuntu
 */
public class GestionPedidosController  {

    private Stage stage;
    
        public void setStage(Stage stage) {
        this.stage = stage;
    }

    void initStage(Parent root) {
        Stage stage=new Stage();
        
        Scene scene=new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
}
