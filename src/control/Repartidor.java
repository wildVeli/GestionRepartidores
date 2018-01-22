/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
  *  Mantiene los datos que contienen los Repartidores
 *  <ul>
 *      <li><strong>id</strong> Id del repartidor</li>
 *      <li><strong>dni</strong> dni del repartidor</li>
 *      <li><strong>fechaAlta</strong> fecha en la que se dio de alta al repartidor</li>
 *      <li><strong>nombre</strong> nombre del repartidor</li>
 *      <li><strong>email</strong> email del repartidor</li>
 *      <li><strong>franquicia</strong> franquicia en la que trabaja el repartidor</li>
 *      <li><strong>password</strong> contraseña de el repartidor</li>
 *      <li><strong>recaudaciones</strong> coleccion de las recaudaciones {@link  gestionrepartidores.entity.Recaudacion} del repartidor</li>
 *      <li><strong>areas</strong> colección de áreas  {@link  gestionrepartidores.entity.Area} en las que trabaja el repartidor</li>
 *      <li><strong>pedidos</strong> colección de los pedidos  {@link  gestionrepartidores.entity.Pedido} que reparte el repartidor</li>
 *  </ul>
 * @author Sergio López Fuentefría
 */
@XmlRootElement(name="repartidor")
public class Repartidor {

    private Integer id;
    private String dni;
    private Date fechaAlta;
    private String nombre;
    private String email;
    private String franquicia;
    private String password;
    private Collection<Recaudacion> recaudaciones;
    private Collection<AreaBean> areas;
    private Collection<PedidoBean> pedidos;

   
    
     
    public Collection<PedidoBean> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Collection<PedidoBean> pedidos) {
        this.pedidos = pedidos;
    }
    

    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFranquicia() {
        return franquicia;
    }

    public void setFranquicia(String franquicia) {
        this.franquicia = franquicia;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlTransient
    public Collection<Recaudacion> getRecaudaciones() {
        return recaudaciones;
    }

    public void setRecaudaciones(Collection<Recaudacion> recaudaciones) {
        this.recaudaciones = recaudaciones;
    }

    @XmlTransient
    public Collection<AreaBean> getAreas() {
        return areas;
    }

    public void setAreas(Collection<AreaBean> areas) {
        this.areas = areas;
    }


    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Repartidor)) {
            return false;
        }
        Repartidor other = (Repartidor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gestionrepartidores.entity.Repartidor[ id=" + id + " ]";
    }
    
}
