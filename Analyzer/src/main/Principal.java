package main;

import Abstracto.Instruccion;
import Analyzers.Lexer;
import Analyzers.Parser;
import Excepciones.Errores;
import Instrucciones.AsignacionVar;
import Instrucciones.Declaracion;
import Instrucciones.Execute;
import Instrucciones.Metodo;
import Simbolo.Arbol;
import Simbolo.Simbolo;
import Simbolo.tablaSimbolos;
import java.awt.Graphics;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Principal extends javax.swing.JFrame {

    FondoPanel fondo = new FondoPanel();
    private JPanel panelTransparente;
    private LinkedList<Errores> listaErroresGlobal = new LinkedList<>();
    private tablaSimbolos tablaGlobal;

    public Principal() {
        this.setContentPane(fondo);
        initComponents();
        ajustarImagenLabel(LBLLOGO, "/img/logo.jpg");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LBLLOGO = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TXTSALIDA = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        TXTENTRADA = new javax.swing.JTextArea();
        BTNREPORTEERRORES = new javax.swing.JButton();
        BTNSALIR = new javax.swing.JButton();
        BTNINTERPRETAR = new javax.swing.JButton();
        BTNREPORTESIMBOLOS = new javax.swing.JButton();
        BTNCARGARARCHIVO1 = new javax.swing.JButton();
        BTNRESET = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TXTSALIDA.setBackground(new java.awt.Color(78, 95, 115));
        TXTSALIDA.setColumns(20);
        TXTSALIDA.setFont(new java.awt.Font("JetBrains Mono Light", 0, 20)); // NOI18N
        TXTSALIDA.setForeground(new java.awt.Color(255, 255, 255));
        TXTSALIDA.setRows(5);
        jScrollPane1.setViewportView(TXTSALIDA);

        TXTENTRADA.setBackground(new java.awt.Color(78, 95, 115));
        TXTENTRADA.setColumns(20);
        TXTENTRADA.setFont(new java.awt.Font("JetBrains Mono Light", 0, 20)); // NOI18N
        TXTENTRADA.setForeground(new java.awt.Color(255, 255, 255));
        TXTENTRADA.setRows(5);
        jScrollPane2.setViewportView(TXTENTRADA);

        BTNREPORTEERRORES.setBackground(new java.awt.Color(98, 166, 142));
        BTNREPORTEERRORES.setFont(new java.awt.Font("JetBrains Mono Light", 0, 18)); // NOI18N
        BTNREPORTEERRORES.setForeground(new java.awt.Color(0, 0, 0));
        BTNREPORTEERRORES.setText("REPORTE ERRORES");
        BTNREPORTEERRORES.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNREPORTEERRORESActionPerformed(evt);
            }
        });

        BTNSALIR.setBackground(new java.awt.Color(98, 166, 142));
        BTNSALIR.setFont(new java.awt.Font("JetBrains Mono Light", 0, 18)); // NOI18N
        BTNSALIR.setForeground(new java.awt.Color(0, 0, 0));
        BTNSALIR.setText("SALIR");
        BTNSALIR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNSALIRActionPerformed(evt);
            }
        });

        BTNINTERPRETAR.setBackground(new java.awt.Color(98, 166, 142));
        BTNINTERPRETAR.setFont(new java.awt.Font("JetBrains Mono Light", 0, 18)); // NOI18N
        BTNINTERPRETAR.setForeground(new java.awt.Color(0, 0, 0));
        BTNINTERPRETAR.setText("INTERPRETAR");
        BTNINTERPRETAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNINTERPRETARActionPerformed(evt);
            }
        });

        BTNREPORTESIMBOLOS.setBackground(new java.awt.Color(98, 166, 142));
        BTNREPORTESIMBOLOS.setFont(new java.awt.Font("JetBrains Mono Light", 0, 18)); // NOI18N
        BTNREPORTESIMBOLOS.setForeground(new java.awt.Color(0, 0, 0));
        BTNREPORTESIMBOLOS.setText("REPORTE SIMBOLOS");
        BTNREPORTESIMBOLOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNREPORTESIMBOLOSActionPerformed(evt);
            }
        });

        BTNCARGARARCHIVO1.setBackground(new java.awt.Color(98, 166, 142));
        BTNCARGARARCHIVO1.setFont(new java.awt.Font("JetBrains Mono Light", 0, 18)); // NOI18N
        BTNCARGARARCHIVO1.setForeground(new java.awt.Color(0, 0, 0));
        BTNCARGARARCHIVO1.setText("CARGAR ARCHIVO");
        BTNCARGARARCHIVO1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNCARGARARCHIVO1ActionPerformed(evt);
            }
        });

        BTNRESET.setBackground(new java.awt.Color(98, 166, 142));
        BTNRESET.setFont(new java.awt.Font("JetBrains Mono Light", 0, 18)); // NOI18N
        BTNRESET.setForeground(new java.awt.Color(0, 0, 0));
        BTNRESET.setText("RESET");
        BTNRESET.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNRESETActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(LBLLOGO, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(BTNCARGARARCHIVO1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BTNINTERPRETAR, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addComponent(BTNREPORTESIMBOLOS, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BTNREPORTEERRORES, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(BTNRESET, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BTNSALIR, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(62, 62, 62))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BTNREPORTESIMBOLOS, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BTNREPORTEERRORES, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BTNCARGARARCHIVO1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BTNINTERPRETAR, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(LBLLOGO, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BTNSALIR, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTNRESET, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BTNSALIRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNSALIRActionPerformed
        System.exit(0);
    }//GEN-LAST:event_BTNSALIRActionPerformed

    private void BTNCARGARARCHIVO1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNCARGARARCHIVO1ActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Archivos de Texto", "jc","txt"));

        int result = fileChooser.showOpenDialog(this);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                BufferedReader br = new BufferedReader(new FileReader(selectedFile));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                br.close();

                TXTENTRADA.setText(sb.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_BTNCARGARARCHIVO1ActionPerformed

    private void BTNINTERPRETARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNINTERPRETARActionPerformed

       try {
            String texto = TXTENTRADA.getText();
            Lexer lexer = new Lexer(new BufferedReader(new StringReader(texto)));
            Parser parser = new Parser(lexer);
            var resultado = parser.parse();
            var ast = new Arbol((LinkedList<Instruccion>) resultado.value);
            var tabla = new tablaSimbolos();
            tablaGlobal = new tablaSimbolos();
            tablaGlobal.setNombre("GLOBAL");
            LinkedList<Errores> lista = new LinkedList<>();
            lista.addAll(lexer.ListaErrores);
            lista.addAll(parser.ListaErrores);
            for (var a : ast.getInstrucciones()){
                if(a == null){
                    continue;
                }
                
                if(a instanceof Metodo){
                    ast.addFunciones(a);
                }
            }
            
            for (var a : ast.getInstrucciones()){
                if(a == null){
                    continue;
                }
                
                if(a instanceof Declaracion || a instanceof AsignacionVar ){
                    var res = a.interpretar(ast, tabla);
                    if(res instanceof Errores errores){
                        lista.add(errores);
                    }
                }   
            }
            
            //Aca empieza la funcion Execute
            Execute e = null;
            
            for(var a : ast.getInstrucciones()){
                if(a == null){
                    continue;
                }
                
                if(a instanceof Execute execute){
                    e = execute;
                    break;
                }
            }
            
            var resultadoExecute = e.interpretar(ast, tabla);
            if(resultadoExecute instanceof Errores){
                System.out.println("Ya no sale Compi");
            }
            
            TXTSALIDA.setText(ast.getConsola());
            listaErroresGlobal = lista;
            
        } catch (Exception e) {
            System.out.println("Error fatal en compilacion de entrada.");
            System.out.println(e);
        }
    }//GEN-LAST:event_BTNINTERPRETARActionPerformed

    private void BTNREPORTEERRORESActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNREPORTEERRORESActionPerformed

        generarReporteErrores(listaErroresGlobal);
        
    }//GEN-LAST:event_BTNREPORTEERRORESActionPerformed

    private void BTNREPORTESIMBOLOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNREPORTESIMBOLOSActionPerformed

        generarReporteSimbolos(tablaGlobal);
    }//GEN-LAST:event_BTNREPORTESIMBOLOSActionPerformed

    private void BTNRESETActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNRESETActionPerformed

        TXTENTRADA.setText("");
        TXTSALIDA.setText("");
        listaErroresGlobal.clear();
        tablaGlobal = null;
    }//GEN-LAST:event_BTNRESETActionPerformed

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

     private void ajustarImagenLabel(JLabel label, String ruta) {
        ImageIcon icon = new ImageIcon(getClass().getResource(ruta));
        Image imagen = icon.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(imagen));
    }

     
    class FondoPanel extends JPanel {
        private Image imagen;

        @Override
        public void paint(Graphics g) {
            imagen = new ImageIcon(getClass().getResource("/img/background.jpg")).getImage();
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
            super.paint(g);
        }
    }

    private void generarReporteErrores(LinkedList<Errores> listaErrores) {
        StringBuilder html = new StringBuilder();
        html.append("<html><head><title>Reporte de Errores</title>");
        html.append("<link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">");
        html.append("<link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>");
        html.append("<link href=\"https://fonts.googleapis.com/css2?family=Roboto&display=swap\" rel=\"stylesheet\">");
        html.append("<style>");
        html.append("body { background-color: black; color: white; font-family: 'Roboto', sans-serif; font-size:30px; display: flex; flex-direction: column; align-items: center; }");
        html.append("h1 { text-align: center; }");
        html.append("table { border-collapse: collapse; width: 80%; margin: 20px auto; }");
        html.append("th, td { font-size:22px; border: 1px solid white; padding: 8px; text-align: center; }");
        html.append("</style>");
        html.append("</head><body>");
        html.append("<h1 class=\"roboto-regular\">Reporte de Errores</h1>");
        html.append("<table><tr><th>Tipo</th><th>Descripción</th><th>Línea</th><th>Columna</th></tr>");
        
        for (Errores error : listaErrores) {
            html.append("<tr>");
            html.append("<td>").append(error.getTipo()).append("</td>");
            html.append("<td>").append(error.getDesc()).append("</td>");
            html.append("<td>").append(error.getColumna()).append("</td>");
            html.append("<td>").append(error.getLinea()).append("</td>");
            html.append("</tr>");
        }

        html.append("</table></body></html>");

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar reporte de errores");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Archivos HTML", "html"));

        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            if (!fileToSave.getAbsolutePath().endsWith(".html")) {
                fileToSave = new File(fileToSave + ".html");
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave))) {
                writer.write(html.toString());
                javax.swing.JOptionPane.showMessageDialog(this, "Reporte de errores guardado en: " + fileToSave.getAbsolutePath(), "Éxito", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                e.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(this, "Error al guardar el reporte de errores.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void generarReporteSimbolos(tablaSimbolos tabla) {
    StringBuilder html = new StringBuilder();
    html.append("<html><head><title>Reporte de Tabla de Simbolos</title>");
    html.append("<link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">");
    html.append("<link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>");
    html.append("<link href=\"https://fonts.googleapis.com/css2?family=Roboto&display=swap\" rel=\"stylesheet\">");
    html.append("<style>");
    html.append("body { background-color: black; color: white; font-family: 'Roboto', sans-serif; font-size:30px; display: flex; flex-direction: column; align-items: center; }");
    html.append("h1 { text-align: center; }");
    html.append("table { border-collapse: collapse; width: 80%; margin: 20px auto; }");
    html.append("th, td { font-size:22px; border: 1px solid white; padding: 8px; text-align: center; }");
    html.append("</style>");
    html.append("</head><body>");
    html.append("<h1 class=\"roboto-regular\">Reporte Simbolos</h1>");
    html.append("<table><tr><th>No.</th><th>Id</th><th>Tipo</th><th>Tipo</th><th>Valor</th></tr>");
    
        int index = 1;
        for (Simbolo simbolo : tabla.getTablaActual().values()) {
            html.append("<tr>");
            html.append("<td>").append(index++).append("</td>");
            html.append("<td>").append(simbolo.getId()).append("</td>");
            html.append("<td>").append(simbolo.getTipo().getTipo().toString()).append("</td>");
            html.append("<td>").append(simbolo.isMutable() ? "Variable" : "Constante").append("</td>");
            html.append("<td>").append(simbolo.getValor().toString()).append("</td>");
            html.append("</tr>");
        }

        html.append("</table></body></html>");

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar reporte de símbolos");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Archivos HTML", "html"));

        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            if (!fileToSave.getAbsolutePath().endsWith(".html")) {
                fileToSave = new File(fileToSave + ".html");
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave))) {
                writer.write(html.toString());
                javax.swing.JOptionPane.showMessageDialog(this, "Reporte de símbolos guardado en: " + fileToSave.getAbsolutePath(), "Éxito", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                e.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(this, "Error al guardar el reporte de símbolos.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNCARGARARCHIVO1;
    private javax.swing.JButton BTNINTERPRETAR;
    private javax.swing.JButton BTNREPORTEERRORES;
    private javax.swing.JButton BTNREPORTESIMBOLOS;
    private javax.swing.JButton BTNRESET;
    private javax.swing.JButton BTNSALIR;
    private javax.swing.JLabel LBLLOGO;
    private javax.swing.JTextArea TXTENTRADA;
    private javax.swing.JTextArea TXTSALIDA;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
