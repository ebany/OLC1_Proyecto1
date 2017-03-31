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
public class GenerarTokens {
    
    //posicion inicial por si no es la expRegular correcta
    int inicioAnalisis=0;        
    //cadena formada
    String analizada="";
    // poscicion 
    int fila = 0;
    int columna = 0;
    
    // cadena completa
    String dTrans = "";
    
    // Tokens Generados
    Vector tokens = new Vector(10,50);
    // Errores Encontrados
    Vector errores = new Vector(10,50);
    
    // elementos para verificar la validez del archivo
    Vector transiciones;
    Vector estados;
    Vector elementos;
    Vector ConjExp;
    Vector tokensName;
    
    public GenerarTokens(Vector transiciones, Vector estados, Vector elementos,Vector ConjExp,Vector tokensName,String dTrans){
        this.transiciones = transiciones;
        this.estados = estados;
        this.elementos = elementos;        
        this.ConjExp = ConjExp;
        this.tokensName = tokensName;
        this.dTrans = dTrans;
        VerificacionDeTransicion();
    }
    
    public void VerificacionDeTransicion(){
        
        char []analizar = dTrans.toCharArray();
        
        String [][]a = (String[][]) transiciones.elementAt(0);        
        
        
        for (int i = 0; i < analizar.length; i++) {   // cadena a analizar
            analizada+= analizar[i];
            
            for (int j = transiciones.size()-1; j >= 0; j--) {  // verificar en que expresion es aceptada
                String[][] expresion = (String[][]) transiciones.elementAt(j);
                
                for (int k = a.length-1; k >= 0; k--) {
                    for (int l = a[k].length-1; l >= 0 ; l--) {
                        if (!"-".equals(expresion[k][l])) {             // verificar transicion
                            //System.out.println("");
                        }
                    }
                }
            }
        }        
    }
    
    private void analizarEnExpReg(){
        
    }
}
