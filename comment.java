/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.platformernow;

import java.awt.Color;
import static com.mycompany.platformernow.GamePanel.randColor;

/**
 *
 * @author eliperr
 */
public class comment {
   
    
    //instances of box
    //hitbox for everything except wobble?
    //clean up code ie commetns and hitbox 
    //new level
    //buttons 
   
}

//
/*
private boolean canMoveHere(int x, int y, int width, int height, Box[] boxes, int[][] leveldata)
          
  {  //check all corners of hitbox
       //what if hits in the center of hitbox???
     
      //check to see if running into boxes
      for (Box box: boxes)
      {    
       if ( y+height>box.y  && (x<box.x+box.width && x+width>box.x))
        {
          //System.out.println("cant go box");
          movingBox=true;
          //System.out.print("can box move?");
          
          if (moveHelper(box.x+ (int) xspeed/2,box.y,box.width-1,box.height-1,leveldata))
          //if (!isSolid(Box.x+(int) xspeed,Box.y, leveldata) && !isSolid(Box.x+(int) xspeed+Box.width,Box.y, leveldata) && !isSolid(Box.x+(int) xspeed,Box.y+Box.height-1,leveldata) && !isSolid(Box.x+(int) xspeed+Box.width, Box.y+Box.height-1, leveldata))
          {box.setPosition(box.x+(int) xspeed/2, box.y);
          
           ArrayList<Box> overlaps=Box.overlapBox(box,boxes);   //if boxes are running into other boxes move each other
              for (int i=0; i<overlaps.size(); i++)
                  
              {
                  Box overlapBox=overlaps.get(i);
                  if (moveHelper(overlapBox.x+(int)xspeed/2,overlapBox.y,overlapBox.width-1,overlapBox.height-1,leveldata))
                  {
                      //System.out.println("overlap box can mvoe");
                      overlapBox.setPosition(overlapBox.x+(int) xspeed/2, overlapBox.y);}
                  
                  else
                  {
                      // System.out.println("overlap box cannot mvoe");
                      box.setPosition(box.x-(int) xspeed/2, box.y); //return moved box back to original position because overlap box cannot move
                      
                  }
                  
              }
          
          }  //try doing box movement here 
          //movement slower when moving box so xspeed/2
           //System.out.print ( "above box?");
         // System.out.println( !(y+height>Box.y));
          //System.exit(0);
          return false;  //continue here , does not work well 
          //stuck inside box but box doesnt move 
          // need to not move here in the first place 
          //especially when landing after jumping 
        }
      }   

////*if (!touching && (x>=GameFrame.xdim || x<=0 ||y>=GameFrame.ydim || y<=0))
//        {
//        
//            System.out.println("color");
//             Color c=randColor();
//             g.setColor(c);
//             g.fillRect(x,y,w,h);
//             repaint();
//                 touching=true;
//                    System.out.println(touching);
//        }
//       else if (x<GameFrame.xdim && x>0 && y<GameFrame.ydim && y>0)
//        {
//            touching=false;
//              g.setColor(Color.BLUE);
//              g.fillRect(x,y,w,h);
//              repaint();
//            System.out.println(touching); 
//        }
//       else
//       {
//          g.setColor(c);
//           g.fillRect(x,y,w,h);
//             repaint();
//           
//       } 
//*/

//if (!touching && (x>=GameFrame.xdim || x<=0 ||y>=GameFrame.ydim || y<=0))
//        {
//        
//            System.out.println("color");
//             //Color c=randColor();
//              
//             // g.setColor(c);
//             g.setColor(Color.RED);
//           
//            
//                 touching=true;
//                    System.out.println(touching);
//        }
//     
//       else if (x<GameFrame.xdim && x>0 && y<GameFrame.ydim && y>0)
//        {
//            touching=false;
//              g.setColor(Color.BLUE);
//             
//            System.out.println(touching); 
//        }
//      
//        g.fillRect(x,y,w,h);
//             repaint(); 

/*
public BufferedImage animate()
          
     {
       
      long now=System.currentTimeMillis();
      
      long time=10000/FPS;
      
      
      
     
      animationTime=now-prevTime;
      
   
      
  
      if ( animationTime>=time)
      {
          
          if (up) //countUp>0 does using this create a better animation or not?
            {
            n=3;
         
            ycount=2;
          //FPS=120;
            }
          else if (left || right)
          {
              ycount=1;
          
              n=6;
              //System.out.println("moving");
              
          }
          else //idle
          {
              n=5;
              
              ycount=0;
              //FPS=60;
          }
          
        animationFrame=(animationFrame+1)%n;
       
      
           prevTime=System.currentTimeMillis();
           
           
                
      }
      
      
       BufferedImage sub=img.getSubimage(animationFrame*64, ycount*40, 64, 40);
    
      
      return sub;
      
       

  /*









         
/*
package com.mycompany.platformernow;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author eliperr
 */

/*
public abstract class Obstacle {
    private static BufferedImage img;
    private BufferedImage subImg;
    private int x, y;
    private static int tick=0;
    private static int xFrame=0;
    private static int yFrame=0;
    private static int tickspeed=5;
    private static int col=8; //# of columns in spritefile
    private static int row=8; //# of rows 
    private final int SCALE=3;
    
    //later can generalize this if adding other types of obstacles// can extend animatable
    //can add other types later 
    public Obstacle(int x, int y)
    {
       this.img=Load.uploadFire();
       this.x=x;
       this.y=y;
        
    }
    
    public void draw(Graphics g, BufferedImage subImg)
    {
        //subImg=img.getSubimage(0,0,120,150);
        g.drawImage(subImg ,x,y, subImg.getWidth()/SCALE, subImg.getHeight()/SCALE, null);
       
        
    }
    
    public static  void updateTick()
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
    
    public static BufferedImage animate()
    {
         BufferedImage sub=img.getSubimage(xFrame*128, yFrame*128, 128, 128);
    
      
      return sub;
        
        
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
    
/*
public  void  setPosition()
  {
   
     float newX=this.x;
      float newY=this.y;
      float newXspeed=xspeed;
      float newYspeed=yspeed;
      if (right && !left && canMoveHere((int)(hitbox.getX() +xspeed), (int)(hitbox.getY()),26, 30, Load.levelData))
      {
          if (xspeed<0)
          {
              xspeed=initSpeed;
          }
          
          this.x+=xspeed;
          
           if ( xspeed+xacceleration<=maxXspeed)
          {
              xspeed+=xacceleration;
              
          }
          
          
      }
      
      else if (left && !right && canMoveHere((int)(hitbox.getX() + xspeed), (int)(hitbox.getY()), 26, 30, Load.levelData))
      {
          
          if (xspeed>0)
          {
              xspeed=-initSpeed;
          }
          
          this.x+=xspeed;
          
           if ( xspeed-xacceleration>=-maxXspeed)
          {
              xspeed-=xacceleration;
              
          }
          
          
      }
      else
      {
          xspeed=0;
      }
      
      if (down&&!up && canMoveHere((int)(hitbox.getX()), (int)(hitbox.getY() +yspeed), 26, 30, Load.levelData))
      {
          //System.out.println(y);
          this.y+=yspeed;
          ///System.out.println("down");
      }
      
      if (up&&!down&& canMoveHere( (int)(hitbox.getX()), (int)(hitbox.getY() -yspeed), 26, 30, Load.levelData))
      {
         this.y-=yspeed; 
      }
     
      hitbox.setRect(  (float) (this.x + xOffset), (float) (this.y +yOffset), 26f, 30f);
      
      if (!canMoveHere((int)(hitbox.getX()), (int)(hitbox.getY()) , w, h, Load.levelData))
      {
          System.out.println ("collision");
      }
       System.out.println(xspeed);
      // System.out.println("on ground?" + onGround((int)(this.x + xOffset), (int)(this.y +yOffset), 26, 30, Load.levelData));
      //int [] result={x, y};
      //return result;
  }
*/


    