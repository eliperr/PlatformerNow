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
public class Obstacle {
    private BufferedImage fireImg, subImg;
    private int x, y;
    private int tick=0;
    private int xFrame=0;
    private int yFrame=0;
    private int tickspeed=5;
    private int col=8; //# of columns in spritefile
    private int row=8; //# of rows 
    private final int SCALE=3;
    
    //later can generalize this if adding other types of obstacles// can extend animatable
    //can add other types later 
    public Obstacle(int x, int y)
    {
       this.fireImg=Load.uploadFire();
       this.x=x;
       this.y=y;
        
    }
    
    public void draw(Graphics g, BufferedImage subImg)
    {
        //subImg=fireImg.getSubimage(0,0,120,150);
        g.drawImage(subImg ,x,y, subImg.getWidth()/SCALE, subImg.getHeight()/SCALE, null);
       
        
    }
    
    public void updateTick()
    {
        
        tick++;
         if (tick>=tickspeed)
         {
            tick=0;
            xFrame++;
           
            
             if (yFrame>=row-1 && xFrame>=col)
                {
               
                yFrame=0;
                
                }
            
            
            if (xFrame>=col)
            {
                
                
                xFrame=0;
                yFrame++;
               
                
            }
            
             
               
                //System.out.println("Xframe" + xFrame + "yframe" + yFrame);
           
            
         }
    
    }
    
    public BufferedImage animate()
    {
         BufferedImage sub=fireImg.getSubimage(xFrame*128, yFrame*128, 128, 128);
    
      
      return sub;
        
        
    }
    
}
