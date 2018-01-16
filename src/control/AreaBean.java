/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sergio López Fuentefría
 */

@XmlRootElement(name="area")
public class AreaBean {
    
    private int cp;
    private String nombre;
    private String descripcion;
    private int mapa;

    public AreaBean(){
        
    }
    public AreaBean(int cp,String nombre,String descripcion,int mapa){
        this.cp=cp;
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.mapa=mapa;
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

    public int getMapa() {
        return mapa;
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

    public void setMapa(int mapa) {
        this.mapa = mapa;
    }
    
    
    
}
