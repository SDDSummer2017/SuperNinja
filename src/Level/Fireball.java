/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Level;

import Model.GameFigure;
import Physics.Acceleration;
import Physics.Force;
import Physics.Velocity;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author abilb
 */
public class Fireball extends GameFigure {

    int iterable = 0; 
    public Fireball(double x, double y) {
        super(x + 32, y, 64);
        this.forces.add(new Force(9, new Acceleration(0, -6)));
        this.velocity = new Velocity();
        velocity.dx = 0; 
        velocity.dy = 0;
    }

    @Override
    public Rectangle2D.Double getCollisionBox() {
       return  new Rectangle2D.Double(x, y, 64, 64);
    }

    @Override
    public void render(Graphics g) {
         Graphics2D g2 = (Graphics2D)g;
            g2.setColor(Color.red);
        g2.fillOval((int)x, (int)y, 64, 64);
         
        super.health = 1000;
    
    }

    @Override
    public void update() {
       this.calculatePhysics();
       iterable++;
    }
    
}
