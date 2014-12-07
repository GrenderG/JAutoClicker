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
public class Timer implements Runnable {
    
    private long time = 0;
    private boolean shouldStop = false;
    private Main context;
    
    public Timer (Main context){
        this.context = context;
    }
    
    @Override
    public void run() {
        try{
            while (!shouldStop){
                
                for (int i = 0; i < 10; i++){
                    Thread.sleep(1000);
                    time += 1;
                }                               
               
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public long getTime(){
        return this.time;
    }
    
    public void stopTimer(){
        this.shouldStop = true;
    }
}
