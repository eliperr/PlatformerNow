/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.platformernow;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


/**   
 *
 * @author eliperr
 */
public class Load {
 
       private static int level=1;
    
      private static BufferedImage[] gameImg=LoadTiles();
      public static int[][] levelData=loadLevelData();
     private static BufferedImage uploadImg(String pic)
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
         BufferedImage levelOne=uploadImg("images/level_one_data.png");
         int[][] levelData=new int [levelOne.getWidth()][levelOne.getHeight()];
         
         for (int x=0; x<levelOne.getWidth();x++ )
         {   
             for (int y=0; y<levelOne.getHeight(); y++)
                 
                 
             {
                 Color color= new Color(levelOne.getRGB(x,y));
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
         
         Fire[] fire =new Fire[3];
         
         fire[0]=new Fire(170,280);
         fire[1]=new Fire(420,280);
         fire[2]=new Fire(700,280);
         
         return fire;
         
         
     }
     
    public static Gem[] initGems()   //make different for diff levels this is level one
             
     {
         
         Gem[] gem =new Gem[3];
         
         gem[0]=new Gem(180,260);
         gem[1]=new Gem(400,260);
         gem[2]=new Gem(550,120);
         
         return gem;
         
         
     }
    

      
     
    
    
}




//"images/player_sprites.png"