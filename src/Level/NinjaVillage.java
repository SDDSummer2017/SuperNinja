/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 
package Level; 
import Controller.Main;
import EventHandling.SoundHandler; 
import Model.Collision;
import Model.GameFigure;
import Model.HitBox;
 
import EventHandling.CheckpointHandler;
import EventHandling.Observer;
import EventHandling.SoundHandler;
import EventHandling.PhysicsHandler;
import Model.GameData;
import Model.GameFigure;
import Model.Nen;
 
import Model.Rai;
import Model.Renderable;
import Model.Terro;
import Model.Updateable;
import View.GamePanel; 
import com.sun.javafx.geom.AreaOp;
import java.awt.Graphics; 
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;
 

/**
 *
 * @author abilb
 */
public class NinjaVillage extends Level {

    BufferedImage img = null;
    TileModel ground;
    
    public NinjaVillage(GameData gameData)
    {
        //This constructor is where level development currently happens. 
        this.width = 10240;
        this.height = 2560; 
 
       
        this.tiles = Collections.synchronizedList(new ArrayList<Tile>());  
        this.terrain = Collections.synchronizedList(new ArrayList<GameFigure>()); 
        
        
        this.collisions = Collections.synchronizedList(new ArrayList<Collision>()); 
        this.renderables = Collections.synchronizedList(new ArrayList<Renderable>()); 
        this.updatables = Collections.synchronizedList(new ArrayList<Updateable>());   
        this.removables = Collections.synchronizedList(new ArrayList<>());
        this.addables = Collections.synchronizedList(new ArrayList<>());
         
 
        gameData.nen.x = GamePanel.CAMERA_WIDTH / 2;
        gameData.nen.y = GamePanel.CAMERA_HEIGHT - gameData.nenSize;
        this.tiles = Collections.synchronizedList(new ArrayList<Tile>()); 
     
        
        checkpointHandler = new CheckpointHandler(gameData); 
 
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
           
            if(i%10 == 0 && i != 0)
            {
                this.terrain.add(new Trap(i * 128, 525.0, this));
            }
            else
            {
                 this.terrain.add(new Platform(i * 128.0, 535.0));
            }
            if(i%3 == 0)
            {
               // this.terrain.add(new Platform(i * 128.0, 200)); 
            }
            
              if(i%4 == 0)
            {
                this.terrain.add(new Platform(i * 128.0, 300)); 
            }
            
            if(i%4==0 && i%10==0)
            {
                this.terrain.add(new Platform(i*128, 172));
            }
                if(i%20 == 0 && i != 0)
            {
                VictoryCheckPoint v = new VictoryCheckPoint(i * 128.0, 300);
                v.registerObserver(checkpointHandler);
                this.terrain.add(v); 
            }
        }
        
 
       
       Rai rai = new Rai((GamePanel.CAMERA_WIDTH), GamePanel.CAMERA_HEIGHT - 90, 100);
              this.addGameData(rai);
      
       rai.cState.registerObserver(new SoundHandler(""));
       rai.mState.registerObserver(new SoundHandler(""));
 
       Terro terro = new Terro((GamePanel.CAMERA_WIDTH), GamePanel.CAMERA_HEIGHT - 90, 100);
              addGameData(terro);
      
       terro.cState.registerObserver(new SoundHandler(""));
       terro.mState.registerObserver(new SoundHandler(""));
       
       
       
       
        //Get event handlers for game logic.
        ArrayList<Object> observers = new ArrayList<>(); 
       
        observers.add(new SoundHandler("")); 
//        for(Tile t : tiles)
//        {
//            gameData.addGameData(t);
//        }
        
           for(GameFigure tr : terrain)
        {
             addGameData(tr);
        } 
        
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
      
 
       for(Renderable r : renderables)
           r.render(g); 
 
        
    }

    @Override
    public void update() {
        int fireballcount = 0; 
        for(Updateable f : this.updatables)
        {
            if(f instanceof Fireball)
            {
                fireballcount++; 
                if(((Fireball)f).iterable > 100){
                this.removables.add(f);}
                this.removables.add(((Fireball) f).hitbox);
                 
                
                
            }
        }
       
        
        for(Updateable u : terrain)
        {
            u.update(); 
            
        }
        synchronized(updatables)
        {
            for(Updateable u : updatables)
                u.update();
        }
    }
    
    
    
}
