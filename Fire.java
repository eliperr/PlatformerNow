/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.platformernow;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;


/**
 *
 * @author eliperr
 */
public class Fire extends Obstacle{
    private static boolean messageSent=false;
    private static JLabel label;
   
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
             GameRunner.restart=false;
         
         }
         
         
     }
     
     public static void deathScreen( GamePanel game)
             
     {   //g.setColor(Color.BLACK);
         //g.drawRect(GamePanel.GAMEWIDTH/2,GamePanel.GAMEHEIGHT/2, 20,20);
         //g.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
        // g.setColor(Color.RED);
         
        if (!messageSent)
         { 
             Sounds.fireDeath();
             //System.out.println("game over");
              label= new JLabel("You died :( press 'r' to restart");
          label.setFont(new java.awt.Font("Arial", Font.ITALIC, 16));
          game.setLayout( new GridBagLayout( ));
          label.setBackground(Color.lightGray);
          label.setOpaque(true);
          label.setForeground(Color.RED);
           //game.setLayout(new FlowLayout());
     //label.setOpaque(true); 
     //label.setVisible(true); 
          game.revalidate();
       game.repaint();
        //game.setComponentZOrder(label,1);
         game.add(label);
         messageSent=true;
        
         }
        
        // g.drawString("You died :( press 'r' to restart",GamePanel.GAMEWIDTH/2,GamePanel.GAMEHEIGHT/2);
         //this is much easier but cant set in front, the label weridly only works of the time
     }
   @Override
     public void draw(Graphics g)
             
     {
         super.draw(g);
         
      /*  if (GameRunner.gameover)
        {
            Fire.deathScreen(g);
        }*/
     }

  public void restart(GamePanel game)
  {
      if (messageSent)
      {game.remove(label);
      }
      
      messageSent=false;
    
   
  }
  public void doStuff(Player p, GamePanel game)
          
  {
      
        //this.updateTick();          //not more efficient because still have to write update tick and aniamte everytime!
             //this.animate();
             this.isDead(p);
              if (GameRunner.gameover)
        {   GameRunner.restart=false;
            Fire.deathScreen(game);
        }
              
              
           
         
  }
  
  
}
