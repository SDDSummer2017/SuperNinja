/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Level;

import Model.GameFigure;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author abilb
 */
public class Platform extends GameFigure{
      private final Rectangle2D.Double collisionBox;
    public Platform(double x, double y) {
        super(x, y, 128);
         collisionBox = new Rectangle2D.Double(x, y, 128, 128);
           
    }

    @Override
    public void render(Graphics g) {
         Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.blue);
        g2.draw(collisionBox);
        g2.fill(collisionBox);
        super.health = 1000;
        g2.setColor(Color.blue);
        
    
    }

    @Override
    public void update() {

    }

    @Override
    public Rectangle2D.Double getCollisionBox() {
             return collisionBox;
    }
    
}
