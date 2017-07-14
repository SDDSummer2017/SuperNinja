/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.Main;
import StatusEffects.DamageEffect;
import View.GamePanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import javax.vecmath.Vector2f;

/**
 *
 * @author matlock
 */
public class ProjectileSmokeBomb extends Projectiles{

    public boolean finnished;
    
    public ProjectileSmokeBomb(double x, double y, double tx, double ty, Color color, boolean isGoodGuy, int d, double s) {
        super(x, y, tx, ty, color, isGoodGuy, d, s);
        finnished = false;
        this.targetX = tx;
        this.targetY = ty;
        this.color = color;
        this.damage = 0;
        this.size = 3;
        this.currentLocation = new Vector2f((float)super.x, (float)super.y);
        this.targetPath = new Vector2f((float)targetX,(float)targetY);
        targetPath.sub(currentLocation);
        targetPath.scale((float) Math.sqrt((double)(GamePanel.CAMERA_HEIGHT * GamePanel.CAMERA_HEIGHT) *
                (double)(GamePanel.CAMERA_WIDTH * GamePanel.WIDTH)));
        targetPath.normalize();
        targetPath.scale(moveDistance);
        
        hitbox = new HitBox(x, y, (int)super.size * 2, (int)super.size * 2, this, new DamageEffect(this, 10, 5000));
        Main.gameData.addGameData(hitbox);
    }
    
    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillOval((int)x - (int)super.size, (int)y - (int)super.size, (int)super.size * 2, (int)super.size * 2);   
    }
    
    @Override
    public void update() {       
       
        currentLocation.add(targetPath);
        
        super.x = currentLocation.x;
        super.y = currentLocation.y;
        hitbox.translate(super.x, super.y);
        if (super.y + size >= 600 && !finnished){
            grow();
        }
        else if(finnished){
            this.health = 0;
        }
        else{}
    }

    public void grow(){
        this.size += 8;
        if (this.size >= 100){
            finnished = true;
        }
    }
    
    @Override
    public Rectangle2D.Double getCollisionBox() {
        return new Rectangle2D.Double(super.x - super.size, super.y - super.size, super.size * 2, super.size * 2);
    }
}
