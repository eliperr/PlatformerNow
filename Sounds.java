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
 
/**
 *
 * @author eliperr
 */
//need to add menu to game with sound preferences and volume later
public class Sounds 
//implements Runnable 
    {     private static final String[] soundNames={"Mario_Jumping-Mike_Koenig","Coin-pick-up-sound-effect", "Hot Sizzling", "Coin-pick-up-sound-effect", "Mario_Jumping-Mike_Koenig"};
    private static Clip[] sounds=loadSounds();//subject to change
      public static  int JUMP=0;
       public static int GET_GEM=1;//final test
        public static final int FIRE=2;
      private static int jump=0;
      public static boolean sound=true; //on
      private static BufferedImage soundButton= Load.uploadSound();
      private static BufferedImage sub=soundButton.getSubimage(0, 0,320,370);
     //public static boolean notjumped=true;
  public static void drawSound(Graphics g)
  {
       
      
      
      
    int x=(int)((double)(GamePanel.GAMEWIDTH)*0.76);
    int y=(int)((double)(GamePanel.GAMEHEIGHT)*0.1);
      g.drawImage(sub ,x,y, (int) (sub.getWidth()/SCALE/18), (int) (soundButton.getHeight()/SCALE/28), null);
    g.setColor(Color.BLUE);
     int hRect=(int)(200);
     int wRect=(int)(200);
     g.fillRect(30, 50, wRect, hRect);
   
   
     
     
   
              
              
    }
      
      
  public static void toggle()
  {
      sound=!sound;
      System.out.println("sound" + sound);
      
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
    
    public static  void test()
    {
        
        // System.out.println("sounds");
         //String[] soundNames={"Mario_Jumping-Mike_Koenig","Coin-pick-up-sound-effect", "Hot Sizzling", "Coin-pick-up-sound-effect"};
       for (int i=0; i<sounds.length; i++)
       {System.out.println(sounds[i].toString());
       System.out.println (  sounds[i].equals(findSound(soundNames[i])));
       }
 
   }
    
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
    
   /* public static void mute()
    {   //if no sound
        for (Clip clip: sounds)
        {
            if (clip.isRunning())
            {
                //clip.stop();  turn on volume
                setVolume(0,clip);
            }
           
        }
        //if sound
          for (Clip clip: sounds)
        {
            if (clip.isRunning())
            {
               setVolume(0.9300098f,clip);
                
       //clip.start();  turn on volume
            }
           
        }
        
        
    }*/
    
    
    public static void playSounds(int id) throws InterruptedException
    {
        
      /* if (!sound)
        {
          
            return;
          //need to not use this or else to keep track of sounds that "should" be on when unmuted  
         //less efficient though 
            
        }*/
        if (id==JUMP)
        {Thread.sleep(1);} //only helpful for jumps
        
        else if (sounds[id].isRunning()&& id==GET_GEM)
        {
            sounds[id].stop();
            //System.out.println("still running");
            
            
            
            if (id==1)  //testing; only helpful for gems--> switch between multiple clips of same type
            {id=3;
             GET_GEM=3;} 
            else if (id==3)
            {
                id=1;
                GET_GEM=1;
            }
            
        /*  if (sounds[id].isRunning()&& id==JUMP)
            {
                 notjumped=false;
            }  
          else if (!sounds[id].isRunning()&& id==JUMP)
          {
              notjumped=true;
              
              System.out.println("jump");
          }
            */
          /* if (sounds[id].isRunning()&& id==JUMP)
           {
               if (id==0)  //testing
            {id=34;
             JUMP=4;} 
            else if (id==4)
            {
                id=1;
                JUMP=1;
            }
               
           }*/
            
            
            //sounds[id]=findSound(soundNames[id]);
            //try to avoid create new clip when possible,
            //only new clips can play on top of each other
            //is there a better way to do this?
                    // could make multiple versions of clips
        }
        
        sounds[id].setMicrosecondPosition(0);
        //openClip(sounds[JUMP]);
        
        sounds[id].start();
       //System.out.println( getVolume(sounds[id]));
        //cannot play a sound, while staying playing previous clip of some type 
    }
    
    
    
    
    
    
    public static void jump() throws InterruptedException  //reduce volume?
            
         
            
            
    { 
        
        //if (jumping)
      {
        //System.out.println("jump " + jump);
         //System.out.println(sounds[JUMP].equals(findSound("Mario_Jumping-Mike_Koenig")));
        Thread.sleep(1);
         sounds[JUMP].setMicrosecondPosition(0);
        //openClip(sounds[JUMP]);
        sounds[JUMP].start();
        jump++;
      }
    //jumping=false;
        //sounds[JUMP].open();
        //openClip(sounds[JUMP]);
       
        
        
       /*  sounds[JUMP].addLineListener((var e) -> {
    if (e.getType() == LineEvent.Type.STOP)  e.getLine().close();
     });*/
        
        
     // Clip clip= findSound("Mario_Jumping-Mike_Koenig");
        //clip.setMicrosecondPosition(0);
     // clip.start();
    } //"sound/Mario_Jumping-Mike_Koenig.wav"
    
    public static void collectGem()
    {
         //findSound("Coin-pick-up-sound-effect");
     sounds[GET_GEM].setMicrosecondPosition(0);
     openClip(sounds[GET_GEM]);
        sounds[GET_GEM].start();
       
     sounds[GET_GEM].addLineListener(new LineListener(){
    public void update(LineEvent e){
        if(e.getType() == LineEvent.Type.STOP){
            e.getLine().close();
                }
            }
    });
            
            
    }
    
    
    
    public static void fireDeath()
    {
       sounds[FIRE].setMicrosecondPosition(0);
        sounds[FIRE].start();
        
        /*if (GameRunner.restart)
        {  System.out.println("restart");
           sounds[FIRE].stop(); 
        }*/
        
     /*  sounds[FIRE].addLineListener(e -> {
    if (e.getType() == LineEvent.Type.STOP) e.getLine().close();
     });*/
       
   
  //findSound("Hot Sizzling");
        //findSound("sound/BabyElephantWalk60.wav");
    }
    
    
    public static void testStop()
    {  sounds[FIRE].stop();
    }
    private static void openClip(Clip c)
    {
          try {
       
         //c= AudioSystem.getClip();
         c.open();
        //return clip;
        
        //clip.start();
          }
           catch (Exception ex) 
          {
        ex.printStackTrace();
          }
          
        
        
        
    }
    
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
    
    /*@Override
    public void run()
    {  while (true)  
        {      //not hamoeover
        System.out.println("sound");
        System.out.println("jump sound in sound: " + Player.jumpSound);
        if (Player.jumpSound)
                {  jump();
            System.out.println("jump");
            }
         }             
    }*/
    
 /*   private static void findSound(String fileName)
    {
          try {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream( new File (fileName));
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
       System.out.println("clips");
       while (clip.isRunning() ) //needs to be separate thread
      /* { 
           if ( GameRunner.restart)
        {
            System.out.println("restart");
            //evt.getLine().close();
         System.out.println (clip);
            
           clip.start();
            clip.stop();
            clip.close();
            //clip.getName();
            
            
            GameRunner.restart=false;
        }
       }
      
       clip.addLineListener( new LineListener() {
  public void update(LineEvent evt) {
   if (evt.getType() == LineEvent.Type.STOP) {
        // System.out.println("start");
           //evt.getLine().close();
           //System.out.println (clip);
      clip.stop();
           clip.close();
    }
   else
     
    {
        if ( GameRunner.restart)
        {
            System.out.println("restart");
            evt.getLine().close();
         System.out.println (clip);
            
           clip.start();
            clip.stop();
            clip.close();
            //clip.getName();
            
            
            GameRunner.restart=false;
        }
        
    }
  }
});
       
        // If you want the sound to loop infinitely, then put: clip.loop(Clip.LOOP_CONTINUOUSLY); 
        // If you want to stop the sound, then use clip.stop();
       } catch (Exception ex) 
        {
        ex.printStackTrace();
         }
        
        
          
          
          
          
          
        
        
        
    }
    
}
*/
  /*while(clip.getMicrosecondLength() != clip.getMicrosecondPosition()) //this would require separate threads

       {
                
              if (GameRunner.isPaused())
             {
                 clip.stop();
                 System.out.println("got here");
             }
       }*/
//sounds for portal--> next level
//sounds for death in fire
//background music
//pushing box???


//make an arraylist of running clips