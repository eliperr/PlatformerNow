/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.platformernow;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


/**
 * 
 * 
 * need to ask about scaling and collision detection system (if object is too small for hitbox corners)
 * @author eliperr
 */


//reset jump after pause
//door to next level--> make next level
//elements like buttons and boxes and ordered gems
//check fire detection
//new player sprite?

public class Player  {
    
        private float x;
       private  float y;
        private final int w;
        private final int h;
        private int FPS=60;
       private Dimension size;
        private BufferedImage playerImg, subImg;
        private long animationTime=5;
        private int animationFrame=1;
         private long prevTime=0;
          private boolean left=false;
    private boolean right=false;
    private boolean up=false;
   private boolean down=false;
   private int countUp=0;
   private int ycount=1;
   private int n=5;//number of frames in animation 
   private int tick=0;
   private final int tickspeed=10;
   //private final int speed=5;
   private String direction="idle";
   private Rectangle2D.Float hitbox;
   private float initSpeed=0;
   private float xspeed=4;
   private float yspeed=4f; 
   private float yspeedInit=5; 
   public float xOffset=21;
   public float yOffset=4;
   private float xacceleration=1f;
   private float yacceleration=1f;
   private float maxXspeed=5;  
   private float maxYspeed=5;
   //make float
   //add friction
   //add gracity 
   private boolean jump=false;
   private float gravity=0.15f;
   private boolean movingBox;
   private float hitBoxWidth=26;
   private float hitBoxHeight=30;
   
   public Player(float x, float y)
   {
    
      this.playerImg=Load.LoadPlayerImg();
      this.x=x;
      this.y=y;
      subImg=this.animate();
      h=this.getHeight();
      w=this.getWidth();
      //System.out.println("height is " + h + " width is " + w );
     
      //hitbox=new Rectangle2D.Float( (float) x, (float) y, (float) w, (float) h);
      hitbox=new Rectangle2D.Float( (float) (x + xOffset), (float) (y + yOffset), hitBoxWidth, hitBoxHeight);
      
      //need to make hitbox smaller?
   }
   
  public int getHitBoxY()
  {
      return (int) (y + yOffset);
      
  }      
   public int getHitBoxX()
  {
      return (int) (x + xOffset);
      
  }      
    
   public int getHitBoxWidth()
  {
      return (int)hitBoxWidth;
      
  } 
   
   public int getHitBoxHeight()
  {
      return (int) hitBoxHeight;
      
  } 
  
   public void drawHitbox(Graphics g)
           
   {   g.setColor(Color.BLUE);
       //g.drawRect((int)(hitbox.getX()), (int)(hitbox.getY()-Obstacle.wobble*2), (int)(hitbox.getWidth()-Obstacle.wobble), (int)(hitbox.getHeight()+Obstacle.wobble));
        Graphics2D g2d = (Graphics2D) g;
       g2d.draw(hitbox);
   }
  
    
  
   public void setPosition(float x, float y)
   {
       this.x=x;
       this.y=y;
      // System.out.println ("x is " + x);
         //System.out.println ("y is " + y);
   }
   public boolean getRight()
   {
       return right;
   }
   
    public boolean getLeft()
   {
       return left;
   }
    
    public boolean getUp()
   {
       return up;
   }
     public boolean getJump()
   {
       return jump;
   }
      public float getXspeed()
   {
       return xspeed;
   }
      
     public float getYspeed()
   {
       return yspeed;
   }
  public void Left()
  {
     
     //x-=10;
     left=true;
    //repaint();
   
  }
  
  public void Right()
  {
      //x+=10;
      right=true;
   
  }
  
  public void Up()
  {
     
      //y-=10; 
      up=true;
        //System.out.println(up);

     
      
  }
  
  public void Down()
  {
     //y+=10;
     down=true;
      //System.out.println(down);
     //repaint();
  }
  
  public  void  setPosition(Box[] box)
  {
   
     float newX=this.x;
      float newY=this.y;
      float newXspeed=xspeed;
      float newYspeed=yspeed;
      
      //separating x and y direction movement works best 
      // x direction
      
      movingBox=false;
     
      if (right && !left)
      {
          /*if (xspeed<0)
          {
              xspeed=initSpeed;
          }*/
          
          newX=this.x+xspeed;
           if ( xspeed+xacceleration<=maxXspeed)
          {
              newXspeed=xspeed+xacceleration;
              
          }
          
          
      }
      
      else if (left && !right )
      {
          
         /* if (xspeed>0)
          {
              xspeed=-initSpeed;
          }*/
          
          newX=this.x+xspeed;
          
           if ( xspeed-xacceleration>=-maxXspeed)
          {
              newXspeed=xspeed-xacceleration;
              
          }
          
          
      }
      else
      {  //decellelerate if not moving
          //what if xspeed doesn't quite equal zero 
          
          newX=this.x+xspeed; 
          if (xspeed<0)       
          {
              newXspeed=xspeed+xacceleration;
          }
          else if (xspeed>0)
              
          {
             newXspeed=xspeed-xacceleration; 
          }
          
          
          
      }
      
   
     
      if ( canMoveHere( (int)(newX+xOffset), (int)(this.y+yOffset), 26, 30, box, Load.levelData)  ) 
          
      {
          this.x=newX;
         
          xspeed=newXspeed;
       //maxXspeed=5;
       //System.out.println("NOT slower because box" + maxXspeed);
                 
          
      }
      else if (movingBox)
      {  //if moving a box can't go to desired position but should still accelerate 
          xspeed=newXspeed;
          //maxXspeed=1f;
          //System.out.println("slower because box" + maxXspeed);
      }
      
     
      else        
      { //System.out.println ("cant move here");
      xspeed=0;  //add y=0 as well if want to slide against wall 
      
      }
      hitbox.setRect(  (float) (this.x + xOffset), (float) (this.y +yOffset), hitBoxWidth, hitBoxHeight);
      
      //y direction
      
       if (up && onGround((int)(this.x + xOffset), (int)(this.y +yOffset), (int) hitBoxWidth, (int) hitBoxHeight, box, Load.levelData))
      {
          jump=true;
          yspeed=newYspeed=yspeedInit;
      }
       else if (!up && onGround((int)(this.x + xOffset), (int)(this.y +yOffset), (int) hitBoxWidth, (int) hitBoxHeight, box, Load.levelData))
       {
           
           jump=false;
          
       }
     
       if (jump || !onGround((int)(this.x + xOffset), (int)(this.y +yOffset), (int) hitBoxWidth, (int) hitBoxHeight, box, Load.levelData))
       {
           
           newY=this.y-newYspeed;
           newYspeed=yspeed-gravity;
           
           
           
       }  
       
       if ( canMoveHere( (int)(this.x+xOffset), (int)(newY+yOffset), (int) hitBoxWidth, (int) hitBoxHeight, box, Load.levelData)) 
          
      {
          
          this.y=newY;
          
          yspeed=newYspeed;
                 
          
      }
      else        
      {    
      yspeed=0; 
       }
       
      hitbox.setRect(  (float) (this.x + xOffset), (float) (this.y +yOffset),  hitBoxWidth, hitBoxHeight);
     
  }

 
  
  
  private boolean canMoveHere(int x, int y, int width, int height, Box[] boxes, int[][] leveldata)
          
  {  
      if (canBoxMove(x, y , width, height, boxes, leveldata))
       
              {return moveHelper(x,y, width, height, leveldata);}
      return false;
      //can try breaking down into smaller and smaller widths and heights if neeeded? width and height/n loop as increasing n -computationaly intensive thoiguh 
  }
  
  
  private boolean moveHelper(int x, int y, int width, int height, int[][] leveldata)
  {
      
       return !isSolid(x,y, leveldata) && !isSolid(x+width,y, leveldata) && !isSolid(x,y+height,leveldata) && !isSolid(x+width, y+height, leveldata);
  }       
  
  private boolean onGround(int x, int y, int width, int height, Box[] boxes, int[][] leveldata )
           
  {  
     for (Box box: boxes) 
         
     { 
         if (x<box.x+box.width && x+width>box.x  && y+height==box.y)
       { 
           
           return true; }
     
     }
     int n=1;
      return  isSolid(x,y+height+n,leveldata) || isSolid(x+width, y+height+n, leveldata) || isSolid(x,y+n,leveldata) || isSolid(x+width, y+n, leveldata);
  }
  
  private boolean isSolid(int x, int y, int[][] leveldata)
  
  {  if (x<0 || x>=GamePanel.GAMEWIDTH || y>=GamePanel.GAMEHEIGHT || y<0)
     { 
        
         return true;}
     
    float xIndex=x/GamePanel.TILESIZE;
    float yIndex=y/GamePanel.TILESIZE;
    
    int val=leveldata[(int)xIndex][(int)yIndex];  //can just use Load.leveldata?
    //System.out.println(val);
   if ( val!=11)
    {
        //System.out.println("solid tile");
        return true;
    
    }     //should include value being more than 48 and less than zero?
                       // if (val>=48 || val<0 || val!=11)
    
    
    return false;
    
  
      
  }
  
  private boolean canBoxMove(int x, int y, int width, int height, Box[] boxes, int[][] leveldata)
  {
      
      
      for (Box box: boxes)
      {     
       if ( y+height>box.y  && (x<box.x+box.width && x+width>box.x))  //if running into box 
        {
            
          
          movingBox=true;
          
          ArrayList <Box> overlaps=new ArrayList<Box>();
          if (boxes.length==1)   //if only one block use the less computationally heavy method
                {  overlaps.addAll(Arrays.asList(boxes)); 
                 //System.out.println("using faster way");
                }
          else
          {
           ArrayList <Box> check=new ArrayList<Box>();
         check.addAll(Arrays.asList(boxes));
           overlaps=Box.overlapBox(box,check,overlaps); 
          }//if boxes are running into other boxes move each other
          
          {
           //move ALL boxes, including box that overlaps one player is touching
           
              for (int i=overlaps.size()-1; i>=0; i--)
                  
              {   float value=xspeed/(overlaps.size()*2); //overlap>=1 //this value gets to small to move if there are three boxes
                  
                 int val=(int)value;
                 //get rid of these to say too many boxes are too heavy for one character to move
                      //makes a min speed of boxes
                     if( value<1 && value>0)
                       {
                          val=1;
                        }
                  else if (value<0 && value>-1)
                      {
                          val=-1;
                      }
              
              
                  Box overlapBox=overlaps.get(i);
                  if (moveHelper(overlapBox.x+val,overlapBox.y,overlapBox.width-1,overlapBox.height-1,leveldata))
                  {  
                      
                      
                      //System.out.println("overlap box can mvoe");
                      //System.out.println("overlapsize " + (overlaps.size())*2);
                      
                      //System.out.println("box speed " + val);
                      overlapBox.setPosition(overlapBox.x+val, overlapBox.y);}
                  
             
                 else
              {
               
                  break;}
                  
               }
              
             
              
          
          }  
          return false;  
        }
     
    }  
      
      
      return true;
  }        
          
  
  
 
  public float getX()
  {
      return x;
  }
  
  public float getY()
  {
          //System.out.println(y);
      return y;
  
  }

  
 
  
 public void stopUp()
 {
     up=false;
       //System.out.println(up);
     
 }
 
 public void stopDown()
 {
     down=false;
     //System.out.println(down);
     
 }
 public void stopRight()
 {
     right=false;
     
 }
 public void stopLeft()
 {
    left=false;
     
 }  
    
   /* public void draw(Graphics g, BufferedImage img)
    {
         g.drawImage(playerImg,x,y, img.getWidth()*(int)(GamePanel.SCALE), img.getHeight()*(int)(GamePanel.SCALE), null); 
    }*/
    
    
    
    public void chooseAnimation()
            
    {  String newDirection;
        if (up)
       {
         newDirection="jump";
         n=3;
         ycount=2;
         }
        else if (left || right)
         {
              newDirection="walking";
              n=6;
              ycount=1;
             
          }
          else //idle
          {
              newDirection="idle";
              n=6;
              ycount=0;
        
          }
        
        if (!newDirection.equals(direction))
        {
            direction=newDirection;
            animationFrame=1;
            tick=0;
                   
        }
        
        
    }
    
    
   public void updateTick()
    {
         
       chooseAnimation();
        
        tick++;
         if (tick>=tickspeed)
         {
            tick=0;
            animationFrame++;
            
            if (animationFrame>=n-1)
            {
                animationFrame=0;
            }
         }
    
    }
    
    
   public BufferedImage animate()
          
     {
       
    
     
       
      
       BufferedImage sub=playerImg.getSubimage(animationFrame*64, ycount*40, 64, 40);
    
      this.subImg=sub;
      return sub;
      
      } 
         

    public void draw(Graphics g, BufferedImage img)
    {
        
       g.drawImage(img,(int)(this.x),(int)this.y, img.getWidth()*(int)(GamePanel.SCALE), img.getHeight()*(int)(GamePanel.SCALE), null);   
        
    }
    
    public final int getWidth()
    {
        return subImg.getWidth()*(int)(GamePanel.SCALE);
    }
    
    public final int getHeight()
            
    {
        return subImg.getHeight()*(int)(GamePanel.SCALE) ;
    }
    
}



