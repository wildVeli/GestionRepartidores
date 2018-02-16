/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.controller;


import controlweb.AreaManager;
import controlweb.InterfacePedidoManager;
import controlweb.InterfaceAreaManager;
import controlweb.InterfaceRepartidorManager;
import controlweb.PedidoManager;
import controlweb.RepartidorManager;

import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 *
 * @author Sergio López Fuentefría
 */
public class GestionRepartidores extends Application {
    
    private static final Logger LOGGER= Logger.getLogger("UI");
    
    @Override
    public void start(Stage stage) throws Exception {
        
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/UI/view/G001UI:Login.fxml"));
        Parent root=(Parent)loader.load();
        
        //Server mode app
        InterfacePedidoManager bussinesslogicController = new PedidoManager();
        InterfaceAreaManager areaManagerController = new AreaManager();
        InterfaceRepartidorManager repartidorManager = new RepartidorManager();
       
        
       /* Client solo app
        PedidosManager bussinesslogicController=new PedidoTestDataGenerator();
        AreaManager areaManagerController=new AreaTestDataGenerator();
        areaManagerController.AreaTestDataGenerator();
       */ 
        
        LoginController login =loader.getController();
        login.setPedidoManager(bussinesslogicController);
        login.setAreaManager(areaManagerController);
        login.setRepartidorManager(repartidorManager);
        login.setStage(stage);
        login.initStage(root);
        
        LOGGER.info("Aplicación iniciada con éxito");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
