/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.Main;
import StatusEffects.DamageEffect;
import StatusEffects.DotEffect;
import View.GamePanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 *
 * @author Garrett A. Clement
 * added sprint 5
 */
public class FlameDragonSpell extends Projectiles {
    ArrayList<BodyPart> dragonBody;
    
    double pi = Math.PI;
    double peroid = 2 * Math.PI/5;
    double amplitude = 20;
    double direction;
    public FlameDragonSpell(double x, double y, double size, int direction) {
        super(x, y, 0, 0, Color.RED, false, 30, 40, GameData.fireballResource);
        //super(x, y, size); 
        this.direction = direction;
        dragonBody = new ArrayList<>();
        BodyPart part = new BodyPart(this.x, this.y, 0, 0, Color.RED, false, 30, 40);
        
        dragonBody.add(part);
        Main.gameData.addGameData(part);
        
    }
        
    @Override
    public void render(Graphics g) {
      
        for(BodyPart h : dragonBody)
        {
            double x = h.getCollisionBox().x;
            double y = h.getCollisionBox().y;
//            g.fillOval((int)x, (int)y, (int)super.size , (int)super.size);
            
        } 
    }
    

    @Override
    public void update() {       
        for(BodyPart h : dragonBody)
        {
            double x_displacement = h.getCollisionBox().x;
            double y_displacement = h.getCollisionBox().y;
            double incX = incrementX(x_displacement);
            double incY = incrementY(incX); 
            h.translate(incX,  (incY * direction) +  y_displacement); 
        }
        remove();
    }
    
        public void increase(){
        BodyPart tmp = new BodyPart(this.x, this.y, 0, 0, Color.RED, false, 30, 50);
        dragonBody.add(tmp);
        Main.gameData.addGameData(tmp.hitbox);
        Main.gameData.addGameData(tmp);
    }
    
    public void remove(){
        double rightDistance = Main.gameData.nen.x + GamePanel.CAMERA_WIDTH/2 + 250;
        double leftDistance = Main.gameData.nen.x - GamePanel.CAMERA_WIDTH/2 - 250;
        double tailX = dragonBody.get(dragonBody.size()-1).getCollisionBox().x;
        
        if( tailX > rightDistance || tailX < leftDistance)
        { 
            for(BodyPart h : dragonBody)
            {
                Main.gameData.removeGameData(h.hitbox);
                Main.gameData.removeGameData(h); 
            }
            Main.gameData.removeGameData(this);
        }
    }
   
    public double incrementX(double x){
        return x += 2 * Math.PI * direction;
    }
    
    public double incrementY(double  x){
        return direction * amplitude * Math.sin(peroid * Math.toRadians(x));
    }
  
    
    @Override
    public Rectangle2D.Double getCollisionBox() {
        return new Rectangle2D.Double(super.x - super.size, super.y - super.size, super.size * 2, super.size * 2);
    }
}
