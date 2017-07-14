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
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import javax.vecmath.Vector2f;

/**
 *
 * @author matlock
 */
public abstract class Projectiles extends GameFigure{
    
    public Color color;
    public double targetX, targetY, dx, dy;
    public double damage;
    public double health;
    public double size;
    
    public final float moveDistance = 20; // moveSpeed
    Vector2f currentLocation;
    Vector2f targetPath;
 
    
    public Projectiles(double x, double y, double tx, double ty, Color color, boolean isGoodGuy, int d, double s) {
        super(x, y, s, isGoodGuy);
        this.size = s;
        this.targetX = tx;
        this.targetY = ty;
        this.color = color;
        super.damage = d;
        super.health = 999; //sets health = to a high number so that the projectile will only be destroyed when specified
    }
    
    @Override
    public void render(Graphics g) {}
    @Override
    public void update() {super.update();}

    @Override
    public Rectangle2D.Double getCollisionBox() {
        return new Rectangle2D.Double(super.x - super.size, super.y - super.size, super.size * 2, super.size * 2);
    }

    
}

