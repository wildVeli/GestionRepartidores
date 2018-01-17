/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;
    
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
    private SimpleStringProperty fechaEntrada;
    private SimpleStringProperty fechaSalida;
    private SimpleStringProperty destino;
    private TipoPago tPago;
    private SimpleObjectProperty repartidor;
    private SimpleObjectProperty area;
    

    
    public PedidoBean(){
        
    }
    
    public PedidoBean(Integer nSeguimiento,
            Integer albaran,
            String fechaEntrada,
            String fechaSalida,
            String destino,
            String tPago,
            Integer repartidor,
            Object area){
        this.nSeguimiento=new SimpleIntegerProperty(nSeguimiento);
        this.albaran=new SimpleIntegerProperty(albaran);
        this.fechaEntrada=new SimpleStringProperty(fechaEntrada);
        this.fechaSalida=new SimpleStringProperty(fechaSalida);
        this.destino=new SimpleStringProperty(destino);
        this.tPago=new SimpleStringProperty(tPago);
        this.repartidor=new SimpleObjectProperty(repartidor);
        this.area=new SimpleObjectProperty(area);
    }

    public void setRepartidor(Integer repartidor){
        this.repartidor.set(repartidor);
    }
    public Object getRepartidor() {
        return repartidor.get();
    }
    public void setArea(Integer area){
        this.area.set(area);
    }
    public Object getArea() {
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

    public void setFechaEntrada(String fechaEntrada){
        this.fechaEntrada.set(fechaEntrada);
    }
    public String getFechaEntrada() {
        return fechaEntrada.get();
    }
    

    public void setFechaSalida(String fechaSalida){
        this.fechaSalida.set(fechaSalida);
    }
    public String getFechaSalida() {
        return fechaSalida.get();
    }

    public void setDestino(String destino){
        this.destino.set(destino);
    }
    public String getDestino() {
        return destino.get();
    }

    public void settPago(String tPago){
        this.tPago.set(tPago);
    }
    @XmlElement(name="tipoPago")
    public String gettPago() {
        return tPago.get();
    }
    
}
