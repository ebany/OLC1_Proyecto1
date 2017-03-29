/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreteer;

import java.io.IOException;

/**
 *
 * @author CodigoG
 */
public class Graphviz {
    
    public void generarGrafica(String ruta, String nombre){
        try {
            String [] cmd = new String [5];
            cmd[0] = "C:\\Program Files\\Graphviz2.38\\bin\\dot.exe";
            cmd[1] = "-Tpng";
            cmd[2] = ruta;
            cmd[3] = "-o";
            cmd[4] = "C:\\txt1\\"+nombre + ".png";
            Runtime rt = Runtime.getRuntime();
            rt.exec(cmd);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
