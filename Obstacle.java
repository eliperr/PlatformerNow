/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.platformernow;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author eliperr
 */


public abstract class Obstacle {
    protected boolean drawable=true;
    protected boolean restartable=false;
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
    protected Rectangle2D.Float hitbox;
      protected int h, w;
      protected  int yOffset;
       protected  int xOffset;
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
       //hitbox=new Rectangle2D.Float( (float) (x + xOffset), (float) (y + yOffset), w,h);
       
   }
   
   public boolean isDrawable()
   {
       return drawable;
   }
   public boolean isRestartable()
   {
       return restartable;
   }
   
   
   public BufferedImage getSubImg()
  {
           
      return subImg;
      
           }
   
    public void setSubImg(BufferedImage sub)
  {
           
      subImg=sub;
      
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
       /* if (this.getClass().getName().contains("Button"))
        {
            
                        System.out.println("drawing button");
                        int w=(int) (subImg.getWidth()/SCALE);
                        System.out.println("subImg.getWidth()/SCALE " + w);

        }*/
        
        
        
    }
    
     
    public void draw(Graphics g)
    {
        //subImg=img.getSubimage(0,0,120,150);
        //System.out.println(SCALE);
        
        if (this.subImg!=null)
        {
         
           g.drawImage(subImg ,x,y, (int) (subImg.getWidth()/SCALE), (int) (subImg.getHeight()/SCALE), null); 
           
           
        }
        
        
       else
        {
            System.out.println(this.getClass().getName() + " no img");
            
        }
       /* if (this.getClass().getName().contains("Button"))
        {
            
                        System.out.println("drawing button");
                        int w=(int) (subImg.getWidth()/SCALE);
                        System.out.println("subImg.getWidth()/SCALE " + w);

        }*/
        
        
        
    }
    public abstract void restart();
    
    
    
    
    public  void updateTick()
    {
        
        tick++;
         if (tick>=tickspeed)
         {
            tick=0;
            xFrame++;
           
            
             if (yFrame>=row-1 && xFrame>=col-1)
                {
               
                yFrame=0;
                
                }
            
            
            if (xFrame>=col-1)
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
      if (this.subImg==null )
     
      {
          System.out.println(this.getClass().getName() + " no subimg");
      }
      
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
 /* public boolean isTouching(Player p)
          
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
      
     /* if (p.getX()<x+this.getWidth()-wobble && p.getX()+p.getWidth()-wobble>=x && p.getY()+p.getHeight()>y+this.getHeight()-wobble && p.getY()-wobble*2<=y)
        {  
            //&& p.getY()>=y+animy && p.getY()<y
            //System.out.println("touching");
            
            return true;
        }
      
      return false;
      
  }*/
    
  public boolean isTouching(Player p)
          
  {
      //tests
     
      //if (p.getX()<x+this.getWidth()-wobble && p.getX()+p.getWidth()-wobble>=x && p.getY()+p.getHeight()>y+this.getHeight()-wobble && p.getY()-wobble*2<=y)
      //(y<p.getYHitBox()  && y+height>p.getYHitBox()+p.getHHitBox())  && (x<p.getXHitBox()+p.getWHitBox() && x+width>p.getXHitBox())
      if (p.getHitBoxX()<this.getXHitBox()+this.getWHitBox() && p.getHitBoxX()+p.getHitBoxWidth()>=this.getXHitBox() && p.getHitBoxY()+p.getHitBoxHeight()>this.getYHitBox() && p.getHitBoxY()<=this.getYHitBox()+this.getHHitBox())
        {  
            
            /* System.out.println("p.getX()" + p.getX());
             System.out.println("x offset" + this.xOffset);
      int res=this.getXHitBox()+this.getWHitBox();
      System.out.println("getXHitBox()+this.getWHitBox() " + res);
        System.out.println("getXHitBox()" + this.getXHitBox());
         System.out.println("this.getWHitBox()" + this.getWHitBox());*/
            
            //&& p.getY()>=y+animy && p.getY()<y
            //System.out.println("touching");
            
            return true;
        }
      
      return false;
      
  }

  
  
  
  public void drawHitbox(Graphics g)
           
   {   //System.out.println("trying to draw");
       
       g.setColor(Color.RED);
       //g.drawRect((int)(hitbox.getX()), (int)(hitbox.getY()-Obstacle.wobble*2), (int)(hitbox.getWidth()-Obstacle.wobble), (int)(hitbox.getHeight()+Obstacle.wobble));
        Graphics2D g2d = (Graphics2D) g;
       g2d.draw(hitbox);
        /*System.out.println( "XH is " + this.getXHitBox() );
         System.out.println( "HH is " + this.getHHitBox() );
        System.out.println( "WH is " + this.getWHitBox() );
        System.out.println( "XH is " + this.getXHitBox() );
         System.out.println( "YH is " + this.getYHitBox() );
          System.out.println( "HH is " + hitbox );*/
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
      
  
  
    
    
}
