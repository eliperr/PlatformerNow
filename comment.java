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
      
         
         
     } */