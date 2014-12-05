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
import javax.swing.ImageIcon;

/**
 *
 * @author Grender
 */
public class Main extends javax.swing.JFrame {

    private double coords[] = new double[2];
    private MouseLogic ml;
    private static final ImageIcon icon = new ImageIcon(Main.class.getResource("/res/icon.png"));
    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        
        this.setIconImage(icon.getImage());
        
        this.setLocationRelativeTo(null);

        this.comoBoxTiempoMax.removeAllItems();
        this.comoBoxIntervalo.removeAllItems();
        this.comoBoxClicMax.removeAllItems();

        this.comoBoxTiempoMax.addItem("Infinito");
        this.comoBoxTiempoMax.addItem("1 min");
        this.comoBoxTiempoMax.addItem("5 min");
        this.comoBoxTiempoMax.addItem("10 min");
        this.comoBoxTiempoMax.addItem("15 min");
        this.comoBoxTiempoMax.addItem("30 min");
        this.comoBoxTiempoMax.addItem("45 min");
        this.comoBoxTiempoMax.addItem("60 min");
        this.comoBoxTiempoMax.addItem("90 min");
        this.comoBoxTiempoMax.addItem("120 min");
        this.comoBoxTiempoMax.addItem("180 min");
        this.comoBoxTiempoMax.addItem("300 min");

        this.comoBoxIntervalo.addItem("Indefinido");
        this.comoBoxIntervalo.addItem("0.01 s");
        this.comoBoxIntervalo.addItem("0.05 s");
        this.comoBoxIntervalo.addItem("0.1 s");
        this.comoBoxIntervalo.addItem("0.5 s");
        this.comoBoxIntervalo.addItem("1 s");
        this.comoBoxIntervalo.addItem("5 s");
        this.comoBoxIntervalo.addItem("10 s");
        this.comoBoxIntervalo.addItem("30 s");
        this.comoBoxIntervalo.addItem("60 s");
        this.comoBoxIntervalo.addItem("90 s");
        this.comoBoxIntervalo.addItem("120 s");
        this.comoBoxIntervalo.addItem("180 s");
        this.comoBoxIntervalo.addItem("300 s");
        this.comoBoxIntervalo.addItem("900 s");

        this.comoBoxClicMax.addItem("Infinito");
        this.comoBoxClicMax.addItem("5 clics");
        this.comoBoxClicMax.addItem("10 clics");
        this.comoBoxClicMax.addItem("30 clics");
        this.comoBoxClicMax.addItem("100 clics");
        this.comoBoxClicMax.addItem("500 clics");
        this.comoBoxClicMax.addItem("1000 clics");
        this.comoBoxClicMax.addItem("2500 clics");
        this.comoBoxClicMax.addItem("5000 clics");
        this.comoBoxClicMax.addItem("10000 clics");
        this.comoBoxClicMax.addItem("250000 clics");
        this.comoBoxClicMax.addItem("500000 clics");
        this.comoBoxClicMax.addItem("750000 clics");
        this.comoBoxClicMax.addItem("1000000 clics");

        this.infoCalibrar.setText("Pulsa el botón para calibrar. Una vez pulsado "
                + "deja el cursor en el lugar de la pantalla donde realizar los clics "
                + "automáticos y espera durante 5 segundos.");

        this.btnCalibrar.addActionListener(new ActionListener() {

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

        this.btnComenzar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int delay = 0;
                boolean isDelayed = true, isInfinite = false, haveMaxClicks = true;
                long timeMax = 0, maxClicks = 0;
                
                switch(Main.this.comoBoxTiempoMax.getSelectedIndex()){
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
                
                switch(Main.this.comoBoxIntervalo.getSelectedIndex()){
                    case 0:
                        isDelayed = false;
                        break;
                    case 1:
                        delay = 10;
                        break;    
                    case 2:
                        delay = 50;
                        break;    
                    case 3:
                        delay = 100;
                        break;
                    case 4:
                        delay = 500;
                        break;
                    case 5:
                        delay = 1000;
                        break;
                    case 6:
                        delay = 5000;
                        break;
                    case 7:
                        delay = 10000;
                        break;
                    case 8:
                        delay = 30000;
                        break;
                    case 9:
                        delay = 60000;
                        break;
                    case 10:
                        delay = 90000;
                        break;
                    case 11:
                        delay = 120000;
                        break;
                    case 12:
                        delay = 180000;
                        break;
                    case 13:
                        delay = 300000;
                        break;
                    case 14:
                        delay = 900000;
                        break;    
                }
                
                switch(Main.this.comoBoxClicMax.getSelectedIndex()){
                    case 0:
                        haveMaxClicks = false;
                        break;
                    case 1:
                        maxClicks = 5;
                        break;
                    case 2:
                        maxClicks = 10;
                        break;
                    case 3:
                        maxClicks = 30;
                        break;
                    case 4:
                        maxClicks = 100;
                        break;    
                    case 5:
                        maxClicks = 500;
                        break;
                    case 6:
                        maxClicks = 1000;
                        break;
                    case 7:
                        maxClicks = 2500;
                        break;
                    case 8:
                        maxClicks = 5000;
                        break;
                    case 9:
                        maxClicks = 10000;
                        break;
                    case 10:
                        maxClicks = 250000;
                        break;
                    case 11:
                        maxClicks = 500000;
                        break;
                    case 12:
                        maxClicks = 750000;
                        break;
                    case 13:
                        maxClicks = 1000000;
                        break;     
                }

                ml = new MouseLogic(Main.this.coords, delay, timeMax, maxClicks, isDelayed, isInfinite, haveMaxClicks, Main.this);
                Thread clicker = new Thread(ml);
                clicker.start();
                
            }

        });

    }

    public void setCoordsText() {
        this.labelCoord.setText("x: " + this.coords[0] + ", y: " + this.coords[1]);
    }
    
    public void setTitleInfo(long clickCount){
        
        this.setTitle("JAutoClicker | Clics: "+clickCount);
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
        comoBoxTiempoMax = new javax.swing.JComboBox();
        comoBoxIntervalo = new javax.swing.JComboBox();
        comoBoxClicMax = new javax.swing.JComboBox();
        btnCalibrar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        infoCalibrar = new javax.swing.JTextPane();
        jPanel2 = new javax.swing.JPanel();
        labelCoordInfo = new javax.swing.JLabel();
        labelCoord = new javax.swing.JLabel();
        btnComenzar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JAutoClicker");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        labelTiempoMax.setText("Tiempo máximo");

        labelIntervalo.setText("Intervalo entre clics");

        labelClicMax.setText("Número máximo de clics");

        comoBoxTiempoMax.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        comoBoxIntervalo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comoBoxIntervalo.setToolTipText("Si eliges \"Indefinido\" junto con un número máximo de clics, quizá no alcance los clics deseados.");

        comoBoxClicMax.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnCalibrar.setText("Calibrar");

        infoCalibrar.setEditable(false);
        infoCalibrar.setBackground(new java.awt.Color(204, 204, 204));
        infoCalibrar.setEnabled(false);
        jScrollPane2.setViewportView(infoCalibrar);

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
                            .addComponent(comoBoxTiempoMax, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comoBoxIntervalo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(labelClicMax)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(comoBoxClicMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnCalibrar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTiempoMax)
                    .addComponent(comoBoxTiempoMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelIntervalo)
                    .addComponent(comoBoxIntervalo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelClicMax)
                    .addComponent(comoBoxClicMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCalibrar)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        labelCoordInfo.setText("Establecer auto clic en coordenadas: ");

        btnComenzar.setText("Comenzar");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnComenzar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(labelCoordInfo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelCoord, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCoordInfo)
                    .addComponent(labelCoord))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnComenzar)
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
    private javax.swing.JButton btnCalibrar;
    private javax.swing.JButton btnComenzar;
    private javax.swing.JComboBox comoBoxClicMax;
    private javax.swing.JComboBox comoBoxIntervalo;
    private javax.swing.JComboBox comoBoxTiempoMax;
    private javax.swing.JTextPane infoCalibrar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelClicMax;
    private javax.swing.JLabel labelCoord;
    private javax.swing.JLabel labelCoordInfo;
    private javax.swing.JLabel labelIntervalo;
    private javax.swing.JLabel labelTiempoMax;
    // End of variables declaration//GEN-END:variables
}
