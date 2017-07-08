/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.Main;
import StatusEffects.StatusEffect;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D; 
import java.util.ArrayList;

/**
 *
 * @author Garrett A. Clement
 */
public class HitBox implements Collision, Renderable, Updateable {

    private int length;
    private int width; 
    private Rectangle2D.Double collisionBox;
    public ArrayList<StatusEffect> statusEffects; 
    public GameFigure gameFigure;
    
    public HitBox(double x, double y, double size) {  
        collisionBox = new Rectangle2D.Double(x, y, width, length);
        statusEffects = new ArrayList<>(); 
    }
    
    public HitBox(double x, double y, int width, int length, GameFigure gameFigure, StatusEffect... statusEffects){
        collisionBox = new Rectangle2D.Double(x, y, width, length);
        this.gameFigure = gameFigure;
        this.length = length;
        this.width = width; 
        this.collisionBox = new Rectangle2D.Double(x, y, width, length);
        
        this.statusEffects = new ArrayList<>();
        for(StatusEffect se : statusEffects)
            this.statusEffects.add(se); 
    }   
 
    public void translate(double x, double y){
        collisionBox = new Rectangle2D.Double(x, y, width, length);
    }
     
    @Override
    public void render(Graphics g) {
       Graphics2D g2 = (Graphics2D)g; 

       g2.setColor(Color.green);
       g2.draw(collisionBox);
       g2.fill(collisionBox); 
       g2.setColor(Color.black); 
       
    }
    
    public void hadHitBox(){
        
    }

    @Override
    public void update() {} 
    
    @Override
    public Rectangle2D.Double getCollisionBox() {
       return collisionBox;
    }
}
