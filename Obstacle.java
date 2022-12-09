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
    public static final int wobble=15; //15
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
        
        if (subImg!=null)
        {
         
           g.drawImage(subImg ,x,y, (int) (subImg.getWidth()/SCALE), (int) (subImg.getHeight()/SCALE), null); 
           
           
        }
        
        
       else
        {
            System.out.println(this.getClass().getName() + " no img");
            
        }
        if (this.getClass().getName().contains("Button"))
        {
            
                        System.out.println("drawing button");
                        int w=(int) (subImg.getWidth()/SCALE);
                        System.out.println("subImg.getWidth()/SCALE " + w);

        }
        
        
        
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
    
    public BufferedImage animate(BufferedImage b)
    {
         BufferedImage sub=b.getSubimage(xFrame*animx, yFrame*animy, animx, animy);
    
      
      return sub;
        
        
    }
   /* 
    public int getWidth()
    {
        return (int) (subImg.getWidth()/SCALE);
        
    }
    
    public int getHeight()
    {
        return (int) (subImg.getHeight()/SCALE);
        
    }
         
    /*
   /* public boolean isTouching(Player p)
    {
        //int x=p.getX();
        //int y=p.getY();
       
      //x test 
      /*   int xc=x+this.getWidth()-wobble;
        int xp=p.getX()+ p.getWidth()-wobble;
        
        System.out.println ("player is " + p.getX() + " to " + xp);
            System.out.println ("object between " + x + " and " + xc);
      if (p.getX()<x+this.getWidth()-wobble && p.getX()+p.getWidth()-wobble>=x)
        {  
            //&& p.getY()>=y+animy && p.getY()<y
            System.out.println("touching");
            
            return true;
        }*/
        
   /*   
      //y test
      int yc=x+this.getHeight()-wobble;
        int yp=p.getY()+ p.getHeight()-wobble;
        
        System.out.println ("player is " + p.getY() + " to " + yp);
            System.out.println ("object between " + y + " and " + yc);
          
      if (p.getY()+p.getHeight()>y+this.getHeight()-wobble && p.getY()-wobble*2<=y )
      {
          System.out.println("touching");
           
          return true;
      }
      
      
        return false;
    }
    */
    
    
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
      //tests
      /*
          int yc=x+this.getHeight()-wobble;
        int yp=p.getY()+ p.getHeight()-wobble;
      
        System.out.println ("player Y is " + p.getY() + " to " + yp);
            System.out.println ("object Y between " + y + " and " + yc);
          
            
         int xc=x+this.getWidth()-wobble;
        int xp=p.getX()+ p.getWidth()-wobble;
        
        System.out.println ("player X is " + p.getX() + " to " + xp);
            System.out.println ("object X between " + x + " and " + xc);
       
         */
      //if (p.getX()<x+this.getWidth()-wobble && p.getX()+p.getWidth()-wobble>=x && p.getY()+p.getHeight()>y+this.getHeight()-wobble && p.getY()-wobble*2<=y)
      
      if (p.getX()<x+this.getWidth()-wobble && p.getX()+p.getWidth()-wobble>=x && p.getY()+p.getHeight()>y+this.getHeight()-wobble && p.getY()-wobble*2<=y)
        {  
            //&& p.getY()>=y+animy && p.getY()<y
            //System.out.println("touching");
            
            return true;
        }
      
      return false;
      
  }
    
    
    
    
}
