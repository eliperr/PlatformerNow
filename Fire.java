/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.platformernow;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author eliperr
 */
public class Fire extends Obstacle{
    
    
   
     public Fire(int x, int y)
    {  super(x, y);
       super.img=Load.uploadFire();
       super.SCALE=3;
       super.row=8;
       super.col=8;
       super.tickspeed=5;
       
       //super.tick=0;
      super.animy=128;
       super.animx=128;
       //super.animy=118;
       
        super.subImg=this.animate(img);
         h=(int)(75/SCALE);
       w=(int)(88/SCALE);
       yOffset=(int)(50/SCALE);
       xOffset=(int)(20/SCALE); //5
      //System.out.println("height is " + h + " width is " + w );
     
      //hitbox=new Rectangle2D.Float( (float) x, (float) y, (float) w, (float) h);
      hitbox=new Rectangle2D.Float( (float) (x + xOffset), (float) (y + yOffset), w, h); 
      
    }
     
     
     public void isDead(Player p)
             
     {
         if (this.isTouching(p) && !GameRunner.gameover)
         {
             
          /*    g.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
         g.setColor(Color.RED);
         g.drawString("dead",100,100);*/
             //System.out.println("dead");
             GameRunner.gameover=true;
         
         }
         
         
     }
     
     public static void deathScreen(Graphics g)
             
     {   //g.setColor(Color.BLACK);
         //g.drawRect(GamePanel.GAMEWIDTH/2,GamePanel.GAMEHEIGHT/2, 20,20);
         g.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
         g.setColor(Color.RED);
         g.drawString("You died :( press 'r' to restart",GamePanel.GAMEWIDTH/2,GamePanel.GAMEHEIGHT/2);
         
     }
       @Override
  public void restart()
  {
  }

  
  public void doStuff(Player p, GamePanel game)
          
  {
      
        //this.updateTick();          //not more efficient because still have to write update tick and aniamte everytime!
             //this.animate();
             this.isDead(p);
  }
  
  
}
