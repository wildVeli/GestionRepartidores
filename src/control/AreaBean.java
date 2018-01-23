/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.Serializable;
import java.util.Collection;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Sergio López Fuentefría
 */

@XmlRootElement(name="area")
public class AreaBean implements Serializable {
    
    private Integer cp;
    private String nombre;
    private String descripcion;
    private Collection<PedidoBean> pedidos;
    

    public AreaBean(){
        
    }
    public AreaBean(int cp,String nombre,String descripcion){
        this.cp=cp;
        this.nombre=nombre;
        this.descripcion=descripcion;
       
    }
    @XmlTransient
    public Collection<PedidoBean> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Collection<PedidoBean> pedidos) {
        this.pedidos = pedidos;
    }
    
    AreaBean(AreaBean area) {
       
    }
    public int getCp() {
        return cp;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
    
    
}
