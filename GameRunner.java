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
import javax.swing.JFrame;
public class GameRunner implements Runnable{
    
    public final double FPS=60;
  public static boolean gameover=false;
  public GamePanel gamePanel;
  private GameFrame gameFrame;
 private Thread gameThread;
  private int frames=0;
  private int updates=0;
  private static boolean pause=false;
 
   public static boolean restart=false;
  
  
  
  public GameRunner()
     {
  
        gamePanel= new GamePanel(); 
       gameFrame= new GameFrame(gamePanel);
        gamePanel.requestFocus();
        
        
         
          
      }
  
    public void startGameLoop()
    {
       gameThread=new Thread(this);
        gameThread.start(); 
    }
    
   public void run() {

long initialTime = System.nanoTime();
final double timeU = 1000000000 / FPS*2;
final double timeF = 1000000000 / FPS;
double deltaU = 0, deltaF = 0;
int frames = 0, updates = 0;
long timer = System.currentTimeMillis();

    while (true) {
        if (gameFrame.focus && !pause && !gameover)
        {    

        long currentTime = System.nanoTime();
        deltaU += (currentTime - initialTime) / timeU;
        deltaF += (currentTime - initialTime) / timeF;
        initialTime = currentTime;

        if (deltaU >= 1) {
            gamePanel.update();
           updates++;
            deltaU--;
        }

        if (deltaF >= 1) {
            gamePanel.repaint();
            frames++;
            deltaF--;
        }

        if (System.currentTimeMillis() - timer > 1000) {
            
               // System.out.println(String.format("UPS: %s, FPS: %s", updates, frames));
            
            frames = 0;
            updates = 0;
            timer += 1000;
        }
    } 
        
        
        else {    //not focus
            
            deltaU = 0;
            deltaF = 0;
           frames = 0;
           updates = 0;
            
        }   
      
        
    }
}
 public static void togglePause()
 {
     pause=!pause;
 }
   public static boolean isPaused()
   {
       return pause;
   }
}
