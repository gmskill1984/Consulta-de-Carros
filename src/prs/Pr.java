/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prs;

import com.itextpdf.text.pdf.PdfWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.Document;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

/**
 *
 * @author UUMMSAL
 */
public class Pr extends javax.swing.JFrame {

    /**
     * Creates new form Pr
     */
    Connection conn;
    String l_b = "------------------------------------------\n";
    String lb = "Marca | Modelo | Preço |  Desdcrição \n";
    String cab = lb + l_b;
    String lin = "";
    String caminho;

    public Pr() {
        initComponents();
        verifcar_arquivo();
        System.out.print(connect());

    }

    public void verifcar_arquivo() {
        // Get the file 
        File f = new File("c:\\pr\\config.txt");

        // Check if the specified file 
        // Exists or not in project
        if (f.exists()) {
            ler_config();
        } else {
            criar_config();
            ler_config();
        }

    }

    public void criar_config() {

        String caminho = JOptionPane.showInputDialog(rootPane, " Entre com o caminho");

        FileWriter arq;
        try {
            arq = new FileWriter("C:\\pr\\config.txt");
            PrintWriter gravarArq = new PrintWriter(arq);

            gravarArq.printf(caminho);

            arq.close();
        } catch (IOException ex) {
            Logger.getLogger(Pr.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ler_config() {

        try {
            FileReader arq = new FileReader("C:\\pr\\config.txt");
            BufferedReader lerArq = new BufferedReader(arq);

            caminho = lerArq.readLine(); // lê a primeira linha
// a variável "linha" recebe o valor "null" quando o processo
// de repetição atingir o final do arquivo texto

            System.out.print(caminho);
            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }

        System.out.println();

    }

    public String connect() {

        String conect = "";
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:" + caminho);

            conect = "Banco de dados conectado";
            return conect;
        } catch (Exception e) {

            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Banco desconectado");
            conect = "Banco de dados desconectado ";
            return conect;
        }
    }

    // seleciona modelo do carro pro tm 
    public void selectTM(String tm) {
        //int i =0;
        String tm_in, prn_in, fam_in, design_in;
        String total = " ";

       
        total += cab;
        try {
            Statement statement = conn.createStatement();

            PreparedStatement stmt = conn.prepareStatement("select * from prs where tm = '" + tm + "'");

            ResultSet resultSet = stmt.executeQuery();
            System.out.print(resultSet);
            while (resultSet.next()) {

                System.out.print(resultSet.getString("tm"));
                tm_in = resultSet.getString("tm");
                prn_in = resultSet.getString("prn");
                fam_in = resultSet.getString("fam");
                design_in = resultSet.getString("desig");

                total += tm_in + " | " + prn_in + "  | " + fam_in + "   |   " + design_in + "\n";
                total += lin;

//ip.add(resultSet.getString("LogName"));
                //logstart.add(resultSet.getString("logstart"));
                //local.add(resultSet.getString("logfinal"));
                //return resultSet.getString("logstart");
            }

        } catch (SQLException ex) {
            System.out.print(ex);
            //return "teste";
        }
        total += "**************************************************";
        TF_resultado.setText(total);
    }

    public void selectPNR(String pnr) {
        //int i =0;
        String tm_in, prn_in, fam_in, design_in;
        String total = " ";
        total += cab;
        System.out.print(tf_pesquisar_pnr.getText());
        try {
            Statement statement = conn.createStatement();

            PreparedStatement stmt = conn.prepareStatement("select * from prs where prn = '" + pnr + "'");

            ResultSet resultSet = stmt.executeQuery();
            System.out.print(resultSet);
            while (resultSet.next()) {

                tm_in = resultSet.getString("tm");
                prn_in = resultSet.getString("prn");
                fam_in = resultSet.getString("fam");
                design_in = resultSet.getString("desig");

                total += tm_in + " | " + prn_in + " |  " + fam_in + "   |  " + design_in + "\n";
                total += lin;

            }

        } catch (SQLException ex) {
            System.out.print(ex);
            //return "teste";
        }
        total += "**************************************************";

        TF_resultado.setText(total);
    }

    public void selectfam(String fam) {
        //int i =0;
        String tm_in, prn_in, fam_in, design_in;
        String total = " ";
        total += cab;

        try {
            Statement statement = conn.createStatement();

            PreparedStatement stmt = conn.prepareStatement("select * from prs where fam = '" + fam + "'");

            ResultSet resultSet = stmt.executeQuery();
            System.out.print(resultSet);

            while (resultSet.next()) {

                //tm_in = resultSet.getString("tm");
                prn_in = resultSet.getString("prn");
                fam_in = resultSet.getString("fam");
                design_in = resultSet.getString("desig");

                total += " | " + prn_in + " |  " + fam_in + "   |   " + design_in + "\n";
                total += lin;

            }

        } catch (SQLException ex) {
            System.out.print(ex);

        }

        total += "**************************************************";

        TF_resultado.setText(total);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_pesquisar_pnr = new javax.swing.JButton();
        tf_pesquisar_tm = new javax.swing.JTextField();
        tf_pesquisar_pnr = new javax.swing.JTextField();
        tf_pesquisar_fAM = new javax.swing.JTextField();
        LB_SHOW = new javax.swing.JLabel();
        lb_tm_tf = new javax.swing.JLabel();
        lb_tm_tf1 = new javax.swing.JLabel();
        lb_tm_tf2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TF_resultado = new javax.swing.JTextArea();
        lb_resultado = new javax.swing.JLabel();
        btn_pesquisar_tm = new javax.swing.JButton();
        btn_pesquisar_fam = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btn_pesquisar_pnr.setText("Pesquisar");
        btn_pesquisar_pnr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesquisar_pnrActionPerformed(evt);
            }
        });

        tf_pesquisar_tm.setBackground(new java.awt.Color(255, 255, 204));
        tf_pesquisar_tm.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tf_pesquisar_tmFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tf_pesquisar_tmFocusLost(evt);
            }
        });

        tf_pesquisar_pnr.setBackground(new java.awt.Color(255, 255, 204));
        tf_pesquisar_pnr.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tf_pesquisar_pnrFocusLost(evt);
            }
        });

        tf_pesquisar_fAM.setBackground(new java.awt.Color(255, 255, 204));
        tf_pesquisar_fAM.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tf_pesquisar_fAMFocusLost(evt);
            }
        });

        LB_SHOW.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        LB_SHOW.setText("Pesquisa");

        lb_tm_tf.setText("Marca");

        lb_tm_tf1.setText("Modelo");

        lb_tm_tf2.setText("Preço");

        TF_resultado.setColumns(20);
        TF_resultado.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        TF_resultado.setRows(5);
        jScrollPane2.setViewportView(TF_resultado);

        lb_resultado.setText("Resultado : ");

        btn_pesquisar_tm.setText("Pesquisar");
        btn_pesquisar_tm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesquisar_tmActionPerformed(evt);
            }
        });

        btn_pesquisar_fam.setText("Pesquisar");
        btn_pesquisar_fam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesquisar_famActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 677, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(lb_tm_tf)
                                .addGap(103, 103, 103)
                                .addComponent(lb_tm_tf1)
                                .addGap(80, 80, 80)
                                .addComponent(lb_tm_tf2))
                            .addComponent(lb_resultado)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tf_pesquisar_tm)
                                    .addComponent(btn_pesquisar_tm, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(LB_SHOW)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(tf_pesquisar_pnr)
                                            .addComponent(btn_pesquisar_pnr, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(tf_pesquisar_fAM)
                                            .addComponent(btn_pesquisar_fam, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(LB_SHOW)
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_tm_tf)
                    .addComponent(lb_tm_tf1)
                    .addComponent(lb_tm_tf2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_pesquisar_tm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_pesquisar_fAM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_pesquisar_pnr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_pesquisar_pnr)
                    .addComponent(btn_pesquisar_tm)
                    .addComponent(btn_pesquisar_fam))
                .addGap(18, 18, 18)
                .addComponent(lb_resultado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_pesquisar_tmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesquisar_tmActionPerformed
        // TODO add your handling code here:
        if (tf_pesquisar_tm.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Favor prenncher o  campo TM!!!");
        } else {
            System.out.print(tf_pesquisar_tm.getText());
            selectTM(tf_pesquisar_tm.getText().toUpperCase());
        }
        tf_pesquisar_tm.setText("");


    }//GEN-LAST:event_btn_pesquisar_tmActionPerformed

    private void btn_pesquisar_pnrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesquisar_pnrActionPerformed
        // TODO add your handling code here:
        if (tf_pesquisar_pnr.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Favor prenncher o  campo PRN!!!");
        } else {
            System.out.print(tf_pesquisar_pnr.getText());
            selectPNR(tf_pesquisar_pnr.getText());
        }

        tf_pesquisar_pnr.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_pesquisar_pnrActionPerformed

    private void btn_pesquisar_famActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesquisar_famActionPerformed
        if (tf_pesquisar_fAM.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Favor prenncher o  campo FAM!!!");
        } else {
            System.out.print(tf_pesquisar_fAM.getText());
            selectfam(tf_pesquisar_fAM.getText().toUpperCase());
        }
        tf_pesquisar_fAM.setText("");

// TODO add your handling code here:
    }//GEN-LAST:event_btn_pesquisar_famActionPerformed

    private void tf_pesquisar_tmFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_pesquisar_tmFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_pesquisar_tmFocusGained

    private void tf_pesquisar_tmFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_pesquisar_tmFocusLost
        tf_pesquisar_tm.setText(tf_pesquisar_tm.getText().toUpperCase());         // TODO add your handling code here:
    }//GEN-LAST:event_tf_pesquisar_tmFocusLost

    private void tf_pesquisar_pnrFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_pesquisar_pnrFocusLost
        tf_pesquisar_pnr.setText(tf_pesquisar_pnr.getText().toUpperCase());         // TODO add your handling code here:
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_pesquisar_pnrFocusLost

    private void tf_pesquisar_fAMFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_pesquisar_fAMFocusLost
        tf_pesquisar_fAM.setText(tf_pesquisar_fAM.getText().toUpperCase());         // TODO add your handling code here:
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_pesquisar_fAMFocusLost

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
            java.util.logging.Logger.getLogger(Pr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pr().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LB_SHOW;
    private javax.swing.JTextArea TF_resultado;
    private javax.swing.JButton btn_pesquisar_fam;
    private javax.swing.JButton btn_pesquisar_pnr;
    private javax.swing.JButton btn_pesquisar_tm;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lb_resultado;
    private javax.swing.JLabel lb_tm_tf;
    private javax.swing.JLabel lb_tm_tf1;
    private javax.swing.JLabel lb_tm_tf2;
    private javax.swing.JTextField tf_pesquisar_fAM;
    private javax.swing.JTextField tf_pesquisar_pnr;
    private javax.swing.JTextField tf_pesquisar_tm;
    // End of variables declaration//GEN-END:variables
}
