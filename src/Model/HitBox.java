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
public class HitBox extends GameFigure {

    private int length;
    private int width; 

    public HitBox(double x, double y, double size) {
        super(x, y, size);
        super.collisionBox = new Rectangle2D.Double(x, y, width, length );
    }
    
    public HitBox(double x, double y, int width, int length){
        super(x, y, 0);
        this.length = length;
        this.width = width;
        super.collisionBox = new Rectangle2D.Double(x, y, width, length);
        
    }   
 
    public void translate(double x, double y){
        super.collisionBox = new Rectangle2D.Double(x, y, width, length);
    }
    
    @Override
    public void render(Graphics g) {
       Graphics2D g2 = (Graphics2D)g; 

       g2.setColor(Color.green);
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
