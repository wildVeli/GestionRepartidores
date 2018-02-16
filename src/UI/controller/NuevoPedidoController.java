/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.controller;

import control.AreaBean;
import control.PedidoBean;
import control.PedidosManager;
import control.Repartidor;
import control.TipoPago;
import controlweb.InterfaceAreaManager;
import controlweb.InterfacePedidoManager;
import controlweb.InterfaceRepartidorManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
    private ComboBox tipoPago;
    @FXML
    private ComboBox repartidor;
    @FXML
    private TextField albaran;
    @FXML
    private DatePicker fechaSalida;
    @FXML
    private TextField destino;
    @FXML
    private ComboBox area;
    @FXML
    private Button guardar;
    @FXML
    private Button atras;
    private TableView<PedidoBean> tablaPedidos;
    private PedidoBean pedidoDetalles;
    private InterfaceAreaManager areaManager;
    private InterfacePedidoManager pedidoManager;
    private InterfaceRepartidorManager repartidorManager;
    private Collection<AreaBean> areas;
    private Collection<Repartidor> repartidores;
    private Stage parentStage;
    
    
    /*without server
    private PedidosManager pedidosManager;
    */
    private ObservableList pedidosData;
    private String formato = "dd/MM/yyyy";
    private StringConverterDate converter=new StringConverterDate();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato);

    public InterfaceRepartidorManager getRepartidorManager() {
        return repartidorManager;
    }

    public void setRepartidorManager(InterfaceRepartidorManager repartidorManager) {
        this.repartidorManager = repartidorManager;
    }

    public Stage getParentStage() {
        return parentStage;
    }

    public void setParentStage(Stage parentStage) {
        this.parentStage = parentStage;
    }
   

    
    public InterfaceAreaManager getAreaManager() {
        return areaManager;
    }

    public void setAreaManager(InterfaceAreaManager areaManager) {
        this.areaManager = areaManager;
    }
    
    

    public void setPedidosData(ObservableList pedidosData) {
        this.pedidosData = pedidosData;
    }

    
    void setPedidoManager(InterfacePedidoManager pedidoManager) {
        this.pedidoManager = pedidoManager;
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
        //Controla que la ventana sea modal
        stage.initOwner(parentStage);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

       

    }
     /**
     * Establece algunos parámetros iniciales de la ventana
     * @param event parámetro que nos permite controlar acciones sobre quien lanzo el evento
     */
    private void handleWindowShowing(WindowEvent event){
                
        areas = areaManager.getAllAreas();
        repartidores = repartidorManager.getAllRepartidores();
        fechaEntrada.setDisable(true);
        numeroSeguimiento.setDisable(true);
        albaran.setDisable(true);

        fechaSalida.getEditor().textProperty().addListener(this::handleTextChangeRequired);
        destino.textProperty().addListener(this::handleTextChangeRequired);
       
        if (tipoVentana.equals("NuevoPedido")) {
            nuevoPedido();

        } else if (tipoVentana.equals("Detalles")) {
            detalles();

        }
                 
        fechaSalida.setConverter(converter.getConverter());
        
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
        if(fechaSalida.getValue().compareTo(LocalDate.parse(fechaEntrada.getText(),formatter))<0){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Fecha salida no debe ser anterior fecha entrada");
                DialogPane dialogPane = alert.getDialogPane();
                        alert.setHeaderText("Fecha salida erronea");
               // dialogPane.getStylesheets().add(getClass().getResource("Custom.css").toExternalForm());
                alert.showAndWait();
        }else{
            //Fecha salida
            SimpleDateFormat sformatter = new SimpleDateFormat("dd/MM/yyyy");
            Date fsalida = new Date();
            try {
                fsalida = sformatter.parse(fechaSalida.getEditor().getText().toString());
            } catch (ParseException ex) {
                Logger.getLogger(NuevoPedidoController.class.getName()).log(Level.SEVERE, null, ex);
            }
            //TipoPago
            TipoPago tipoElegido= TipoPago.METALICO;
            if(tipoPago.getSelectionModel().getSelectedIndex()==0)
                tipoElegido=TipoPago.TARJETA;
            
            // Repartidor
            Repartidor RepartidorSeleccionado = null;
            for (Repartidor repar : repartidores) {
                if(repar.getNombre().equals(repartidor.getSelectionModel().getSelectedItem().toString())){
                    RepartidorSeleccionado = repar;
                    break;
                }
                    
            }
            //Area
            AreaBean areaSeleccionada = null;
            for (AreaBean are : areas) {
                if(are.getNombre().equals(area.getSelectionModel().getSelectedItem().toString())){
                    areaSeleccionada = are;
                    break;
                }
                    
            }
            PedidoBean pedidoBean = new PedidoBean(Integer.valueOf(numeroSeguimiento.getText()),
                    Integer.valueOf(albaran.getText()), new Date(),
                    fsalida,
                    destino.getText(),
                    tipoElegido,
                    RepartidorSeleccionado,
                    areaSeleccionada);
                //Guarda un nuevo pedido
                if (tipoVentana.equals("NuevoPedido")) {
                    pedidoManager.addPedido(pedidoBean);
                    LOGGER.info("admin añade un nuevo pedido");

                //Modifica un pedido existente de los datos
                } else if (tipoVentana.equals("Detalles")) {
                    tablaPedidos.getItems().remove(pedidoDetalles);
                    pedidoManager.updatePedido(pedidoBean);     
                    LOGGER.info("admin modifica un pedido");
                }
                tablaPedidos.getItems().add(pedidoBean);
                tablaPedidos.refresh();

                stage.close();
            
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
        if (!(destino.getText().isEmpty() || fechaSalida.getEditor().getText().isEmpty())) {
            guardar.setDisable(false);
        } else {
            guardar.setDisable(true);
        }

    }
    /**
     * Establece párametros iniciales de la ventana en el modo "NuevoPedido"
     */
    private void nuevoPedido() {
        
      
      
        
       //ArrayList<PedidoBean> x = (ArrayList)pedidosManager.getAllPedidos();
         
        PedidoBean x = pedidoManager.getDatosNuevoPedido();
        guardar.setDisable(true);
        numeroSeguimiento.setText(String.valueOf(x.getNSeguimiento()+1));
        albaran.setText(String.valueOf(x.getAlbaran()+1));
        String fentrada = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        fechaEntrada.setText(fentrada);
        stage.setTitle("Nuevo pedido");
        
        tipoPago.getItems().add("TARJETA");
        tipoPago.getItems().add("METÁLICO");

        tipoPago.getSelectionModel().select(0);
        
     
        for (Repartidor repart : repartidores) {
            repartidor.getItems().add(repart.getNombre());
        } 
        repartidor.getSelectionModel().select(0);
       
        for (AreaBean are : areas) {
            area.getItems().add(are.getNombre());
        }
        area.getSelectionModel().select(0);
               
    }
    /**
     * Establece párametros iniciales de la ventana en el modo "Detalles"
     */
    private void detalles() {
        stage.setTitle("Detalles pedido");
        numeroSeguimiento.setText(String.valueOf(pedidoDetalles.getNSeguimiento()));
        String fentrada = new SimpleDateFormat("dd/MM/yyyy").format(pedidoDetalles.getFechaEntrada());
        fechaEntrada.setText(fentrada);
        albaran.setText(String.valueOf(pedidoDetalles.getAlbaran()));
        String fsalida = new SimpleDateFormat("yyyy-MM-dd").format(pedidoDetalles.getFechaSalida());
        
        fechaSalida.setValue(LocalDate.parse(fsalida));
        
        tipoPago.getItems().add("TARJETA");
        tipoPago.getItems().add("METÁLICO");
        
        LOGGER.info(pedidoDetalles.gettPago().toString());
        if(pedidoDetalles.gettPago().equals("TARJETA"))
            tipoPago.setValue("TARJETA");
        else
            tipoPago.setValue("METÁLICO");
       
        destino.setText(pedidoDetalles.getDestino());
        //TODO añadir repartidores
        for (Repartidor repart : repartidores) {
            repartidor.getItems().add(repart.getNombre());
        } 
        repartidor.setValue(pedidoDetalles.getRepartidor().getNombre());
       // repartidor.setText(String.valueOf(pedidoDetalles.getRepartidor()));
       
       
       
        for (AreaBean are : areas) {
            area.getItems().add(are.getNombre());
        }
               
        area.setValue(pedidoDetalles.getArea().getNombre());
       
    }

}
