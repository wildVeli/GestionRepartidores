/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.controller;

import javafx.fxml.FXML;
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
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    
    /*Inicialización
    El capo de texto de seguimiento, albarán y fecha de entrada se deshabilitan.
    El campo de texto fecha de salida, tipo de pago, destino y área se habilitan
    El sistema asigna un número de seguimiento válido y rellena el campo de texto número de seguimiento
    El sistema asigna un albarán valido y rellena el campo de texto albarán con él
    El sistema rellena el campo de fecha de entrada con la fecha del sistema
    El botón guardar se deshabilita
    El botón atrás se habilita
    */
    
    /*Botón guardar  pulsación
        Se comprueba que los campos fecha de salida, tipo de pago, destino, repartidor y área estén informados.
    En el caso de estarlo, se presenta un diálogo de confirmación para confirmar el nuevo pedido
    En el caso de no estarlo se presentará un cuadro de diálogo al usuario notificándole qué campos de texto 
        están incorrectamente informados y se cambia el borde de los campos de texto correspondientes a rojo.
    */
    /*Botón atras pulsación
    Se muestra un diálogo de confirmación para cerrar la ventana.
    */

    
    
  
}
