/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaanalitico;

import java.io.FileWriter;
    import java.io.IOException;
    import java.io.PrintWriter;
    import java.util.Vector;

public class PrintHtml {
    
    Vector noRecuperables;
    Vector noRecuperablesLinea;
    Vector noRecuperablesColumna;
    
    Vector Recuperables;
    Vector RecuperablesLinea;
    Vector RecuperablesColumna;
    
    Vector lex = new Vector(10,50);
    Vector lexLinea = new Vector(10,50);
    Vector lexColumna = new Vector(10,50);
    
    public PrintHtml(Vector noRecuperables, Vector noRecuperablesLinea, Vector noRecuperablesColumna, 
            Vector Recuperables, Vector RecuperablesLinea, Vector RecuperablesColumna,
            Vector lex, Vector lexColumna, Vector lexLinea){
        
        this.noRecuperables = noRecuperables;
        this.noRecuperablesLinea = noRecuperablesLinea;
        this.noRecuperablesColumna = noRecuperablesColumna;
        
        this.Recuperables = Recuperables;
        this.RecuperablesLinea = RecuperablesLinea;
        this.RecuperablesColumna = RecuperablesColumna;        
        
        this.lex = lex;
        this.lexColumna = lexColumna;
        this.lexLinea = lexLinea;
    }
   
    public void pagina(){
        
        FileWriter filewriter = null;
        PrintWriter printw = null;
        
        try{     
            filewriter = new FileWriter("Errores.html");  
            printw = new PrintWriter(filewriter);
            
            printw.println("<html>"); 
            printw.println("<head><meta http-equiv=\"content-type\" charset=\"utf-8\"> <title>Errores D-ER</title></head>"); 
            
            if (!noRecuperables.contains(null)) {                
                // Errores No recupeerables
                printw.println("<center><h1><font color=\"navy\">"+"Errores Sintacticos No Recuperables"+"</font></h1></center>");
                printw.println("<TABLE border=\"1\" width=\"300\" align=\"center\">"+
                            "<th> Lexema</th><th> Columna</th><th> Fila</th>");

                for(int a=0; a<noRecuperables.size(); a++){

                    printw.println(
                                "<TR>"+ 
                                    "<TD align=\"center\">"+  noRecuperables.elementAt(a)  +" </TD>"+
                                    "<TD align=\"center\">"+ noRecuperablesLinea.elementAt(a)+" </TD>"+
                                    "<TD align=\"center\">"+ noRecuperablesColumna.elementAt(a)+" </TD>"+
                                "</TR>"
                    );
                }            
                printw.println("</TABLE>");
            }
            
            if (!Recuperables.contains(null)) {
                // errores Recuperables
                printw.println("<center><h1><font color=\"navy\">"+"Errores Sintacticos Recuperables"+"</font></h1></center>");
                printw.println("<TABLE border=\"1\" width=\"300\" align=\"center\">"+
                            "<th> Lexema</th><th> Columna</th><th> Fila</th>");

                for(int a=0; a<Recuperables.size(); a++){

                    printw.println(
                                "<TR>"+ 
                                    "<TD align=\"center\">"+ Recuperables.elementAt(a)  +" </TD>"+
                                    "<TD align=\"center\">"+ RecuperablesLinea.elementAt(a)+" </TD>"+
                                    "<TD align=\"center\">"+ RecuperablesColumna.elementAt(a)+" </TD>"+
                                "</TR>"
                    );
                }            
                printw.println("</TABLE>");
            }
            
            if (!lex.contains(null)) {
                // errores Recuperables
                printw.println("<center><h1><font color=\"navy\">"+"Errores Lexicos"+"</font></h1></center>");
                printw.println("<TABLE border=\"1\" width=\"300\" align=\"center\">"+
                            "<th> Lexema</th><th> Columna</th><th> Fila</th>");

                for(int a=0; a<lex.size(); a++){

                    printw.println(
                                "<TR>"+ 
                                    "<TD align=\"center\">"+ lex.elementAt(a)  +" </TD>"+
                                    "<TD align=\"center\">"+ lexColumna.elementAt(a)+" </TD>"+
                                    "<TD align=\"center\">"+ lexLinea.elementAt(a)+" </TD>"+
                                "</TR>"
                    );
                }            
                printw.println("</TABLE>");
            }
            
            
            printw.println("</html>"); 
            printw.close();
        }
        catch(IOException m){}
    } // fin metodo
    
}

