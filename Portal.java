package com.mycompany.platformernow;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author eliperr
 */

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Portal extends Obstacle{
 public static boolean nextLevel=false;   
 public static boolean passedLevel=false;     
  //private static BufferedImage subImg;
 // private static BufferedImage subImg;
 
    public Portal(int x, int y)
    {
        
        super(x, y);
       super.img=Load.uploadPortal();
       super.SCALE=3;
       super.row=6;
       super.col=5;
       super.tickspeed=7;
       
       //super.tick=0;
       super.animx=190;
       super.animy=190;
         super.subImg=this.animate(img);
         
           h=(int)(90/SCALE);  //145 for entire portal, but want in center 
       w=(int)(90/SCALE);
       yOffset=(int)(50/SCALE);
       xOffset=(int)(50/SCALE); //5
      //System.out.println("height is " + h + " width is " + w );
     
      //hitbox=new Rectangle2D.Float( (float) x, (float) y, (float) w, (float) h);
      hitbox=new Rectangle2D.Float( (float) (x + xOffset), (float) (y + yOffset), w, h); 
      
       
    }
    
 
            
    public void nextLevel(Player p,GamePanel game) throws InterruptedException
    {  //clean this up?
        if ((isTouching(p)&&!nextLevel) || passedLevel)
        {  //wait one second before moving forward?
            nextLevel=true;
           passedLevel=false;
            Load.upLevel();
             //System.out.println(Load.getLevel());
              p.setPosition(100,270);
             if (Load.initPlatform()!=null)
         {game.platform=Load.initPlatform();} //test
          else
            {
                game.platform=null;
                
            }
             
            game.ObstacleList=Load.initLevel();
             game.box=Load.initBoxes();
             Platform.n=0;
            
            //System.out.println("starting " + game.platform);
            Gem.resetGemNum();
          nextLevel=false;
              //time to load new level:
          GameRunner.togglePause();
          
          try {
                Thread.sleep(100);
              } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
              }
          GameRunner.togglePause();
          }// if make normal platform as seperate object, can probably remove because wont need much time to load
    }
    
    @Override
    public void doStuff (Player p, GamePanel game) 
   {
     try {
         //this.updateTick();
         //this.animate();
         this.nextLevel(p,game);
     } catch (InterruptedException ex) {
         Logger.getLogger(Portal.class.getName()).log(Level.SEVERE, null, ex);
     } ///have to put this here to prevent errors from sleeping thread
   }
}
