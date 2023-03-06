/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.platformernow;


import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
 
/**
 *
 * @author eliperr
 */
public class Sounds 
//implements Runnable 
    {  private static Clip[] sounds=loadSounds();//subject to change
      private static final int JUMP=0;
       private static final int GET_GEM=1;
        private static final int FIRE=2;
      private static int jump=0;
     public static boolean jumping;
        public static Clip[] loadSounds()
        {  String[] soundNames={"Mario_Jumping-Mike_Koenig","Coin-pick-up-sound-effect", "Hot Sizzling"};
        sounds=new Clip[soundNames.length];
        for (int i=0; i<sounds.length; i++)
        {
            sounds[i]=findSound(soundNames[i]);
        
        }
        
        return sounds;
        
        }  
    
    public static  void test()
    {
        
        // System.out.println("sounds");
         String[] soundNames={"Mario_Jumping-Mike_Koenig","Coin-pick-up-sound-effect", "Hot Sizzling"};
       for (int i=0; i<sounds.length; i++)
       {System.out.println(sounds[i].toString());
       System.out.println (  sounds[i].equals(findSound(soundNames[i])));
       }
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
    jumping=false;
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