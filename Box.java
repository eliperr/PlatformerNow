/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.platformernow;

/**
 *
 * @author eliperr
 */
import static com.mycompany.platformernow.Player.isSolid;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
public class Box extends Obstacle{
//try to extend Obstacle but too different and neeed too many references 
    private static Collection<? extends Box> ArrayList(ArrayList<Box> boxes) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public  int width;
    public  int height;
    public  int x, y; 
   public Color color;
    private Rectangle rect;
    public  int wobble =5;
    private float yspeed, yaccel,maxYspeed;
    private float gravity;
    private float newYspeed;
    private int boxIndex=0; //make x y floats again
    private double counter=0;
    private Obstacle o;
    public Box(int width, int height,  int x, int y, Color color)
    {   super(x,y);
    this.x=x;
    this.y=y;
    //System.out.println(" startign x " + super.x );
        //System.out.println("y " + y );
        this.width=width;
        this.height=height;
       // this.x=x;
       // this.y=y;
        
        this.color=color;
        yspeed=0;
        yaccel=0;
        maxYspeed=4;
        gravity=0.3f;
        //rect=new Rectangle (x, y, width, height);
        
        
    }
    public String getColor()
    { return color.toString();
    }
    //@Override
    public void restart(GamePanel game)
    {   game.box=Load.initBoxes();
         boxIndex=0;
    }
    //@Override
    public void draw(Graphics g)
    {
        g.setColor(color);
        g.fillRect(x,y, width, height);
        //System.out.println("x " + x );
        //System.out.println("y " + y );
    }
   // @Override
    //TOOOOOOOOOO complicated for no gain
    public void doStuff(Player p, GamePanel game)
    {  
      //  System.out.println(boxIndex);
        fall(game.platform, game.box);
       //game.box.set(boxIndex,this);
       
       /*   int find=0;
       // enneficient to do it is this way
      for (int i=0;i<game.ObstacleList.size();i++)
      {
          if (game.ObstacleList.get(i) instanceof Box)
          { 
              //System.out.println("got here");
              // System.exit(0);
             find =i; 
             i= game.ObstacleList.size();
          }
         
      }  int result=find+boxIndex;
        game.ObstacleList.set(result, this);
       
       System.out.println("find " + result);
        if (boxIndex<game.box.size()-1)
        {
            boxIndex++;
            
        }
        else
        {
             boxIndex=0;
             
        }
     */
      
    }
    
    
    @Override 
   //
       public void updateTick()
       {
          
           
       }
       
    @Override 
   
       public void drawHitbox(Graphics g)
       { 
       };    
       
       
        
    @Override 
   
       public BufferedImage animate()
       { return null;
       }; 
       
    public  void setPosition (int x, int y)
            
    {
     super.x=this.x=x;
       super.y=this.y=y;
        
    }
    
    public int getTop()
    {
        
        return this.y;
    }
    public void fall(ArrayList<Platform> p, ArrayList<Box> b)
    {      //should accelertate?
        //System.out.println("falling");
        if (!this.onGround((int)x,(int)y,width, height,p,b, Load.levelData))
            
        {
            newYspeed=yspeed + gravity;
           //System.out.println("newYspeed " + newYspeed);
            
        }
        if (!this.onGround((int)x,(int)y+(int)newYspeed,width, height-1,p, b, Load.levelData)) //-1 so exactly on ground with no gaps
        
        {
            yspeed=newYspeed;
            y=y+(int)yspeed;
            //System.out.println(" y " + y );
            //System.out.println(" yspeed" + yspeed );
            
            
        }
        else
        {   
            
            yspeed=0; //slow down until exactly on ground
            if (this.o!=null)
            {followObstacle(this.o);
                
            }
        }
        
        
    }        
    
    // need to make work for more than two boxes
    public static boolean overlapBox(Box a, Box b)
            //&& a.y>=b.y)
           
    {     
 if ((a.x+a.width>=b.x && a.x<=b.x+ b.width) &&  (a.y+a.height>b.y  && b.y+b.height>=a.y))
//if ((a.x+a.width>=b.x && a.x<=b.x+ b.width) &&  (a.y+a.height>=b.y +b.height && b.y<=a.y))  //add or on ground to last statement             
        //if ((a.x+a.width>=b.x && a.x<=b.x+ b.width) &&  (a.y<b.y +b.height && b.y<a.y + a.height))
        //if ((a.x+a.width>=b.x && a.x<=b.x+ b.width) &&  (a.y<=b.y +b.height && b.y<=a.y + a.height))
        {  //if a below  or same level as b
        //System.out.println("boxes overlap");
        return true;
        }
        
       //System.out.println("boxes do not overlap");
        return false;
                
    }
    
     public static boolean groundBox(Box a, Box b)
            //&& a.y>=b.y)
    {    
         //System.out.println("gets here");
         //if ((a.x+a.width>=b.x && a.x<=b.x+ b.width) &&  (a.y<=b.y +b.height && b.y<=a.y + a.height))              
        if (a.x<b.x+b.width && a.x+a.width>b.x && Math.abs(a.y+a.height-b.y)<=4 )
        //x<p.getXHitBox()+p.getWHitBox() && x+width>p.getXHitBox() 
            
            //x<p.getXHitBox()+p.getWHitBox() && x+width>p.getXHitBox()  && Math.abs(y+height-p.getYHitBox())<=4
        {
        //System.out.println("boxes on top of box");
        return true;
        }
        
       //System.out.println("boxes do not overlap");
        return false;
                
    }
     //need to check if box is on grouhd on the box just like overlap box but only on ground -put in onground check to see if falling
    
    
     
     public static boolean groundBox(Box checkBox, ArrayList <Box> boxes)
            
    {
         
      
        
        for (Box b: boxes)
        {   
           if  (b!=checkBox && groundBox( checkBox, b) ) //&& (!overlaps.contains(b)) //checbox canmoxeinX direction
               
           {
               
                   
              //  overlaps.add(b);
                  //System.out.println ("added " + b);
                 //System.exit(0);
              //ensure all the way on ground:
                              checkBox.setPosition(checkBox.x, b.y-checkBox.height);
               return true;
              
               
             // overlaps=overlapBox(b, check,overlaps);
              //calls itself to find other boxes overlapping with the overlapped box
               //but not already checked box
               
           }
            
        }
       
       // return overlaps;
        return false;
                
        //return checkbox if no overlaps?
       //return false; 
    }
     
    public static ArrayList <Box>  overlapBox(Box checkBox, ArrayList <Box> boxes, ArrayList overlaps, int[][]leveldata)
            
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
                 
                 //canBoxMoveX (Box checkBox, ArrayList <Box> boxes, int[][] leveldata)
                   overlaps.add(b);
                  //System.out.println("adding");
                 
                /* else
                 {   overlaps.clear();
                   // overlaps.add(checkBox);  //just say ccannot move
                    
                     //System.out.println("cannot move x" + checkBox.color);
                     
                       
       
          
            for  (int i=0; i<overlaps.size(); i++)
            {
          
              
              System.out.println(overlaps.get(i));
             
            
            }
            System.out.println("yellow?");
            //System.exit(0);
            return overlaps;
                     
                     
                     
                 }*/
                  //System.out.println ("added " + b);
                 //System.exit(0);
             
               
              
               
              overlaps=overlapBox(b, check,overlaps,leveldata);
              //calls itself to find other boxes overlapping with the overlapped box
               //but not already checked box
               
           }
            
        }
     
        return overlaps;
        
                
       
    } 
     
    
    
    
    
    
    
    
    
    
    
    public static boolean  canBoxMoveX (Box checkBox, ArrayList <Box> boxes, int value, int[][] leveldata)
            
    {
        int maxX=0;
       
        Box maxBox=checkBox;
        
        
         for (Box a: boxes)
         { 
             
             
            if ( a.y==checkBox.y ) 
            {    
                 
                    
                    
                if (Math.abs((a.x+a.width)-(checkBox.x+checkBox.width))>maxX)
                {
                    maxX=Math.abs((a.x+a.width)-(checkBox.x+checkBox.width)); //find box farthest away from checkbox, absolute value to find in left or right direction 
                    
                    maxBox=a;  
                   
                }
                
            }
            
            
         }
            
         
            if (Player.moveHelper((int)(maxBox.x+value),(int)(maxBox.y),(int)(maxBox.width-1),(int)(maxBox.height-1),leveldata)  )        //need tro try other valeus besides 1
             
                    {
                        
                      
                                
                        return true;
                    }
       
            return false;
       
    } 
     
    
  
    
    
    
  
    
    
    
    
    
    //is box on ground--?--> fall
    private boolean onGround(int x, int y, int width, int height, ArrayList<Platform> platform, ArrayList<Box> box, int[][] leveldata )
           
  {    
                
                
                for (Obstacle o:GamePanel.ObstacleList)
              {   
                  if (o.onGround(width, height ,x ,y ) )
                      
                  { this.o=o; //helping box to follow obstacle (usually staying on platform)

                      
                      return true;    }
                  
                  this.o=null; 
                   //if (this.color==Color.red)
                   //{System.out.println ("set to null");}
                  }
              
                    
                    
            
      
         if (groundBox (this, box))
                            { //System.out.println("gets hre 2");
                                return true;
                                
                               
                            }
     int n=0;
      return  Player.isSolid(x,y+height+n,leveldata) || Player.isSolid(x+width, y+height+n, leveldata) || Player.isSolid(x,y+n,leveldata) || Player.isSolid(x+width, y+n, leveldata);
  }
    
    //is player on ground (standing on box)
    public boolean onGround(int x, int y, int width, int height)
    
    {
        return (x<this.x+this.width && x+width>this.x  && y+height==this.y);
        
    }
    
    private void followObstacle(Obstacle o)
    { //box should stay on platform and follow its movements 
        if (o.isMovingRight())
        {
            this.x++;
            /* this.x=x+(int)counter;
            if (counter==0)
            {
                counter=1;
            }
            else
            {
                counter=0;
            }*/
           
        }
        else
        {
            
            this.x--;
        }
        //what if not moving? if platform on
        /*  System.out.println(" counter " + counter);
        System.out.println(" box x " + x);
        System.out.println("platform x " + o.getXHitBox());
        int  res=o.getXHitBox()+o.getWidth();
        System.out.println("platform x + w" + res);
        //this.x=o.getXHitBox()+(o.getWidth()/2);*/
        
       //this.x=this.x/o.getXHitBox();
        
        
        
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

    