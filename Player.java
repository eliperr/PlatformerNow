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
    
        private int x;
       private  int y;
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
   private int xspeed=5;
   private int yspeed=5;
   private int xOffset=21;
   private int yOffset=4;
   
   public Player(int x, int y)
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
    
  
   public void setPosition(int x, int y)
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
     /* if (canMoveHere(this.x +xspeed, this.y+speed, w, h, Load.levelData))
      {
          xspeed=speed;
          yspeed=speed;
          
      }
      else
      {
          xspeed=yspeed=0;
      }*/
       //System.out.println (canMoveHere(this.x +xspeed, this.y, w, h, Load.levelData));
      
      if (right && !left && canMoveHere((int)(hitbox.getX()) +xspeed, (int)(hitbox.getY()),26, 30, Load.levelData))
      {
          this.x+=xspeed;
          
      }
      
      if (left && !right && canMoveHere((int)(hitbox.getX()) -xspeed, (int)(hitbox.getY()), 26, 30, Load.levelData))
      {
          
          this.x-=xspeed;
          
      }
      
      if (down&&!up && canMoveHere((int)(hitbox.getX()), (int)(hitbox.getY()) +yspeed, 26, 30, Load.levelData))
      {
          //System.out.println(y);
          this.y+=yspeed;
          ///System.out.println("down");
      }
      
      if (up&&!down&& canMoveHere( (int)(hitbox.getX()), (int)(hitbox.getY()) -yspeed, 26, 30, Load.levelData))
      {
         this.y-=yspeed; 
      }
     
      hitbox.setRect(  (float) (this.x + xOffset), (float) (this.y +yOffset), 26f, 30f);
      
      if (!canMoveHere((int)(hitbox.getX()), (int)(hitbox.getY()) , w, h, Load.levelData))
      {
          System.out.println ("collision");
      }
      
      //int [] result={x, y};
      //return result;
  }
  
  
  private boolean canMoveHere(int x, int y, int width, int height, int[][] leveldata)
          
  {  //check all corners of hitbox
       //what if hits in the center of hitbox???
      return !isSolid(x,y, leveldata) && !isSolid(x+width,y, leveldata) && !isSolid(x,y+height,leveldata) && !isSolid(x+width, y+height, leveldata);
      //can try breaking down into smaller and smaller widths and heights if neeeded? width and height/n loop as increasing n -computationaly intensive thoiguh 
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
  
  
  
  public int getX()
  {
      return x;
  }
  
  public int getY()
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
        
       g.drawImage(img,this.x,this.y, img.getWidth()*(int)(GamePanel.SCALE), img.getHeight()*(int)(GamePanel.SCALE), null);   
        
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



