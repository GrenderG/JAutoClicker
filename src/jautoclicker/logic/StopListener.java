/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jautoclicker.logic;

import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

/**
 *
 * @author Grender
 */
public class StopListener implements NativeKeyListener {
    
    private MouseLogic ml;
    
    public StopListener (MouseLogic ml){
        this.ml = ml;
    }
    
    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        if (e.getKeyCode() == NativeKeyEvent.VK_ESCAPE){
            this.ml.isEscapePressed(true);
        }            
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {

    }
    
}
