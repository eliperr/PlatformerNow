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
public abstract class Obstacle {
   protected BufferedImage img;
    protected BufferedImage subImg;
    protected int x;
    protected int y;
   protected int tick;
    protected int xFrame;
    protected  int yFrame;
    protected  int tickspeed;
    protected  int col; //# of columns in spritefile
    protected  int row; //# of rows 
    protected double SCALE;
    protected int animx; //coordinates for animating
    protected int animy;
    protected int wobble=10;
    //later can generalize this if adding other types of obstacles// can extend animatable
    //can add other types later 
   public Obstacle(int x, int  y)
   {
       this.x=x;
       this.y=y;
       this.tick=0;
       this.xFrame=0;
       this.yFrame=0;
       
   }
    
    public void draw(Graphics g, BufferedImage subImg)
    {
        //subImg=img.getSubimage(0,0,120,150);
        //System.out.println(SCALE);
        g.drawImage(subImg ,x,y, (int) (subImg.getWidth()/SCALE), (int) (subImg.getHeight()/SCALE), null);
       
        
    }
    
    public  void updateTick()
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
         BufferedImage sub=img.getSubimage(xFrame*animx, yFrame*animy, animx, animy);
    
      this.subImg=sub;
      return sub;
        
        
    }
    
    public int getWidth()
    {
        return (int) (subImg.getWidth()/SCALE);
        
    }
    
    public int getHeight()
    {
        return (int) (subImg.getHeight()/SCALE);
        
    }
         
    
    public boolean isTouching(Player p)
    {
        //int x=p.getX();
        //int y=p.getY();
        int xc=x+animx;
        int xp=p.getX()+ p.getWidth();
        
        System.out.println ("player is " + p.getX() + " to " + xp);
            System.out.println ("object between " + x + " and " + xc);
        if (p.getX()<x+this.getWidth()-wobble && p.getX()+p.getWidth()-wobble>=x)
        {  
            //&& p.getY()>=y+animy && p.getY()<y
            System.out.println("touching");
            
            return true;
        }
         
        return false;
    }
    
    
    //*public init()
            
   /* { 
      
        Cord[]arr= Load.getFireArray (1); 
        this [] fire = new this[arr.length];
//keep at level one for now 
        for (int i=0; i<arr.length; i++)
        {
         =new Obstacle(170,280);  
            
        }
        
    }*/
    
}
