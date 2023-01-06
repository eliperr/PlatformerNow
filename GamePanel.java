/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.platformernow;


/**
 *
 * @author eliperr
 */


//dont need to keep images in  here, keep in object
//array of Obstacles 
//other more efficient wayS?
//boxes for buttons that only turn on temporarily 
//more general button, button that makes blue box appear -add event to button to be specificied later 
//make it harder to jump as wide --shouldn't be able to accelerate x while in air!
//comments
//wait  before collecting gem and before moving forward with portal 
//less wobble in cloud
//new levels!

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
 import java.io.InputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;
public class GamePanel extends JPanel{
    
        private int x=100;
       private  int y=100;   //dont need these anymore. remove
        private int w=100;
        private int h=100;
        //private int FPS=60;
        private boolean touching;
        private Color c=Color.blue;
       private int xdim, ydim;
        private Dimension size;
        private BufferedImage playerImg;
        private long animationTime=5;
        private int animationFrame=1;
         private long prevTime=0;
          private boolean left=false;
    private boolean right=false;
    private boolean up=false;
   private boolean down=false;
   private int countUp=0;
   private int ycount=1;
   private int n=5;//number of frames in animation 
   private int tick=5;//speed of animation
   //private BufferedImage gameImg,fireImg, gemImg, portalImg, buttonImg;
   public Player player;
   public Fire fire[];
   public Gem gem[];
   public Portal portal;
   public Box box[];
   public Button button;
   public Platform platform;
   public ArrayList<Obstacle[]>ObstacleList;
   
    public final static int TILESIZE=32;
  public final static float SCALE=1f; ///This cannot be scaled easily 
  public final static int TILESINWIDTH=26;
  public final static int TILESINHEIGHT=14;
  public final static int SCALEDTILESIZE=(int) (TILESIZE*SCALE);
  public final static int GAMEWIDTH=SCALEDTILESIZE*TILESINWIDTH;
   public final static int GAMEHEIGHT=SCALEDTILESIZE*TILESINHEIGHT;
   
    
    public GamePanel()
    {    //init
        ObstacleList=Load.levelOne();
         this.player= new Player(100f,285f); 
         this.fire=Load.initFires();
         this.gem=Load.initGems();              //should make an array of Obstacles?
         this.box=Load.initBoxes();
         this.portal=new Portal(735,275); 
         this.button=new Button (160,190);
         this.platform=new Platform (240, 180, 90, 0);
         playerImg= player.animate();
         
        //keep updating image to draw as you animate player
         //this.gameImg=Load.LoadGameImg();
         //System.out.println("player height is " + player.getHeight() );
         
        
          //this.gemImg=Load.uploadGem();
       addKeyListener(new keyboardInputs(this));
    size=new Dimension (GAMEWIDTH,GAMEHEIGHT);
     
       setPreferredSize(size);
       
       
         
         
    }
    
    public void restart()
            
    {   GameRunner.gameover=false;
    player.setPosition(100f,285f);
    for ( int b=0; b<box.length; b++)
    {
        
        box[b]=Load.initBoxes()[b];
        //box[b].setPosition(Load.initBoxes()[b].x,Load.initBoxes()[b].y);
    }
     
        for (Obstacle[] i:ObstacleList)
        {
            if (i[0].isRestartable())
                    { System.out.println( "restart" + i[0].getClass());
                        for (Obstacle o:i)
                        { o.restart();}
                        
                        
                    }
            
        }
       /*Gem.resetGems(gem);
        button.restart();
         platform.reset(240, 180);*/
        
       
       
       //will change to depend on level later 
       //will restart the level you are on
    }
    
   
    
    public void update()
 {   //pattern: updatetick and animate all except box and button (button only animate
     playerImg= player.animate(); 
      player.updateTick();
      player.setPosition(box,platform);
      boolean platformOn=false;
     //x=player.setPosition(x,y)[0];
     //y=player.setPosition(x,y)[1];
      for (Obstacle[] i:ObstacleList)
      {
          if (!(i[0] instanceof Button) && !(i[0] instanceof Platform)) //not button, platform
          {
             i[0].updateTick();
             i[0].animate();
              for (Obstacle o:i)
                     {
                         //System.out.println("set fire" + o);
                        o.setSubImg(i[0].getSubImg());
                    }
          }
              if (i[0] instanceof Fire)
             {   Fire[] fire=(Fire[])i;
                for (Fire f:fire)
                    { f.isDead(player);  }
                }
              
              
              else if (i[0] instanceof Gem)
             {   Gem[] gem=(Gem[])i;
                //System.out.println("gem");
                for (Gem g:gem)
                    { g.collectGem(player);  }
                }
              
              else if (i[0] instanceof Button)
              {
                  
                
                  Button[] button=(Button[])i;  //only one button now but will work with multiple buttons
                  
                  for (Button b:button)
                    { b.animate();  
                     b.run(player);
                     if (b.turnedOn())
                     {
                        box[1]=Load.newBoxesToAdd()[0]; 
                         
                     }
                     if (b.isOn())
                     {
                         platformOn=true;
                       System.out.println("platform on");
                     }
                     
                    }
                }
              
              else if (i[0] instanceof Platform)
              { 
                    if (platformOn)
                        {
                      platform=(Platform)i[0];      
                   i[0].updateTick();
                   //platform.updateTick();
                     
                        }

                 
                }
              
               else if (i[0] instanceof Portal)
              { 
                   Portal[] port=(Portal[])i;
                  
                  port[0].nextLevel(player);

                 
                } 
                  
            //making it this abstract is limitng flexibility. Maybe not the best?
            //will be quite difficult to implement platform, harder to interact with other components 
          
      }
     //fire:
    /* fire[0].updateTick();   //should all have same update tick?
     fire[0].animate();
     
     for (Fire f:fire)
      {
          f.setSubImg(fire[0].getSubImg());
      }*/
     //gem:
    /* gem[0].updateTick();
     gem[0].animate();
      for (Gem shiny:gem)
      {
          shiny.setSubImg(gem[0].getSubImg());
      }*/
     
     //portal.updateTick();
      // portal.animate();
     //animate
     
       
      
       
     //button.animate();
     
     //button.run(player);
     /*if (button.isOn())
        {
     platform.updateTick();
     //box[1]=new Box(30,30,480,195, Color.BLUE);
        }
     /*if (button.turnedOn())
         
        { //System.out.println ("turned on");
         box[1]=Load.newBoxesToAdd()[0];
        }*/
     /*else
     {System.out.println ("turned OFF");
     }*/
     //box.move(player);
     
     for (Box b:box)
         {  b.fall(platform);
                                     }
     
     //gem[0].collectGem(player);
   /*  for (Gem shiny:gem)
         {  shiny.collectGem(player);
                                     }
     
      for (Fire f:fire)
         { f.isDead(player);  }*/
                 
  
     //portal.nextLevel(player);
 }
    //should thus go into player class? do I need animate here? why moving faster?
  
  
  @Override
   
  public void paintComponent(Graphics g)
    {  //pattern draw all and drawHitbox
        
        super.paintComponent(g);
        
         Load.LoadGameImg(g);
         boolean draw=true;
         
         
         for (Obstacle[] i:ObstacleList)
      {  
          
             //i[0].draw(g);
             //i[0].animate();
              for (Obstacle o:i)
              {    
                       if (o.isDrawable())
                       {
                        o.draw(g);
                        o.drawHitbox(g);
                       }
                    
          }
          
      }
         
      /*   for (Fire f:fire)
         { f.draw(g);
           f.drawHitbox(g);
                      //if make many obstacles array of arrays
                 }*/
         
         /*for (Gem shiny:gem)
         {  if (!shiny.getCollectedYet())
             
             shiny.draw(g);
            shiny.drawHitbox(g);
                 }*/
        /* portal.draw(g);
         portal.drawHitbox(g);
         
         
         button.draw(g);
         button.drawHitbox(g);
         
         platform.draw(g);
    
         platform.drawHitbox(g);*/
       
         for (Box b: box)
         {    
           b.drawBox(g);
         }
         player.draw(g,playerImg); //img can just be stored in object itself?
         player.drawHitbox(g);
         
         Gem.drawGemCount(g);
         
         
         
         
       
        if (GameRunner.gameover)
        {
            Fire.deathScreen(g);
        }
         // gem.draw(g,gemImg);
         //player.drawPlayer(g);
              //g.drawImage(gameImg,0,0,gameImg.getWidth(),gameImg.getHeight(),null);
              //g.drawImage(playerImg,x,y, playerImg.getWidth()*(int)(GamePanel.SCALE), playerImg.getHeight()*(int)(GamePanel.SCALE), null); 
              
          
        
    }
 
  //starting with stand still
  //change ycount for different frames of animation,0 for standstill
 
 
  public void paintSquare(Graphics g)
  {
      xdim=this.getWidth();
        ydim=this.getHeight();
        
         //g.fillRect(x,y,w,h); 
      
         // System.out.println(y);  
        
       if (!touching && (x+w>=xdim || x<=0 ||y+h>=ydim || y<=0))
        {
        
            //System.out.println("color");
             c=randColor();
              //c=Color.RED;
             // g.setColor(c);/////
            // g.setColor(c);
           
            
                 touching=true;//if already touching don't want to choose another random color
                    //System.out.println(touching);
        }
     
       else if (x+w<xdim && x>0 && y+h<ydim && y>0)
        {
            touching=false;
           c=Color.BLUE;
             
            //System.out.println(touching); 
        }
        g.setColor(c);
        g.fillRect(x,y,w,h);
             //repaint();
      
  }
  
  

 
  public static Color randColor()
  {  int red=(int) (255*Math.random());
     int green=(int) (255*Math.random());
     int blue=(int) (255*Math.random());
      //System.out.println ("color is " + red + " " + green + " " + blue);
      return  new Color(red, green, blue);
    
  }
}
