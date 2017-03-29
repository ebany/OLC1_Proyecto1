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
public class UNOMAS {
    
    // valores numericos de nodos
    int nodoInicio, nodoFinal;
    int nodo2, nodo3;
    // transicion string o object
    Object transicion2;
    String transicion1 = "Epsilon", transicion3 = "Epsilon", transicion4 = "Epsilon";
    // nodos alcanzables con epsilon
    Vector inicio = new Vector(10,50);
    Vector noEpsilon = new Vector (10,50);
        
}
