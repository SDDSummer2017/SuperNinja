package Model;

import Model.States.CombatState;
import Model.States.MotionState;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.AffineTransformOp;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public abstract class GameFigure implements Collision {

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
    public int moveFrameIndex, idleFrameIndex, jumpFrameIndex, attackFrameIndex, idleFrameDelayCount;
    public boolean jump, movingLeft, movingRight ;
    public Image staticImage;
    public final Image[] moveRightAnimation, moveLeftAnimation, idleAnimation, jumpAnimation, attackRightAnimation, attackLeftAnimation;

 
    //Static game figure constructor (no animation)
    public GameFigure(double x, double y, double size) {
        this.hit = false;
        this.x = x;
        this.y = y;
        this.size = size;
        this.location = new Point2D.Double(x - (size/2), y-(size/2));
        health = 100;
        this.moveFrameIndex = this.idleFrameIndex = this.idleFrameDelayCount = this.jumpFrameIndex = 0;
        this.moveLeftAnimation =  null;
        this.attackLeftAnimation = null;
        this.moveRightAnimation = null;
        this.attackRightAnimation = null;
        this.idleAnimation = null;
        this.jumpAnimation = null;
        this.staticImage = null;
        
    }
    //Animation game figure constructor (backwards compatibility
    public GameFigure(double x, double y, double size, int mLength, int iLength, int jLength, int arLength, int alLength, String name) {
        this.hit = false;
        this.x = x;
        this.y = y;
        this.size = size;
        this.location = new Point2D.Double(x - (size/2), y-(size/2));
        health = 100;
        this.moveLeftAnimation =  new Image[mLength];
        this.moveRightAnimation = new Image[mLength];
        this.attackLeftAnimation = new Image[arLength];
        this.attackRightAnimation = new Image[alLength];
        this.idleAnimation = new Image[iLength];
        this.jumpAnimation = new Image[jLength];
        this.staticImage = null;
        
        this.loadAnimations(name);
    }
    //Prviate so that it can be called from constructor
    private void loadAnimations(String name){
        String imagePath = System.getProperty("user.dir");
        String separator = System.getProperty("file.separator");
        //Move Animations
        for(int i=0;i<moveRightAnimation.length;i++){
            moveRightAnimation[i] = getImage(imagePath + separator + "images" + separator + name + separator + "Run_Right" + separator
                + "Run__00" + i + ".png");
            
            moveLeftAnimation[i] = getImage(imagePath + separator + "images" + separator + name + separator + "Run_Left" + separator
                + "Run__00" + i + ".png");
        }
        //Idle Animation 
        for(int i=0;i<idleAnimation.length;i++){
            idleAnimation[i] = getImage(imagePath + separator + "images" + separator + name + separator + "Idle" + separator
                + "Idle__00" + i + ".png");
        }
        //Jump Animation
        for(int i=0;i<jumpAnimation.length;i++){
            jumpAnimation[i] = getImage(imagePath + separator + "images" + separator + name + separator + "Jump" + separator
                + "Jump__00" + i + ".png");
        }
        //Attack Right Animation
        for(int i=0;i<attackRightAnimation.length;i++){
            attackRightAnimation[i] = getImage(imagePath + separator + "images" + separator + name + separator + "Attack_Right" + separator
                + "Attack__00" + i + ".png");
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
    
    public Point2D.Double getLocation(){
        return location;
    }

    
    public abstract void render(Graphics g);

    public abstract void update();

}
