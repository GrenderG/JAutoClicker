/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jautoclicker.visual;

import jautoclicker.logic.MouseLogic;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Grender
 */
public class Main extends javax.swing.JFrame {

    private double coords[] = new double[2];
    private MouseLogic ml;

    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        this.setLocationRelativeTo(null);

        this.jComboBox1.removeAllItems();
        this.jComboBox2.removeAllItems();
        this.jComboBox3.removeAllItems();

        this.jComboBox1.addItem("Infinito");
        this.jComboBox1.addItem("1 min");
        this.jComboBox1.addItem("5 min");
        this.jComboBox1.addItem("10 min");
        this.jComboBox1.addItem("15 min");
        this.jComboBox1.addItem("30 min");
        this.jComboBox1.addItem("45 min");
        this.jComboBox1.addItem("60 min");
        this.jComboBox1.addItem("90 min");
        this.jComboBox1.addItem("120 min");
        this.jComboBox1.addItem("180 min");
        this.jComboBox1.addItem("300 min");

        this.jComboBox2.addItem("Indefinido");
        this.jComboBox2.addItem("0.5 s");
        this.jComboBox2.addItem("1 s");
        this.jComboBox2.addItem("5 s");
        this.jComboBox2.addItem("10 s");
        this.jComboBox2.addItem("30 s");
        this.jComboBox2.addItem("60 s");
        this.jComboBox2.addItem("90 s");
        this.jComboBox2.addItem("120 s");
        this.jComboBox2.addItem("180 s");
        this.jComboBox2.addItem("300 s");
        this.jComboBox2.addItem("900 s");

        this.jComboBox3.addItem("Infinito");
        this.jComboBox3.addItem("5 clics");
        this.jComboBox3.addItem("10 clics");
        this.jComboBox3.addItem("30 clics");
        this.jComboBox3.addItem("100 clics");
        this.jComboBox3.addItem("1000 clics");
        this.jComboBox3.addItem("2500 clics");
        this.jComboBox3.addItem("5000 clics");
        this.jComboBox3.addItem("10000 clics");
        this.jComboBox3.addItem("250000 clics");
        this.jComboBox3.addItem("500000 clics");
        this.jComboBox3.addItem("750000 clics");
        this.jComboBox3.addItem("999999 clics");

        this.jTextPane2.setText("Pulsa el botón para calibrar. Una vez pulsado "
                + "deja el cursos en el lugar de la pantalla donde realizar los clics "
                + "automáticos y espera durante 5 segundos.");

        this.jButton1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    Thread.sleep(5000);
                    Main.this.setCoords();
                    setCoordsText();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });

        this.jButton2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int delay = 0;
                boolean isDelayed = true, isInfinite = false;
                long timeMax = 0, maxClicks = 0;
                
                switch(Main.this.jComboBox1.getSelectedIndex()){
                    case 0:
                        isInfinite = true;
                        break;
                    case 1:
                        timeMax = 60000;
                        break;
                    case 2:
                        timeMax = 300000;
                        break;
                    case 3:
                        timeMax = 600000;
                        break;
                    case 4:
                        timeMax = 900000;
                        break;
                    case 5:
                        timeMax = 1800000;
                        break;
                    case 6:
                        timeMax = 2700000;
                        break;
                    case 7:
                        timeMax = 3600000;
                        break;
                    case 8:
                        timeMax = 5400000;
                        break;
                    case 9:
                        timeMax = 7200000;
                        break;
                    case 10:
                        timeMax = 10800000;
                        break;
                    case 11:
                        timeMax = 18000000;
                        break;    
                }
                
                switch(Main.this.jComboBox2.getSelectedIndex()){
                    case 0:
                        isDelayed = false;
                        break;
                    case 1:
                        delay = 500;
                        break;
                    case 2:
                        delay = 1000;
                        break;
                    case 3:
                        delay = 5000;
                        break;
                    case 4:
                        delay = 10000;
                        break;
                    case 5:
                        delay = 30000;
                        break;
                    case 6:
                        delay = 60000;
                        break;
                    case 7:
                        delay = 90000;
                        break;
                    case 8:
                        delay = 120000;
                        break;
                    case 9:
                        delay = 180000;
                        break;
                    case 10:
                        delay = 300000;
                        break;
                    case 11:
                        delay = 900000;
                        break;    
                }
                
                /*switch(Main.this.jComboBox3.getSelectedIndex()){
                    case 1:
                        delay = 0;
                        break;
                    case 2:
                        delay = 500;
                        break;
                    case 3:
                        delay = 1000;
                        break;
                    case 4:
                        delay = 5000;
                        break;
                    case 5:
                        delay = 10000;
                        break;
                    case 6:
                        delay = 30000;
                        break;
                    case 7:
                        delay = 60000;
                        break;
                    case 8:
                        delay = 90000;
                        break;
                    case 9:
                        delay = 120000;
                        break;
                    case 10:
                        delay = 180000;
                        break;
                    case 11:
                        delay = 300000;
                        break;
                    case 12:
                        delay = 900000;
                        break;    
                }*/
                System.out.println(Main.this.jComboBox1.getSelectedIndex());
                System.out.println(Main.this.jComboBox2.getSelectedIndex());
                ml = new MouseLogic(Main.this.coords, delay, timeMax, maxClicks, isDelayed, isInfinite);
                Thread clicker = new Thread(ml);
                clicker.start();
                
            }

        });

    }

    public void setCoordsText() {
        this.jLabel2.setText("x: " + this.coords[0] + ", y: " + this.coords[1]);
    }

    public void setCoords() {

        Point p = MouseInfo.getPointerInfo().getLocation();

        this.coords[0] = (double) p.x;
        this.coords[1] = (double) p.y;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        labelTiempoMax = new javax.swing.JLabel();
        labelIntervalo = new javax.swing.JLabel();
        labelClicMax = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        jComboBox3 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JAutoClicker");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        labelTiempoMax.setText("Tiempo máximo");

        labelIntervalo.setText("Intervalo entre clic");

        labelClicMax.setText("Número máximo de clics");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton1.setText("Calibrar");

        jTextPane2.setEditable(false);
        jTextPane2.setBackground(new java.awt.Color(255, 255, 204));
        jTextPane2.setEnabled(false);
        jScrollPane2.setViewportView(jTextPane2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(labelIntervalo, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(labelTiempoMax, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(labelClicMax)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTiempoMax)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelIntervalo)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelClicMax)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("Establecer auto clic en coordenadas: ");

        jButton2.setText("Comenzar");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jTextPane2;
    private javax.swing.JLabel labelClicMax;
    private javax.swing.JLabel labelIntervalo;
    private javax.swing.JLabel labelTiempoMax;
    // End of variables declaration//GEN-END:variables
}
