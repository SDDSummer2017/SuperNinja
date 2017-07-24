package Model;

import Controller.Main;
import Model.States.CombatState;
import Model.States.MotionState;
import Physics.Acceleration;
import Physics.Force;
import Physics.Velocity; 
import StatusEffects.EffectsManager;
import java.awt.Image; 
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D; 
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
 
import java.util.ArrayList;
 

public abstract class GameFigure implements Collision, Renderable, Updateable {

    public double x; // for a faster access
    public double y;
    public double size;
    public double health;
    public double damage;
    public Rectangle2D.Double collisionBox;
    public boolean isFacingRight;
    public CombatState cState;
    public MotionState mState;
    public boolean airborn;
    public Point2D.Double location;
    public static final int GRAVITY = 8;
    public boolean hit;
    public EffectsManager effectsManager;
    public HitBox hitbox;
    public boolean isGoodGuy;
    //Animation Attributes
    public int moveFrameIndex, idleFrameIndex, jumpFrameIndex, attackFrameIndex, deathFrameIndex;
    public boolean jump, movingLeft, movingRight, diedFacingRight;
    public Image staticImage;


 
    public double mass; 
    public Acceleration acceleration;
    public Velocity velocity; 
    public ArrayList<Force> forces;
 
    public double maxHealth; 
    public GameFigure(double x, double y, double size, boolean isGoodGuy) {
 
        this.hit = false;
        this.x = x;
        this.y = y;
        this.size = size;
        this.location = new Point2D.Double(x - (size/2), y-(size/2));
        health = 100;
 
        this.isGoodGuy = isGoodGuy;
        this.effectsManager = new EffectsManager();
 
        maxHealth = health; 
  
        this.moveFrameIndex = this.idleFrameIndex = this.jumpFrameIndex = this.deathFrameIndex = 0;
        this.staticImage = null;
        this.forces = new ArrayList<Force>();
        
    }
    //Animation game figure constructor (backwards compatibility
    public GameFigure(double x, double y, double size, int animationLength, String name, boolean isGoodGuy) {
        this.hit = false;
        this.x = x;
        this.y = y;
        this.size = size;
        this.location = new Point2D.Double(x - (size/2), y-(size/2));
        health = 100;
 
        this.isGoodGuy = isGoodGuy;
        this.effectsManager = new EffectsManager();
 
        maxHealth = health;
 
        this.staticImage = null;
        
        this.diedFacingRight = false;
        
    }
    //Prviate so that it can be called from constructor

    public boolean fileExists(String path){
        return new File(path).exists();
    }
    
    public Point2D.Double getLocation(){
        return location;
    }

    @Override
    public void update(){

        effectsManager.applyEffects(this);
        
        if(this.health <= 0) 
            Main.gameData.removeGameData(this); 
        
    }
    public void calculatePhysics()
    {
        
       //ApplyForce
       for(Force f : forces)
       {
        velocity.dx += f.dvx();
        velocity.dy += f.dvy(); 
        
       } 
       
       //Apply Velocity
       x += velocity.dx; 
       y += velocity.dy; 
       
    }
    /******************************************
     * STATIC METHODS
    ******************************************/
    public static Image getImage(String fileName) {
        Image image = null;
        try {
            image = ImageIO.read(new File(fileName));
        } catch (IOException ioe) {
            System.out.println("Error: Cannot open image:" + fileName);
            JOptionPane.showMessageDialog(null, "Error: Cannot open image:" + fileName);
        }
        return image;
    }
    public static Image flipImageHorizontally(Image img)
    {
       
        AffineTransform at;
        AffineTransformOp ato;
        BufferedImage bi;   
        
        at = AffineTransform.getScaleInstance(-1, 1);     
        at.translate(-img.getWidth(null), 0);
        ato = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        
        bi = (BufferedImage) img;
        img = ato.filter(bi, null); 
        return img;
    }
}
