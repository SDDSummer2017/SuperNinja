package Model;

import Controller.Main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import javax.vecmath.Vector2f;
import View.GamePanel;


public class EnemyBullet extends GameFigure {
    
    private final Color color;
    public double targetX, targetY, dx, dy;
    private final double damage = 3;
    private final float moveDistance = 10;
    Vector2f currentLocation;
    Vector2f targetPath;
    
 

    
    public EnemyBullet(double x, double y, double tx, double ty, Color color) {
        super(x, y, 3);
        this.targetX = tx;
        this.targetY = ty;
        this.color = color;
        this.currentLocation = new Vector2f((float)super.x, (float)super.y);
        this.targetPath = new Vector2f((float)targetX,(float)targetY);
        targetPath.sub(currentLocation);
        targetPath.scale((float) Math.sqrt((double)(GamePanel.CAMERA_HEIGHT * GamePanel.CAMERA_HEIGHT) *
                (double)(GamePanel.CAMERA_WIDTH * GamePanel.WIDTH)));
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
        if (this.getCollisionBox().intersects(Main.gameData.nen.getCollisionBox())){
            super.hit = true;
            Main.gameData.nen.health -= damage;
        }
    }
        

    @Override
    public Rectangle2D.Double getCollisionBox() {
        return new Rectangle2D.Double(super.x - super.size, super.y - super.size, super.size * 2, super.size * 2);
    }

    
}
 