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
public class OR {
    
    // nodos a los cuales se les debe asignar un numero
    int nodoInicio, nodoFinal;
    int nodo2, nodo3, nodo4, nodo5;
    // valor con el que pasa de un estado a otro \\\\  2 y 5 pueden ser un string o una clase con mas transiciones
    Object transicion2, transicion5;
    // epsilon por regla
    String transicion1= "Epsilon", transicion3= "Epsilon", transicion4= "Epsilon" , transicion6 = "Epsilon";
    // nodos alcanzables con epsilon
    Vector inicio = new Vector(10,50);
    Vector noEpsilon = new Vector (10,50);
    Vector noEpsilon2 = new Vector (10,50); // se pone o no se pone ???????
}
