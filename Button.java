/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.platformernow;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
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
        super.restartable=true;
        super.img=Load.uploadButton();  //Button
        super.SCALE=45;
         h=(int)(965/SCALE);
       w=(int)(965/SCALE);
       yOffset=(int)(0/SCALE);
       xOffset=(int)(0/SCALE); //5
       //super.subImg=super.img.getSubimage(0,1100,1000,1000);
      //System.out.println("height is " + h + " width is " + w );
     
      //hitbox=new Rectangle2D.Float( (float) x, (float) y, (float) w, (float) h);
      hitbox=new Rectangle2D.Float( (float) (x + xOffset), (float) (y + yOffset), w, h); 
      
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
    public boolean turnedOn()  //when turned on for the first time only, blue box falls down into place 
    {   boolean temp=neverPressed;     //test if turned on for the first time during game
      
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
            super.subImg=super.img.getSubimage(1125,0,1000,1000); 
        }
        
        else
        {
            super.subImg=super.img.getSubimage(0,1100,1000,1000);
            //super.subImg=super.img;
            
        }
        
        //System.out.println("getting in bitton");
        
        return subImg;
    }
      @Override  
    public void doStuff(Player p,GamePanel game)   //wish I could access player without adding it in
   { this.animate();  
       this.run(p);         
           if (this.turnedOn())
                  {
                      game.box[1]=Load.newBoxesToAdd()[0]; 
                         
                   }
                   if (this.isOn())
                     {
                         game.platformOn=true;
                      // System.out.println("platform on");
                     }
                     
          }
       @Override  
       public void updateTick()
       {
       }
    
    //only needs draw and toutching  //override animate
    //and dont need tick 
    
}
