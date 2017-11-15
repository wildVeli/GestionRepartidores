/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.controller;

import control.PedidoBean;
import control.PedidosManager;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Sergio
 */
public class NuevoPedidoController {
    
    private String tipoVentana;
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
    private DatePicker fechaSalida;
    @FXML
    private TextField destino;
    @FXML
    private TextField area;
    @FXML
    private Button guardar;
    @FXML
    private Button atras;
    private TableView<PedidoBean> tablaPedidos;
    private PedidoBean pedidoDetalles;
    private PedidosManager pedidosManager;
    
    
    void setPedidosManager(PedidosManager pedidosManager) {
        this.pedidosManager=pedidosManager;
    }

    public void setPedidoDetalles(PedidoBean pedidoDetalles) {
        this.pedidoDetalles = pedidoDetalles;
    }


    
    public void setTablaPedidos(TableView<PedidoBean> tablaPedidos) {
        this.tablaPedidos = tablaPedidos;
    }
        
    public void setTipoVentana(String tipoVentana) {
        this.tipoVentana = tipoVentana;
    }
    
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    /*Inicialización NUEVO PEDIDO 
    El campo de texto de seguimiento, albarán y fecha de entrada se deshabilitan.
    El campo de texto fecha de salida, tipo de pago, destino y área se habilitan
    El sistema asigna un número de seguimiento válido y rellena el campo de texto número de seguimiento
    El sistema asigna un albarán valido y rellena el campo de texto albarán con él
    El sistema rellena el campo de fecha de entrada con la fecha del sistema
    El botón guardar se deshabilita
    El botón atrás se habilita
    */
    public void initStage(Parent root){
        stage=new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        

        fechaEntrada.setDisable(true);
        numeroSeguimiento.setDisable(true);
        albaran.setDisable(true);
            
        fechaSalida.getEditor().textProperty().addListener(this::handleTextChangeRequired);
        area.textProperty().addListener(this::handleTextChangeRequired);
        destino.textProperty().addListener(this::handleTextChangeRequired);
        repartidor.textProperty().addListener(this::handleTextChangeRequired);
        tipoPago.textProperty().addListener(this::handleTextChangeRequired);
        
        if(tipoVentana.equals("NuevoPedido")){
            Random rnd=new Random();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            guardar.setDisable(true);
            numeroSeguimiento.setText(String.valueOf(rnd.nextInt()));
            albaran.setText(String.valueOf(rnd.nextInt())); 
            fechaEntrada.setText(dateFormat.format(date));
            stage.setTitle("Nuevo pedido");
           

           
        }else if (tipoVentana.equals("Detalles")){
            stage.setTitle("Detalles pedido");
            numeroSeguimiento.setText(String.valueOf(pedidoDetalles.getNSeguimiento()));
            fechaEntrada.setText(pedidoDetalles.getFechaEntrada());
            albaran.setText(String.valueOf(pedidoDetalles.getAlbaran()));
            fechaSalida.getEditor().setText(pedidoDetalles.getFechaSalida());
            tipoPago.setText(pedidoDetalles.gettPago());
            destino.setText(pedidoDetalles.getDestino());
            repartidor.setText(String.valueOf(pedidoDetalles.getRepartidor()));
            area.setText(String.valueOf(pedidoDetalles.getArea()));
            
        }
        stage.show();
        
        
        
    }
    /*Botón guardar  pulsación
        Se comprueba que los campos tengan el tipo de dato correcto y se 
        guarda en la base de datos
    */
    @FXML
    private void handleBotonGuardarAction (ActionEvent event){
        PedidoBean pedidoBean = new PedidoBean(Integer.valueOf(numeroSeguimiento.getText()),
            Integer.valueOf(albaran.getText()),fechaEntrada.getText(),fechaSalida.getEditor().getText(),
            destino.getText(),tipoPago.getText(),Integer.valueOf(repartidor.getText()),Integer.valueOf(area.getText()));
        //Guarda un nuevo pedido
        if(tipoVentana.equals("NuevoPedido")){
            pedidosManager.addPedido(pedidoBean);
            
       //Modifica un pedido existente de los datos
        }else if (tipoVentana.equals("Detalles")){
            tablaPedidos.getItems().remove(pedidoDetalles);
            pedidosManager.updatePedido(pedidoBean);
        }
        tablaPedidos.getItems().add(pedidoBean);
        tablaPedidos.refresh();
        stage.close();
           
    }
    /*Botón atras pulsación
    Se cierra la ventana.
    */
    @FXML
    private void handleBotonAtrasAction (ActionEvent event){
            stage.close();
    }
    private void handleTextChangeRequired (Observable value,String oldValue,String newValue){
        if(!(repartidor.getText().isEmpty() || tipoPago.getText().isEmpty()||
            destino.getText().isEmpty()||area.getText().isEmpty()||
            fechaSalida.getEditor().getText().isEmpty())){
            guardar.setDisable(false);
        }else {
            guardar.setDisable(true);
        }
        

    }
  
}
