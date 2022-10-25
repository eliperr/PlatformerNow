/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.platformernow;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
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
       super.animx=super.animy=128;
        super.subImg=this.animate(img);
      
    }
     
     
     public void isDead(Player p)
             
     {
         if (this.isTouching(p) && !GameRunner.gameover)
         {
             
          /*    g.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
         g.setColor(Color.RED);
         g.drawString("dead",100,100);*/
             System.out.println("dead");
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

}
