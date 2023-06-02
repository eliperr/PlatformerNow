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
import java.util.ArrayList;
public class Platform extends Obstacle{
    
    private int maxX,  maxY, minX, minY;
    private boolean movingRight=true;
      private boolean movingDown=true;
      public static boolean platformOn=false;
     public static int n;
     public int testX=240;
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
       tickspeed=1;
       //tickspeed=5;
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
    
    public void restart(GamePanel game)
    {
       this.x=minX;
       this.y=minY;
       super.hitbox.setRect(  (float) (x + xOffset ), (float) (y + yOffset),w, h);
   
        
    }
    
    public void setSpeed(int i)
    {
        
       this.tickspeed=i;
    }
    
    
    
    @Override
    public  void updateTick()   //make static?
    {
        if (platformOn)
            
        {    
           // System.out.println("tickspeed " + tickspeed);
            //tickspeed=0;
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
                System.out.println("x is " + x );
                testX=x;
               
               
             }
             
        }      
    
    }
    
    public int getX()
    {
        return x;
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
            //kind of annaying but need two instances of platform with this OOP
   {  platformOn=game.platformOn;
  // testX=x;
    System.out.println("dostuff testx" + testX);
     System.out.println("dostuff x" + x);
      System.out.println("dostuff getx" + getX());
    
       
   
   
   //not nessecary to have reference with new way having check onGround method by going through all obstacles
     /* if (game.platform!=null) 
      {
       int numPlatforms=game.platform.size();
  //System.out.println(" start: n " +n);
  // System.out.println("numPlatforms" + numPlatforms);
    //. System.out.println("numplatforms " + numPlatforms);
   
       //maybe someday each platform has its own on button, for now all turned on at once
        
        
     game.platform.set(n,this);
        
       //add platform  reference and then index changes accordingly 
       if (n<numPlatforms-1) //0 for list of 2, otherwise if was 1 start again
      {      
       
            //System.out.println(game.platform);
             
                 // if (!game.platform.contains(this)) //dont know i need to add this, not efficient 
                 //but other ways of doing it make it wobbly when moving
                  //{
     // game.platform.set(n,this);
                 // }
      // System.out.println("end");
     //  game.platform=new ArrayList<Platform>();
         // 
      
     n++;
      
      }
      
      else
      {   
         n=0; 
         
         
      }
       */
      //System.out.println("platform in obstacle list is " + this);
       //System.out.println(game.platform);
      
      //}  //need to modify for multiple platforms //add into arraylist
   }  //need setter?//0 for testing purposes
    
   @Override
   public  BufferedImage animate()
   { return img;
   };
   
   public boolean onGround(int width, int height, int  x, int y)
   {
       
     return (this!=null && x<this.getXHitBox()+this.getWHitBox() && x+width>this.getXHitBox()  && Math.abs(y+height-this.getYHitBox())<=4);
   }
   
    @Override
   public int getXHitBox()
    {  int res=x+xOffset;
        //System.out.println("getXHitBox()" + this.x);
         System.out.println("testX" + testX);
        return this.x+ xOffset;
        
    }
    
    @Override
    public int getYHitBox()
    {
        
        return y + yOffset;
    }
    @Override
    public int getHHitBox()
    {
        return h;
    }
    @Override
    public int getWHitBox()
    {
        return w;
    }
      
   
   
   
}


  /*

if (platformOn)
                        {
                      platform=(Platform)i[0];      
                   i[0].updateTick();

*/


//when update tick instead of changing subimage change x and y 
//try touching based off hitbox 