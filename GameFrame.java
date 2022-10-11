/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.platformernow;

//why does github never work and whatever i do always has some other error?
/**
 *
 * @author eliperr
 */
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
//import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import javax.swing.JFrame;
public class GameFrame extends JFrame implements ComponentListener {
    public static  int xdim=400;
    public static int ydim=400;
    GamePanel gamepanel=new GamePanel();
    public boolean focus=true;
    public GameFrame(GamePanel gamepanel)
            
    {  
       JFrame jframe=new JFrame();
       //jframe.setSize(xdim,ydim);
   
       jframe.add(gamepanel);
       jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       jframe.pack();
       jframe.setResizable(false);
        jframe.setVisible(true);
        jframe.addWindowFocusListener(new WindowFocusListener() {
           @Override
           public void windowGainedFocus(WindowEvent e) {
               focus=true;
               //System.out.println(focus);
           }

           @Override
           public void windowLostFocus(WindowEvent e) {
               focus=false;
               //System.out.println(focus);
           }
       });
    }
    
    @Override
     public void componentResized(ComponentEvent ce) {
      ydim = this.getHeight();
     xdim = this.getWidth();
    System.out.println("resize");
     }
    
     
    

     
  public void componentHidden(ComponentEvent ce) {};
  public void componentShown(ComponentEvent ce) {};
  public void componentMoved(ComponentEvent ce) { };
  
 
    
    
}
