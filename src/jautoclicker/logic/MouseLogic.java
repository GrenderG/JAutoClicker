/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jautoclicker.logic;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

/**
 *
 * @author Grender
 */
public class MouseLogic implements Runnable {

    private long maxClicks;
    private long timeNow;
    private int delay;
    private long maxTime;
    boolean isDelayed;
    boolean isInfinite;
    private double[] coords;
    
    public MouseLogic(double[] coords, int delay, long maxTemp, long maxClicks, boolean isDelayed, boolean isInfinite) {
        this.coords = coords;
        this.maxClicks = maxClicks;
        this.timeNow = System.currentTimeMillis();
        if (isDelayed) {
            this.delay = delay;
            this.isDelayed = isDelayed;
        }
        if (!isInfinite) {
            this.maxTime = maxTemp;
            this.isInfinite = isInfinite;
        }
    }

    @Override
    public void run() {
        long actualTime = System.currentTimeMillis();

        if (isDelayed && !isInfinite) {

            while (actualTime < timeNow + maxTime) {
                try {
                    startClicking();
                    Thread.sleep(delay);
                    actualTime += delay;
                } catch (InterruptedException ex) {
                    Logger.getLogger(MouseLogic.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else if (isInfinite && !isDelayed) {
            while (true) {
                startClicking();
            }
        } else if (isInfinite && isDelayed) {
            while (true) {
                try {
                    startClicking();
                    Thread.sleep(delay);
                    actualTime += delay;                   
                } catch (InterruptedException ex) {
                    Logger.getLogger(MouseLogic.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void startClicking() {
            try {

                Robot bot = new Robot();

                int mask = InputEvent.BUTTON1_DOWN_MASK;

                bot.mouseMove((int)coords[0], (int)coords[1]);
                bot.mousePress(mask);
                bot.mouseRelease(mask);

            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
