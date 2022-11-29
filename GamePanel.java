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


//restart fx after death
//instsance of level handler
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
   private BufferedImage gameImg,fireImg, gemImg, portalImg;
   public Player player;
   public Fire fire[];
   public Gem gem[];
   public Portal portal;
   public Box box[];
   
    public final static int TILESIZE=32;
  public final static float SCALE=1f; ///This cannot be scaled easily 
  public final static int TILESINWIDTH=26;
  public final static int TILESINHEIGHT=14;
  public final static int SCALEDTILESIZE=(int) (TILESIZE*SCALE);
  public final static int GAMEWIDTH=SCALEDTILESIZE*TILESINWIDTH;
   public final static int GAMEHEIGHT=SCALEDTILESIZE*TILESINHEIGHT;
   
    
    public GamePanel()
    {    //init
         this.player= new Player(100f,285f); 
         this.fire=Load.initFires();
         this.gem=Load.initGems();
         this.box=Load.initBoxes();
         this.portal=new Portal(500,200); 
         
         
         this. playerImg= player.animate(); //keep updating image to draw as you animate player
         //this.gameImg=Load.LoadGameImg();
         //System.out.println("player height is " + player.getHeight() );
         
         this.fireImg=fire[0].animate();
         this.gemImg=gem[0].animate();
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
        box[b].setPosition(Load.initBoxes()[b].x,Load.initBoxes()[b].y);
    }
     
        
       Gem.resetGems(gem);
        
        
       //will change to depend on level later 
       //will restart the level you are on
    }
    
   
    
    public void update()
 { 
     
      player.updateTick();
      playerImg= player.animate();
      
      player.setPosition(box);
     //x=player.setPosition(x,y)[0];
     //y=player.setPosition(x,y)[1];
     fire[0].updateTick();   //should all have same update tick?
     fireImg=fire[0].animate();
     gem[0].updateTick();
     gemImg=gem[0].animate();
     portal.updateTick();
     portalImg=portal.animate();
     //box.move(player);
     
     
     
     //gem[0].collectGem(player);
     for (Gem shiny:gem)
         {  shiny.collectGem(player);
                                     }
     
      for (Fire f:fire)
         { f.isDead(player);  }
                 
  
     portal.nextLevel(player);
 }
    //should thus go into player class? do I need animate here? why moving faster?
  
  
  @Override
   
  public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
         Load.LoadGameImg(g);
         for (Fire f:fire)
         { f.draw(g,fireImg);  //if make many obstacles array of arrays
                 }
         
         for (Gem shiny:gem)
         {  if (!shiny.getCollectedYet())
             
             shiny.draw(g,gemImg);
                 }
         portal.draw(g,portalImg);
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
