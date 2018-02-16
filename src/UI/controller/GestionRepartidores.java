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
        
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/UI/view/G006UI:GestionPedidos.fxml"));
        Parent root=(Parent)loader.load();
            
        
        //Server mode app
        InterfacePedidoManager pedidoManager = new PedidoManager();
        InterfaceAreaManager areaManager= new AreaManager();
        InterfaceRepartidorManager repartidorManager = new RepartidorManager();
       
        
       /* Client solo app
        PedidosManager bussinesslogicController=new PedidoTestDataGenerator();
        AreaManager areaManagerController=new AreaTestDataGenerator();
        areaManagerController.AreaTestDataGenerator();
       */ 
        

        GestionPedidosController gestionPedidos=loader.getController();
        gestionPedidos.setPedidoManager(pedidoManager);
        gestionPedidos.setAreaManager(areaManager);
        gestionPedidos.setRepartidorManager(repartidorManager);
        gestionPedidos.initStage(root);
        
        LOGGER.info("Aplicación iniciada con éxito");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
