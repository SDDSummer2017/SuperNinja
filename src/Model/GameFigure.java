package Model;

import Model.States.CombatState;
import Model.States.MotionState;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
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
    
    //Animation Attributes
    public int moveFrameIndex, idleFrameIndex, idleFrameDelayCount, jumpFrameIndex;
    public boolean jump, movingLeft, movingRight ;
    public Image staticImage;
    public final Image[] moveRightAnimation, moveLeftAnimation, idleAnimation, jumpAnimation;
    private final int moveAnimationLength, idleAnimationLength, jumpAnimationLength;

 
    //Static game figure constructor (no animation)
    public GameFigure(double x, double y, double size) {
        this.hit = false;
        this.x = x;
        this.y = y;
        this.size = size;
        this.location = new Point2D.Double(x - (size/2), y-(size/2));
        this.moveFrameIndex = this.idleFrameIndex = this.idleFrameDelayCount = this.jumpFrameIndex = 0;
        this.moveAnimationLength = this.idleAnimationLength = this.jumpAnimationLength = 0;
        this.moveLeftAnimation =  null;
        this.moveRightAnimation = null;
        this.idleAnimation = null;
        this.jumpAnimation = null;
        this.staticImage = null;
        
    }
    //Animation game figure constructor (backwards compatibility
    public GameFigure(double x, double y, double size, int mLength, int iLength, int jLength, String name) {
        this.hit = false;
        this.x = x;
        this.y = y;
        this.size = size;
        this.location = new Point2D.Double(x - (size/2), y-(size/2));
        
        this.moveAnimationLength = mLength;
        this.idleAnimationLength = iLength;
        this.jumpAnimationLength = jLength;
        this.moveLeftAnimation =  new Image[mLength];
        this.moveRightAnimation = new Image[mLength];
        this.idleAnimation = new Image[iLength];
        this.jumpAnimation = new Image[jLength];
        this.staticImage = null;
        
        this.loadAnimations(name);
        this.staticImage = null;
    }
    //Prviate so that it can be called from constructor
    private void loadAnimations(String name){
        String imagePath = System.getProperty("user.dir");
        String separator = System.getProperty("file.separator");
        //Move Animations
        for(int i=0;i<moveRightAnimation.length;i++){
            moveRightAnimation[i] = getImage(imagePath + separator + "images" + separator
                + name + "_Right" + i + ".jpg");
            
            moveLeftAnimation[i] = getImage(imagePath + separator + "images" + separator
                + name + "_Left" + i + ".jpg");
        }
        //Idle Animation 
        for(int i=0;i<idleAnimation.length;i++){
            idleAnimation[i] = getImage(imagePath + separator + "images" + separator
                + name + "_Idle" + i + ".png");
        }
        //Jump Animation
        for(int i=0;i<jumpAnimation.length;i++){
            jumpAnimation[i] = getImage(imagePath + separator + "images" + separator
                + name + "_Jump" + i + ".png");
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
