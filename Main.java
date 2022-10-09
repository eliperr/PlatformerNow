package com.mycompany.platformernow;

/**
 *
 * @author eliperr
 */
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
}
}