/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.platformernow;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author eliperr
 */
public class LevelManager {
    
    public static ArrayList<BufferedImage> levels=sortLevels();
   
    public static ArrayList<BufferedImage> sortLevels()
    {
      File folder = new File("images/lvls");
    File[] listOfFiles = folder.listFiles();
    Arrays.sort(listOfFiles );
    ArrayList<BufferedImage> sorted=new ArrayList<>();
    for (File f:listOfFiles )
    {    if (f.getName().contains("png"))
            {sorted.add( Load.uploadImg("images/lvls/" + f.getName()));
       //System.out.println (f.getName());
            }
    }
    return sorted;

    /*    for (File file : listOfFiles) {
    if (file.isFile()) {
        System.out.println(Integer.parseInt(file.getName().substring(0,1)) );
        }
     */
     
        
    }
    
    
    
    
}
