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
public class CONCAT {
    
    // nodos a los que se le asigna un numero
    int nodoInicio, nodo2, nodoFin;
    // valor con el que pasa de un estado a otro \\\\ string o object
    Object transicion1, transicion2;
    // nodos alcanzables con epsilon
    Vector inicio = new Vector(10,50);
    Vector noEpsilon = new Vector (10,50);
    Vector inicio2 = new Vector(10,50);
    Vector noEpsilon2 = new Vector (10,50);
        
}
