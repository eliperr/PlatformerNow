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
             Sounds.jumping=true;
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
               break;
            case 82: //r for restart
                panel.restart();
               
                break;
            case 78: //for testing will be removed for players 
                Portal.passedLevel=true;
                break;
           /* case isDigitKey(): //set to level 0
                Load.setLevel(-1);
               Portal.passedLevel=true;
               
               break;*/
             //add to go to next level here for testing  
                //System.out.println("r");
          //default:
                //panel.player.stopUp();
   
        }
          //for level testing-not for actual play
        if (e.getKeyCode()>=48 && e.getKeyCode()<=57) 
            //need to expand past level 9 eventually
        {
             Load.setLevel(e.getKeyCode()-49);
               Portal.passedLevel=true;
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
