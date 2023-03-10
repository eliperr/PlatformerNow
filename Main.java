package com.mycompany.platformernow;

/**
 *
 * @author eliperr
 */
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
public class Main  {
  
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
    GameRunner game=new GameRunner();
    game.startGameLoop();
 Thread gameThread=new Thread(game);
        gameThread.start();
      //  Sounds.test();
     /* Sounds sound=new Sounds();  
  Thread soundThread=new Thread(sound);
        soundThread.start(); */
        
        
        //LevelManager.sortLevels();
        
        
        
        //test:
        
        
    

    }
   
}

//need sound effects
//song to play on repeat
//sound effect for fire death
//sound effect for get gem
//sound effect for portal?
//sound effect for jump