/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaanalitico;

/**
 *
 * @author CodigoG
 */
public class SistemaAnalitico {
    static Interfaz interfaz;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        interfaz = new Interfaz();
        interfaz.setVisible(true);
    }
    
}
