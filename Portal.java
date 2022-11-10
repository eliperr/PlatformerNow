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
       super.SCALE=2;
       super.row=4;
       super.col=4;
       super.tickspeed=7;
       
       //super.tick=0;
       super.animx=180;
       super.animy=200;
         super.subImg=this.animate(img);
        if (super.subImg==null)
        {
            System.out.println("null");
        }
        else 
        {
            System.out.println("pic");
        }
    }
    
    public void nextLevel(Player p)
    {
        if (isTouching(p))
        {
            System.out.println("next level");
        }
    }
}
