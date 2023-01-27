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

public class Portal extends Obstacle{
 private static boolean nextLevel=false;   
    
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
    
 
            
    public void nextLevel(Player p,GamePanel game)
    {
        if (isTouching(p)&&!nextLevel)
        {  //wait one second before moving forward?
            nextLevel=true;
            System.out.println("next level");
            Load.upLevel();
              p.setPosition(100,270);
            game.ObstacleList=Load.initLevel();
           nextLevel=false;
            
            
            //System.exit(0);
        }
    }
    
    @Override
    public void doStuff (Player p, GamePanel game) 
   {
         //this.updateTick();
             //this.animate();
       this.nextLevel(p,game);
   }
}
