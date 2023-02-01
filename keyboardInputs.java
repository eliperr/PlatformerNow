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
import java.awt.event.MouseListener; 
import java.awt.event.MouseAdapter; 
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
public class keyboardInputs extends MouseAdapter implements KeyListener, MouseListener 
{
   
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
        //https://www.toptal.com/developers/keycode
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
            case 32: //space
               GameRunner.togglePause();
            case 82: //r for restart
                panel.restart();
                //System.out.println("r");
          //default:
                //panel.player.stopUp();
   
        }
    }
    
    //helper for adding obstacles
   @Override
    public void mouseClicked(MouseEvent e) 
    {
       System.out.println("mouse");
        
        System.out.println(e.getPoint());

    }
   
}
