/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.platformernow;

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
      
    }

}
