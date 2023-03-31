/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.platformernow;


import static com.mycompany.platformernow.GamePanel.SCALE;
import static com.mycompany.platformernow.Load.uploadImg;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;


//button sound 

 
/**
 *
 * @author eliperr
 */
//need to add menu to game with sound preferences and volume later
public class Sounds 
//implements Runnable 
    {     private static final String[] soundNames={"Mario_Jumping-Mike_Koenig","Coin-pick-up-sound-effect", "Coin-pick-up-sound-effect", "Hot Sizzling", "Move Forward", "Portal-sound-effect", "interface", "interface"};
    private static Clip[] sounds=loadSounds();//subject to change
      public static  int JUMP=0;
       public static int GET_GEM=1;//final test
       public static int gemSwitch,buttonSwitch=0;
        public static final int FIRE=3;
      
      public static final int SONG=4;
      public static final int PORTAL=5;
      public static  int BUTTON=6;
      
      public static boolean sound=true; //on
      private static BufferedImage soundButton= Load.uploadSound();
      private static BufferedImage sub;
     //public static boolean notjumped=true;
      
      //draw button that shows whether sound muted
  public static void drawSound(Graphics g)
  {
       
      if (sound)
          
      {
          sub=soundButton.getSubimage(0, 0,380,310); 
          
      }
      
      else if (!sound)
          
      {
           sub=soundButton.getSubimage(410,0,380,310); 
          
      }
      
      
    int x=(int)((double)(GamePanel.GAMEWIDTH)*0.90);
    int y=(int)((double)(GamePanel.GAMEHEIGHT)*0.1);
      
    g.setColor(Color.WHITE);
     int hRect=(int)(sub.getHeight()/SCALE/12);
     int wRect=(int)(sub.getWidth()/SCALE/12);
     g.fillRect((int) (x + sub.getWidth()/SCALE/14-26*SCALE), (int) (y + sub.getHeight()/SCALE/14/3), wRect, hRect);
   g.drawImage(sub ,(int)(x+3/SCALE),(int)(y+8/SCALE), (int) (sub.getWidth()/SCALE/14), (int) (sub.getHeight()/SCALE/14), null);
   
     
    
   
              
              
    }
      
    // toggle sound
  public static void toggle()
  {
      sound=!sound;
     // System.out.println("sound" + sound);
      
      //makes sure sounds are turned on/off even if they are in the middle of running:
      
       //if no sound
       if (!sound)
       { 
            for (Clip clip: sounds)
            {
                //if (clip.isRunning())
                {
                    //clip.stop();  turn on volume
                    setVolume(0,clip);
                }

            }
       }   
        //if sound
        
       else if (sound)
       {       for (Clip clip: sounds)
              {
                  //if (clip.isRunning())
                  {
                     setVolume(0.9300098f,clip);

             //clip.start();  turn on volume
                  }

              }
       }
  }
       
     public static Clip[] loadSounds()
        {  //String[] soundNames={"Mario_Jumping-Mike_Koenig","Coin-pick-up-sound-effect", "Hot Sizzling", "Coin-pick-up-sound-effect"};
      
            //System.out.println(Arrays.toString(soundNames));
        sounds=new Clip[Sounds.soundNames.length];
        for (int i=0; i<sounds.length; i++)
        {
            sounds[i]=findSound(soundNames[i]);
        
        }
        
        return sounds;
        
        }  
    
   
    //set volume, helpful for turning sound on and off 
    public static void setVolume(float vol, Clip clip)
    {
       FloatControl gainControl=(FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
     float range = gainControl.getMaximum() - gainControl.getMinimum();
    float gain = (range * vol) + gainControl.getMinimum();
    gainControl.setValue(gain);

        
    }
    
    public static float getVolume (Clip clip)
    {
       FloatControl gainControl=(FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
       float range = gainControl.getMaximum() - gainControl.getMinimum();
   float gain= gainControl.getValue();
   float vol=(gain-gainControl.getMinimum())/range;
       return vol; 
       
       //determined default is 0.9300098;
    }
    
  
    //play clip from sound list 
    public static void playSounds(int id) throws InterruptedException
    {
        
      /* if (!sound)
        {
          
            return;
          //need to not use this or else to keep track of sounds that "should" be on when unmuted  
         //less efficient though 
            
        }*/
        if (id==JUMP )
        {Thread.sleep(1);} //only helpful for jumps
        
        else if (sounds[id].isRunning()&& id==GET_GEM)
        {
            sounds[id].stop();
            //System.out.println("still running");
            //Thread.sleep(1);
            id=id+1;
            
           /* if (id==1)  //testing; only helpful for gems--> switch between multiple clips of same type
            {id=2;
             //GET_GEM=2;
            else if (id==2 )
            {
                id=1;
                GET_GEM=1;
            }
            */
           //id=switchClip( gemSwitch,id);
          
         
        }
        
        
        else if (sounds[id].isRunning()&& id==BUTTON)
        {
            sounds[id].stop();
            Thread.sleep(1);
            //System.out.println("still running");
            //id=id+1;
            
            
          if (id==6)  //testing; only helpful for gems--> switch between multiple clips of same type
            {id=7;
             BUTTON=7;} 
            else if (id==7)
            {
                BUTTON=6;
                id=6;
            }   
        
        }
        sounds[id].setMicrosecondPosition(0);
        //openClip(sounds[JUMP]);
        
        sounds[id].start();
        
        if (id==4)
        {
            sounds[id].loop(Clip.LOOP_CONTINUOUSLY);
            
        }
        else if (id==5)
        {
            
                
               // Thread.sleep(10);
            
            
        }
       //System.out.println( getVolume(sounds[id]));
        //cannot play a sound, while staying playing previous clip of some type 
    }
    
    public static void stopSound(int id)
    {
        sounds[id].stop();
        
    }
    
    //for some clips need to switch to other clip while first is playing
   public static int switchClip(int c,int id)
           
   {
       if (c==0)
       {
           c=1;
          
           
       }
       else if (c==1)
       {
           c=0;
           
       }
        id=id+c;
        
        System.out.println( "c " + c);
       return id;
       
   }
 
    
    
    
   
    
    
   
    
   
    //get sound based on file name
     private  static Clip findSound(String fileName)
    {
        Clip clip=null;
          try {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream( new File ("sound/" + fileName + ".wav"));
         clip = AudioSystem.getClip();
         clip.open(audioInputStream);
        return clip;
        
        //clip.start();
          }
           catch (Exception ex) 
          {
        ex.printStackTrace();
          }
          
          return clip;
          
    }
    
}  
    
   