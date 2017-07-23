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
import javax.vecmath.Vector2d;
import javax.vecmath.Vector2f;

/**
 *
 * @author Garrett A. Clement
 */
public class SoulFlameSpell extends Projectiles {
   
    
    private final Color color;
    public double targetX, targetY, dx, dy;
    private final double damage = 50;
    
    private final float moveDistance = 20;
    Vector2f currentLocation;
    Vector2f targetPath;
    
    
    public SoulFlameSpell(double x, double y, double tx, double ty) {
        super(x, y, tx, ty, Color.RED, false, 25, 40);
        //super(x, y, 40);
        this.targetX = tx;
        this.targetY = ty; 
        this.color  = Color.RED;
        this.currentLocation = new Vector2f((float)super.x, (float)super.y);
        this.targetPath = new Vector2f((float)targetX,(float)targetY);
        targetPath.sub(currentLocation);
        targetPath.normalize();
        targetPath.scale(moveDistance);
        
        hitbox = new HitBox(x - size/2, y - size/2, 40, 40, this, new DamageEffect(this, 15, 5000), new DotEffect(this, 2, 5000, 1000));
        Main.gameData.addGameData(hitbox); 
        
        
    }
    
    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillOval((int)(x - super.size), (int)(y - super.size), (int)super.size, (int)super.size);   
    }
    @Override
    public void update() {       

        currentLocation.add(targetPath);

        super.x = currentLocation.x;
        super.y = currentLocation.y;
        hitbox.translate(super.x - super.size, super.y - super.size);
    }

    @Override
    public Rectangle2D.Double getCollisionBox() {
        return new Rectangle2D.Double(super.x, super.y, super.size * 2, super.size * 2);
    }

}
