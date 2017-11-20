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
public interface AreaManager {
    
       /**
     * Genera datos de prueba de las áreas
     */
    public void AreaTestDataGenerator();
     /**
     * Recoge todos los nombres de las áreas
     * @return una colección con los nombres de las áreas
     */
    public Collection getAllAreaNames();
    /**
     * Busca el CP correspondiente al nombre de un área
     * @param selectedItem nombre del área que se consultará
     * @return Devuelve el CP correspondiente a un área
     */
    public int getNumeroArea(String selectedItem);
}
