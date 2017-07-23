/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Level;

import Model.GameFigure;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author abilb
 */
public class Trap extends Platform {

 
    Level level;
    int iterable; 
    public Trap(double x, double y, Level level, Tile tile) {
        super(x, y, tile);
        this.level = level; 
        iterable = 0; 
 
    }

    @Override
    public void render(Graphics g) {
       // g.setColor(Color.red);
        super.render(g);
       
    }

    @Override
    public void update() {
        super.update();
        iterable++; 
        //5 seconds or so
        if(iterable == 100)
        {
            level.addGameData(new Fireball(x, y + 25));
            iterable = 0;
        }
    }

    @Override
    public Rectangle2D.Double getCollisionBox() {
         return super.getCollisionBox();
    }
    
}
