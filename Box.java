/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.platformernow;

/**
 *
 * @author eliperr
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
public class Box {
    
    public static  int width;
    public static int height;
    public static  int x, y; //static for now
    private Color color;
    private Rectangle rect;
    public static int wobble =5;
    
    public Box(int width, int height,  int x, int y, Color color)
    {
        this.width=width;
        this.height=height;
        this.x=x;
        this.y=y;
        this.color=color;
        //rect=new Rectangle (x, y, width, height);
        
        
    }
    
    
    public void drawBox(Graphics g)
    {
        g.setColor(color);
        g.fillRect(x,y, width, height);
        
    }
    
    public void setPosition (int x, int y)
            
    {
        this.x=x;
        this.y=y;
        
    }
    
    public void move (Player p)   //use hitbox for all objects ,c hange touching to hitbox for all objects (modify fire, gem)
            //why doesnt offset work for this? want to do without hard coding wobble
    { 
        //player must not be in air-y direction 
        //also have to be able to jump on to box 
        //player cannot move past box <-- WORK on this 
        //box cannot go past walls-need can move here operation 
        //need to reset box position
        
        //use hitbox instead of wobble?!!! YES AND NEED TO CLEAN UP 
        
        //needs to be in correct y plane else break
        int res=(int)(p.getY()+p.getHeight()- wobble);
        System.out.println("p.getY()+p.getHeight()" +res);
        System.out.println("this.y" +this.y);
        if ((p.getHitBoxY()+p.getHitBoxHeight())<=this.y ||(int)(p.getY())>=this.y+height)
        {
            return;
        }
        
        
        if (p.getX()+p.getWidth()-wobble>=this.x && p.getX()<=this.x && p.getRight() && !p.getJump())
        {
            System.out.println("coming from left");
            //p.setPosition(this.x-p.getWidth() + wobble, p.getY());
             System.out.println ("box x is " + this.x);
            this.x+=(int)p.getXspeed();
        
           
            
        }
        else if (p.getX()+3*wobble+1<=this.x+this.width && p.getX()>=this.x && p.getLeft() &&!p.getJump())
        {
            System.out.println("coming from right");
             //p.setPosition(this.x+this.width-3*wobble,p.getY());
              //int res=this.x + this.width;
                //System.out.println ("box x is " + res);
             
            this.x+=(int)p.getXspeed();
            
          
           
        }
        
        
        
        else 
        {
            //System.out.println("not touching");
        }
        
        
    }
    
    
    
}
