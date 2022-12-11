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
      private Rectangle2D.Float hitbox;
      private int h, w;
      private final int yOffset;
       private final int xOffset; //need to work on XOffset and why this doesnt work when platform moving 
    public Platform (int x, int y, int distX, int distY)
    {
        super(x, y);
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
      //System.out.println("height is " + h + " width is " + w );
     
      //hitbox=new Rectangle2D.Float( (float) x, (float) y, (float) w, (float) h);
      hitbox=new Rectangle2D.Float( (float) (x + xOffset), (float) (y + yOffset), w,h);
    }  //will usualy only move in the x direction
    
    
    public BufferedImage getImg()
    
    {
    return super.img;
    }
    
    public void drawHitbox(Graphics g)
           
   {   g.setColor(Color.RED);
       //g.drawRect((int)(hitbox.getX()), (int)(hitbox.getY()-Obstacle.wobble*2), (int)(hitbox.getWidth()-Obstacle.wobble), (int)(hitbox.getHeight()+Obstacle.wobble));
        Graphics2D g2d = (Graphics2D) g;
       g2d.draw(hitbox);
   }
  
    public int getXHitBox()
    {
        
        return x+ xOffset;
    }
    
    public int getYHitBox()
    {
        
        return y + yOffset;
    }
    public int getHHitBox()
    {
        return h;
    }
    public int getWHitBox()
    {
        return w;
    }
    
    
    @Override
    public  void updateTick()
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
           
           hitbox.setRect(  (float) (this.x + xOffset ), (float) (this.y + yOffset), w, h); 
            
         }
    
    }
    
    
    
    
    
}


  

//when update tick instead of changing subimage change x and y 
//try touching based off hitbox 