/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author ubuntu
 */
public class NuevoPedidoController {
    
    private Stage stage;
    @FXML
    private TextField numeroSeguimiento;
    @FXML
    private TextField fechaEntrada;
    @FXML
    private TextField tipoPago;
    @FXML
    private TextField repartidor;
    @FXML
    private TextField albaran;
    @FXML
    private TextField fechaSalida;
    @FXML
    private TextField destino;
    @FXML
    private TextField area;
    @FXML
    private Button guardar;
    @FXML
    private Button atras;
    
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    /*Inicialización NUEVO SEGUIMIENTO 
    El campo de texto de seguimiento, albarán y fecha de entrada se deshabilitan.
    El campo de texto fecha de salida, tipo de pago, destino y área se habilitan
    El sistema asigna un número de seguimiento válido y rellena el campo de texto número de seguimiento
    El sistema asigna un albarán valido y rellena el campo de texto albarán con él
    El sistema rellena el campo de fecha de entrada con la fecha del sistema
    El botón guardar se deshabilita
    El botón atrás se habilita
    */
    public void initStage(Parent root,String tipo){
        Scene scene = new Scene(root);
        stage.setScene(scene);
       
        if(tipo.equals("NuevoPedido")){
            Random rnd=new Random();
            numeroSeguimiento.setDisable(true);
            fechaEntrada.setDisable(true);
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            fechaEntrada.setText(dateFormat.format(date));
            numeroSeguimiento.setText(String.valueOf(rnd.nextInt()));
            albaran.setDisable(true);
            albaran.setText(String.valueOf(rnd.nextInt()));         
            guardar.setDisable(true);           
            stage.setTitle("Nuevo pedido");
            
            
        }else{
            stage.setTitle("Detalles ");
        }
        stage.show();
        
    }
    
    

    
    /*Botón guardar  pulsación
        Se comprueba que los campos fecha de salida, tipo de pago, destino, repartidor y área estén informados.
    En el caso de estarlo, se presenta un diálogo de confirmación para confirmar el nuevo pedido
    En el caso de no estarlo se presentará un cuadro de diálogo al usuario notificándole qué campos de texto 
        están incorrectamente informados y se cambia el borde de los campos de texto correspondientes a rojo.
    */
    /*Botón atras pulsación
    Se muestra un diálogo de confirmación para cerrar la ventana.
    */

    void initStage(Parent root) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
  
}
