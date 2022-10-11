/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.platformernow;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author eliperr
 */
public class Gem  extends Obstacle{
   

  public Gem(int x, int y)
          
  {
      
      super(x,y);
      super.img=Load.uploadGem();
      
     
     super.tickspeed=10;
    super.col=4; //# of columns in spritefile
    super.row=2; //# of rows 
    super.SCALE=1.5;
    super. animx=40; //coordinates for animating
    super. animy=40;
      
      
      
      
  }












}