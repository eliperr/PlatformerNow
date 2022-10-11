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
    
}

//
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
//*//

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
    


    