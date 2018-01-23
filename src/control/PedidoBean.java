/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;
    
import java.io.Serializable;
import java.util.Date;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Sergio
 */
@XmlRootElement(name="pedido")
public class PedidoBean implements Serializable{
   
    private SimpleIntegerProperty nSeguimiento;
    private SimpleIntegerProperty albaran;
    private Date/*SimpleObjectProperty<Date>*/ fechaEntrada;
    private Date/*SimpleObjectProperty<Date>*/ fechaSalida;
    private SimpleStringProperty destino;
    private SimpleObjectProperty<TipoPago> tPago;
    private SimpleObjectProperty<Repartidor> repartidor;
    private SimpleObjectProperty<AreaBean> area;
    

    
    public PedidoBean() {
        this.nSeguimiento= new SimpleIntegerProperty();
        this.albaran= new SimpleIntegerProperty();
        this.destino= new SimpleStringProperty();
        this.tPago=new SimpleObjectProperty();
        /*
        this.repartidor=new SimpleObjectProperty();
        this.area= new SimpleObjectProperty();
        */
    }
    
    public PedidoBean(Integer nSeguimiento,
            Integer albaran,
            Date fechaEntrada,
            Date fechaSalida,
            String destino,
            TipoPago tPago,
            Repartidor repartidor,
            AreaBean area){
        this.nSeguimiento=new SimpleIntegerProperty(nSeguimiento);
        this.albaran=new SimpleIntegerProperty(albaran);
        this.fechaEntrada=fechaEntrada;
        this.fechaSalida=fechaSalida;
        this.destino=new SimpleStringProperty(destino);
        this.tPago=new SimpleObjectProperty(tPago);
        this.repartidor=new SimpleObjectProperty(repartidor);
        this.area=new SimpleObjectProperty(area);
    }

    public void setRepartidor(Repartidor repartidor){
        this.repartidor.set(repartidor);
    }
    @XmlTransient
    public Repartidor getRepartidor() {
        return repartidor.get();
    }
    public void setArea(AreaBean area){
        this.area.set(area);
    }
    @XmlTransient
    public AreaBean getArea() {
        return area.get();
    }
    public void setNSeguimiento(Integer nSeguimiento){
        this.nSeguimiento.set(nSeguimiento);
    }
    public Integer getNSeguimiento() {
        return nSeguimiento.get();
    }

    public void setAlbaran(Integer albaran){
        this.albaran.set(albaran);
    }
    public Integer getAlbaran() {
        return albaran.get();
    }

    public void setFechaEntrada(Date fechaEntrada){
        this.fechaEntrada=fechaEntrada;
    }
    public Date getFechaEntrada() {
        return fechaEntrada;
    }
    

    public void setFechaSalida(Date fechaSalida){
        this.fechaSalida=fechaSalida;
    }
    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setDestino(String destino){
        this.destino.set(destino);
    }
    public String getDestino() {
        return destino.get();
    }

    public void settPago(TipoPago tPago){
        this.tPago.set(tPago);
    }
    @XmlElement(name="tipoPago")
    public TipoPago gettPago() {
        return tPago.get();
    }
    
}
