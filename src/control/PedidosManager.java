/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.Collection;

/**
 *
 * @author Sergio López
 */
public interface PedidosManager {
    
    public Collection getAllPedidos();
    public void pedidoExiste(Integer nSeguimiento);
    public void addPedido(PedidoBean pedidoBean);
    public void updatePedido(PedidoBean pedidoBean);
}
