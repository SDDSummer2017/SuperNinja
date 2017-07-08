package Model;
 
import Controller.Main;
import StatusEffects.DamageEffect;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import javax.vecmath.Vector2f;
import View.GamePanel;


public class Shuriken extends GameFigure {
    
    private final Color color;
    public double targetX, targetY, dx, dy;
    private final double damage = 50;
    private HitBox hitbox;
    private final float moveDistance = 20;
    Vector2f currentLocation;
    Vector2f targetPath;
    
    
    public Shuriken(double x, double y, double tx, double ty, Color color, boolean isGoodGuy) {
        super(x, y, 3, isGoodGuy);
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
        
        hitbox = new HitBox(x, y, (int)super.size * 2, (int)super.size * 2, this, new DamageEffect(this, 10, 5000));
        Main.gameData.addGameData(hitbox);
//hitbox = new HitBox(x, y, GRAVITY, GRAVITY, this, (int)super.size * 2, (int)super.size * 2, new DamageEffect(this, 10, 5000));
        
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
        
         
//        synchronized (bullets) {
//            for (GameFigure b : bullets) {
//                b.update();
//                if(b.hit==true 
//                        || b.x < 0
//                        || b.x > GamePanel.CAMERA_WIDTH
//                        || b.y > GamePanel.CAMERA_HEIGHT
//                        || b.y <0)deadBullets.add(b);
//            }
//        }       
  
        }

    @Override
    public Rectangle2D.Double getCollisionBox() {
        return new Rectangle2D.Double(super.x - super.size, super.y - super.size, super.size * 2, super.size * 2);
    }

    
}

