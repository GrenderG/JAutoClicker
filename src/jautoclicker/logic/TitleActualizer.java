/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jautoclicker.logic;

import jautoclicker.visual.Main;

/**
 *
 * @author Grender
 */
public class TitleActualizer implements Runnable {
    
    private Main context;
    private MouseLogic ml;
    private Timer timer;
    private boolean shouldStop = false;
    
    public TitleActualizer(Main context, MouseLogic ml, Timer timer){
        this.context = context;
        this.ml = ml;
        this.timer = timer;
    }
    
    @Override
    public void run() {
        while (!shouldStop){
            try{
                Thread.sleep(10);
            }catch(Exception e){
                e.printStackTrace();
            }
            
            setTitleTimer();
        }
    }
    
    public void setTitleTimer(){
        context.setTitle("JAutoClicker | Clicks: "+ml.getClickCount()+" | ~"+timer.getTime()+" s");
    }
    
    public void setShouldStop(boolean shouldStop){
        this.shouldStop = shouldStop;
    }
}
