/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Level;

import EventHandling.Observer;
import EventHandling.SoundHandler;
import EventHandling.PhysicsHandler;
import Model.GameFigure;
import Model.Rai;
import Model.Renderable;
import Model.Terro;
import Model.Updateable;
import View.GamePanel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author abilb
 */
public class NinjaVillage extends Level {

    BufferedImage img = null;
    TileModel ground;
    
    public NinjaVillage()
    {
        //This constructor is where level development currently happens. 
        this.width = 10240;
        this.height = 2560; 
       
        this.tiles = Collections.synchronizedList(new ArrayList<Tile>()); 
        this.allies = Collections.synchronizedList(new ArrayList<GameFigure>()); 
        this.enemies = Collections.synchronizedList(new ArrayList<GameFigure>()); 
        this.terrain = Collections.synchronizedList(new ArrayList<GameFigure>()); 
        this.remove = Collections.synchronizedList(new ArrayList<GameFigure>());
       //Load Resources
        
       try {
            img = ImageIO.read(new File("images/tiles/Rendered Textures/Walls/Wall 1 NE.png"));
            ground = new TileModel(img); 
            } 
        catch (IOException ex) {
            Logger.getLogger(NinjaVillage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
         
        
       //Load Game Content
    
        for(int i = 0; i < 80; i++)
        {
            //Loading in floor tiles. 
            this.terrain.add(new Platform(i * 128.0, 535.0));
        }
        
        this.terrain.add(new Platform(256, 300));
        this.terrain.add(new Trap(400, 200));
        
       Rai rai = new Rai((GamePanel.CAMERA_WIDTH), GamePanel.CAMERA_HEIGHT - 90, 100);
              enemies.add(rai);
      
       rai.cState.registerObserver(new SoundHandler(""));
       rai.mState.registerObserver(new SoundHandler(""));
        
       Terro terro = new Terro((GamePanel.CAMERA_WIDTH), GamePanel.CAMERA_HEIGHT - 90, 100);
              enemies.add(terro);
      
       terro.cState.registerObserver(new SoundHandler(""));
       terro.mState.registerObserver(new SoundHandler(""));
       
        //Get event handlers for game logic.
        ArrayList<Object> observers = new ArrayList<>(); 
       
        observers.add(new SoundHandler("")); 
         
        
    }

     
    
    
    @Override
    public void render(Graphics g) {
       for(Renderable r : tiles)
       {
           r.render(g);
       }
       
       for(Renderable r : terrain)
       {
           r.render(g);
       }
       
       for(Renderable r : allies)
       {
          r.render(g);
       }
       for(Renderable r : enemies)
       {
           r.render(g);
       }
        
    }

    @Override
    public void update() {
      
        for(GameFigure r : remove)
        {
            if(terrain.contains(r)){
            terrain.remove(r);}
            
            if(enemies.contains(r)){
            enemies.remove(r);}
            if(allies.contains(r)){
            allies.remove(r);}
        }
        
        for(Updateable u : terrain)
        {
            u.update(); 
        }
        for(Updateable u : allies)
        {
            u.update(); 
        }
        for(Updateable u : enemies)
        {
            u.update(); 
        }
        
    }
    
    
    
}
