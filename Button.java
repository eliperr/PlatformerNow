/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.platformernow;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eliperr
 */

public class Button extends Obstacle {
   private boolean pressed=false;
    boolean wasNotTouching=true;
     boolean neverPressed=true;
    // private static int numIDs=0;
     private  int id;
   //what should button do when pressed? Open door?
   //maybe button stays
    public Button (int x, int y, int id)
    {    
        super(x, y);
        super.restartable=true;
        super.img=Load.uploadButton();  //Button
        super.SCALE=45;
         h=(int)(965/SCALE);
       w=(int)(965/SCALE);
       yOffset=(int)(0/SCALE);
       xOffset=(int)(0/SCALE); //5
       this.id=id;
       //super.subImg=super.img.getSubimage(0,1100,1000,1000);
      //System.out.println("height is " + h + " width is " + w );
     //ID=numIDs;
      //hitbox=new Rectangle2D.Float( (float) x, (float) y, (float) w, (float) h);
      hitbox=new Rectangle2D.Float( (float) (x + xOffset), (float) (y + yOffset), w, h); 
      //numIDs++;
      //need better way to keep track of numIDS if skipping levels or restarting levels -may need to be hardcoded
      
    }
    
    public void run(Player p) throws InterruptedException 
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
       {  //Sounds.stopSound(Sounds.BUTTON);
          
          System.out.println("button on");
           pressed=true;
           wasNotTouching=false;
           Sounds.playSounds(Sounds.BUTTON);
       }
       else if (pressed && this.isTouching(p) && wasNotTouching)
       {
          // Sounds.stopSound(Sounds.BUTTON);
          
            System.out.println("button off");
           pressed=false;
           wasNotTouching=false;
            Sounds.playSounds(Sounds.BUTTON);
       }
       
    }  
    @Override
    public void restart(GamePanel game)
    {
        pressed=false;
         wasNotTouching=true;
         neverPressed=true;
        game.platformOn=false;
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
   {   try {
       //this.animate();
       this.run(p);
       } catch (InterruptedException ex) {
           Logger.getLogger(Button.class.getName()).log(Level.SEVERE, null, ex);
       }
           
                      decide(game); //determine what effect button will have
                                      // System.out.println(this.isOn() +"platform is on");

                   //}
                 
                 //System.out.println(this.isOn() +"platform is on");
                  //System.out.println(!this.isOn() +"platform is off");
                if (Portal.nextLevel)
                {
                    this.restart(game);
                }   

          }
       @Override  
       public void updateTick()
       {
       }
    
       
       public  void decide(GamePanel game)
               
       {
           switch (this.id)
               
           {
               case 0:
                   
                   
               {
                    if (this.turnedOn())
                  {
                      
                       game.box.add(new Box(30,30,480,100, Color.BLUE)); 
                  }     
                   
                   if (this.isOn())
                     {
                         game.platformOn=true;
                       //System.out.println("platform on");
                     }
                   else
                   {
                       game.platformOn=false;
                       //System.out.println("platform false");
                   }
                   break;
               }
               
               
               case 2:
               {
                   
                    if (this.turnedOn())
                  {
                      
                       game.box.add(new Box(30,30,480,100, Color.RED)); 
                  }   
                   
                   
                   
                   
                   
                   
               }
               
               default:
               {
                   
                     if (this.isOn())
                     {
                         game.platformOn=true;
                       //System.out.println("platform on");
                     }
                   else
                   {
                       game.platformOn=false;
                        //System.out.println("platform false");
                   }
              
                   break;
                   
                   
               }
           }
           
       }
    //only needs draw and toutching  //override animate
    //and dont need tick 
    
}
