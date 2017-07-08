/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Garrett A. Clement
 */
public class Dummy extends GameFigure {

    private Rectangle2D.Double collisionBox;
    
    public Dummy(double x, double y, double size) {
        super(x, y, size, false);
        super.health = 50;
        int meaningless;
        collisionBox = new Rectangle2D.Double(x, y, size, size);
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.red);
        g2.draw(collisionBox);
        g2.fill(collisionBox);
        g2.setColor(Color.black);
    }

    @Override
    public void update() { 
    }

    @Override
    public Rectangle2D.Double getCollisionBox() {
        return collisionBox;
    }
    
}
