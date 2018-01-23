/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sergio López Fuentefría
 */
@XmlRootElement(name="recaudacion")
public class Recaudacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dia;
    private Float dinero;
    @ManyToOne
    private Repartidor repartidor;

    
    public Repartidor getRepartidor() {
        return repartidor;
    }

    public void setRepartidor(Repartidor repartidor) {
        this.repartidor = repartidor;
    }

    public Float getDinero() {
        return dinero;
    }

    public void setDinero(Float dinero) {
        this.dinero = dinero;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }
    


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dia != null ? dia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the dia fields are not set
        if (!(object instanceof Recaudacion)) {
            return false;
        }
        Recaudacion other = (Recaudacion) object;
        if ((this.dia == null && other.dia != null) || (this.dia != null && !this.dia.equals(other.dia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gestionrepartidores.entity.Recaudacion[ dia=" + dia + " ]";
    }
    
}
