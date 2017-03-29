/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaanalitico;

import java.util.Vector;

/**
 *
 * @author CodigoG
 */
public class Expresion {
    
    Object ExpReg;
    Vector elementos = new Vector(10,50);
    
    public void addEG(Object b){        
        String a[] = b.getClass().getCanonicalName().split("\\.");        
        if (a.length>2 && !elementos.contains(b)){
            elementos.addElement(b);            
        }        
    }
    
    public void copiar(Vector a){
        for (int i = 0; i < a.size(); i++) {
            addEG(a.elementAt(i));            
        }
    }
}
