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

//need to to place player in correct position for each levwl listed in LoAD
            //new obstacles for each level
            //modify level images
            
//make boxes an obstacle more efficiwnty 

//boxes for buttons that only turn on temporarily 
//more general button, button that makes blue box appear -add event to button to be specificied later 

//wait  before collecting gem and before moving forward with portal]

//more buttons
//new levels!
//new level with many platforms mobing back and forth, need to make detection work with many platforms

//fix pause problem (what is this??)


//NEXT UP
//make platforms the way the used to be not in object list--need to do this not working as well as an Obstacle
//add settings and level menu

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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
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
   public ArrayList<Box> box;
   public Button button;
   public ArrayList <Platform> platform=new ArrayList<Platform>();//test
   public static ArrayList<Obstacle>ObstacleList;
   public boolean platformOn=false;
    public final static int TILESIZE=32;
  public final static float SCALE=1f; ///This cannot be scaled easily 
  public final static int TILESINWIDTH=26; //levels need to be tilesinwidth size unless a scrolling letter 
  public final static int TILESINHEIGHT=14;
  public final static int SCALEDTILESIZE=(int) (TILESIZE*SCALE);
  public final static int GAMEWIDTH=SCALEDTILESIZE*TILESINWIDTH;
   public final static int GAMEHEIGHT=SCALEDTILESIZE*TILESINHEIGHT;
    public  static int p=0; //platform index
    public int scrollOffset=0;
    public GamePanel()
    {    //init
        ObstacleList=Load.initLevel();
        JLabel test=new JLabel("one", JLabel.CENTER);
        test.setVisible(false);
        this.add(test);
      
         this.player= new Player(100f,285f); 
         //this.fire=Load.initFires();
         //this.gem=Load.initGems();              //should make an array of Obstacles?
         this.box=Load.initBoxes();
         //this.portal=new Portal(735,275); 
         //this.button=new Button (160,190);
         
         if (Load.initPlatform()!=null)
         {this.platform=Load.initPlatform();} //test
         playerImg= player.animate();
         
        //keep updating image to draw as you animate player
         //this.gameImg=Load.LoadGameImg();
         //System.out.println("player height is " + player.getHeight() );
         
        
          //this.gemImg=Load.uploadGem();
          keyboardInputs key=new keyboardInputs(this);
       addKeyListener(key);
       addMouseListener(key);
    size=new Dimension (GAMEWIDTH,GAMEHEIGHT);
     
       setPreferredSize(size);
       
       
       try {
                Sounds.playSounds(Sounds.SONG);
                //System.out.println("playing sound");
            } catch (InterruptedException ex) {
                Logger.getLogger(GameRunner.class.getName()).log(Level.SEVERE, null, ex);
            }  
         
    }
    
    public void restart()
            
    {  GameRunner.restart=true;
      Sounds.stopSound(Sounds.FIRE);
      this.repaint();
        try {
                Sounds.playSounds(Sounds.SONG);
                
            } catch (InterruptedException ex) {
                Logger.getLogger(GameRunner.class.getName()).log(Level.SEVERE, null, ex);
            }  
        GameRunner.gameover=false;
    player.setPosition(100f,285f);
    /*for ( int b=0; b<box.size(); b++)
    {
        
        box.set(b,Load.initBoxes().get(b));
        //box[b].setPosition(Load.initBoxes()[b].x,Load.initBoxes()[b].y);
    }*/
    box=Load.initBoxes();
     
        for (Obstacle o:ObstacleList)
        {
            
                    
                        { o.restart(this);}
                        
                        
                    
            
        }
       /*Gem.resetGems(gem);
        button.restart();
         platform.reset(240, 180);*/
        
       
       
       //will change to depend on level later 
       //will restart the level you are on
    }
    
   
    
    public void update() throws InterruptedException
 {   //pattern: updatetick and animate all except box and button (button only animate
     checkOffset();
     playerImg= player.animate(); 
      player.updateTick();
      player.setPosition(box,platform);
      /*if (platform!=null && platform.size()>=1)
      {System.out.println("gamepanel platform" +  platform.get(0).getX());
       /*if( platform.get(0).getX()!=240)
       {System.exit(0);}
      }*/
     //x=player.setPosition(x,y)[0];
     //y=player.setPosition(x,y)[1];
      for (Obstacle o:ObstacleList)
      {
          
             o.updateTick();
             o.animate();
             o.doStuff(player, this);
             
              
            /*for (Box b:box)
        {System.out.println(b.getColor()); }
            System.out.println("stop"); */
          
          
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
    *************************************************8 platform.updateTick();
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
         {  b.fall(platform,box);
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
        
         Load.LoadGameImg(g,scrollOffset);
         
         
         
         for (Obstacle o:ObstacleList)
      {  
          
             //i[0].draw(g);
             //i[0].animate();
               
                       //if (o.isDrawable())
                       
                        o.draw(g);
                        o.drawHitbox(g);
                       
                    
          
          
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
           b.draw(g);
        }
         player.draw(g,playerImg, scrollOffset); //img can just be stored in object itself?
         player.drawHitbox(g);
         Sounds.drawSound(g);
         Load.drawText(g,player, this);
         //Gem.drawGemCount(g);
         
         
         
         /* if (GameRunner.gameover)
        { 
       
            Fire.deathScreen(this);
        }*/
       
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
  
       //scrolling method:
   public void checkOffset()
         {
             
             
             int playerX=player.getHitBoxX();
             
             //TILES in entire level-TILES visible=TILE OFFset * size in pixels of tile
             int maxOffset=((int)Load.getLevelWidth()-TILESINWIDTH) *TILESIZE;
             int maxOffset2=((int)Load.getLevelData().length-TILESINWIDTH) *TILESIZE; //total length in tiles minus tiles we can see
             
                   //  System.out.println("maxOffset " + maxOffset);
                     // System.out.println("maxOffset2 " + maxOffset2);
                      // System.out.println("Load.getLevelWidth() " + Load.getLevelWidth());
                        //System.out.println("Load.getLevelData() " + Load.getLevelData().length);
                     //Load.getLevel().getWidth()*GamePanel.TILESIZE*0.52;
                     
                     //scrolloffset-move player back to stay in frame, level and objects that were forward also move back to stay 
                     //in frame and give the appearance of moving -to give this effect scrolloffset is substracted from player position
             if (playerX-scrollOffset>=(GAMEWIDTH*0.8))      //if players apparent position (considering scrolloffset) outside
                                                              //borders need to change scrolloffset
             {
                 
                 scrollOffset=(int)(playerX-(GAMEWIDTH*0.8)); //how far the player is a head of the border, 
                 //will be the scrolloffset and will be substracted from the player so player cannot get ahead of border
                 
                 
                 System.out.println("scrollOffset right " + scrollOffset); 
                 
             }
             
             else if (playerX-scrollOffset<(GAMEWIDTH*0.2))
                 
             {
                 
                 scrollOffset=(int)(playerX-(GAMEWIDTH*0.2));
                 
                 
                 System.out.println("scrollOffset left " + scrollOffset); 
                 
                 
             }
             
             
             if (scrollOffset>maxOffset)
                 
             {
                 
                 scrollOffset=maxOffset;
             }
                
             
             else if (scrollOffset<0)
             {
                 
                 scrollOffset=0;
             }
                 
             
             
             
             
             
             
         }          
             
  
  
  
  
}
