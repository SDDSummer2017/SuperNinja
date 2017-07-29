/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Level;

import EventHandling.CheckpointHandler;
import Model.Collision;
import Model.GameData;
import Model.GameFigure;
import Model.Kisara;
import Model.Renderable;
import Model.Updateable;
import View.GamePanel;
import java.awt.Graphics;
import java.awt.Image;
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
public class NinjaTower extends Level{

    private TileModel ground; 
    public NinjaTower(GameData gameData)
    {
            //This constructor is where level development currently happens. 
        this.width = 1900;
        this.height = 2560;
        this.nenStartX = -500; 
        this.nenStartY = 2420; 
        this.gameData = gameData; 
        this.tiles = Collections.synchronizedList(new ArrayList<Tile>());  
        this.terrain = Collections.synchronizedList(new ArrayList<GameFigure>()); 
        
        
        this.collisions = Collections.synchronizedList(new ArrayList<Collision>()); 
        this.renderables = Collections.synchronizedList(new ArrayList<Renderable>()); 
        this.updatables = Collections.synchronizedList(new ArrayList<Updateable>());   
        this.removables = Collections.synchronizedList(new ArrayList<>());
        this.addables = Collections.synchronizedList(new ArrayList<>());
        Image img;
        
        try {
            img = ImageIO.read(new File("images/tiles/Rendered Textures/Walls/Wall 2 SE.png"));
            ground = new TileModel(img); 
            } 
        catch (IOException ex) {
            Logger.getLogger(NinjaVillage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
  
        this.tiles = Collections.synchronizedList(new ArrayList<Tile>()); 
        
        
        checkpointHandler = new CheckpointHandler(gameData); 
        
        for(int i = 0; i < 20; i++)
        {
            this.terrain.add(new Platform(i * 128, this.height, new Tile(i * 128, this.height, ground)));
        }
        
        for(int i = 0; i < 80; i++)
        {
           for(int j = 0; j < 5; j++)
           {
               if(j % 2 == 0){
               this.terrain.add(new Platform(j * 300, i * 350, new Tile(j * 300, i * 350, ground)));}
               else
               {
                    
                       
                  Spawner s =  new Spawner(j * 275, i * 250, this, Kisara.class, new Tile(j * 275, i * 250, ground));
                  s.registerObserver(checkpointHandler);
                   
                   this.terrain.add(s);
                   
               }
           }
        }
        
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
