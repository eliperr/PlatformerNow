/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.platformernow;

import static com.mycompany.platformernow.LevelManager.sortLevels;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.imageio.ImageIO;


/**   
 *
 * @author eliperr
 */
public class Load {
 
       private static int levelNum=0;
       private static final int TOTALNUMBEROFLEVELS=3;
       public static boolean wontOverlap=true; //this should change with different levels, default is true
       //remove algrotihm deciding if boxes will overlap if know they couldnt 
      private static BufferedImage[] gameImg=LoadTiles();
      public static int[][] levelData=loadLevelData();
      
      public static  int getLevel()
      {
         return levelNum; 
          
      }
      
      public static  void upLevel()
      {
        levelNum++;
        if (levelNum>=TOTALNUMBEROFLEVELS)
        {levelNum=0;}
        
          levelData=loadLevelData();
      }
      
     public static BufferedImage uploadImg(String pic)
   {
      
       BufferedImage img=null; 
       
       try
       {
          img=ImageIO.read(new File(pic)); 
          //img= ImageIO.read(stream); 
          //System.out.println(img);
       }
       catch (IOException e)
               {
                   System.out.println("error");
                   e.printStackTrace();
                   
               }
       
       return img;
       
   }
   
     
     
     
    public static BufferedImage LoadPlayerImg()
    {
       return uploadImg("images/player_sprites.png");
        
    }
    
    public static BufferedImage uploadFire()
    {
        
        return uploadImg("images/fire-sprite-sheet.png");
    }
    
    
    public static BufferedImage uploadGem()
    {
        return uploadImg("images/gem.png");
        
    }
    
    public static BufferedImage uploadPortal()
    {
        //return uploadImg("images/portal.png");
        return uploadImg("images/05 WhirlPoolGrey .png");
        
        
    }
    
    public static BufferedImage uploadButton()
    {
        //return uploadImg("images/portal.png");
        return uploadImg("images/button.png");
        
        
    }
    
    public static BufferedImage uploadPlatform()
    {
        //return uploadImg("images/portal.png");
        return uploadImg("images/cloud.png");
        
        
    }
    
    public static BufferedImage[] LoadTiles()
            
            
    {   BufferedImage gamefile=uploadImg("images/outside_sprites.png");
       
        gameImg=new BufferedImage[48];
        int index=0;
        for (int j=0; j<4;j++)
         {
            for (int i=0; i<12; i++) 
            { 
              gameImg[index]=gamefile.getSubimage(i*GamePanel.TILESIZE,j*GamePanel.TILESIZE,GamePanel.TILESIZE,GamePanel.TILESIZE);
               index++; 
            }
             
         }
        
        
       return gameImg;
        
    }
    
    
    /* public static BufferedImage LoadGameImg()
            
            
    {  
        
       return uploadImg("images/outside_sprites.png");
        
    }*/
     
     /*  public static BufferedImage LoadGameImg()
            
            
    {  
        
       return LoadTiles()[2];
        
    }*/
       
     public static int[][] loadLevelData() 
             
     {   
         BufferedImage level=LevelManager.levels.get(levelNum);
                 //uploadImg("images/lvls/1.png");
         int[][] levelData=new int [level.getWidth()][level.getHeight()];
         
         for (int x=0; x<level.getWidth();x++ )
         {   
             for (int y=0; y<level.getHeight(); y++)
                 
                 
             {
                 Color color= new Color(level.getRGB(x,y));
                   levelData[x][y]=color.getRed();
                 
                 
             }
             
             
             
             
         }
             
         return levelData;
         
         
     }
     
     public static void LoadGameImg(Graphics g)
             
     {
         int index;
         
         for (int x=0; x<GamePanel.TILESINWIDTH;x++)
         {
             
             for (int y=0; y<GamePanel.TILESINHEIGHT; y++)
                 
             {
                 index=levelData[x][y];
                 g.drawImage(gameImg[index],x*GamePanel.TILESIZE,y*GamePanel.TILESIZE,null);
                 //
                 
             }
             
             
         }
         
         
     }
     
    /* public static Cord[] getFireArray (int level)
             
     {   Cord[]arr=null;
     
         switch (level)
         { case 1:
               
         arr=new Cord[2];
          arr[0]=new Cord(170,280);
          arr[1]=new Cord(300,280);
             
            break;     
     
                  
                  
         
         }
                         
         return arr;
         
         
     }*/
     
     public static Fire[] initFires()   //make different for diff levels this is level one
             
     {  
          Fire[] fire;
          System.out.print ("level is " + Load.levelNum);
         switch (Load.levelNum)
        {
             
            
             case 0:      
                 {   
                     int w=(int)(88/GamePanel.SCALE);
                     int len=(int)((GamePanel.GAMEWIDTH-250)/w);
                   fire =new Fire[len];
                     for (int f=0; f<len; f++)
                     {
                        
                         fire[f] =new Fire(400+(f*50), 280);
                     }   
                        
                                  
                   
                    //fire[0]=new Fire(400,280);
                    //fire[1]=new Fire(450,280);
         //fire[4]=new Fire(170,280);
                 break;
                 }
             case 1:
             { //test
                 fire =new Fire[4];

                   // fire[0]=new Fire(170,280);
                   fire[0]=new Fire(335,215);//320

                   fire[1]=new Fire(290,215);
                    fire[2]=new Fire(420,280); //420
                    fire[3]=new Fire(700,280);
                  break;
             }
         default:
         { 
              fire =null;
              System.out.print("null fire");
           
         }
         
         
       }     
         return fire;
         
         
     }
     
    public static Gem[] initGems()   //make different for diff levels this is level one
             
     {  Gem[] gem;
         switch (Load.levelNum)
       {       case 0: 
         {      
             
             
               
               int w=(int)(30/GamePanel.SCALE);
                     int len=(int)((GamePanel.GAMEWIDTH-600)/w);
             
                    gem =new Gem[len+4];
              //gem =new Gem[4];
              gem[0]=new Gem(140,210);
              gem[1]=new Gem(195,256);
              gem[2]=new Gem(226,222);
              gem[3]=new Gem(417,154);
              
              
                     for (int g=4; g<len+4; g++)
                     {
                        
                         gem[g] =new Gem(417+((g-4)*50), 154);
                     }   
                        
              break;
         }
         
             case 1:
                 
             {    gem =new Gem[4];

            gem[0]=new Gem(65,200);
            gem[1]=new Gem(350,160);
            gem[2]=new Gem(550,120);
            gem[3]=new Gem(615,290);
            break;
             }
             default:
             {
                 gem=null;
             }
         }          
        return gem; 
     }
    
    public static ArrayList<Box> initBoxes()
    {        
        ArrayList<Box> box =new ArrayList<>(); 
       box.add(new Box(30,30,90,290, Color.RED));
      // box.add(new Box(30,30,50,290, Color.PINK));
       //box.add(new Box(30,30,160,290, Color.GREEN));
       //box.add(new Box(0,0,0,0, Color.BLUE));
       box.add(new Box (30, 30,618,290, Color.BLACK));
       
        
        /*Box[] box=new Box[3];
       box[0]=new Box(30,30,90,290, Color.RED);
       box[1]=new Box(0,0,0,0, Color.BLUE); //place holder box, could also use an array list
        //box[1]=new Box(30,30,480,100, Color.BLUE);
   //box[1]=new Box(30, 30,160,290, Color.BLACK);   
        box[2]=new Box(30, 30,618,290, Color.BLACK);*/
        return box;
        
    }  //
    
    
    //only needed when using array not needed with arraylist
  /* public static ArrayList<Box>  newBoxesToAdd()  //needs to be different for diffferent levels 
           
   {
       //Box[] box=new Box[1];
        ArrayList<Box> box =new ArrayList<>(); 
       box.add(new Box(30,30,480,100, Color.BLUE));
       
       return box;
   }*/
    
    //do these need to be in 2d matrix, 2d should work with new way
     public static ArrayList<Obstacle> initLevel()
     {
         ArrayList<Obstacle> level=new ArrayList<Obstacle>();
         Fire fire[]=Load.initFires();
        if (fire !=null )
                {level.addAll(Arrays.asList(fire));}
                 
                 
             Gem gem[] = Load.initGems(); 
              if (gem !=null )
         level.addAll(Arrays.asList(gem));
         //levelOne.addAll(Load.initBoxes());
        /* Box[] boxy=new Box[Load.initBoxes().size()];
         for (int i=0; i<boxy.length; i++)
         {  
             boxy[i]=Load.initBoxes().get(i);  //need to change this to arraylist
             
         }
         levelOne.add(boxy);*/
        //tried adding boxes, but too diffferent need to be own group
         //tester
        //Portal portal=new Portal(735,275); 
        Portal portal=new Portal(30,270);
        //Portal [] p=new Portal[1];
       // p[0]=portal;
        level.add(portal);
        
        Button button=new Button (160,190);
         //Button [] b=new Button[1]; //can add more buttons later easier
         //b[0]=button;
         level.add(button);
         
        
        Platform platform=new Platform (240, 180, 90, 0);
        //Platform[]plat=new Platform[1];
        //plat[0]=platform;
         level.add(platform);   
        
         //levelOne.add(Load.initBoxes());
         return level;
     }
    
    
}




//"images/player_sprites.png"