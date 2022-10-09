/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.platformernow;

/**
 *
 * @author eliperr
 */
import java.awt.event.KeyListener; 
import java.awt.event.KeyEvent;
public class keyboardInputs implements KeyListener{
   
   private GamePanel panel;
   
    
    
 public keyboardInputs(GamePanel panel)
 {
     this.panel=panel;
     
 }
    
    
   @Override
        public void keyTyped(KeyEvent e){
        
    }
    
        @Override
    public void keyReleased(KeyEvent e){
        
        switch(e.getKeyCode()){
            case 38:   //up
            panel.player.stopUp();
            break;
            case 37: //left
            panel.player.stopLeft();
            break;
           
            case 39: //right
            panel.player.stopRight();
            break;
            case 40://down
             panel.player.stopDown();
             break;
    }
    }
    //need to reset here!!! set released movements to false then can try animation
    @Override
    public void keyPressed (KeyEvent e)
    { 
        
        switch(e.getKeyCode()){
            case 38:   //up
            panel.player.Up();
            break;
            case 37: //left
            panel.player.Left();
            break;
           
            case 39: //right
            panel.player.Right();
            break;
            case 40://down
             panel.player.Down();
             break;
           /// default:
                //panel.reset();
   
        }
    }
   
}
