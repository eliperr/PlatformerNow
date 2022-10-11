/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.platformernow;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author eliperr
 */
public interface Animatable {
    
    
    public abstract void draw(Graphics g, BufferedImage img);
    public abstract BufferedImage animate();
    public abstract void updateTick();
    
}
