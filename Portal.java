package com.mycompany.platformernow;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author eliperr
 */

import java.awt.Graphics;
import java.awt.image.BufferedImage;
public class Portal extends Obstacle{
    
    
  //private static BufferedImage subImg;
 // private static BufferedImage subImg;
 
    public Portal(int x, int y)
    {
        
        super(x, y);
       super.img=Load.uploadPortal();
       super.SCALE=3;
       super.row=6;
       super.col=5;
       super.tickspeed=7;
       
       //super.tick=0;
       super.animx=190;
       super.animy=190;
         super.subImg=this.animate(img);
       
    }
    
    public void nextLevel(Player p)
    {
        if (isTouching(p))
        {
            System.out.println("next level");
            System.exit(0);
        }
    }
}
