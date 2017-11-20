/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.time.LocalDate;
import java.util.Collection;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 *
 * @author Sergio López
 */
public interface PedidosManager {
    
    public Collection getAllPedidos();
    public void pedidoExiste(Integer nSeguimiento);
    public void addPedido(PedidoBean pedidoBean);
    public void updatePedido(PedidoBean pedidoBean);
    public Collection getPedidosBusquedaSimple(String selectedItem, String tfBuscarSimple);
    public Collection getPedidosBusquedaAvanzada(String selectedItem, LocalDate dpfechaEntrada, LocalDate dpfechaSalida);
    public void removePedido(Integer nSeguimiento);
}
