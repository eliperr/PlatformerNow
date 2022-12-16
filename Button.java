/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.platformernow;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author eliperr
 */

public class Button extends Obstacle {
   private boolean pressed=false;
    boolean wasNotTouching=true;
     boolean neverPressed=true;
   //what should button do when pressed? Open door?
   //maybe button stays
    public Button (int x, int y)
    {
        super(x, y);
        super.img=Load.uploadButton();  //Button
        super.SCALE=45;
    }
    
    public void run(Player p)
    {
        //boolean wasNotTouching=true;
      /*if (this.isTouching(p))      
      {
          pressed=true; //button stays on until restart 
          
      }*/
        //pressed=this.isTouching(p); //button only on temporarily when pressed
        //could be multiple buttons with different mechanics
      
   //button turns on but turns off when pressed again:
   
     if (!this.isTouching(p))
     {
         
         wasNotTouching=true;
     }
        
       if (!pressed && this.isTouching(p) && wasNotTouching) 
       {
           
           pressed=true;
           wasNotTouching=false;
       }
       else if (pressed && this.isTouching(p) && wasNotTouching)
       {
           
           pressed=false;
           wasNotTouching=false;
       }
        
       
    }       
    public void restart()
    {
        pressed=false;
         wasNotTouching=true;
         neverPressed=true;
    }
    
    public boolean isOn()
    {
        return pressed;
    }
    public boolean turnedOn()
    {   boolean temp=neverPressed;
      
        boolean turnedOn= pressed && temp;
        if (turnedOn)
        {
            neverPressed=false;
        }
      
        return turnedOn;
    }
    
   
   /* public void draw(Graphics g)
    {
        //subImg=img.getSubimage(0,0,120,150);
        //System.out.println(SCALE);
        
        if (subImg!=null)
        {
         
           g.drawImage(subImg ,x,y, (int) (subImg.getWidth()/SCALE), (int) (subImg.getHeight()/SCALE), null); 
        }*/
    
    @Override
    public BufferedImage animate ()
    {
        if (pressed)
        {
            super.subImg=super.img.getSubimage(1100,0,1000,1000); 
        }
        
        else
        {
            super.subImg=super.img.getSubimage(0,1110,1000,1000);
            //super.subImg=super.img;
            
        }
        
        //System.out.println("getting in bitton");
        
        return subImg;
    }
        
    
    
    
    //only needs draw and toutching  //override animate
    //and dont need tick 
    
}
