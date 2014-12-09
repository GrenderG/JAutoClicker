/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jautoclicker.visual;

import jautoclicker.logic.MouseLogic;
import jautoclicker.logic.StopListener;
import jautoclicker.logic.Timer;
import jautoclicker.logic.TitleActualizer;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.jnativehook.GlobalScreen;

/**
 *
 * @author Grender
 */
public class Main extends javax.swing.JFrame {

    private double coords[] = new double[]{0.0, 0.0};
    private MouseLogic ml;
    private Timer timer;
    private TitleActualizer ta;
    private static final ImageIcon icon = new ImageIcon(Main.class.getResource("/res/icon.png"));
    
    private final URL soundPath[] = new URL[]{Main.class.getResource("/sounds/1.wav"), 
                                        Main.class.getResource("/sounds/2.wav"),
                                        Main.class.getResource("/sounds/3.wav"), 
                                        Main.class.getResource("/sounds/4.wav"), 
                                        Main.class.getResource("/sounds/5.wav")};

    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        
        try{
            GlobalScreen.registerNativeHook();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        this.setIconImage(icon.getImage());
        
        this.setLocationRelativeTo(null);
        
        this.setCoordsText();
        
        this.comboBoxMaxTime.removeAllItems();
        this.comboBoxInterval.removeAllItems();
        this.comboBoxMaxClick.removeAllItems();
        this.comboBoxButtonToPress.removeAllItems();
        
        this.btnStart.setEnabled(false);

        this.comboBoxMaxTime.addItem("Infinite");
        this.comboBoxMaxTime.addItem("1 min");
        this.comboBoxMaxTime.addItem("5 min");
        this.comboBoxMaxTime.addItem("10 min");
        this.comboBoxMaxTime.addItem("15 min");
        this.comboBoxMaxTime.addItem("30 min");
        this.comboBoxMaxTime.addItem("45 min");
        this.comboBoxMaxTime.addItem("60 min");
        this.comboBoxMaxTime.addItem("90 min");
        this.comboBoxMaxTime.addItem("120 min");
        this.comboBoxMaxTime.addItem("180 min");
        this.comboBoxMaxTime.addItem("300 min");

        this.comboBoxInterval.addItem("Undefined");
        this.comboBoxInterval.addItem("0.01 s");
        this.comboBoxInterval.addItem("0.05 s");
        this.comboBoxInterval.addItem("0.1 s");
        this.comboBoxInterval.addItem("0.5 s");
        this.comboBoxInterval.addItem("1 s");
        this.comboBoxInterval.addItem("5 s");
        this.comboBoxInterval.addItem("10 s");
        this.comboBoxInterval.addItem("30 s");
        this.comboBoxInterval.addItem("60 s");
        this.comboBoxInterval.addItem("90 s");
        this.comboBoxInterval.addItem("120 s");
        this.comboBoxInterval.addItem("180 s");
        this.comboBoxInterval.addItem("300 s");
        this.comboBoxInterval.addItem("900 s");

        this.comboBoxMaxClick.addItem("Infinite");
        this.comboBoxMaxClick.addItem("5 clicks");
        this.comboBoxMaxClick.addItem("10 clicks");
        this.comboBoxMaxClick.addItem("30 clicks");
        this.comboBoxMaxClick.addItem("100 clicks");
        this.comboBoxMaxClick.addItem("500 clicks");
        this.comboBoxMaxClick.addItem("1000 clicks");
        this.comboBoxMaxClick.addItem("2500 clicks");
        this.comboBoxMaxClick.addItem("5000 clicks");
        this.comboBoxMaxClick.addItem("10000 clicks");
        this.comboBoxMaxClick.addItem("250000 clicks");
        this.comboBoxMaxClick.addItem("500000 clicks");
        this.comboBoxMaxClick.addItem("750000 clicks");
        this.comboBoxMaxClick.addItem("1000000 clicks");
        
        this.comboBoxButtonToPress.addItem("Left button");
        this.comboBoxButtonToPress.addItem("Wheel button");
        this.comboBoxButtonToPress.addItem("Right button");
        
        this.infoCalibrate.setText("Press the button to get coords. Once is pressed, "
                + "keep the pointer in the place you want to do the "
                + "automatic clicks and wait for 5 seconds.");
        
        this.infoStart.setText("Press ESCAPE to interrupt click action.");
        
        this.btnCalibrate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    
                    for (int i = 0; i < 5; i++){
                        Applet.newAudioClip(soundPath[i]).play();
                        Thread.sleep(1000);
                    }
                    
                    Main.this.coords = Main.this.getCoords();
                    setCoordsText();
                    Main.this.setStartEnabled(true);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });

        this.btnStart.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int delay = 0, whichButton = 0;
                boolean isDelayed = true, isInfinite = false, haveMaxClicks = true;
                long timeMax = 0, maxClicks = 0;
                
                switch(Main.this.comboBoxMaxTime.getSelectedIndex()){
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
                
                switch(Main.this.comboBoxInterval.getSelectedIndex()){
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
                
                switch(Main.this.comboBoxMaxClick.getSelectedIndex()){
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
                
                switch(Main.this.comboBoxButtonToPress.getSelectedIndex()){
                    case 0:
                        whichButton = 0;
                        break;
                    case 1:
                        whichButton = 1;
                        break;
                    case 2:
                        whichButton = 2;
                        break;
                }
                
                timer = new Timer();
                Thread tTimer = new Thread(timer);
                
                ml = new MouseLogic(Main.this.coords, whichButton, delay, timeMax, maxClicks, isDelayed, isInfinite, haveMaxClicks, Main.this, Main.this.timer);
                GlobalScreen.getInstance().addNativeKeyListener(new StopListener(ml));
                Thread clicker = new Thread(ml);
                
                ta = new TitleActualizer(Main.this, ml, timer);
                Thread tActualizer = new Thread(ta);
                
                Main.this.setCalibrateEnabled(false);
                Main.this.setStartEnabled(false);
                tTimer.start();
                clicker.start();
                tActualizer.start();
                
            }

        });

    }
    
    public MouseLogic getMouseLogic(){
        return this.ml;
    }
    
    public Timer getTimer(){
        return this.timer;
    }
    
    public TitleActualizer getTitleActualizer(){
        return this.ta;
    }
    
    public void setCalibrateEnabled(boolean isEnabled){
        this.btnCalibrate.setEnabled(isEnabled);
    }
    
    public void setStartEnabled(boolean isEnabled){
        this.btnStart.setEnabled(isEnabled);
    }
    
    public double[] getCoords() {

        Point p = MouseInfo.getPointerInfo().getLocation();

        return (new double[]{p.x, p.y});

    }
    
    public void showFinishDialog(){
        JOptionPane.showMessageDialog(null, "Task finished.", "JAutoClicker", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void setCoordsText() {
        this.labelCoord.setText("x: " + this.coords[0] + ", y: " + this.coords[1]);
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
        labelMaxTime = new javax.swing.JLabel();
        labelInterval = new javax.swing.JLabel();
        labelMaxClick = new javax.swing.JLabel();
        comboBoxMaxTime = new javax.swing.JComboBox();
        comboBoxInterval = new javax.swing.JComboBox();
        comboBoxMaxClick = new javax.swing.JComboBox();
        btnCalibrate = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        infoCalibrate = new javax.swing.JTextPane();
        labelButtonToPress = new javax.swing.JLabel();
        comboBoxButtonToPress = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        labelCoordInfo = new javax.swing.JLabel();
        labelCoord = new javax.swing.JLabel();
        btnStart = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        infoStart = new javax.swing.JTextPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        itemLaunchAgain = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        itemExit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JAutoClicker");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        labelMaxTime.setText("Max. time");

        labelInterval.setText("Click interval");

        labelMaxClick.setText("Max. number of clicks");

        comboBoxMaxTime.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBoxMaxTime.setPreferredSize(new java.awt.Dimension(120, 24));

        comboBoxInterval.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBoxInterval.setToolTipText("\"Undefined\" may cause weird behaviours, be careful.");
        comboBoxInterval.setPreferredSize(new java.awt.Dimension(120, 24));

        comboBoxMaxClick.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBoxMaxClick.setPreferredSize(new java.awt.Dimension(120, 24));

        btnCalibrate.setText("Calibrate");

        infoCalibrate.setEditable(false);
        infoCalibrate.setBackground(new java.awt.Color(240, 240, 240));
        infoCalibrate.setEnabled(false);
        jScrollPane2.setViewportView(infoCalibrate);

        labelButtonToPress.setText("Button to press");

        comboBoxButtonToPress.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBoxButtonToPress.setPreferredSize(new java.awt.Dimension(120, 24));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(labelInterval, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(labelMaxTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboBoxMaxTime, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBoxInterval, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelMaxClick)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(comboBoxMaxClick, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnCalibrate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelButtonToPress)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(comboBoxButtonToPress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelMaxTime)
                    .addComponent(comboBoxMaxTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboBoxInterval, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelInterval))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelMaxClick)
                    .addComponent(comboBoxMaxClick, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelButtonToPress)
                    .addComponent(comboBoxButtonToPress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCalibrate)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        labelCoordInfo.setText("Do auto-clicks in: ");

        btnStart.setText("Start");

        infoStart.setEditable(false);
        infoStart.setEnabled(false);
        jScrollPane1.setViewportView(infoStart);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(labelCoordInfo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelCoord, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
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
                .addComponent(btnStart)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu1.setText("File");

        itemLaunchAgain.setText("Reset to default");
        itemLaunchAgain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemLaunchAgainActionPerformed(evt);
            }
        });
        jMenu1.add(itemLaunchAgain);
        jMenu1.add(jSeparator1);

        itemExit.setText("Exit");
        itemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemExitActionPerformed(evt);
            }
        });
        jMenu1.add(itemExit);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Help");

        jMenuItem1.setText("Help contents");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

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
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_itemExitActionPerformed

    private void itemLaunchAgainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemLaunchAgainActionPerformed
        this.comboBoxButtonToPress.setSelectedIndex(0);
        this.comboBoxInterval.setSelectedIndex(0);
        this.comboBoxMaxClick.setSelectedIndex(0);
        this.comboBoxMaxTime.setSelectedIndex(0);
        this.coords = new double[]{0.0, 0.0};
        this.btnStart.setEnabled(false);
        this.btnCalibrate.setEnabled(true);
        this.setTitle("JAutoClicker");
        this.setCoordsText();
    }//GEN-LAST:event_itemLaunchAgainActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        new Help().setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

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
    private javax.swing.JButton btnCalibrate;
    private javax.swing.JButton btnStart;
    private javax.swing.JComboBox comboBoxButtonToPress;
    private javax.swing.JComboBox comboBoxInterval;
    private javax.swing.JComboBox comboBoxMaxClick;
    private javax.swing.JComboBox comboBoxMaxTime;
    private javax.swing.JTextPane infoCalibrate;
    private javax.swing.JTextPane infoStart;
    private javax.swing.JMenuItem itemExit;
    private javax.swing.JMenuItem itemLaunchAgain;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelButtonToPress;
    private javax.swing.JLabel labelCoord;
    private javax.swing.JLabel labelCoordInfo;
    private javax.swing.JLabel labelInterval;
    private javax.swing.JLabel labelMaxClick;
    private javax.swing.JLabel labelMaxTime;
    // End of variables declaration//GEN-END:variables
}
