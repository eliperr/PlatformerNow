/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.platformernow;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author eliperr
 */
public class Gem  extends Obstacle{
   
  private static int gemsCollected=0;
  private boolean collectedYet;
  //private static BufferedImage subImg;
  private static BufferedImage subImg;
  private static double SCALE=1.5;
  public Gem(int x, int y)
          
  {
      
      super(x,y);
      super.img=Load.uploadGem();
      this.collectedYet=false;
     
     super.tickspeed=10;
    super.col=4; //# of columns in spritefile
    super.row=2; //# of rows 
    super.SCALE=1.5;
    super. animx=40; //coordinates for animating
    super. animy=40;
    super.subImg=this.subImg=this.animate(img);
      
      
      
  }


public void collectGem(Player p)
        
{
    if (this.isTouching(p)&&!collectedYet)
    {
        
        collectedYet=true;
        gemsCollected++;
        
        System.out.print("collected! "+ gemsCollected );
        //System.exit(0);
    }
    
    
}

public boolean getCollectedYet()
{
    return collectedYet;
    
}


public static void resetGems(Gem[]gem)
{
    gemsCollected=0;
    for (Gem shiny:gem)
         { shiny.collectedYet=false;
                                     }
}

public static void drawGemCount(Graphics g)
        
{  
    int x=(int)((double)(GamePanel.GAMEWIDTH)*0.8);
    int y=(int)((double)(GamePanel.GAMEHEIGHT)*0.1);
     g.drawImage(subImg ,x,y, (int) (subImg.getWidth()/SCALE), (int) (subImg.getHeight()/SCALE), null);
     g.setColor(Color.WHITE);
     int hRect=(int)(20/1.5*SCALE);
     int wRect=(int)(30/1.5*SCALE);
     g.fillRect(x + (int) (subImg.getWidth()/SCALE)+(int)(7*SCALE), y+ (int) (subImg.getWidth()/SCALE/3), wRect, hRect);
     g.setColor(Color.BLACK);
     String res=Gem.gemsCollected + " ";
     g.drawString(res, x+(int) (subImg.getWidth()/SCALE)+(int)(7*SCALE) + wRect/2,y+(int) (subImg.getHeight()/SCALE) -hRect/4);
    
    
}





 
         





}