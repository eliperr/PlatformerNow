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


/**
 * 
 * 
 * need to ask about scaling and collision detection system (if object is too small for hitbox corners)
 * @author eliperr
 */
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
   private float yspeed=4;
   private float xOffset=21;
   private float yOffset=4;
   private float xacceleration=1f;
   private float yacceleration=1f;
   private float maxXspeed=5;  
   private float maxYspeed=5;
   //make float
   //add friction
   //add gracity 
   public Player(float x, float y)
   {
    
      this.playerImg=Load.LoadPlayerImg();
      this.x=x;
      this.y=y;
      subImg=this.animate();
      h=this.getHeight();
      w=this.getWidth();
      System.out.println("height is " + h + " width is " + w );
     
      //hitbox=new Rectangle2D.Float( (float) x, (float) y, (float) w, (float) h);
      hitbox=new Rectangle2D.Float( (float) (x + xOffset), (float) (y + yOffset), 26f, 30f);
      
      //need to make hitbox smaller?
   }
   
   
   public void drawHitbox(Graphics g)
           
   {   g.setColor(Color.BLUE);
       //g.drawRect((int)(hitbox.getX()), (int)(hitbox.getY()-Obstacle.wobble*2), (int)(hitbox.getWidth()-Obstacle.wobble), (int)(hitbox.getHeight()+Obstacle.wobble));
        Graphics2D g2d = (Graphics2D) g;
       g2d.draw(hitbox);
   }
   /* private void uploadImg()
   {
      // InputStream stream=getClass().getResourceAsStream("/bluediamond.png");
       //for some reason this way of getting an image doesn't work regardless of the path 
       
       
       try
       {
        this.img=ImageIO.read(new File("images/player_sprites.png")); 
          //img= ImageIO.read(stream); 
          //System.out.println(img);
       }
       catch (IOException e)
               {
                   System.out.println("error");
                   e.printStackTrace();
                   
               }
       
   }*/
    
  
   public void setPosition(float x, float y)
   {
       this.x=x;
       this.y=y;
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
  
  public  void  setPosition()
  {
   
     float newX=this.x;
      float newY=this.y;
      float newXspeed=xspeed;
      float newYspeed=yspeed;
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
          
          
          //newXspeed=xspeed=0;
      }
      
      if (down&&!up )
      {
          //System.out.println(y);
          yspeed=5;
          newY=this.y+yspeed;
         
          ///System.out.println("down");
      }
      
      if (up&&!down)
      {
          yspeed=5;
        newY=this.y-yspeed;
      }
     
      
      if ( canMoveHere( (int)(newX+xOffset), (int)(newY+yOffset), 26, 30, Load.levelData)) 
          
      {
          this.x=newX;
          this.y=newY;
          xspeed=newXspeed;
          yspeed=newYspeed;
                 
          
      }
      else        //too complicated and doesnt solve the problem, causes more glitcehs 
      { System.out.println ("cant move here");
        /* xspeed=0;
         yspeed=0; */ //if can't move somewhere needs to decelerate into reaches that exact point  but this causes glitches 
         newXspeed=xspeed;
         while (!canMoveHere( (int)(newX+xOffset), (int)(newY+yOffset), 26, 30, Load.levelData)  && xspeed!=0)
                 {
          if (newXspeed<0)       
          {
              newXspeed=newXspeed+xacceleration;
          }
          else if (newXspeed>0)
              
          {
             newXspeed=newXspeed-xacceleration; 
          }
          newX=this.x+newXspeed; 
          if (canMoveHere( (int)(newX+xOffset), (int)(newY+yOffset), 26, 30, Load.levelData))
          {
               this.x=newX;
          this.y=newY;
          xspeed=newXspeed;
          yspeed=newYspeed;
          }
          System.out.println("xspeed " + newXspeed);
          
       }
      
      }
      hitbox.setRect(  (float) (this.x + xOffset), (float) (this.y +yOffset), 26f, 30f);
      
      if (!canMoveHere((int)(hitbox.getX()), (int)(hitbox.getY()) , w, h, Load.levelData))
      {
          System.out.println ("collision");
      }
       //System.out.println("xspeed is "+ xspeed);
       
      System.out.println("on ground?" + onGround((int)(this.x + xOffset), (int)(this.y +yOffset), 26, 30, Load.levelData));
      //int [] result={x, y};
      //return result;
  }

  
 /*public  void  setPosition()
  {
   int newX=this.x;
     int newY=this.y;
      
      if (right && !left)
      {
          
          newX=this.x+xspeed;
         
          
          
      }
      
     else if (left && !right)
      {
          
          newX=this.x-xspeed;
          
          
      }
      else
     {
         xspeed=initSpeed;
          
     }
      
      if (down&&!up)
      {
         
          newY=this.y+yspeed;
          
      }
      
      if (up&&!down)
      {
         newY=this.y-yspeed; 
      }
      
      if (canMoveHere(newX + xOffset,newY+yOffset,26, 30, Load.levelData))
      {
          this.x=newX;
          this.y=newY;
          
         if (right && xspeed+xacceleration<=maxXspeed)
          {
              xspeed+=xacceleration;
              
          }
         System.out.println(xspeed);
              
          
      }
     
      hitbox.setRect(  (float) (this.x + xOffset), (float) (this.y +yOffset), 26f, 30f);
      
      if (!canMoveHere((int)(hitbox.getX()), (int)(hitbox.getY()) , w, h, Load.levelData))
      {
          System.out.println ("collision");
      }
      
      //int [] result={x, y};
      //return result;
  } 
  */
  
  
  
  private boolean canMoveHere(int x, int y, int width, int height, int[][] leveldata)
          
  {  //check all corners of hitbox
       //what if hits in the center of hitbox???
      return !isSolid(x,y, leveldata) && !isSolid(x+width,y, leveldata) && !isSolid(x,y+height,leveldata) && !isSolid(x+width, y+height, leveldata);
      //can try breaking down into smaller and smaller widths and heights if neeeded? width and height/n loop as increasing n -computationaly intensive thoiguh 
  }
  
  private boolean onGround(int x, int y, int width, int height, int[][] leveldata )
           
  {  int res=y+height;
      System.out.print("y+ height:" + res);
     System.out.print("y:" + y);
     int n=3;
      return  isSolid(x,y+height+n,leveldata) || isSolid(x+width, y+height+n, leveldata) || isSolid(x,y+n,leveldata) || isSolid(x+width, y+n, leveldata);
  }
  
  private boolean isSolid(int x, int y, int[][] leveldata)
  
  {  if (x<0 || x>=GamePanel.GAMEWIDTH || y>=GamePanel.GAMEHEIGHT || y<0)
     { 
         /*System.out.println("game dim");
         System.out.println("x is " + x);
         System.out.println(" gamepanel x is " + GamePanel.GAMEWIDTH);
         System.out.println("y is " + y);
         System.out.println(" gamepanel y is " + GamePanel.GAMEHEIGHT);*/
         return true;}
     
    float xIndex=x/GamePanel.TILESIZE;
    float yIndex=y/GamePanel.TILESIZE;
    
    int val=leveldata[(int)xIndex][(int)yIndex];  //can just use Load.leveldata?
    //System.out.println(val);
   if (val>=48 || val<0 || val!=11)
    {
        //System.out.println("solid tile");
        return true;
    
    }     //should include value being more than 48 and less than zero?
                       // if (val>=48 || val<0 || val!=11)
    
    
    return false;
    
  
   
      
      
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
    //System.out.println(tick);
    }
    
    
   public BufferedImage animate()
          
     {
       
    
        //chooseAnimation();
          
       
      
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



