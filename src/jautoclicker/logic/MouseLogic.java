/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jautoclicker.logic;

import jautoclicker.visual.Main;
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
    
    public MouseLogic(double[] coords, int delay, long maxTemp, long maxClicks, boolean isDelayed, boolean isInfinite, boolean haveMaxClicks, Main context) {
        this.coords = coords;
        this.timeNow = System.currentTimeMillis();
        this.isInfinite = isInfinite;
        this.isDelayed = isDelayed;
        this.haveMaxClicks = haveMaxClicks;
        this.context = context;
        
        if (isDelayed) {
            this.delay = delay;
        }
        if (!isInfinite) {
            this.maxTime = maxTemp;
        }
        
        if (haveMaxClicks){
            this.maxClicks = maxClicks;
        }
    }

    @Override
    public void run() {
        long actualTime = System.currentTimeMillis();
        long clickCount = 0;
        
        if (isDelayed && !isInfinite && !haveMaxClicks ||
                !isDelayed && !isInfinite && !haveMaxClicks) {

            while (actualTime < timeNow + maxTime) {
                try {
                    startClicking();
                    clickCount++;
                    context.setTitleInfo(clickCount);
                    if (isDelayed)
                        Thread.sleep(delay);
                    
                    actualTime += delay;  
                } catch (InterruptedException ex) {
                    Logger.getLogger(MouseLogic.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else if (isInfinite && !isDelayed && !haveMaxClicks) {
            while (true) {
                startClicking();
                clickCount++;
                context.setTitleInfo(clickCount);
            }
            
        } else if (isInfinite && isDelayed && !haveMaxClicks) {
            while (true) {
                try {
                    startClicking();
                    clickCount++;
                    context.setTitleInfo(clickCount);
                    Thread.sleep(delay);
                    actualTime += delay;                   
                } catch (InterruptedException ex) {
                    Logger.getLogger(MouseLogic.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (isDelayed && !isInfinite && haveMaxClicks ||
                !isInfinite && !isDelayed && haveMaxClicks) {

            while (actualTime < timeNow + maxTime && clickCount < maxClicks) {
                try {
                    startClicking();
                    clickCount++;
                    context.setTitleInfo(clickCount);
                    if (isDelayed)
                        Thread.sleep(delay);
                    actualTime += delay;
                } catch (InterruptedException ex) {
                    Logger.getLogger(MouseLogic.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else if (isInfinite && !isDelayed && haveMaxClicks) {
            while (true) {
                startClicking();               
                clickCount++;
                context.setTitleInfo(clickCount);
                System.out.println(clickCount);
                if (clickCount == maxClicks)
                    break;
            }
            
        } else if (isInfinite && isDelayed && haveMaxClicks) {
            while (true) {
                try {
                    startClicking();
                    clickCount++;
                    context.setTitleInfo(clickCount);
                    Thread.sleep(delay);
                    actualTime += delay;
                    
                    if (clickCount == maxClicks)
                        break;
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
