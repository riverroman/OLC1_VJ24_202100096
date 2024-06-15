package main;

import Abstracto.Instruccion;
import Analyzers.Lexer;
import Analyzers.Parser;
import Excepciones.Errores;
import Simbolo.Arbol;
import Simbolo.tablaSimbolos;
import java.awt.Graphics;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

        BTNCARGARARCHIVO1.setBackground(new java.awt.Color(98, 166, 142));
        BTNCARGARARCHIVO1.setFont(new java.awt.Font("JetBrains Mono Light", 0, 18)); // NOI18N
        BTNCARGARARCHIVO1.setForeground(new java.awt.Color(0, 0, 0));
        BTNCARGARARCHIVO1.setText("CARGAR ARCHIVO");
        BTNCARGARARCHIVO1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNCARGARARCHIVO1ActionPerformed(evt);
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
                                .addGap(0, 0, Short.MAX_VALUE)
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
                .addComponent(BTNSALIR, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Archivos de Texto", "jc"));

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
            tabla.setNombre("GLOBAL");
            ast.setConsola("");
            for (var a : ast.getInstrucciones()){
                var res = a.interpretar(ast, tabla);
                if (res instanceof Errores){
                    Errores error = (Errores) res;
                    System.out.println(error);
                }
            }
            TXTSALIDA.setText(ast.getConsola());
        } catch (Exception e) {
            System.out.println("Error fatal en compilacion de entrada.");
            System.out.println(e);
        }
    }//GEN-LAST:event_BTNINTERPRETARActionPerformed

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
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNCARGARARCHIVO1;
    private javax.swing.JButton BTNINTERPRETAR;
    private javax.swing.JButton BTNREPORTEERRORES;
    private javax.swing.JButton BTNREPORTESIMBOLOS;
    private javax.swing.JButton BTNSALIR;
    private javax.swing.JLabel LBLLOGO;
    private javax.swing.JTextArea TXTENTRADA;
    private javax.swing.JTextArea TXTSALIDA;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
