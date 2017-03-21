/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package islas;

import Excepciones.ListaVaciaException;
import Vista.Grafico;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author j4v13
 */
public class Islas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ListaVaciaException {
        try {
            // TODO code application logic here
            new Grafico();
        } catch (IOException ex) {
            Logger.getLogger(Islas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
