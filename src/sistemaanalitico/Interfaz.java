/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaanalitico;

import java.awt.Desktop;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.runtime.Symbol;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

/**
 *
 * @author CodigoG
 */
public class Interfaz extends javax.swing.JFrame {

    Interfaz n;
    JFileChooser exp;
    interpreteer.Graphviz generar = new interpreteer.Graphviz();
    int contador = 1;
    int concatenado = 0;
    Vector estadosSubC = new Vector(20,50);
    
    // tabla de transicion
    Vector tablaT = new Vector(20,50);
    Vector elementosE = new Vector(20,50);
    Vector estadosE = new Vector(20,50);
    Vector nombreT = new Vector(20,50);
    
    //ConjExpresiones ---- valores validos
    Vector ConjExp = new Vector(20,50);
    
    // nombreTokens
    Vector tokensName = new Vector(20,50);
    
    Boolean unomasT = false;
    Boolean ceromasT = false;
    
    /**
     * Creates new form Interfaz
     */
    public Interfaz() {
                
        exp = new JFileChooser();
        //this.setLocationRelativeTo(null);
        this.setResizable(false);
        initComponents();
        jMenu1.setText("Archivo");
        jMenu2.setText("Herramientas");
        jMenuItem1.setText("Abrir D-ER");
        jMenuItem2.setText("Guardar D-ER");
        jMenuItem3.setText("Analizar");
        jMenuItem4.setText("Guardar Token's");
        jMenuItem5.setText("Guardar Errores");
        jMenuItem6.setText("Ver Reporte");
        jComboBox1.removeAllItems();
        buttonGroup1.add(jRadioButton1);
        buttonGroup1.add(jRadioButton2);    
        jTextArea1.setLineWrap(true);
        jTextArea1.setWrapStyleWord(true);
        jTextArea2.setLineWrap(true);
        jTextArea2.setWrapStyleWord(true);
        jTextArea2.setEditable(false);
        n = this;
        
        // Servidor Via sockets
        new Thread(new Runnable(){

            @Override
            public void run() {
                try {
                    //servidor creado---esperando conexion en puerto 35577
                    System.out.println("Servidor activo");
                    ServerSocket socket = new ServerSocket(5555);
                    // se ha conectado un cliente
                    Socket cliente = socket.accept();
                    System.out.println("Conectado con cliente: "+cliente.getInetAddress());
                    
                    // flujo entrante desde el cliente
                    String txt = recibir(cliente);

                    // el cliente lee los datos hasta un max de 10 seg de espera
                    cliente.setSoLinger(true, 10);
                    
                    // flujo de salida de datos -----> Aqui se envia el xml
                    GenerarTokens xml = new GenerarTokens(tablaT,estadosE,elementosE,ConjExp,tokensName,txt);
                    
                    String txtE = "Esto deberia ser el XML";
                    enviar(txtE,cliente);
                    // se cierra la conexion con el cliente, setSoLinger espera a que el cliente retire los datos
                    cliente.close();
                    socket.close();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            
            public String recibir(Socket cliente) throws IOException{
                InputStream is = cliente.getInputStream();
                    byte[] lenBytes = new byte[4];
                    is.read(lenBytes, 0, 4);
                    int len = (((lenBytes[3] & 0xff)<<24)|((lenBytes[2] & 0xff)<<16)|((lenBytes[1] & 0xff)<<8)|(lenBytes[0] & 0xff));
                    byte[] receivedBytes = new byte[len];
                    is.read(receivedBytes, 0, len);
                    String received = new String(receivedBytes, 0, len);
                    JOptionPane.showMessageDialog(n, "Archivo D-trans:\n\n"+received);                
                    System.out.println(received);
                    return received;
            }
            
            public void enviar (String txtE, Socket cliente) throws IOException{                
                OutputStream os = cliente.getOutputStream();
                byte[] toSendBytes = txtE.getBytes();
                int toSendLen = toSendBytes.length;
                byte[] toSendLenBytes = new byte[4];
                toSendLenBytes[0] = (byte)(toSendLen & 0xff);
                toSendLenBytes[1] = (byte)((toSendLen >> 8) & 0xff);
                toSendLenBytes[2] = (byte)((toSendLen >> 16) & 0xff);
                toSendLenBytes[3] = (byte)((toSendLen >> 24) & 0xff);
                os.write(toSendLenBytes);
                os.write(toSendBytes);
            }
        }).start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("AFD / AFN ");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Tabla de transiciones");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jRadioButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRadioButton1.setText("AFD");

        jRadioButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRadioButton2.setText("AFN");

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("Mostrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jMenu1.setText("File");

        jMenuItem1.setText("jMenuItem1");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("jMenuItem2");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        jMenuItem3.setText("jMenuItem3");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem4.setText("jMenuItem4");
        jMenu2.add(jMenuItem4);

        jMenuItem5.setText("jMenuItem5");
        jMenu2.add(jMenuItem5);

        jMenuItem6.setText("jMenuItem6");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(866, 866, 866))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jButton1))
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioButton1)
                            .addComponent(jRadioButton2))
                        .addGap(79, 79, 79)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 1612, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1087, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton2)
                        .addGap(41, 41, 41)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(jButton1)))
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE))
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // abrir d-er
        exp.showOpenDialog(this);
        File nuevo = exp.getSelectedFile();
        Scanner s = null;
        String a = "";
        try {
            s = new Scanner(nuevo);            
            while(s.hasNextLine()){
                a += s.nextLine();
            }            
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }finally{
            if(s!=null)
                s.close();
        }
        //System.out.println(a);
        jTextArea1.append(a);
        Analizar(a);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // guardar d-er
        String texto = jTextArea1.getText();
        System.out.println(texto);
        JFileChooser file = new JFileChooser();
        file.showSaveDialog(this);
        File guarda = file.getSelectedFile();
        
        if (guarda!=null) {
            try {
                FileWriter save = new FileWriter(guarda+".der");
                save.write(texto);
                save.close();
                JOptionPane.showMessageDialog(null,"Se ha guardado exitosamente","Informacion", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }else{
            JOptionPane.showMessageDialog(null,"Su archivo no se ha guardado","Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        for (Enumeration e = buttonGroup1.getElements(); e.hasMoreElements();) {
            JRadioButton a = (JRadioButton)e.nextElement();
            if (a.getModel()==buttonGroup1.getSelection()) {                
                if ("AFN".equals(a.getText())) {
                    String nombre = (String) jComboBox1.getSelectedItem();                    
                    ImageIcon imagen = new ImageIcon("c:\\txt1\\"+nombre+".png");
                    ImageIcon icono = new ImageIcon(imagen.getImage().getScaledInstance(jLabel3.getWidth(), jLabel3.getHeight(), Image.SCALE_SMOOTH));
                    jLabel3.setIcon(icono);
                    jTextArea2.setText("");
                }
                if ("AFD".equals(a.getText())) {
                    String nombre = (String) jComboBox1.getSelectedItem();                                        
                    ImageIcon imagen = new ImageIcon("c:\\txt1\\"+nombre+"1.png");
                    ImageIcon icono = new ImageIcon(imagen.getImage().getScaledInstance(jLabel3.getWidth(), jLabel3.getHeight(), Image.SCALE_SMOOTH));
                    jLabel3.setIcon(icono);
                    jTextArea2.setText("");
                    //estadosE
                    int posT = indiceTtransicion(nombre,nombreT);
                    if (posT!=100) {
                        String temp[][] = (String[][]) tablaT.elementAt(posT);                                                
                        Vector temp1 = (Vector) elementosE.elementAt(posT);                           
                        for (int i = temp1.size()-1; i >= 0; i--) {
                            String abc = String.valueOf(temp1.elementAt(i));
                            jTextArea2.append("\t"+abc);                            
                        }
                        
                        Vector temp2 = (Vector)estadosE.elementAt(posT);                        
                        
                        for (int i = temp.length-1; i >= 0; i--) {
                            jTextArea2.append("\n");                     
                            String abc = String.valueOf(temp2.elementAt(i));
                            jTextArea2.append(abc+"\t");
                            for (int j = temp[i].length-1; j >=0; j--) {                                
                               jTextArea2.append(temp[i][j]+"\t");
                            }
                        }
                    }
                    //tablaT
                    //jTextArea2
                }
                
            }
        }        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // abrir reporte de errores
        File path = new File ("Errores.html");   
        try {
            Desktop.getDesktop().open(path);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // analizar
        
        contador = 1;
        concatenado = 0;
        estadosSubC.clear();
        tablaT.clear();
        elementosE.clear();
        estadosE.clear();
        nombreT.clear();
        unomasT = false;
        ceromasT = false;
        jComboBox1.removeAllItems();
        ConjExp.clear();
        tokensName.clear();
        
        String texto = jTextArea1.getText();
        FileWriter fichero = null;
        PrintWriter pw = null;
        
        StringReader miReader = new StringReader(texto);
        AnalizadorLexico miAnalizador = new AnalizadorLexico(miReader); 
        miAnalizador.erroresLex.clear();
        miAnalizador.erroresLexColumna.clear();
        miAnalizador.erroresLexLinea.clear();
        sintactico parser = new sintactico(miAnalizador);
        
        parser.ConjExp.clear();
        parser.columnaNoRecuperables.clear();
        parser.columnaRecuperables.clear();
        parser.elementosG.clear();
        parser.expresiones.clear();
        parser.lineaNoRecuperables.clear();
        parser.lineaRecuperables.clear();
        parser.noRecuperables.clear();
        parser.nombreExpresiones.clear();
        parser.recuperables.clear();
        parser.tokensName.clear();
        
        try {
            System.out.println("Cadena Analizada: ");
            parser.parse();            
            // si hay errores
            for (int i = 0; i < miAnalizador.erroresLex.size(); i+=2) {
                    miAnalizador.erroresLex.removeElementAt(i);
                    miAnalizador.erroresLexColumna.removeElementAt(i);
                    miAnalizador.erroresLexLinea.removeElementAt(i);
                }
            
            if ((parser.noRecuperables.isEmpty()) && parser.recuperables.isEmpty() && miAnalizador.erroresLex.isEmpty()) { // size igual a 0                                
                JOptionPane.showMessageDialog(this, "Cadena aceptada");  
                for (int i = 0; i < parser.ConjExp.size(); i++) {
                    ConjExp.addElement(parser.ConjExp.elementAt(i));
                }
                for (int i = 0; i < parser.tokensName.size(); i++) {
                    tokensName.addElement(parser.tokensName.elementAt(i));
                    Def temp = (Def)tokensName.elementAt(i);                    
                }
            }else{                     
                JOptionPane.showMessageDialog(this, "El archivo contiene errores");                                                
                PrintHtml html = new PrintHtml(parser.noRecuperables,parser.lineaNoRecuperables,parser.columnaNoRecuperables,
                        parser.recuperables,parser.lineaRecuperables,parser.columnaRecuperables,
                        miAnalizador.erroresLex,miAnalizador.erroresLexColumna,miAnalizador.erroresLexLinea);
                html.pagina();                                
            }

            // Graficar AFN
            for (int i = 0; i < parser.expresiones.size(); i++) {
                fichero = new FileWriter("C:\\txt1\\"+parser.nombreExpresiones.elementAt(i)+".txt");
                pw = new PrintWriter(fichero);
                pw.println("Digraph g{");
                pw.println("graph[center=1 ,rankdir=LR];");
                
                // nombre de expresiones
                //System.out.println("\n---"+parser.nombreExpresiones.elementAt(i)+"---");                
                jComboBox1.addItem(parser.nombreExpresiones.elementAt(i));
                
                // elementos de la gramatica
                Vector temp = (Vector) parser.elementosG.elementAt(i);                                 
                //System.out.println("Conjunto de caracteres: "+temp+"\n");
                
                AddNumeroThompson(parser.expresiones.elementAt(i), pw);
                
                // elementos de la gramatica sin repetirse---Expresion regual, la primera transicion
                SubConjuntos(temp, parser.expresiones.elementAt(i), (String)parser.nombreExpresiones.elementAt(i));
                
                
                pw.println("}");
                if(null!= fichero ){
                    try {
                        fichero.close();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                generar.generarGrafica("C:\\txt1\\"+parser.nombreExpresiones.elementAt(i)+".txt", (String) parser.nombreExpresiones.elementAt(i));
                // Conjuntos                
            }
            for (int j = 0; j < parser.ConjExp.size(); j++) {
                    Conjuntos t = (Conjuntos)parser.ConjExp.elementAt(j);
                    System.out.println("Nombre de Conjunto: "+t.nombre);
                    if (t.conjComa.size()!=0) {
                        System.out.println("Elementos: "+t.conjComa);
                    }else{
                        System.out.println("Rango: "+t.conjRaya);
                    }
                }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
    }
    
    public void Analizar(String texto){ 
        contador = 1;
        concatenado = 0;
        estadosSubC.clear();
        tablaT.clear();
        elementosE.clear();
        estadosE.clear();
        nombreT.clear();
        unomasT = false;
        ceromasT = false;
        jComboBox1.removeAllItems();
        ConjExp.clear();
        tokensName.clear();
        
        FileWriter fichero = null;
        PrintWriter pw = null;
        
        StringReader miReader = new StringReader(texto);
        AnalizadorLexico miAnalizador = new AnalizadorLexico(miReader); 
        miAnalizador.erroresLex.clear();
        miAnalizador.erroresLexColumna.clear();
        miAnalizador.erroresLexLinea.clear();
                
        sintactico parser = new sintactico(miAnalizador);        
        parser.ConjExp.clear();
        parser.columnaNoRecuperables.clear();
        parser.columnaRecuperables.clear();
        parser.elementosG.clear();
        parser.expresiones.clear();
        parser.lineaNoRecuperables.clear();
        parser.lineaRecuperables.clear();
        parser.noRecuperables.clear();
        parser.nombreExpresiones.clear();
        parser.recuperables.clear();
        parser.tokensName.clear();
        
        try {
            System.out.println("Cadena Analizada: ");
            parser.parse();  
            for (int i = 0; i < miAnalizador.erroresLex.size(); i+=2) {
                    miAnalizador.erroresLex.removeElementAt(i);
                    miAnalizador.erroresLexColumna.removeElementAt(i);
                    miAnalizador.erroresLexLinea.removeElementAt(i);
                }
            // si hay errores
            if (parser.noRecuperables.isEmpty() && parser.recuperables.isEmpty() && miAnalizador.erroresLex.isEmpty()) { // size igual a 0                                
                JOptionPane.showMessageDialog(this, "Cadena aceptada");    
                for (int i = 0; i < parser.ConjExp.size(); i++) {
                    ConjExp.addElement(parser.ConjExp.elementAt(i));
                }
                for (int i = 0; i < parser.tokensName.size(); i++) {
                    tokensName.addElement(parser.tokensName.elementAt(i));
                    Def temp = (Def)tokensName.elementAt(i);                    
                }
            }else{                
                JOptionPane.showMessageDialog(this, "El archivo contiene errores");                
                for (int i = 0; i < miAnalizador.erroresLex.size(); i+=2) {
                    miAnalizador.erroresLex.removeElementAt(i);
                    miAnalizador.erroresLexColumna.removeElementAt(i);
                    miAnalizador.erroresLexLinea.removeElementAt(i);
                }                
                PrintHtml html = new PrintHtml(parser.noRecuperables,parser.lineaNoRecuperables,parser.columnaNoRecuperables,
                        parser.recuperables,parser.lineaRecuperables,parser.columnaRecuperables,
                        miAnalizador.erroresLex,miAnalizador.erroresLexColumna,miAnalizador.erroresLexLinea);
                html.pagina();                                
            }
            
            // Graficar AFN
            for (int i = 0; i < parser.expresiones.size(); i++) {
                fichero = new FileWriter("C:\\txt1\\"+parser.nombreExpresiones.elementAt(i)+".txt");
                pw = new PrintWriter(fichero);
                pw.println("Digraph g{");
                pw.println("graph[center=1 ,rankdir=LR];");
                
                // nombre de expresiones
                //System.out.println("\n---"+parser.nombreExpresiones.elementAt(i)+"---");                
                jComboBox1.addItem(parser.nombreExpresiones.elementAt(i));
                
                // elementos de la gramatica
                Vector temp = (Vector) parser.elementosG.elementAt(i);                                 
                //System.out.println("Conjunto de caracteres: "+temp+"\n");
                
                AddNumeroThompson(parser.expresiones.elementAt(i), pw);
                
                // elementos de la gramatica sin repetirse---Expresion regual, la primera transicion
                SubConjuntos(temp, parser.expresiones.elementAt(i), (String)parser.nombreExpresiones.elementAt(i));
                
                
                pw.println("}");
                if(null!= fichero ){
                    try {
                        fichero.close();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                generar.generarGrafica("C:\\txt1\\"+parser.nombreExpresiones.elementAt(i)+".txt", (String) parser.nombreExpresiones.elementAt(i));
                // Conjuntos                
            }
            for (int j = 0; j < parser.ConjExp.size(); j++) {
                    Conjuntos t = (Conjuntos)parser.ConjExp.elementAt(j);
                    System.out.println("Nombre de Conjunto: "+t.nombre);
                    if (t.conjComa.size()!=0) {
                        System.out.println("Elementos: "+t.conjComa);
                    }else{
                        System.out.println("Rango: "+t.conjRaya);
                    }
                }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
//        try{
//            Symbol s = (Symbol)miAnalizador.next_token();
//            while(s.sym!=0){
//                System.out.println("Lexema: " +s.value+"    Token: "+s.sym );
//                s=(Symbol)miAnalizador.next_token();
//            }   
//        }catch(Exception e){
//            System.out.println(e.getMessage());
//        }
    }
    
    // añadir los indices correctos a los nodos
    public void AddNumeroThompson(Object inicial, PrintWriter pw ){    
        String a[] = inicial.getClass().getCanonicalName().split("\\.");        
        // si es una clase hecha
        if (a.length==2){            
            contador = 1;
            opciones(a[1],inicial, pw);
            pw.println(contador-1+" [peripheries=2]");
        }else{  // si es String            
            //System.out.println(inicial);
            contador = 2;
            String ini = String.valueOf(inicial).replaceAll("\"", "");
            pw.println("1 -> 2 [fontsize=20, label=\""+ini+"\"]");  
            pw.println(contador+" [peripheries=2]");
        }
    }
    
    // Saber el tipo de operacion que es----- + | * ? .
    public void opciones(String a, Object nodos, PrintWriter pw){
        
        switch(a){
            case "CEROMAS":                
                CEROMAS z2 = (CEROMAS)nodos;
                contador--;
                z2.nodoInicio = contador;                
                contador++;
                z2.nodo2 = contador;
                contador++;                                                
                pw.println(z2.nodoInicio+" -> "+z2.nodo2+" [fontsize=20, label="+z2.transicion1+"]");
                String m2[] = z2.transicion2.getClass().getCanonicalName().split("\\.");
                if (m2.length==2){                    
                    opciones(m2[1],z2.transicion2,pw);
                    contador--;
                    z2.nodo3 = contador;
                    contador++;                    
                }else{  // si es String                   
                    z2.nodo3 = contador;
                    contador++;
                    pw.println(z2.nodo2+" -> "+z2.nodo3+" [fontsize=20, label="+z2.transicion2+"]");
                }                
                z2.nodoFinal = contador;
                contador++;
                
                // EPSILON´S
                    z2.inicio.addElement(z2.nodoInicio);
                    z2.inicio.addElement(z2.nodo2);
                    z2.inicio.addElement(z2.nodoFinal);                                     
                    
                    z2.noEpsilon.addElement(z2.nodo3);
                    z2.noEpsilon.addElement(z2.nodo2);
                    z2.noEpsilon.addElement(z2.nodoFinal);
                // EPSILON´S
                    
                pw.println(z2.nodo3+" -> "+z2.nodoFinal+" [fontsize=20, label="+z2.transicion4+"]");
                pw.println(z2.nodo3+" -> "+z2.nodo2+" [fontsize=20, label="+z2.transicion3+"]");
                pw.println(z2.nodoInicio+" -> "+z2.nodoFinal+" [fontsize=20, label="+z2.transicion5+"]");
                //System.out.println(z2.nodoInicio+"--"+z2.transicion1+"--"+z2.nodo2+"--"+z2.transicion2+"--"+z2.nodo3+"--"+z2.transicion4+"--"+z2.nodoFinal);
                break;
            case "UNOMAS":                
                UNOMAS z1 = (UNOMAS)nodos;
                contador--;
                z1.nodoInicio = contador;
                contador++;
                z1.nodo2 = contador;
                contador++;
                pw.println(z1.nodoInicio+" -> "+z1.nodo2+" [fontsize=20, label="+z1.transicion1+"]");
                String m1[] = z1.transicion2.getClass().getCanonicalName().split("\\.");
                if (m1.length==2){                    
                    opciones(m1[1],z1.transicion2,pw);
                    contador--;
                    z1.nodo3 = contador;
                    contador++;
                }else{  // si es String                    
                    z1.nodo3 = contador;
                    contador++;
                    pw.println(z1.nodo2+" -> "+z1.nodo3+" [fontsize=20, label="+z1.transicion2+"]");
                }                
                z1.nodoFinal = contador;
                contador++;
                
                // EPSILON´S                
                    z1.inicio.addElement(z1.nodoInicio);
                    z1.inicio.addElement(z1.nodo2);
                    
                    z1.noEpsilon.addElement(z1.nodo3);
                    z1.noEpsilon.addElement(z1.nodo2);
                    z1.noEpsilon.addElement(z1.nodoFinal);                    
                // EPSILON´S
                
                pw.println(z1.nodo3+" -> "+z1.nodoFinal+" [fontsize=20, label="+z1.transicion4+"]");
                pw.println(z1.nodo3+" -> "+z1.nodo2+" [fontsize=20, label="+z1.transicion3+"]");                
                //System.out.println(z1.nodoInicio+"--"+z1.transicion1+"--"+z1.nodo2+"--"+z1.transicion2+"--"+z1.nodo3+"--"+z1.transicion4+"--"+z1.nodoFinal);
                break;
            case "CONCAT":                              
                contador--;
                CONCAT x = (CONCAT) nodos;                
                x.nodoInicio = contador;                
                contador++;
                String m[] = x.transicion1.getClass().getCanonicalName().split("\\.");
                if (m.length==2){                                      
                    opciones(m[1],x.transicion1,pw);
                    contador--;
                    x.nodo2 = contador;
                    contador++;
                }else{  // si es String                                                           
                    x.nodo2 = contador;
                    contador++;
                    pw.println(x.nodoInicio+" -> "+x.nodo2+" [fontsize=20, label="+x.transicion1+"]");                                                            
                }
                String n[] = x.transicion2.getClass().getCanonicalName().split("\\.");
                if (n.length==2){                                      
                    opciones(n[1],x.transicion2,pw);
                    contador--;
                    x.nodoFin = contador;                                                           
                    contador++;                    
                }else{  // si es String                                       
                    x.nodoFin = contador;                                
                    contador++;
                    pw.println(x.nodo2+" -> "+x.nodoFin+" [fontsize=20, label="+x.transicion2+"]");                     
                }           
                
                // EPSILON´S                
                    x.inicio.addElement(x.nodoInicio);
                    
                        x.inicio2.addElement(x.nodo2);
                    
                    x.noEpsilon.addElement(x.nodo2);
                    
                        x.noEpsilon2.addElement(x.nodoFin);
                // EPSILON´S
                
                //System.out.println(x.nodoInicio+"--"+x.transicion1+"--"+x.nodo2+"--"+x.transicion2+"--"+x.nodoFin);                
                break;
            case "OR":                                
                OR y = (OR) nodos;
                contador--;
                y.nodoInicio = contador;
                contador++;
                y.nodo2 = contador;
                contador++;
                pw.println(y.nodoInicio+" -> "+y.nodo2+" [fontsize=20, label="+y.transicion1+"]");
                String o[] = y.transicion2.getClass().getCanonicalName().split("\\.");
                if (o.length==2){                   
                    opciones(o[1],y.transicion2,pw);
                    contador--;
                    y.nodo3 = contador;
                    contador++;                    
                }else{  // si es String                    
                    y.nodo3 = contador;
                    contador++;
                    pw.println(y.nodo2+" -> "+y.nodo3+" [fontsize=20, label="+y.transicion2+"]");
                }
                y.nodo4 = contador;
                contador++;
                pw.println(y.nodoInicio+" -> "+y.nodo4+" [fontsize=20, label="+y.transicion4+"]");
                String p[] = y.transicion5.getClass().getCanonicalName().split("\\.");
                if (p.length==2){                    
                    opciones(p[1],y.transicion5,pw);
                    contador--;                    
                    y.nodo5 = contador;
                    contador++;                    
                }else{  // si es String                    
                    y.nodo5 = contador;
                    contador++;
                    pw.println(y.nodo4+" -> "+y.nodo5+" [fontsize=20, label="+y.transicion5+"]");
                }
                y.nodoFinal = contador;
                contador++;
                
                // EPSILON´S
                    y.inicio.addElement(y.nodoInicio);
                    y.inicio.addElement(y.nodo2);
                    y.inicio.addElement(y.nodo4);                    
                    
                    y.noEpsilon.addElement(y.nodo3);
                    y.noEpsilon.addElement(y.nodoFinal);
                    
                    y.noEpsilon2.addElement(y.nodo5);
                    y.noEpsilon2.addElement(y.nodoFinal);
                // EPSILON´S
                
                pw.println(y.nodo3+" -> "+y.nodoFinal+" [fontsize=20, label="+y.transicion3+"]");
                pw.println(y.nodo5+" -> "+y.nodoFinal+" [fontsize=20, label="+y.transicion6+"]");
                //System.out.println(y.nodoInicio+"--"+y.transicion1+"--"+y.nodo2+"--"+y.transicion2+"--"+y.nodo3+"--"+y.transicion3+"--"+y.nodoFinal);
                //System.out.println(y.nodoInicio+"--"+y.transicion4+"--"+y.nodo4+"--"+y.transicion5+"--"+y.nodo5+"--"+y.transicion6+"--"+y.nodoFinal);
                break;
            case "CEROUNO":                
                CEROUNO z = (CEROUNO)nodos;
                contador--;
                z.nodoInicio = contador;
                contador++;
                z.nodo2 = contador;
                contador++;
                pw.println(z.nodoInicio+" -> "+z.nodo2+" [fontsize=20, label="+z.transicion1+"]");
                String p1[] = z.transicion2.getClass().getCanonicalName().split("\\.");
                if (p1.length==2){                    
                    opciones(p1[1],z.transicion2,pw);
                    contador--;
                    z.nodo3 = contador;
                    contador++;
                }else{  // si es String                    
                    z.nodo3 = contador;
                    contador++;
                    pw.println(z.nodo2+" -> "+z.nodo3+" [fontsize=20, label="+z.transicion2+"]");
                }
                z.nodoFinal = contador;
                contador++;
                
                // EPSILON´S                
                    z.inicio.addElement(z.nodoInicio);
                    z.inicio.addElement(z.nodo2);
                    z.inicio.addElement(z.nodoFinal);
                    
                    z.noEpsilon.addElement(z.nodo3);
                    z.noEpsilon.addElement(z.nodoFinal);                    
                // EPSILON´S
                
                pw.println(z.nodo3+" -> "+z.nodoFinal+" [fontsize=20, label="+z.transicion3+"]");
                pw.println(z.nodoInicio+" -> "+z.nodoFinal+" [fontsize=20, label="+z.transicion4+"]");                
                break;
        }
    }
    
    public void SubConjuntos(Vector elementos, Object Expresion, String nombre){   
        nombreT.addElement(nombre);
        
        int aceptacion = 0;
        FileWriter fichero = null;        
        PrintWriter pw = null;
        Cerradura nuevo = new Cerradura();
        try {
            fichero = new FileWriter("C:\\txt1\\"+nombre+"1.txt");
            pw = new PrintWriter(fichero);
            pw.println("Digraph g{");
            pw.println("graph[center=1 ,rankdir=LR];");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        String a[] = Expresion.getClass().getCanonicalName().split("\\.");
        
        if (a.length==2){                        
            elementosE.addElement(elementos);
            epsilon(a[1], Expresion, nuevo);            
            if ("CEROMAS".equals(a[1])) {
                CEROMAS A = (CEROMAS)Expresion;
                nuevo.cerraduras.addElement(A.inicio);     
                aceptacion = A.nodoFinal;
                //System.out.println("Cerradura: " + A.inicio);
            }else{
                if ("CEROUNO".equals(a[1])) {
                    CEROUNO A = (CEROUNO)Expresion;
                    nuevo.cerraduras.addElement(A.inicio);
                    aceptacion = A.nodoFinal;
                    //System.out.println("Cerradura: " + A.inicio);
                }else{
                    if ("CONCAT".equals(a[1])) {
                        CONCAT A = (CONCAT)Expresion;
                        nuevo.cerraduras.addElement(A.inicio);
                        aceptacion = A.nodoFin;
                        //System.out.println("Cerradura: " + A.inicio);
                    }else{
                        if ("OR".equals(a[1])) {
                            OR A = (OR)Expresion;
                            nuevo.cerraduras.addElement(A.inicio);
                            aceptacion = A.nodoFinal;
                            //System.out.println("Cerradura: " + A.inicio);
                        }else{
                            if ("UNOMAS".equals(a[1])) {
                                UNOMAS A = (UNOMAS)Expresion;
                                nuevo.cerraduras.addElement(A.inicio);
                                aceptacion = A.nodoFinal;
                                //System.out.println("Cerradura: " + A.inicio);
                            }
                        }
                    }
                }
            }            
            int n = 64+nuevo.cerraduras.size();
            for (int i = 0; i < nuevo.cerraduras.size(); i++) {
                char temp = (char) n;
                nuevo.nombre.addElement(temp);
                n--;
            }
            
            // Crear tabla de transiciones
            //Indices Columnas --> elementos.elementAt()
            //Indices Filas --> nuevo.nombre.elementAt()
            //Filas , columnas
            estadosE.addElement(nuevo.nombre); 
            String [][] transiciones= new String[nuevo.nombre.size()][elementos.size()];                        
            
            for (int i = nuevo.cerraduras.size()-1; i >= 0; i--) {
                //System.out.println(nuevo.cerraduras.elementAt(i)+" = "+ nuevo.nombre.elementAt(i));                
                for (int j = 0; j < nuevo.irNumero.size(); j++) {
                    int t = (int) nuevo.irNumero.elementAt(j);
                    Vector t1 = (Vector) nuevo.cerraduras.elementAt(i);
                    if (t1.contains(t-1)) {                         
                        for (int k = 0; k < nuevo.cerraduras.size(); k++) {
                            Vector t2 = (Vector) nuevo.cerraduras.elementAt(k);
                            if (nuevo.irNumero.elementAt(j)==t2.elementAt(0)) {
                                pw.println(nuevo.nombre.elementAt(i)+" -> "+nuevo.nombre.elementAt(k)+" [fontsize=20, label="+nuevo.irLetra.elementAt(j)+"]");                                
                                int abCol = indiceElementos((String) nuevo.irLetra.elementAt(j),elementos);                                
                                int abFil = indiceEstado(String.valueOf(nuevo.nombre.elementAt(i)),nuevo.nombre);                                                                
                                if (abCol!=100 && abFil!=100) {
                                    transiciones[abFil][abCol] = String.valueOf(nuevo.nombre.elementAt(k));
                                }
                                //System.out.println("ir("+ nuevo.nombre.elementAt(i)+","+nuevo.irLetra.elementAt(j)+" ) = "+nuevo.irNumero.elementAt(j)+" -> "+nuevo.nombre.elementAt(k));
                            }
                        }                        
                    }
                }                
            }
            
            for (int i = 0; i < transiciones.length; i++) { //Filas            
                for (int j = 0; j < transiciones[i].length; j++) { // Columnas
                    if (transiciones[i][j]==null) {
                        transiciones[i][j] = "-";
                    }                    
                }
            }
            
            
            Vector add = new Vector(5,10);
            for (int i = 0; i < nuevo.cerraduras.size(); i++) {
                Vector t1 = (Vector) nuevo.cerraduras.elementAt(i);
                if (t1.contains(aceptacion)) {
                    pw.println(nuevo.nombre.elementAt(i)+" [peripheries=2]");                    
                    
                    if (!add.contains(nuevo.nombre.elementAt(i))) {
                        add.addElement(nuevo.nombre.elementAt(i));
                        String abc = nuevo.nombre.elementAt(i)+"*";
                        nuevo.nombre.removeElementAt(i);                                                
                        nuevo.nombre.add(i, abc);                        
                    }
                    
                }
            }
            
            tablaT.addElement(transiciones);
            
            pw.println("}");
                if(null!= fichero ){
                    try {
                        fichero.close();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            generar.generarGrafica("C:\\txt1\\"+nombre+"1.txt", nombre+"1");
        }else{  // si es String                       
//            //estados
            Vector temp = new Vector(5,10);
            temp.addElement("A");
            temp.addElement("B*");
            estadosE.addElement(temp);
            
////            //elementos
            Vector temp1 = new Vector(5,10);
            temp1.addElement(Expresion);              
            elementosE.addElement(temp1);
            
            String l[][] = new String[2][1];
            l[0][0] = "B";
            l[1][0] = "-";            
            tablaT.addElement(l);
            String ta = String.valueOf(Expresion).replaceAll("\"", "");
            pw.println("A"+" -> "+"B"+" [fontsize=20, label=\""+ta+"\"]");
            pw.println("B"+" [peripheries=2]");                        
            pw.println("}");
                if(null!= fichero ){
                    try {
                        fichero.close();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            generar.generarGrafica("C:\\txt1\\"+nombre+"1.txt", nombre+"1");
        }
    }
    
    public int indiceElementos(String elemento, Vector elementos){        
        for (int i = 0; i < elementos.size(); i++) {
            if (elemento.equals(String.valueOf(elementos.elementAt(i)))) {                
                return i;
            }
        }
        return 100;
    }
    
    public int indiceEstado(String elemento, Vector elementos){          
        for (int i = 0; i < elementos.size(); i++) {
            if (elemento.equals(String.valueOf(elementos.elementAt(i)))) {                 
                return i;
            }
        }
        return 100;
    }
    
    public int indiceTtransicion(String elemento, Vector elementos){          
        for (int i = 0; i < elementos.size(); i++) {
            if (elemento.equals(String.valueOf(elementos.elementAt(i)))) {                 
                return i;
            }
        }
        return 100;
    }
    // solo se calcula los iniciales
    // en cada uno cuando termina de analizar, se puede crear el ir??
    public void epsilon(String tipo, Object exp, Cerradura add){
    
        switch(tipo){
            case "CEROMAS":                
                CEROMAS v = (CEROMAS)exp;                  
                
//                if (unomasT == true) {
//                    for (int i = 0; i < v.inicio.size(); i++) {
//                        if (!v.noEpsilon.contains(v.inicio.elementAt(i))) {
//                            v.noEpsilon.addElement(v.inicio.elementAt(i));
//                        }
//                    }                    
//                }
//                if (ceromasT == true) {
//                    for (int i = 0; i < v.inicio.size(); i++) {
//                        if (!v.noEpsilon.contains(v.inicio.elementAt(i))) {
//                            v.noEpsilon.addElement(v.inicio.elementAt(i));
//                        }
//                    }                    
//                }
                ceromasT = true;
                String a1[] = v.transicion2.getClass().getCanonicalName().split("\\.");
                if (a1.length==2) {                                          
                    if ("CEROMAS".equals(a1[1])) {
                        //System.out.println("CEROMAS->CEROMAS");
                        CEROMAS e1 = (CEROMAS)v.transicion2 ;                        
                        for (int i = 0; i < v.noEpsilon.size(); i++) {
                            if (!e1.noEpsilon.contains(v.noEpsilon.elementAt(i))) {
                                e1.noEpsilon.addElement(v.noEpsilon.elementAt(i));
                            }
                        }
                        for (int i = 0; i < e1.inicio.size(); i++) {
                            if (!e1.noEpsilon.contains(e1.inicio.elementAt(i))) {
                                e1.noEpsilon.addElement(e1.inicio.elementAt(i));
                            }
                        }
                        epsilon(a1[1], e1,add);
                        ceromasT = false;
                        // añadir inicio
                        for (int i = 0; i < e1.inicio.size(); i++) {
                            if (!v.inicio.contains(e1.inicio.elementAt(i))) {
                                v.inicio.addElement(e1.inicio.elementAt(i));
                            }
                        }
                    }else{
                        if ("CEROUNO".equals(a1[1])) {
                            //System.out.println("CEROMAS->CEROUNO");
                            CEROUNO e2 = (CEROUNO)v.transicion2 ;                            
                            for (int i = 0; i < e2.inicio.size(); i++) {
                                if (!e2.noEpsilon.contains(e2.inicio.elementAt(i))) {
                                    e2.noEpsilon.addElement(e2.inicio.elementAt(i));
                                }
                            }
                            for (int i = 0; i < v.noEpsilon.size(); i++) {
                                if (!e2.noEpsilon.contains(v.noEpsilon.elementAt(i))) {
                                    e2.noEpsilon.addElement(v.noEpsilon.elementAt(i));
                                }
                            }
                            epsilon(a1[1], e2,add);
                            ceromasT = false;
                            // añadir inicio
                            for (int i = 0; i < e2.inicio.size(); i++) {
                                if (!v.inicio.contains(e2.inicio.elementAt(i))) {
                                    v.inicio.addElement(e2.inicio.elementAt(i));
                                }
                            }
                        }else{
                            if ("CONCAT".equals(a1[1])) {
                                //System.out.println("CEROMAS->CONCAT");
                                CONCAT e3 = (CONCAT)v.transicion2 ;
                                for (int i = 0; i < v.noEpsilon.size(); i++) {
                                    if (!e3.noEpsilon2.contains(v.noEpsilon.elementAt(i))) {
                                        e3.noEpsilon2.addElement(v.noEpsilon.elementAt(i));
                                    }
                                }
                                for (int i = 0; i < e3.inicio.size(); i++) {
                                    if (!e3.noEpsilon.contains(e3.inicio.elementAt(i))) {
                                        e3.noEpsilon.addElement(e3.inicio.elementAt(i));
                                    }
                                }
                                epsilon(a1[1], e3,add);
                                ceromasT = false;
                                // añadir inicio
                                for (int i = 0; i < e3.inicio.size(); i++) {
                                    if (!v.inicio.contains(e3.inicio.elementAt(i))) {
                                        v.inicio.addElement(e3.inicio.elementAt(i));
                                    }
                                }
                            }else{
                                if ("OR".equals(a1[1])) {
                                    //System.out.println("CEROMAS->OR");
                                    OR e4 = (OR)v.transicion2 ;
                                    for (int i = 0; i < e4.inicio.size(); i++) {
                                        if (e4.noEpsilon.contains(e4.inicio.elementAt(i))) {
                                            e4.noEpsilon.addElement(e4.inicio.elementAt(i));
                                        }
                                        if (e4.noEpsilon2.contains(e4.inicio.elementAt(i))) {
                                            e4.noEpsilon2.addElement(e4.inicio.elementAt(i));
                                        }
                                    }
                                    for (int i = 0; i < v.noEpsilon.size(); i++) {
                                        if (!e4.noEpsilon.contains(v.noEpsilon.elementAt(i))) {
                                            e4.noEpsilon.addElement(v.noEpsilon.elementAt(i));
                                        }
                                        if (!e4.noEpsilon2.contains(v.noEpsilon.elementAt(i))) {
                                            e4.noEpsilon2.addElement(v.noEpsilon.elementAt(i));
                                        }
                                    }
                                    epsilon(a1[1], e4,add); 
                                    ceromasT = false;
                                    // añadir inicio
                                    for (int i = 0; i < e4.inicio.size(); i++) {
                                        if (!v.inicio.contains(e4.inicio.elementAt(i))) {
                                            v.inicio.addElement(e4.inicio.elementAt(i));
                                        }
                                    }
                                }else{
                                    //System.out.println("CEROMAS->UNOMAS");
                                    UNOMAS e5 = (UNOMAS)v.transicion2 ; 
                                    for (int i = 0; i < v.noEpsilon.size(); i++) {
                                        if (!e5.noEpsilon.contains(v.noEpsilon.elementAt(i))) {
                                            e5.noEpsilon.addElement(v.noEpsilon.elementAt(i));
                                        }
                                    }
                                    for (int i = 0; i < e5.inicio.size(); i++) {
                                        if (!e5.noEpsilon.contains(e5.inicio.elementAt(i))) {
                                            e5.noEpsilon.addElement(e5.inicio.elementAt(i));
                                        }
                                    }
                                    epsilon(a1[1], e5,add);   
                                    ceromasT = false;
                                    // añadir inicio
                                    for (int i = 0; i < e5.inicio.size(); i++) {
                                        if (!v.inicio.contains(e5.inicio.elementAt(i))) {
                                            v.inicio.addElement(e5.inicio.elementAt(i));
                                        }
                                    }
                                }
                            }
                        }                        
                    } // termina                    
                }else{                              // es un string, entonces no pasa a otro estado   
                    add.cerraduras.addElement(v.noEpsilon);
                    add.irNumero.addElement(v.nodo3);
                    add.irLetra.addElement(v.transicion2);
                    //System.out.println("ir: " + v.transicion2 +" =" + v.nodo3);                    
                    //System.out.println("Cerradura: " + v.noEpsilon);
                }                                
                break;
            case "CEROUNO":
                CEROUNO w = (CEROUNO)exp;  
                
                if (unomasT == true) {
                    for (int i = 0; i < w.inicio.size(); i++) {
                        if (!w.noEpsilon.contains(w.inicio.elementAt(i))) {
                            w.noEpsilon.addElement(w.inicio.elementAt(i));
                        }
                    }                    
                }
                if (ceromasT == true) {
                    for (int i = 0; i < w.inicio.size(); i++) {
                        if (!w.noEpsilon.contains(w.inicio.elementAt(i))) {
                            w.noEpsilon.addElement(w.inicio.elementAt(i));
                        }
                    }                    
                }
                
                String a2[] = w.transicion2.getClass().getCanonicalName().split("\\.");
                if (a2.length==2) {
                    // no es un string, entonces al objecto le agrego los epsilon
                    // si es CEROUNO o CEROMAS add a Inicio los noEpsilon
                    if ("CEROMAS".equals(a2[1])) {
                        //System.out.println("CEROUNO->CEROMAS");
                        CEROMAS e1 = (CEROMAS)w.transicion2 ;
                        for (int i = 0; i < w.noEpsilon.size(); i++) {
                            if (!e1.noEpsilon.contains(w.noEpsilon.elementAt(i))) {
                                e1.noEpsilon.addElement(w.noEpsilon.elementAt(i));
                            }
                        }
                        epsilon(a2[1], e1, add);
                        for (int i = 0; i < e1.inicio.size(); i++) {
                            if (!w.inicio.contains(e1.inicio.elementAt(i))) {
                                w.inicio.addElement(e1.inicio.elementAt(i));
                            }
                        }
                    }else{
                        if ("CEROUNO".equals(a2[1])) {
                            //System.out.println("CEROUNO->CEROUNO");
                            CEROUNO e2 = (CEROUNO)w.transicion2 ;
                            for (int i = 0; i < w.noEpsilon.size(); i++) {
                                if (!e2.noEpsilon.contains(w.noEpsilon.elementAt(i))) {
                                    e2.noEpsilon.addElement(w.noEpsilon.elementAt(i));
                                }
                            }
                            epsilon(a2[1], e2, add);
                            for (int i = 0; i < e2.inicio.size(); i++) {
                                if (!w.inicio.contains(e2.inicio.elementAt(i))) {
                                    w.inicio.addElement(e2.inicio.elementAt(i));
                                }
                            }
                        }else{
                            if ("CONCAT".equals(a2[1])) {
                                //System.out.println("CEROUNO->CONCAT");
                                CONCAT e3 = (CONCAT)w.transicion2 ;
                                for (int i = 0; i < w.noEpsilon.size(); i++) {
                                    if (!e3.noEpsilon2.contains(w.noEpsilon.elementAt(i))) {
                                        e3.noEpsilon2.addElement(w.noEpsilon.elementAt(i));
                                    }
                                }
                                epsilon(a2[1], e3, add);
                                for (int i = 0; i < e3.inicio.size(); i++) {
                                    if (!w.inicio.contains(e3.inicio.elementAt(i))) {
                                        w.inicio.addElement(e3.inicio.elementAt(i));
                                    }
                                }
                            }else{
                                if ("OR".equals(a2[1])) {
                                    //System.out.println("CEROUNO->OR");
                                    OR e4 = (OR)w.transicion2 ;
                                    for (int i = 0; i < w.noEpsilon.size(); i++) {
                                        if (!e4.noEpsilon.contains(w.noEpsilon.elementAt(i))) {
                                            e4.noEpsilon.addElement(w.noEpsilon.elementAt(i));
                                        }
                                        if (!e4.noEpsilon2.contains(w.noEpsilon.elementAt(i))) {
                                            e4.noEpsilon2.addElement(w.noEpsilon.elementAt(i));
                                        }
                                    }
                                    epsilon(a2[1], e4, add);   
                                    for (int i = 0; i < e4.inicio.size(); i++) {
                                        if (!w.inicio.contains(e4.inicio.elementAt(i))) {
                                            w.inicio.addElement(e4.inicio.elementAt(i));
                                        }
                                    }
                                }else{
                                    UNOMAS e5 = (UNOMAS)w.transicion2 ;
                                    //System.out.println("CEROUNO->UNOMAS");  
                                    for (int i = 0; i < w.noEpsilon.size(); i++) {
                                        if (!e5.noEpsilon.contains(w.noEpsilon.elementAt(i))) {
                                            e5.noEpsilon.addElement(w.noEpsilon.elementAt(i));
                                        }
                                    }
                                    epsilon(a2[1], e5, add);
                                    for (int i = 0; i < e5.inicio.size(); i++) {
                                        if (!w.inicio.contains(e5.inicio.elementAt(i))) {
                                            w.inicio.addElement(e5.inicio.elementAt(i));
                                        }
                                    }
                                }
                            }
                        }                        
                    } // termina                                        
                }else{        
                    add.cerraduras.addElement(w.noEpsilon);
                    add.irNumero.addElement(w.nodo3);
                    add.irLetra.addElement(w.transicion2);
                    //System.out.println("ir: " + w.transicion2 +" =" + w.nodo3);
                    //System.out.println("Cerradura: " + w.noEpsilon);
                }                
                break;
            case "CONCAT":
                CONCAT x = (CONCAT)exp;   
                
//                if (unomasT == true) {
//                    for (int i = 0; i < x.inicio.size(); i++) {
//                        if (!x.noEpsilon2.contains(x.inicio.elementAt(i))) {
//                            x.noEpsilon2.addElement(x.inicio.elementAt(i));
//                        }
//                    }                    
//                }
//                if (ceromasT == true) {
//                    for (int i = 0; i < x.inicio.size(); i++) {
//                        if (!x.noEpsilon2.contains(x.inicio.elementAt(i))) {
//                            x.noEpsilon2.addElement(x.inicio.elementAt(i));
//                        }
//                    }                    
//                }
                
                String a3[] = x.transicion1.getClass().getCanonicalName().split("\\.");
                String a7[] = x.transicion2.getClass().getCanonicalName().split("\\."); 
                CEROMAS A1 = null;
                CEROUNO A2 = null;
                CONCAT A3 = null;
                OR A4 = null;
                UNOMAS A5 = null;                                
                if (a7.length==2) {
                    if ("CEROMAS".equals(a7[1])) {
                        //System.out.println("CONCAT2->CEROMAS");
                        CEROMAS e1 = (CEROMAS)x.transicion2 ;
                        for (int i = 0; i < x.noEpsilon2.size(); i++) {
                            if (!e1.noEpsilon.contains(x.noEpsilon2.elementAt(i))) {
                                e1.noEpsilon.addElement(x.noEpsilon2.elementAt(i));
                            }
                        }
                        epsilon(a7[1], e1, add);  
                        A1 = e1;
                        // añadir inicio
                        for (int i = 0; i < e1.inicio.size(); i++) {
                            if (!x.inicio2.contains(e1.inicio.elementAt(i))) {
                                x.inicio2.addElement(e1.inicio.elementAt(i));
                            }
                        }
                    }else{
                        if ("CEROUNO".equals(a7[1])) {
                            //System.out.println("CONCAT2->CEROUNO");
                            CEROUNO e2 = (CEROUNO)x.transicion2 ;
                            for (int i = 0; i < x.noEpsilon2.size(); i++) {
                                if (!e2.noEpsilon.contains(x.noEpsilon2.elementAt(i))) {
                                    e2.noEpsilon.addElement(x.noEpsilon2.elementAt(i));
                                }
                            }
                            epsilon(a7[1], e2, add);  
                            A2 = e2;
                            // añadir inicio
                            for (int i = 0; i < e2.inicio.size(); i++) {
                                if (!x.inicio2.contains(e2.inicio.elementAt(i))) {
                                    x.inicio2.addElement(e2.inicio.elementAt(i));
                                }
                            }
                        }else{
                            if ("CONCAT".equals(a7[1])) {
                                //System.out.println("CONCAT2->CONCAT");
                                CONCAT e3 = (CONCAT)x.transicion2 ;
                                for (int i = 0; i < x.noEpsilon2.size(); i++) {
                                    if (!e3.noEpsilon2.contains(x.noEpsilon2.elementAt(i))) {
                                        e3.noEpsilon2.addElement(x.noEpsilon2.elementAt(i));
                                    }
                                }
                                epsilon(a7[1], e3, add); 
                                A3 = e3;
                                // añadir inicio
                                for (int i = 0; i < e3.inicio.size(); i++) {
                                    if (!x.inicio2.contains(e3.inicio.elementAt(i))) {
                                        x.inicio2.addElement(e3.inicio.elementAt(i));
                                    }
                                }
                            }else{
                                if ("OR".equals(a7[1])) {
                                    //System.out.println("CONCAT2->OR");
                                    OR e4 = (OR)x.transicion2 ;
                                    for (int i = 0; i < x.noEpsilon2.size(); i++) {
                                        if (!e4.noEpsilon.contains(x.noEpsilon2.elementAt(i))) {
                                            e4.noEpsilon.addElement(x.noEpsilon2.elementAt(i));
                                        }
                                        if (!e4.noEpsilon2.contains(x.noEpsilon2.elementAt(i))) {
                                            e4.noEpsilon2.addElement(x.noEpsilon2.elementAt(i));
                                        }
                                    }
                                    epsilon(a7[1], e4, add);  
                                    A4 = e4;
                                    // añadir inicio
                                    for (int i = 0; i < e4.inicio.size(); i++) {
                                        if (!x.inicio2.contains(e4.inicio.elementAt(i))) {
                                            x.inicio2.addElement(e4.inicio.elementAt(i));
                                        }
                                    }
                                }else{
                                    UNOMAS e5 = (UNOMAS)x.transicion2 ;
                                    //System.out.println("CONCAT2->UNOMAS");                                     
                                    for (int i = 0; i < x.noEpsilon2.size(); i++) {
                                        if (!e5.noEpsilon.contains(x.noEpsilon2.elementAt(i)) ) {
                                            e5.noEpsilon.addElement(x.noEpsilon2.elementAt(i));
                                        }
                                    }
                                    epsilon(a7[1], e5, add);
                                    A5 = e5;                                        
                                    // añadir inicio
                                    for (int i = 0; i < e5.inicio.size(); i++) {
                                        if (!x.inicio2.contains(e5.inicio.elementAt(i))) {
                                            x.inicio2.addElement(e5.inicio.elementAt(i));
                                        }
                                    }
                                }
                            }
                        }                        
                    } // termina
                    //epsilon(a7[1],x.transicion2);
                }else{   
                    add.cerraduras.addElement(x.noEpsilon2);
                    add.irNumero.addElement(x.nodoFin);
                    add.irLetra.addElement(x.transicion2);                    
                    //System.out.println("ir: " + x.transicion2 +" =" + x.nodoFin);                    
                    //System.out.println("Cerradura: " + x.noEpsilon2);
                }
                if (a3.length==2) {
                    if ("CEROMAS".equals(a3[1])) {
                        //System.out.println("CONCAT1->CEROMAS");
                        CEROMAS e1 = (CEROMAS)x.transicion1 ;
                        for (int i = 0; i < x.noEpsilon.size(); i++) {
                            if (!e1.noEpsilon.contains(x.noEpsilon.elementAt(i))) {
                                e1.noEpsilon.addElement(x.noEpsilon.elementAt(i));
                            }
                        }                        
                        if (A1!=null) {
                            for (int i = 0; i < A1.inicio.size(); i++) {
                                if (!e1.noEpsilon.contains(A1.inicio.elementAt(i))) {
                                    e1.noEpsilon.addElement(A1.inicio.elementAt(i));
                                }
                            }                            
                        }else{
                            if(A2!=null){
                                for (int i = 0; i < A2.inicio.size(); i++) {
                                    if (!e1.noEpsilon.contains(A2.inicio.elementAt(i))) {
                                        e1.noEpsilon.addElement(A2.inicio.elementAt(i));
                                    }
                                }
                            }else{
                                if (A3!=null) {
                                    for (int i = 0; i < A3.inicio.size(); i++) {
                                        if (!e1.noEpsilon.contains(A3.inicio.elementAt(i))) {
                                            e1.noEpsilon.addElement(A3.inicio.elementAt(i));
                                        }
                                    }
                                }else{
                                    if (A4!=null) {
                                        for (int i = 0; i < A4.inicio.size(); i++) {
                                            if (!e1.noEpsilon.contains(A4.inicio.elementAt(i))) {
                                                e1.noEpsilon.addElement(A4.inicio.elementAt(i));
                                            }
                                        }
                                    }else{
                                        if (A5!=null) {
                                            for (int i = 0; i < A5.inicio.size(); i++) {
                                                if (!e1.noEpsilon.contains(A5.inicio.elementAt(i))) {
                                                    e1.noEpsilon.addElement(A5.inicio.elementAt(i));
                                                }                                                
                                            }                                            
                                        }
                                    }
                                }
                            }
                        }
                        epsilon(a3[1], e1, add);
                        // añadir inicio
                        for (int i = 0; i < e1.inicio.size(); i++) {
                            if (!x.inicio.contains(e1.inicio.elementAt(i))) {
                                x.inicio.addElement(e1.inicio.elementAt(i));
                            }
                        }
                        for (int i = 0; i < x.inicio2.size(); i++) {
                            if (!x.inicio.contains(x.inicio2.elementAt(i))) {
                                x.inicio.addElement(x.inicio2.elementAt(i));
                            }
                        }
                    }else{
                        if ("CEROUNO".equals(a3[1])) {
                            //System.out.println("CONCAT1->CEROUNO");
                            CEROUNO e2 = (CEROUNO)x.transicion1 ;
                            for (int i = 0; i < x.noEpsilon.size(); i++) {
                                if (!e2.noEpsilon.contains(x.noEpsilon.elementAt(i))) {
                                    e2.noEpsilon.addElement(x.noEpsilon.elementAt(i));
                                }
                            }
                            if (A1!=null) {
                                for (int i = 0; i < A1.inicio.size(); i++) {
                                    if (!e2.noEpsilon.contains(A1.inicio.elementAt(i))) {
                                        e2.noEpsilon.addElement(A1.inicio.elementAt(i));
                                    }
                                }
                            }else{
                                if(A2!=null){
                                    for (int i = 0; i < A2.inicio.size(); i++) {
                                        if (!e2.noEpsilon.contains(A2.inicio.elementAt(i))) {
                                            e2.noEpsilon.addElement(A2.inicio.elementAt(i));
                                        }
                                    }
                                }else{
                                    if (A3!=null) {
                                        for (int i = 0; i < A3.inicio.size(); i++) {
                                            if (!e2.noEpsilon.contains(A3.inicio.elementAt(i))) {
                                                e2.noEpsilon.addElement(A3.inicio.elementAt(i));
                                            }
                                        }
                                    }else{
                                        if (A4!=null) {
                                            for (int i = 0; i < A4.inicio.size(); i++) {
                                                if (!e2.noEpsilon.contains(A4.inicio.elementAt(i))) {
                                                    e2.noEpsilon.addElement(A4.inicio.elementAt(i));
                                                }
                                            }
                                        }else{
                                            if (A5!=null) {
                                                for (int i = 0; i < A5.inicio.size(); i++) {
                                                    if (!e2.noEpsilon.contains(A5.inicio.elementAt(i))) {
                                                        e2.noEpsilon.addElement(A5.inicio.elementAt(i));
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            epsilon(a3[1], e2, add);  
                            // añadir inicio                            
                            for (int i = 0; i < e2.inicio.size(); i++) {
                                if (!x.inicio.contains(e2.inicio.elementAt(i))) {
                                    x.inicio.addElement(e2.inicio.elementAt(i));
                                }
                            }      
                            for (int i = 0; i < x.inicio2.size(); i++) {
                                if (!x.inicio.contains(x.inicio2.elementAt(i))) {
                                    x.inicio.addElement(x.inicio2.elementAt(i));
                                }
                            }
                        }else{
                            if ("CONCAT".equals(a3[1])) {
                                //System.out.println("CONCAT1->CONCAT");
                                CONCAT e3 = (CONCAT)x.transicion1 ;
                                for (int i = 0; i < x.noEpsilon.size(); i++) {
                                    if (!e3.noEpsilon2.contains(x.noEpsilon.elementAt(i))) {
                                        e3.noEpsilon2.addElement(x.noEpsilon.elementAt(i));
                                    }
                                }
                                if (A1!=null) {
                                    for (int i = 0; i < A1.inicio.size(); i++) {
                                        if (!e3.noEpsilon2.contains(A1.inicio.elementAt(i))) {
                                            e3.noEpsilon2.addElement(A1.inicio.elementAt(i));
                                        }
                                    }
                                }else{
                                    if(A2!=null){
                                        for (int i = 0; i < A2.inicio.size(); i++) {
                                            if (!e3.noEpsilon2.contains(A2.inicio.elementAt(i))) {
                                                e3.noEpsilon2.addElement(A2.inicio.elementAt(i));
                                            }
                                        }
                                    }else{
                                        if (A3!=null) {
                                            for (int i = 0; i < A3.inicio.size(); i++) {
                                                if (!e3.noEpsilon2.contains(A3.inicio.elementAt(i))) {
                                                    e3.noEpsilon2.addElement(A3.inicio.elementAt(i));
                                                }
                                            }
                                        }else{
                                            if (A4!=null) {
                                                for (int i = 0; i < A4.inicio.size(); i++) {
                                                    if (!e3.noEpsilon2.contains(A4.inicio.elementAt(i))) {
                                                        e3.noEpsilon2.addElement(A4.inicio.elementAt(i));
                                                    }
                                                }
                                            }else{
                                                if (A5!=null) {
                                                    for (int i = 0; i < A5.inicio.size(); i++) {
                                                        if (!e3.noEpsilon2.contains(A5.inicio.elementAt(i))) {
                                                            e3.noEpsilon2.addElement(A5.inicio.elementAt(i));
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                epsilon(a3[1], e3, add);  
                                // añadir inicio
                                for (int i = 0; i < e3.inicio.size(); i++) {
                                    if (!x.inicio.contains(e3.inicio.elementAt(i))) {
                                        x.inicio.addElement(e3.inicio.elementAt(i));
                                    }
                                }
                            }else{
                                if ("OR".equals(a3[1])) {
                                    //System.out.println("CONCAT1->OR");
                                    OR e4 = (OR)x.transicion1 ;
                                    for (int i = 0; i < x.noEpsilon.size(); i++) {
                                        if (!e4.noEpsilon.contains(x.noEpsilon.elementAt(i))) {
                                            e4.noEpsilon.addElement(x.noEpsilon.elementAt(i));
                                        }
                                        if (!e4.noEpsilon2.contains(x.noEpsilon.elementAt(i))) {
                                            e4.noEpsilon2.addElement(x.noEpsilon.elementAt(i));
                                        }
                                    }
                                    if (A1!=null) {
                                        for (int i = 0; i < A1.inicio.size(); i++) {
                                            if (!e4.noEpsilon.contains(A1.inicio.elementAt(i))) {
                                                e4.noEpsilon.addElement(A1.inicio.elementAt(i));
                                            }
                                            if (!e4.noEpsilon2.contains(A1.inicio.elementAt(i))) {
                                                e4.noEpsilon2.addElement(A1.inicio.elementAt(i));
                                            }
                                        }
                                    }else{
                                        if(A2!=null){
                                            for (int i = 0; i < A2.inicio.size(); i++) {
                                                if (!e4.noEpsilon.contains(A2.inicio.elementAt(i))) {
                                                    e4.noEpsilon.addElement(A2.inicio.elementAt(i));
                                                }
                                                if (!e4.noEpsilon2.contains(A2.inicio.elementAt(i))) {
                                                    e4.noEpsilon2.addElement(A2.inicio.elementAt(i));
                                                }
                                            }
                                        }else{
                                            if (A3!=null) {
                                                for (int i = 0; i < A3.inicio.size(); i++) {
                                                    if (!e4.noEpsilon.contains(A3.inicio.elementAt(i))) {
                                                        e4.noEpsilon.addElement(A3.inicio.elementAt(i));
                                                    }
                                                    if (!e4.noEpsilon2.contains(A3.inicio.elementAt(i))) {
                                                        e4.noEpsilon2.addElement(A3.inicio.elementAt(i));
                                                    }
                                                }
                                            }else{
                                                if (A4!=null) {
                                                    for (int i = 0; i < A4.inicio.size(); i++) {
                                                        if (!e4.noEpsilon.contains(A4.inicio.elementAt(i))) {
                                                            e4.noEpsilon.addElement(A4.inicio.elementAt(i));
                                                        }
                                                        if (!e4.noEpsilon2.contains(A4.inicio.elementAt(i))) {
                                                            e4.noEpsilon2.addElement(A4.inicio.elementAt(i));
                                                        }
                                                    }
                                                }else{
                                                    if (A5!=null) {
                                                        for (int i = 0; i < A5.inicio.size(); i++) {
                                                            if (!e4.noEpsilon.contains(A5.inicio.elementAt(i))) {
                                                                e4.noEpsilon.addElement(A5.inicio.elementAt(i));
                                                            }
                                                            if (!e4.noEpsilon2.contains(A5.inicio.elementAt(i))) {
                                                                e4.noEpsilon2.addElement(A5.inicio.elementAt(i));
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    epsilon(a3[1], e4, add);  
                                    // añadir inicio
                                    for (int i = 0; i < e4.inicio.size(); i++) {
                                        if (!x.inicio.contains(e4.inicio.elementAt(i))) {
                                            x.inicio.addElement(e4.inicio.elementAt(i));
                                        }
                                    }
                                }else{
                                    UNOMAS e5 = (UNOMAS)x.transicion1 ;
                                    //System.out.println("CONCAT1->UNOMAS");                                    
                                    for (int i = 0; i < x.noEpsilon.size(); i++) {
                                        if (!e5.noEpsilon.contains(x.noEpsilon.elementAt(i))) {
                                            e5.noEpsilon.addElement(x.noEpsilon.elementAt(i));
                                        }
                                    }
                                    if (A1!=null) {
                                        for (int i = 0; i < A1.inicio.size(); i++) {
                                            if (!e5.noEpsilon.contains(A1.inicio.elementAt(i))) {
                                                e5.noEpsilon.addElement(A1.inicio.elementAt(i));
                                            }
                                        }
                                    }else{
                                        if(A2!=null){
                                            for (int i = 0; i < A2.inicio.size(); i++) {
                                                if (!e5.noEpsilon.contains(A2.inicio.elementAt(i))) {
                                                    e5.noEpsilon.addElement(A2.inicio.elementAt(i));
                                                }
                                            }
                                        }else{
                                            if (A3!=null) {
                                                for (int i = 0; i < A3.inicio.size(); i++) {
                                                    if (!e5.noEpsilon.contains(A3.inicio.elementAt(i))) {
                                                        e5.noEpsilon.addElement(A3.inicio.elementAt(i));
                                                    }
                                                }
                                            }else{
                                                if (A4!=null) {
                                                    for (int i = 0; i < A4.inicio.size(); i++) {
                                                        if (!e5.noEpsilon.contains(A4.inicio.elementAt(i))) {
                                                            e5.noEpsilon.addElement(A4.inicio.elementAt(i));
                                                        }
                                                    }
                                                }else{
                                                    if (A5!=null) {
                                                        for (int i = 0; i < A5.inicio.size(); i++) {
                                                            if (!e5.noEpsilon.contains(A5.inicio.elementAt(i))) {
                                                                e5.noEpsilon.addElement(A5.inicio.elementAt(i));
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    epsilon(a3[1], e5, add);   
                                    // añadir inicio
                                    for (int i = 0; i < e5.inicio.size(); i++) {
                                        if (!x.inicio.contains(e5.inicio.elementAt(i))) {
                                            x.inicio.addElement(e5.inicio.elementAt(i));
                                        }
                                    }
                                }
                            }
                        }                        
                    } // termina  
                    //epsilon(a3[1],x.transicion1);
                }else{ 
                    if (A1!=null) {
                        for (int i = 0; i < A1.inicio.size(); i++) {
                            if (!x.noEpsilon.contains(A1.inicio.elementAt(i))) {
                                x.noEpsilon.addElement(A1.inicio.elementAt(i));
                            }
                        }
                    }else{
                        if (A2!=null) {
                            for (int i = 0; i < A2.inicio.size(); i++) {
                                if (!x.noEpsilon.contains(A2.inicio.elementAt(i))) {
                                    x.noEpsilon.addElement(A2.inicio.elementAt(i));
                                }
                            }
                        }else{
                            if (A3!=null) {
                                for (int i = 0; i < A3.inicio.size(); i++) {
                                    if (!x.noEpsilon.contains(A3.inicio.elementAt(i))) {
                                        x.noEpsilon.addElement(A3.inicio.elementAt(i));
                                    }
                                }                                                             
                            }else{
                                if (A4!=null) {
                                    for (int i = 0; i < A4.inicio.size(); i++) {
                                        if (!x.noEpsilon.contains(A4.inicio.elementAt(i))) {
                                            x.noEpsilon.addElement(A4.inicio.elementAt(i));
                                        }
                                    }
                                }else{
                                    if (A5!=null) {
                                        for (int i = 0; i < A5.inicio.size(); i++) {
                                            if (!x.noEpsilon.contains(A5.inicio.elementAt(i))) {
                                                x.noEpsilon.addElement(A5.inicio.elementAt(i));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }         
                    add.cerraduras.addElement(x.noEpsilon);
                    add.irNumero.addElement(x.nodo2);
                    add.irLetra.addElement(x.transicion1);
                    //System.out.println("ir: " + x.transicion1 +" =" + x.nodo2);                    
                    //System.out.println("Cerradura: " + x.noEpsilon);
                }
                break;
            case "OR":
                OR y = (OR)exp;                
                if (unomasT == true) {
                    for (int i = 0; i < y.inicio.size(); i++) {
                        if (!y.noEpsilon.contains(y.inicio.elementAt(i))) {
                            y.noEpsilon.addElement(y.inicio.elementAt(i));
                        }
                    }
                    for (int i = 0; i < y.inicio.size(); i++) {
                        if (!y.noEpsilon2.contains(y.inicio.elementAt(i))) {
                            y.noEpsilon2.addElement(y.inicio.elementAt(i));
                        }
                    }
                }
                if (ceromasT == true) {
                    for (int i = 0; i < y.inicio.size(); i++) {
                        if (!y.noEpsilon.contains(y.inicio.elementAt(i))) {
                            y.noEpsilon.addElement(y.inicio.elementAt(i));
                        }
                    }
                    for (int i = 0; i < y.inicio.size(); i++) {
                        if (!y.noEpsilon2.contains(y.inicio.elementAt(i))) {
                            y.noEpsilon2.addElement(y.inicio.elementAt(i));
                        }
                    }
                }
                String a4[] = y.transicion2.getClass().getCanonicalName().split("\\.");
                if (a4.length==2) {
                    if ("CEROMAS".equals(a4[1])) {
                        //System.out.println("OR->CEROMAS");
                        CEROMAS e1 = (CEROMAS)y.transicion2 ;
                        for (int i = 0; i < y.noEpsilon.size(); i++) {
                            if (!e1.noEpsilon.contains(y.noEpsilon.elementAt(i))) {
                                e1.noEpsilon.addElement(y.noEpsilon.elementAt(i));
                            }
                        }
                        epsilon(a4[1], e1, add);
                        for (int i = 0; i < e1.inicio.size(); i++) {
                            if (!y.inicio.contains(e1.inicio.elementAt(i))) {
                                y.inicio.addElement(e1.inicio.elementAt(i));
                            }
                        }
                    }else{
                        if ("CEROUNO".equals(a4[1])) {
                            //System.out.println("OR->CEROUNO");
                            CEROUNO e2 = (CEROUNO)y.transicion2 ;
                            for (int i = 0; i < y.noEpsilon.size(); i++) {
                                if (!e2.noEpsilon.contains(y.noEpsilon.elementAt(i))) {
                                    e2.noEpsilon.addElement(y.noEpsilon.elementAt(i));
                                }
                            }
                            epsilon(a4[1], e2, add);
                            for (int i = 0; i < e2.inicio.size(); i++) {
                                if (!y.inicio.contains(e2.inicio.elementAt(i))) {
                                    y.inicio.addElement(e2.inicio.elementAt(i));
                                }
                            }
                        }else{
                            if ("CONCAT".equals(a4[1])) {
                                //System.out.println("OR->CONCAT");
                                CONCAT e3 = (CONCAT)y.transicion2 ;
                                for (int i = 0; i < y.noEpsilon.size(); i++) {
                                    if (!e3.noEpsilon2.contains(y.noEpsilon.elementAt(i))) {
                                        e3.noEpsilon2.addElement(y.noEpsilon.elementAt(i));
                                    }
                                }
                                epsilon(a4[1], e3, add);
                                for (int i = 0; i < e3.inicio.size(); i++) {
                                    if (!y.inicio.contains(e3.inicio.elementAt(i))) {
                                        y.inicio.addElement(e3.inicio.elementAt(i));
                                    }
                                }
                            }else{
                                if ("OR".equals(a4[1])) {
                                    //System.out.println("OR->OR");
                                    OR e4 = (OR)y.transicion2 ;
                                    for (int i = 0; i < y.noEpsilon.size(); i++) {
                                        if (!e4.noEpsilon.contains(y.noEpsilon.elementAt(i))) {
                                            e4.noEpsilon.addElement(y.noEpsilon.elementAt(i));
                                        }
                                        if (!e4.noEpsilon2.contains(y.noEpsilon.elementAt(i))) {
                                            e4.noEpsilon2.addElement(y.noEpsilon.elementAt(i));
                                        }
                                    }
                                    epsilon(a4[1], e4, add);  
                                    for (int i = 0; i < e4.inicio.size(); i++) {
                                        if (!y.inicio.contains(e4.inicio.elementAt(i))) {
                                            y.inicio.addElement(e4.inicio.elementAt(i));
                                        }
                                    }
                                }else{
                                    UNOMAS e5 = (UNOMAS)y.transicion2 ;
                                    //System.out.println("OR->UNOMAS"); 
                                    for (int i = 0; i < y.noEpsilon.size(); i++) {
                                        if (!e5.noEpsilon.contains(y.noEpsilon.elementAt(i))) {
                                            e5.noEpsilon.addElement(y.noEpsilon.elementAt(i));
                                        }
                                    }
                                    epsilon(a4[1], e5, add);
                                    for (int i = 0; i < e5.inicio.size(); i++) {
                                        if (!y.inicio.contains(e5.inicio.elementAt(i))) {
                                            y.inicio.addElement(e5.inicio.elementAt(i));
                                        }
                                    }
                                }
                            }
                        }                        
                    } // termina
                    //epsilon(a4[1], y.transicion2);                                        
                }                                
                
                String a6[] = y.transicion5.getClass().getCanonicalName().split("\\.");
                if (a6.length==2) {
                    if ("CEROMAS".equals(a6[1])) {
                        //System.out.println("OR->CEROMAS");
                        CEROMAS e1 = (CEROMAS)y.transicion5 ;
                        for (int i = 0; i < y.noEpsilon2.size(); i++) {
                            if (!e1.noEpsilon.contains(y.noEpsilon2.elementAt(i))) {
                                e1.noEpsilon.addElement(y.noEpsilon2.elementAt(i));
                            }
                        }
                        epsilon(a6[1], e1, add);
                        for (int i = 0; i < e1.inicio.size(); i++) {
                            if (!y.inicio.contains(e1.inicio.elementAt(i))) {
                                y.inicio.addElement(e1.inicio.elementAt(i));
                            }
                        }
                    }else{
                        if ("CEROUNO".equals(a6[1])) {
                            //System.out.println("OR->CEROUNO");
                            CEROUNO e2 = (CEROUNO)y.transicion5 ;
                            for (int i = 0; i < y.noEpsilon2.size(); i++) {
                                if (!e2.noEpsilon.contains(y.noEpsilon2.elementAt(i))) {
                                    e2.noEpsilon.addElement(y.noEpsilon2.elementAt(i));
                                }
                            }
                            epsilon(a6[1], e2, add);
                            for (int i = 0; i < e2.inicio.size(); i++) {
                                if (!y.inicio.contains(e2.inicio.elementAt(i))) {
                                    y.inicio.addElement(e2.inicio.elementAt(i));
                                }
                            }
                        }else{
                            if ("CONCAT".equals(a6[1])) {
                                //System.out.println("OR->CONCAT");
                                CONCAT e3 = (CONCAT)y.transicion5 ;
                                for (int i = 0; i < y.noEpsilon.size(); i++) {
                                    if (!e3.noEpsilon2.contains(y.noEpsilon2.elementAt(i))) {
                                        e3.noEpsilon2.addElement(y.noEpsilon2.elementAt(i));
                                    }
                                }
                                epsilon(a6[1], e3, add);
                                for (int i = 0; i < e3.inicio.size(); i++) {
                                    if (!y.inicio.contains(e3.inicio.elementAt(i))) {
                                        y.inicio.addElement(e3.inicio.elementAt(i));
                                    }
                                }
                            }else{
                                if ("OR".equals(a6[1])) {
                                    //System.out.println("OR->OR");
                                    OR e4 = (OR)y.transicion5 ;
                                    for (int i = 0; i < y.noEpsilon2.size(); i++) {
                                        if (!e4.noEpsilon.contains(y.noEpsilon2.elementAt(i))) {
                                            e4.noEpsilon.addElement(y.noEpsilon2.elementAt(i));
                                        }
                                        if (!e4.noEpsilon2.contains(y.noEpsilon2.elementAt(i))) {
                                            e4.noEpsilon2.addElement(y.noEpsilon2.elementAt(i));
                                        }
                                    }
                                    epsilon(a6[1], e4, add);   
                                    for (int i = 0; i < e4.inicio.size(); i++) {
                                        if (!y.inicio.contains(e4.inicio.elementAt(i))) {
                                            y.inicio.addElement(e4.inicio.elementAt(i));
                                        }
                                    }
                                }else{
                                    UNOMAS e5 = (UNOMAS)y.transicion5 ;
                                    //System.out.println("OR->UNOMAS");  
                                    for (int i = 0; i < y.noEpsilon2.size(); i++) {
                                        if (!e5.noEpsilon.contains(y.noEpsilon2.elementAt(i))) {
                                            e5.noEpsilon.addElement(y.noEpsilon2.elementAt(i));
                                        }
                                    }
                                    epsilon(a6[1], e5, add);
                                    for (int i = 0; i < e5.inicio.size(); i++) {
                                        if (!y.inicio.contains(e5.inicio.elementAt(i))) {
                                            y.inicio.addElement(e5.inicio.elementAt(i));
                                        }
                                    }
                                }
                            }
                        }                        
                    } // termina
                    //epsilon(a6[1], y.transicion5);
                }     
                
                if (unomasT == true) {
                    for (int i = 0; i < y.inicio.size(); i++) {
                        if (!y.noEpsilon.contains(y.inicio.elementAt(i))) {
                            y.noEpsilon.addElement(y.inicio.elementAt(i));
                        }
                    }
                    for (int i = 0; i < y.inicio.size(); i++) {
                        if (!y.noEpsilon2.contains(y.inicio.elementAt(i))) {
                            y.noEpsilon2.addElement(y.inicio.elementAt(i));
                        }
                    }
                }
                if (ceromasT == true) {
                    for (int i = 0; i < y.inicio.size(); i++) {
                        if (!y.noEpsilon.contains(y.inicio.elementAt(i))) {
                            y.noEpsilon.addElement(y.inicio.elementAt(i));
                        }
                    }
                    for (int i = 0; i < y.inicio.size(); i++) {
                        if (!y.noEpsilon2.contains(y.inicio.elementAt(i))) {
                            y.noEpsilon2.addElement(y.inicio.elementAt(i));
                        }
                    }
                }
                
                if (a4.length!=2){                         
                    //System.out.println("en or1");
                    add.cerraduras.addElement(y.noEpsilon);
                    add.irNumero.addElement(y.nodo3);
                    add.irLetra.addElement(y.transicion2);
                    //System.out.println("ir: " + y.transicion2 +" =" + y.nodo3);
                    //System.out.println("Cerradura: " + y.noEpsilon);
                }
                if (a6.length!=2) {
                    //System.out.println("en or2");
                    add.cerraduras.addElement(y.noEpsilon2);
                    add.irNumero.addElement(y.nodo5);
                    add.irLetra.addElement(y.transicion5);
                    //System.out.println("ir: " + y.transicion5 +" =" + y.nodo5);
                    //System.out.println("Cerradura: " + y.noEpsilon2);   
                }
                break;
            case "UNOMAS":
                UNOMAS z = (UNOMAS)exp;  
                
//                if (ceromasT == true) {
//                    for (int i = 0; i < z.inicio.size(); i++) {
//                        if (!z.noEpsilon.contains(z.inicio.elementAt(i))) {
//                            z.noEpsilon.addElement(z.inicio.elementAt(i));
//                        }
//                    }                    
//                }
//                if (unomasT == true) {
//                    for (int i = 0; i < z.inicio.size(); i++) {
//                        if (!z.noEpsilon.contains(z.inicio.elementAt(i))) {
//                            z.noEpsilon.addElement(z.inicio.elementAt(i));
//                        }
//                    }                    
//                }
                unomasT = true;
                String a5[] = z.transicion2.getClass().getCanonicalName().split("\\.");
                if (a5.length==2) {
                    // no es un string, entonces al objecto le agrego los epsilon
                    // si es CEROUNO o CEROMAS add a Inicio los noEpsilon
                    if ("CEROMAS".equals(a5[1])) {
                        CEROMAS e1 = (CEROMAS)z.transicion2 ;
                        //System.out.println("UNOMAS -> CEROMAS");
                        for (int i = 0; i < z.noEpsilon.size(); i++) {
                            if (!e1.noEpsilon.contains(z.noEpsilon.elementAt(i))) {
                                e1.noEpsilon.addElement(z.noEpsilon.elementAt(i));
                            }
                        }
                        for (int i = 0; i < e1.inicio.size(); i++) {
                            if (!e1.noEpsilon.contains(e1.inicio.elementAt(i))) {
                                e1.noEpsilon.addElement(e1.inicio.elementAt(i));
                            }
                        }
                        epsilon(a5[1], e1, add);
                        unomasT = false;
                        for (int i = 0; i < e1.inicio.size(); i++) {
                            if (!z.inicio.contains(e1.inicio.elementAt(i))) {
                                z.inicio.addElement(e1.inicio.elementAt(i));
                            }
                        }
                    }else{
                        if ("CEROUNO".equals(a5[1])) {
                            //System.out.println("UNOMAS -> CEROUNO");
                            CEROUNO e2 = (CEROUNO)z.transicion2 ;
                            for (int i = 0; i < z.noEpsilon.size(); i++) {
                                if (!e2.noEpsilon.contains(z.noEpsilon.elementAt(i))) {
                                    e2.noEpsilon.addElement(z.noEpsilon.elementAt(i));
                                }
                            }
                            for (int i = 0; i < e2.inicio.size(); i++) {
                                if (!e2.noEpsilon.contains(e2.inicio.elementAt(i))) {
                                    e2.noEpsilon.addElement(e2.inicio.elementAt(i));
                                }
                            }
                            epsilon(a5[1], e2, add);
                            unomasT = false;
                            for (int i = 0; i < e2.inicio.size(); i++) {
                                if (!z.inicio.contains(e2.inicio.elementAt(i))) {
                                    z.inicio.addElement(e2.inicio.elementAt(i));
                                }
                            }
                        }else{
                            if ("CONCAT".equals(a5[1])) {
                                CONCAT e3 = (CONCAT)z.transicion2 ; 
                                //System.out.println("UNOMAS -> CONCAT");
                                for (int i = 0; i < z.noEpsilon.size(); i++) {
                                    if (!e3.noEpsilon2.contains(z.noEpsilon.elementAt(i))) {
                                        e3.noEpsilon2.addElement(z.noEpsilon.elementAt(i));
                                    }
                                }
                                for (int i = 0; i < e3.inicio.size(); i++) {
                                    if (!e3.noEpsilon2.contains(e3.inicio.elementAt(i))) {
                                        e3.noEpsilon2.addElement(e3.inicio.elementAt(i));
                                    }
                                }
                                epsilon(a5[1], e3, add);
                                unomasT = false;
                                for (int i = 0; i < e3.inicio.size(); i++) {
                                    if (!z.inicio.contains(e3.inicio.elementAt(i))) {
                                        z.inicio.addElement(e3.inicio.elementAt(i));
                                    }
                                }
                            }else{
                                if ("OR".equals(a5[1])) {
                                    OR e4 = (OR)z.transicion2 ;
                                    //System.out.println("UNOMAS -> OR");
                                    for (int i = 0; i < z.noEpsilon.size(); i++) {
                                        if (!e4.noEpsilon.contains(z.noEpsilon.elementAt(i))) {
                                            e4.noEpsilon.addElement(z.noEpsilon.elementAt(i));
                                        }
                                        if (!e4.noEpsilon2.contains(z.noEpsilon.elementAt(i))) {
                                            e4.noEpsilon2.addElement(z.noEpsilon.elementAt(i));
                                        }
                                    }
                                    for (int i = 0; i < e4.inicio.size(); i++) {
                                        if (!e4.noEpsilon.contains(e4.inicio.elementAt(i))) {
                                            e4.noEpsilon.addElement(e4.inicio.elementAt(i));
                                        }
                                        if (!e4.noEpsilon2.contains(e4.inicio.elementAt(i))) {
                                            e4.noEpsilon2.addElement(e4.inicio.elementAt(i));
                                        }
                                    }
                                    epsilon(a5[1], e4, add);
                                    unomasT = false;
                                    for (int i = 0; i < e4.inicio.size(); i++) {
                                        if (!z.inicio.contains(e4.inicio.elementAt(i))) {
                                            z.inicio.addElement(e4.inicio.elementAt(i));
                                        }
                                    }
                                }else{
                                    UNOMAS e5 = (UNOMAS)z.transicion2 ;
                                    //System.out.println("UNOMAS -> UNOMAS");
                                    for (int i = 0; i < z.noEpsilon.size(); i++) {
                                        if (!e5.noEpsilon.contains(z.noEpsilon.elementAt(i))) {
                                            e5.noEpsilon.addElement(z.noEpsilon.elementAt(i));
                                        }
                                    }
                                    for (int i = 0; i < e5.inicio.size(); i++) {
                                        if (!e5.noEpsilon.contains(e5.inicio.elementAt(i))) {
                                            e5.noEpsilon.addElement(e5.inicio.elementAt(i));
                                        }
                                    }
                                    epsilon(a5[1], e5, add);
                                    unomasT = false;
                                    for (int i = 0; i < e5.inicio.size(); i++) {
                                        if (!z.inicio.contains(e5.inicio.elementAt(i))) {
                                            z.inicio.addElement(e5.inicio.elementAt(i));
                                        }
                                    }                                    
                                }
                            }
                        }                        
                    } // termina                                        
                }else{   
                    add.cerraduras.addElement(z.noEpsilon);
                    add.irNumero.addElement(z.nodo3);
                    add.irLetra.addElement(z.transicion2);
                    //System.out.println("ir: " + z.transicion2 +" =" + z.nodo3);
                    //System.out.println("Cerradura: " + z.noEpsilon);
                    unomasT = false;                                                // dkjfksdjfk ver lo del true kfksdjfjsdf
                }                
                break;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    // End of variables declaration//GEN-END:variables
}
