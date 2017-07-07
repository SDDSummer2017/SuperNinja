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

    public Trap(double x, double y) {
        super(x, y);
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        g.setColor(Color.red);
    }

    @Override
    public void update() {
        super.update(); 
    }

    @Override
    public Rectangle2D.Double getCollisionBox() {
         return super.getCollisionBox();
    }
    
}
