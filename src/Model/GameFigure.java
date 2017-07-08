package Model;

import Model.States.CombatState;
import Model.States.MotionState;
import Physics.Acceleration;
import Physics.Force;
import Physics.Velocity;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
 
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
 
    public HitBox hitbox;
    
    //Animation Attributes
    public int moveFrameIndex, idleFrameIndex, jumpFrameIndex, attackFrameIndex;
    public boolean jump, movingLeft, movingRight ;
    public Image staticImage;
    public final Image[] runAnimation, idleAnimation, jumpAnimation, dashAnimation; 
    public final Image[] lightAttackAnimation, heavyAttackAnimation, rangeAttackAnimation;
   
 
    //Static game figure constructor (no animation)
 
    public double mass; 
    public Acceleration acceleration;
    public Velocity velocity; 
    public ArrayList<Force> forces;
 
    public GameFigure(double x, double y, double size) {
        this.hit = false;
        this.x = x;
        this.y = y;
        this.size = size;
        this.location = new Point2D.Double(x - (size/2), y-(size/2));
        health = 100;
        this.moveFrameIndex = this.idleFrameIndex = this.jumpFrameIndex = 0;
        this.runAnimation = null;
        this.dashAnimation = null;
        this.lightAttackAnimation = null;
        this.heavyAttackAnimation = null;
        this.rangeAttackAnimation = null;
        this.idleAnimation = null;
        this.jumpAnimation = null;
        this.staticImage = null;
        
    }
    //Animation game figure constructor (backwards compatibility
    public GameFigure(double x, double y, double size, int animationLength, String name) {
        this.hit = false;
        this.x = x;
        this.y = y;
        this.size = size;
        this.location = new Point2D.Double(x - (size/2), y-(size/2));
        health = 100;
        this.runAnimation = new Image[animationLength];
        this.dashAnimation = new Image[animationLength];
        this.lightAttackAnimation = new Image[animationLength];
        this.heavyAttackAnimation = new Image[animationLength];
        this.rangeAttackAnimation = new Image[animationLength];
        this.idleAnimation = new Image[animationLength];
        this.jumpAnimation = new Image[animationLength];
        this.staticImage = null;
        
        this.loadAnimations(name);
    }
    //Prviate so that it can be called from constructor
    private void loadAnimations(String name){
        String imagePath = System.getProperty("user.dir");
        String separator = System.getProperty("file.separator");
        Image img;
        //Move Animation
        for(int i=0;i<runAnimation.length;i++){
            runAnimation[i] = getImage(imagePath + separator + "images" + separator + name + separator + "Run" + separator
                + "Run__00" + i + ".png");          
        }
        //Idle Animation 
        for(int i=0;i<idleAnimation.length;i++){
            idleAnimation[i] = getImage(imagePath + separator + "images" + separator + name + separator + "Idle" + separator
                + "Idle__00" + i + ".png");
        }
        //Evade Animation 
        for(int i=0;i<dashAnimation.length;i++){
            dashAnimation[i] = getImage(imagePath + separator + "images" + separator + name + separator + "Dash" + separator
                + "Slide__00" + i + ".png");
        }
        //Jump Animation
        for(int i=0;i<jumpAnimation.length;i++){
            jumpAnimation[i] = getImage(imagePath + separator + "images" + separator + name + separator + "Jump" + separator
                + "Jump__00" + i + ".png");
        }
        //Light Attack Animation
        for(int i=0;i<lightAttackAnimation.length;i++){
            lightAttackAnimation[i] = getImage(imagePath + separator + "images" + separator + name + separator + "Light_Attack" + separator
                + "Attack__00" + i + ".png");
        }
        //Heavy Attack Right Animation
        for(int i=0;i<heavyAttackAnimation.length;i++){
            heavyAttackAnimation[i] = getImage(imagePath + separator + "images" + separator + name + separator + "Heavy_Attack" + separator
                + "Attack__00" + i + ".png");
        }      
        //Ranged Attack Animation
        for(int i=0;i<rangeAttackAnimation.length;i++){
            rangeAttackAnimation[i] = getImage(imagePath + separator + "images" + separator + name + separator + "Range_Attack" + separator
                + "Throw__00" + i + ".png");
        }
    }
    
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
    
    public Point2D.Double getLocation(){
        return location;
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
     
    


    

    
    
}
