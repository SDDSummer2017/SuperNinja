/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Level;

import EventHandling.CheckpointHandler;
import EventHandling.Observer;
import Model.Kisara;
import Model.Rai;
import Model.Terro;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.lang.ProcessBuilder.Redirect.Type;

/**
 *
 * @author abilb
 */
public class Spawner extends Checkpoint {
    public boolean spawned;
    private Level level; 
    public final Class<?> enemy;
    public Spawner(double x, double y, Level level, Class<?> enemy, Tile tile) {
        super(x, y, tile);
        this.level = level;
        this.enemy = enemy; 
    }
    
    @Override
    public void update()
    {
          super.update();
         
        //5 seconds or so
        
            
           
           
        
        
        
    }
    
    @Override
    public void notifyObservers()
    {
        for(Observer o : observers)
        { 
             o.onNotify("Spawn");
             if(o instanceof CheckpointHandler)
             {
                 ((CheckpointHandler)o).onNotify(this);
             }
        }
    }
    @Override
    public void render(Graphics g)
    {
         Graphics2D g2 = (Graphics2D)g;
     
         super.render(g);
         super.health = 1000;
      
    }

}
