/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 
package Level;

import EventHandling.Observer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author abilb
 */
public class VictoryCheckPoint extends Checkpoint {
    
    public VictoryCheckPoint(double x, double y, Tile tile) {
        super(x, y, tile);
    }
    
    @Override 
    public void notifyObservers()
    {
        for(Observer o : observers)
        {
            o.onNotify("Level Complete");
        }
    }
    @Override
     public void render(Graphics g) {
         Graphics2D g2 = (Graphics2D)g;
//        g2.setColor(Color.yellow);
//        g2.draw(collisionBox);
//        g2.fill(collisionBox);
        super.render(g);
        super.health = 1000;
            
    
    }
    
    
}
