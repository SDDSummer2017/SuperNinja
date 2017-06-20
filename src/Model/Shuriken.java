package Model;

import Controller.Main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import javax.vecmath.Vector2f;
import View.GamePanel;


public class Shuriken extends GameFigure {
    
    private final Color color;
    public double targetX, targetY, dx, dy;
    private final double damage = 50;
    private final float moveDistance = 20;
    Vector2f currentLocation;
    Vector2f targetPath;
    
    
    public Shuriken(double x, double y, double tx, double ty, Color color) {
        super(x, y, 3);
        this.targetX = tx;
        this.targetY = ty;
        this.color = color;
        this.currentLocation = new Vector2f((float)super.x, (float)super.y);
        this.targetPath = new Vector2f((float)targetX,(float)targetY);
        targetPath.sub(currentLocation);
        targetPath.scale((float) Math.sqrt((double)(GamePanel.PHEIGHT * GamePanel.PHEIGHT) *
                (double)(GamePanel.PWIDTH * GamePanel.WIDTH)));
        targetPath.normalize();
        targetPath.scale(moveDistance);
        
        
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
        
        synchronized (Main.gameData.enemys) {
            for (GameFigure f : Main.gameData.enemys) {
                    if (this.getCollisionBox().intersects(f.getCollisionBox())){
                        super.hit = true;
                        f.health -= damage;
                    }
                }
            }
        }

    @Override
    public Rectangle2D.Double getCollisionBox() {
        return new Rectangle2D.Double(super.x - super.size, super.y - super.size, super.size * 2, super.size * 2);
    }

    
}

