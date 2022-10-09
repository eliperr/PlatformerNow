/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.platformernow;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


/**
 *
 * @author eliperr
 */
public class Player {
    
        private int x;
       private  int y;
        private int w=100;
        private int h=100;
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
   private final int speed=5;
   private String direction="idle";
   
   public Player(int x, int y)
   {
    
      this.playerImg=Load.LoadPlayerImg();
      this.x=x;
      this.y=y;
  
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
  
  public int[] setPosition(int x, int y)
  {
      if (right && !left)
      {
          x+=speed;
          
      }
      
      if (left && !right)
      {
          
          x-=speed;
          
      }
      
      if (down&&!up)
      {
          //System.out.println(y);
          y+=speed;
          ///System.out.println("down");
      }
      
      if (up&&!down)
      {
         y-=speed; 
      }
     
      int [] result={x, y};
      return result;
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
    
    public void drawPlayer(Graphics g)
    {
         g.drawImage(playerImg,x,y, playerImg.getWidth()*(int)(GamePanel.SCALE), playerImg.getHeight()*(int)(GamePanel.SCALE), null); 
    }
    
    
    
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
    
      
      return sub;
      
      } 
         

    public void draw(Graphics g, BufferedImage img, int x, int y)
    {
        
       g.drawImage(img,x,y, img.getWidth()*(int)(GamePanel.SCALE), img.getHeight()*(int)(GamePanel.SCALE), null);   
        
    }
    
    
    
}



