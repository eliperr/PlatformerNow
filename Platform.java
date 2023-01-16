/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.platformernow;

/**
 *
 * @author eliperr
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
public class Platform extends Obstacle{
    
    private int maxX,  maxY, minX, minY;
    private boolean movingRight=true;
      private boolean movingDown=true;
      public boolean platformOn=false;
      //private Rectangle2D.Float hitbox;
      //private int h, w;
      //private final int yOffset;
      // private final int xOffset; //need to work on XOffset and why this doesnt work when platform moving 
    public Platform (int x, int y, int distX, int distY)
    {
        super(x, y);
        restartable=true;
        minX=x;
        minY=y;
        maxX=minX+distX;
        maxY=minY+distY;
        super.img=Load.uploadPlatform(); 
        super.SCALE=15;
        h=(int)(260/SCALE);
         w=(int)(1000/SCALE);
       yOffset=(int)(350/SCALE);
       xOffset=(int)(0/SCALE);
       subImg=img;
      //System.out.println("height is " + h + " width is " + w );
     
      //hitbox=new Rectangle2D.Float( (float) x, (float) y, (float) w, (float) h);
      hitbox=new Rectangle2D.Float( (float) (x + xOffset), (float) (y + yOffset), w, h);
    }  
//will usualy only move in the x direction
    
    public void reset(int x, int y )
    {
        
        this.x=x;
        this.y=y;
        
        super.hitbox.setRect(  (float) (x + xOffset ), (float) (y + yOffset),w, h); 
        
        
    }        
    
    public void restart()
    {
       this.x=minX;
       this.y=minY;
       super.hitbox.setRect(  (float) (x + xOffset ), (float) (y + yOffset),w, h);
        
    }
    
    
    
    @Override
    public  void updateTick()   //make static?
    {
        if (platformOn)
            
        {    
            tick++;
             if (tick>=tickspeed)
             {
                tick=0;
                if (x<=minX)
                    { movingRight=true;}
                else if (x>=maxX)

                  {movingRight=false;}

                if (movingRight)
                {
                    x++;
                }
                else 
                {
                    x--;
                }

                if (y<=minY)
                    { movingDown=true;}

                else if (y>=maxY)

                  {movingDown=false;}

                if (movingDown)
                {
                    y++;
                }
                else 
                {
                    y--;
                }

              super.hitbox.setRect(  (float) (x + xOffset ), (float) (y + yOffset),w, h); 
                System.out.println("updating");
             }
             
        }      
    
    }
    
     public static  boolean canMovePlatform(int x, int y, int width, int height, Platform p, int[][] leveldata)
  {
      if ( (y<p.getYHitBox()  && y+height>p.getYHitBox()+p.getHHitBox())  && (x<p.getXHitBox()+p.getWHitBox() && x+width>p.getXHitBox()))
      {
          return false;
      }
      return true;
  }
    @Override
   public void doStuff(Player p, GamePanel game)
           
   {
       this.platformOn=game.platformOn;
       game.platform=this; //need to modify for multiple platforms //add into arraylist
   }  //need setter?
    
   @Override
   public  BufferedImage animate()
   { return img;
   };
   
   
}


  /*

if (platformOn)
                        {
                      platform=(Platform)i[0];      
                   i[0].updateTick();

*/


//when update tick instead of changing subimage change x and y 
//try touching based off hitbox 