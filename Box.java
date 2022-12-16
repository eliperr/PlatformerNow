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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
public class Box {

    private static Collection<? extends Box> ArrayList(ArrayList<Box> boxes) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public  int width;
    public  int height;
    public  float x, y; //static for now
    private Color color;
    private Rectangle rect;
    public  int wobble =5;
    private float yspeed, yaccel,maxYspeed;
    private float gravity;
    private float newYspeed;
    
    public Box(int width, int height,  float x, float y, Color color)
    {
        this.width=width;
        this.height=height;
        this.x=x;
        this.y=y;
        this.color=color;
        yspeed=0;
        yaccel=0;
        maxYspeed=4;
        gravity=0.3f;
        //rect=new Rectangle (x, y, width, height);
        
        
    }
    
    
    public void drawBox(Graphics g)
    {
        g.setColor(color);
        g.fillRect((int)x,(int)y, width, height);
        
    }
    
    public  void setPosition (float x, float y)
            
    {
        this.x=x;
        this.y=y;
        
    }
    
    public void fall(Platform p)
    {   
        if (!this.onGround((int)x,(int)y,width, height,p, Load.levelData))
            
        {
            newYspeed=yspeed + gravity;
           //System.out.println("newYspeed " + newYspeed);
            
        }
        if (!this.onGround((int)x,(int)y+(int)newYspeed,width, height,p, Load.levelData))
        
        {
            yspeed=newYspeed;
            y=y+(int)yspeed;
            //System.out.println(" y " + y );
            //System.out.println(" yspeed" + yspeed );
            
            
        }
        else
        {
            
            yspeed=0;
        }
        
        
    }        
    
    // need to make work for more than two boxes
    public static boolean overlapBox(Box a, Box b)
            //&& a.y>=b.y)
    {                   
        if ((a.x+a.width>=b.x && a.x<=b.x+ b.width) &&  (a.y<=b.y+b.height && b.y<=a.y+a.height))
        
        {
        //System.out.println("boxes overlap");
        return true;
        }
        
       //System.out.println("boxes do not overlap");
        return false;
                
    }
    //add case for know boxes can never overlap
    public static ArrayList <Box>  overlapBox(Box checkBox, ArrayList <Box> boxes, ArrayList overlaps)
            
    {
        //ArrayList <Box> overlaps=new ArrayList<Box>();
        if (!overlaps.contains(checkBox))
                {overlaps.add(checkBox);}
         //System.out.println ("added " + checkBox); 
        ArrayList <Box> check=boxes;
          //check.remove(checkBox); causes concurent modification exception, but not 100% needed
        //next boxes to check 
        //check.addAll(ArrayList(boxes));
        
        for (Box b: boxes)
        {   //!equating an object 
           if  (b!=checkBox && overlapBox( checkBox, b) && (!overlaps.contains(b)))
               
           {
               
                   
                overlaps.add(b);
                  //System.out.println ("added " + b);
                 //System.exit(0);
             
               
              
               
              overlaps=overlapBox(b, check,overlaps);
              //calls itself to find other boxes overlapping with the overlapped box
               //but not already checked box
               
           }
            
        }
       /* if (overlaps.size()>2)
       
        {    
            for  (int i=0; i<overlaps.size(); i++)
            {
          
              
              System.out.println(overlaps.get(i));
             
            
            }
            System.out.println("end of overlaps");
            //System.exit(0);
        }*/
        //System.exit(0);
        
        //should be faster way to do using Collections but dont have wifi 
        return overlaps;
        
                
        //return checkbox if no overlaps?
       //return false; 
    }
    
    private boolean onGround(int x, int y, int width, int height, Platform platform, int[][] leveldata )
           
  {  
     
        if (x<platform.getXHitBox()+platform.getWHitBox() && x+width>platform.getXHitBox()  && Math.abs(y+height-platform.getYHitBox())<=4)
       { //jump=false;
         //this.setPosition(this.getX(),platform.getYHitBox()-height);
           //System.out.println("on cloud");
           /*System.out.println("player x " + x);
           int res=platform.getXHitBox()+platform.getWHitBox();
            System.out.println("cloud x " + x);*/
            
            
           return true; }  
         
     
    
     int n=1;
      return  Player.isSolid(x,y+height+n,leveldata) || Player.isSolid(x+width, y+height+n, leveldata) || Player.isSolid(x,y+n,leveldata) || Player.isSolid(x+width, y+n, leveldata);
  }
    
    
}



 //not using anymores, box detection in player method 
   /* public void move (Player p)   //use hitbox for all objects ,c hange touching to hitbox for all objects (modify fire, gem)
            //why doesnt offset work for this? want to do without hard coding wobble
    { 
        //player must not be in air-y direction 
        //also have to be able to jump on to box 
        //player cannot move past box <-- WORK on this 
        //box cannot go past walls-need can move here operation 
        //need to reset box position
        
        //use hitbox instead of wobble?!!! YES AND NEED TO CLEAN UP 
        
        //needs to be in correct y plane else break
        /*int res=(int)(p.getY()+p.getHeight()- wobble);
        System.out.println("p.getY()+p.getHeight()" +res);
        System.out.println("this.y" +this.y);*/
        /*
        if ((p.getHitBoxY()+p.getHitBoxHeight())<=this.y ||(int)(p.getY())>=this.y+height)
        {
            return;
        }
        
        //if (p.getX()+p.getWidth()-wobble>=this.x && p.getX()<=this.x && p.getRight() && !p.getJump())
        if (p.getHitBoxX()+p.getHitBoxWidth()>=this.x && p.getHitBoxX()<=this.x && p.getRight() && !p.getJump())
        {
            System.out.println("coming from left");
            //p.setPosition(this.x-p.getWidth() + wobble, p.getY());
             System.out.println ("box x is " + this.x);
            this.x+=(int)p.getXspeed();
        
           
            
        }
        //else if (p.getX()+3*wobble+1<=this.x+this.width && p.getX()>=this.x && p.getLeft() &&!p.getJump())
        
        else if (p.getHitBoxX()<=this.x +this.width && p.getHitBoxX()>=this.x && p.getLeft() && !p.getJump())
        
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
        
        */

    