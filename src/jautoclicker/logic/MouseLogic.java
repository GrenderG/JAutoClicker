/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jautoclicker.logic;

import jautoclicker.visual.Main;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    boolean haveMaxClicks;
    private double[] coords;
    private Main context;
    private int whichButton;
    private long clickCount = 0;
    private Timer timer;
    private boolean escapePressed = false;
    
    public MouseLogic(double[] coords, int whichButton, int delay, long maxTemp, long maxClicks, boolean isDelayed, boolean isInfinite, boolean haveMaxClicks, Main context, Timer timer) {
        this.whichButton = whichButton;
        this.coords = coords;
        this.timeNow = System.currentTimeMillis();
        this.isInfinite = isInfinite;
        this.isDelayed = isDelayed;
        this.haveMaxClicks = haveMaxClicks;
        this.context = context;
        this.timer = timer;
        this.escapePressed = false;
        
        if (isDelayed) {
            this.delay = delay;
        }
        if (!isInfinite) {
            this.maxTime = maxTemp;
        }

        if (haveMaxClicks) {
            this.maxClicks = maxClicks;
        }
    }
    
    public MouseLogic(){
        
    }
    
    @Override
    public void run() {
        long actualTime = System.currentTimeMillis();

        if (isDelayed && !isInfinite && !haveMaxClicks
                || !isDelayed && !isInfinite && !haveMaxClicks) {

            while (actualTime < timeNow + maxTime && !escapePressed) {
                try {
                    startClicking();
                    clickCount++;
                    if (isDelayed) {
                        Thread.sleep(delay);
                        actualTime += delay;
                    } else {
                        actualTime = System.currentTimeMillis();
                    }

                } catch (InterruptedException ex) {
                    Logger.getLogger(MouseLogic.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else if (isInfinite && !isDelayed && !haveMaxClicks) {
            while (true && !escapePressed) {
                startClicking();
                clickCount++;
            }

        } else if (isInfinite && isDelayed && !haveMaxClicks) {
            while (true && !escapePressed) {
                try {
                    startClicking();
                    clickCount++;
                    Thread.sleep(delay);
                    actualTime += delay;
                } catch (InterruptedException ex) {
                    Logger.getLogger(MouseLogic.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (isDelayed && !isInfinite && haveMaxClicks
                || !isInfinite && !isDelayed && haveMaxClicks) {

            while (actualTime < timeNow + maxTime && clickCount < maxClicks && !escapePressed) {
                try {
                    startClicking();
                    clickCount++;
                    if (isDelayed) {
                        Thread.sleep(delay);
                    }
                    actualTime += delay;
                } catch (InterruptedException ex) {
                    Logger.getLogger(MouseLogic.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else if (isInfinite && !isDelayed && haveMaxClicks) {
            while (true && !escapePressed) {
                startClicking();
                clickCount++;
                if (clickCount == maxClicks) {
                    break;
                }
            }

        } else if (isInfinite && isDelayed && haveMaxClicks) {
            while (true && !escapePressed) {
                try {
                    startClicking();
                    clickCount++;
                    Thread.sleep(delay);
                    actualTime += delay;
                    if (clickCount == maxClicks) {
                        break;
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(MouseLogic.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        context.getTimer().stopTimer();
        context.setCalibrateEnabled(true);
        context.setStartEnabled(true);
        context.getTitleActualizer().setShouldStop(true);
        context.showFinishDialog();
        
    }
    
    public void isEscapePressed(boolean isEscapePressed){
        this.escapePressed = true;
    }
    
    public long getClickCount(){
        return this.clickCount;
    }
    
    public void setCoords(double[] coords){
        this.coords[0] = coords[0];
        this.coords[1] = coords[1];
    }
    
    public void startClicking() {
        try {

            Robot bot = new Robot();
            int mask;
            
            if (whichButton == 0)
                mask = InputEvent.BUTTON1_DOWN_MASK;
            else if (whichButton == 1)
                mask = InputEvent.BUTTON2_DOWN_MASK;
            else
                mask = InputEvent.BUTTON3_DOWN_MASK;

            bot.mouseMove((int) coords[0], (int) coords[1]);
            bot.mousePress(mask);
            bot.mouseRelease(mask);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
