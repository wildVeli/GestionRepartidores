/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;
    
import java.util.Date;
import java.util.Enumeration;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sergio
 */
@XmlRootElement(name="pedido")
public class PedidoBean {
   
    private SimpleIntegerProperty nSeguimiento;
    private SimpleIntegerProperty albaran;
    private SimpleObjectProperty<Date> fechaEntrada;
    private SimpleObjectProperty<Date> fechaSalida;
    private SimpleStringProperty destino;
    private TipoPago tPago;
    private Repartidor repartidor;
    private AreaBean area;
    

    
    public PedidoBean(){
        
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
        this.fechaEntrada=new SimpleObjectProperty(fechaEntrada);
        this.fechaSalida=new SimpleObjectProperty(fechaSalida);
        this.destino=new SimpleStringProperty(destino);
        this.tPago=tPago;
        this.repartidor=repartidor;
        this.area=area;
    }

    public void setRepartidor(Repartidor repartidor){
        this.repartidor = repartidor;
    }
    public Object getRepartidor() {
        return repartidor;
    }
    public void setArea(AreaBean area){
        this.area = area;
    }
    public Object getArea() {
        return area;
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
        this.fechaEntrada.set(fechaEntrada);
    }
    public Date getFechaEntrada() {
        return fechaEntrada.get();
    }
    

    public void setFechaSalida(Date fechaSalida){
        this.fechaSalida.set(fechaSalida);
    }
    public Date getFechaSalida() {
        return fechaSalida.get();
    }

    public void setDestino(String destino){
        this.destino.set(destino);
    }
    public String getDestino() {
        return destino.get();
    }

    public void settPago(TipoPago tPago){
        this.tPago = tPago;
    }
    @XmlElement(name="tipoPago")
    public TipoPago gettPago() {
        return tPago;
    }
    
}
