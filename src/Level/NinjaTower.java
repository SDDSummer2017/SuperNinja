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
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author abilb
 */
public class NinjaTower extends Level{

    public NinjaTower(GameData gameData)
    {
            //This constructor is where level development currently happens. 
        this.width = 1900;
        this.height = 2560;
        this.nenStartX = 80; 
        this.nenStartY = 2490; 
        this.gameData = gameData; 
        this.tiles = Collections.synchronizedList(new ArrayList<Tile>());  
        this.terrain = Collections.synchronizedList(new ArrayList<GameFigure>()); 
        
        
        this.collisions = Collections.synchronizedList(new ArrayList<Collision>()); 
        this.renderables = Collections.synchronizedList(new ArrayList<Renderable>()); 
        this.updatables = Collections.synchronizedList(new ArrayList<Updateable>());   
        this.removables = Collections.synchronizedList(new ArrayList<>());
        this.addables = Collections.synchronizedList(new ArrayList<>());
         
 
  
        this.tiles = Collections.synchronizedList(new ArrayList<Tile>()); 
        
        
        checkpointHandler = new CheckpointHandler(gameData); 
        
        for(int i = 0; i < 20; i++)
        {
            this.terrain.add(new Platform(i * 128, this.height));
        }
        
        for(int i = 0; i < 80; i++)
        {
           for(int j = 0; j < 5; j++)
           {
               if(j % 2 == 0){
               this.terrain.add(new Platform(j * 300, i * 350));}
               else
               {
                    
                       
                  Spawner s =  new Spawner(j * 275, i * 250, this, Kisara.class);
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
