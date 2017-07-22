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
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * Just trying to merge
 * @author Garrett A. Clement
 */
public class FlameDragonSpell extends Projectiles {
    ArrayList<HitBox> dragonBody;
    
    double pi = Math.PI;
    double peroid = 2 * Math.PI/5;
    double amplitude = 20;
    double direction;
    public FlameDragonSpell(double x, double y, double size, int direction) {
        super(x, y, 0, 0, Color.RED, false, 30, 40);
        //super(x, y, size); 
        this.direction = direction;
        dragonBody = new ArrayList<>();
        hitbox = new HitBox(x, y, 50, 50, this, new DamageEffect(this, 30, 5000), new DotEffect(this, 2, 5000, 1000));
        dragonBody.add(hitbox);
        Main.gameData.addGameData(hitbox);
        
    }
        
    @Override
    public void render(Graphics g) {
      
        for(HitBox h : dragonBody)
        {
            double x = h.getCollisionBox().x;
            double y = h.getCollisionBox().y;
            g.fillOval((int)x, (int)y, (int)super.size , (int)super.size);
 
        } 
    }
    

    @Override
    public void update() {       
        for(HitBox h : dragonBody)
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
        HitBox tmp = new HitBox(x, y, 50, 50, this, new DamageEffect(this, 30, 500));
        dragonBody.add(tmp);
        Main.gameData.addGameData(tmp);
    }
    
    public void remove(){
        double rightDistance = Main.gameData.nen.x + GamePanel.CAMERA_WIDTH/2 + 250;
        double leftDistance = Main.gameData.nen.x - GamePanel.CAMERA_WIDTH/2 - 250;
        double tailX = dragonBody.get(dragonBody.size()-1).getCollisionBox().x;
        
        if( tailX > rightDistance || tailX < leftDistance)
        { 
            for(HitBox h : dragonBody)
                Main.gameData.removeGameData(h);
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
