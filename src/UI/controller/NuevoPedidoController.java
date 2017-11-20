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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;
import java.util.logging.Logger;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.StringConverter;

/**
 *
 * @author Sergio López Fuentefría
 */
public class NuevoPedidoController {

    private static final Logger LOGGER= Logger.getLogger("UI");
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
    private ObservableList pedidosData;
    private String pattern="dd/MM/yyyy";

    public void setPedidosData(ObservableList pedidosData) {
        this.pedidosData = pedidosData;
    }

    
    void setPedidosManager(PedidosManager pedidosManager) {
        this.pedidosManager = pedidosManager;
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
    /**
     * Primer método del controlador que iniciará todas las bases y mostrara el Stage
     * @param root contiene el FXML cargado para establecerlo en la nueva escena
     */
    public void initStage(Parent root) {
        stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setOnShowing(this::handleWindowShowing);
        stage.show();

       

    }
     /**
     * Establece algunos parámetros iniciales de la ventana
     * @param event parámetro que nos permite controlar acciones sobre quien lanzo el evento
     */
    private void handleWindowShowing(WindowEvent event){
                
        fechaEntrada.setDisable(true);
        numeroSeguimiento.setDisable(true);
        albaran.setDisable(true);

        fechaSalida.getEditor().textProperty().addListener(this::handleTextChangeRequired);
        area.textProperty().addListener(this::handleTextChangeRequired);
        destino.textProperty().addListener(this::handleTextChangeRequired);
        repartidor.textProperty().addListener(this::handleTextChangeRequired);
        tipoPago.textProperty().addListener(this::handleTextChangeRequired);

        if (tipoVentana.equals("NuevoPedido")) {
            nuevoPedido();

        } else if (tipoVentana.equals("Detalles")) {
            detalles();

        }
        
        StringConverter converter = new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }
            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        };             
        fechaSalida.setConverter(converter);
        
    }
    /*Botón guardar  pulsación
        Se comprueba que los campos tengan el tipo de dato correcto y se 
        guarda en la base de datos
     */
    /**
     * Controla las acciones efectudas por el botón guardar
     *      -Comprueba que los campos sean correctos
     *      -Guarda un nuevo pedido en caso de que la ventana este en modo "NuevoPedido"
     *      o el pedido modificado en caso de estar en modo "Detalles"
     *
     */
    @FXML
    private void handleBotonGuardarAction() {
        //Se comprueba que los campos tengan el dato correcto
        if (repartidor.getText().matches("[0-9]+") && area.getText().matches("[0-9]+")) {
            PedidoBean pedidoBean = new PedidoBean(Integer.valueOf(numeroSeguimiento.getText()),
                    Integer.valueOf(albaran.getText()), fechaEntrada.getText(), fechaSalida.getEditor().getText(),
                    destino.getText(), tipoPago.getText(), Integer.valueOf(repartidor.getText()), Integer.valueOf(area.getText()));
            //Guarda un nuevo pedido
            if (tipoVentana.equals("NuevoPedido")) {
                pedidosManager.addPedido(pedidoBean);
                LOGGER.severe("admin añade un nuevo pedido");

            //Modifica un pedido existente de los datos
            } else if (tipoVentana.equals("Detalles")) {
                tablaPedidos.getItems().remove(pedidoDetalles);
                pedidosManager.updatePedido(pedidoBean);     
                LOGGER.severe("admin modifica un pedido");
            }
            tablaPedidos.getItems().add(pedidoBean);
            //tablaPedidos.refresh();

            stage.close();

        } else {
            //Especifica la alerta en caso de error
            Alert alert = new Alert(Alert.AlertType.ERROR, "Los campos área y repartidor son númericos");

            alert.setTitle("Campos Incorrectos");
            alert.setHeaderText("Corrija los campos");
            DialogPane dialogPane = alert.getDialogPane();

            // dialogPane.getStylesheets().add(getClass().getResource("Custom.css").toExternalForm());
            alert.showAndWait();
        }

    }

    /*Botón atras pulsación
    Se cierra la ventana.
     */
    /**
     * Controla las acciones efectuadas por el botón "Átras"
     *      -Cierra la ventana
     */
    @FXML
    private void handleBotonAtrasAction() {
        stage.close();
    }
    /**
     * Controla cuando se informa los diferentes campos de texto asociados.
     * @param value campo que se ha modificado
     * @param oldValue valor que tenía el objeto que se acaba de modificar
     * @param newValue valor nuevo que tiene el objeto que se acaba de modificar
     */
    private void handleTextChangeRequired(Observable value, String oldValue, String newValue) {
        if (!(repartidor.getText().isEmpty() || tipoPago.getText().isEmpty()
                || destino.getText().isEmpty() || area.getText().isEmpty()
                || fechaSalida.getEditor().getText().isEmpty())) {
            guardar.setDisable(false);
        } else {
            guardar.setDisable(true);
        }

    }
    /**
     * Establece párametros iniciales de la ventana en el modo "NuevoPedido"
     */
    private void nuevoPedido() {
        Random rnd = new Random();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        guardar.setDisable(true);
        numeroSeguimiento.setText(String.valueOf(rnd.nextInt()));
        albaran.setText(String.valueOf(rnd.nextInt()));
        fechaEntrada.setText(dateFormat.format(date));
        stage.setTitle("Nuevo pedido");
    }
    /**
     * Establece párametros iniciales de la ventana en el modo "Detalles"
     */
    private void detalles() {
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

}
