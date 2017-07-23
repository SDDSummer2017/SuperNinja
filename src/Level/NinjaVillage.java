/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 
package Level; 
import Model.Collision;
 
import EventHandling.CheckpointHandler;
import EventHandling.SoundHandler;
import Model.GameData;
import Model.GameFigure;
import Model.Kisara;
 
import Model.Rai;
import Model.Renderable;
import Model.Sage;
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
    TileModel portal; 
    AnimatedTileModel portals; 
    public NinjaVillage(GameData gameData)
    {
        //This constructor is where level development currently happens. 
        this.width = 10240;
        this.height = 2560; 
        this.nenStartX = 128; 
        this.nenStartY = 535;
        
        this.tiles = Collections.synchronizedList(new ArrayList<Tile>());  
        this.terrain = Collections.synchronizedList(new ArrayList<GameFigure>()); 
        
        
        this.collisions = Collections.synchronizedList(new ArrayList<Collision>()); 
        this.renderables = Collections.synchronizedList(new ArrayList<Renderable>()); 
        this.updatables = Collections.synchronizedList(new ArrayList<Updateable>());   
        this.removables = Collections.synchronizedList(new ArrayList<>());
        this.addables = Collections.synchronizedList(new ArrayList<>());
 
 
        
        this.tiles = Collections.synchronizedList(new ArrayList<Tile>()); 
     
        
        checkpointHandler = new CheckpointHandler(gameData); 
 
       //Load Resources
        
       try {
            img = ImageIO.read(new File("images/tiles/Rendered Textures/Walls/Wall 2 SE.png"));
            ground = new TileModel(img); 
            } 
        catch (IOException ex) {
            Logger.getLogger(NinjaVillage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
         
       
       try {
            img = ImageIO.read(new File("images/portal.png"));
            portal = new TileModel(img); 
            } 
        catch (IOException ex) {
            Logger.getLogger(NinjaVillage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        ArrayList<TileModel> portalTiles = new ArrayList<>(); 
        for(int i = 0; i < 7; i++)
        {
             try {
            img = ImageIO.read(new File("images/portal" + i + ".png"));
            portalTiles.add(new TileModel(img));
            } 
        catch (IOException ex) {
            Logger.getLogger(NinjaVillage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
         
        }
       
       //Load Game Content
    
        for(int i = 0; i < 80; i++)
        {
            //Loading in floor tiles. 
           
            if(i%10 == 0 && i != 0)
            {
                this.terrain.add(new Trap(i * 128, 525.0, this, new Tile(i * 128, 525, ground)));
            }
            else
            {
                if(i == 2)
                {
                    Spawner s =  new Spawner(i * 128, 535, this, Kisara.class, new Tile( i * 128, i * 535, ground));
                    s.registerObserver(checkpointHandler);
                    
                    this.terrain.add(s);
                }
                else if(i == 6)
                {
                     Spawner s =  new Spawner(i * 128, 535, this, Rai.class, new Tile( i * 128, i * 535, ground));
                    s.registerObserver(checkpointHandler);
                    
                    this.terrain.add(s);
                }
                else if(i == 3)
                {
                    Spawner s =  new Spawner(i * 128, 535, this, Terro.class, new Tile( i * 128, i * 535, ground));
                    s.registerObserver(checkpointHandler);
                    
                    this.terrain.add(s);
                }
                 else if(i == 7)
                {
                    Spawner s =  new Spawner(i * 128, 535, this, Sage.class, new Tile( i * 128, i * 535, ground));
                    s.registerObserver(checkpointHandler);
                    
                    this.terrain.add(s);
                }
             
                    this.terrain.add(new Platform(i * 128.0, 535.0, new Tile(i * 128, 535, ground)));
                
                 
            }
            if(i%3 == 0)
            {
               // this.terrain.add(new Platform(i * 128.0, 200)); 
            }
            
              if(i%4 == 0 && i%20 != 0)
            {
                this.terrain.add(new Platform(i * 128.0, 300, new Tile(i * 128, 300, ground))); 
            }
            
            if(i%4==0 && i%10==0)
            {
                this.terrain.add(new Platform(i*128, 172, new Tile(i * 128, 172, ground)));
            }
                if(i%20 == 0 && i != 0)
            {
                VictoryCheckPoint v = new VictoryCheckPoint(i * 128.0, 300, new Tile(i * 128, 300, portal, new AnimatedTileModel(i * 128, 300, portalTiles)));
                v.registerObserver(checkpointHandler);
                this.terrain.add(v); 
            }
        }
        
  
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
