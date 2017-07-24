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
    public final Image[] runAnimation, idleAnimation, jumpAnimation, dashAnimation, deathAnimation; 
    public final Image[] lightAttackAnimation, heavyAttackAnimation, rangeAttackAnimation;
    public final Image[] specialAttack1Animation, specialAttack2Animation;
    
 
    //Static game figure constructor (no animation)
 
    public double mass; 
    public Acceleration acceleration;
    public Velocity velocity; 
    public ArrayList<Force> forces;
 
    public double maxHealth; 
    public GameFigure(double x, double y, double size, boolean isGoodGuy, ImageResource imageResource) {
 
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
        this.deathAnimation = imageResource.deathAnimation;
        this.runAnimation = imageResource.runAnimation;
        this.dashAnimation = imageResource.dashAnimation;
        this.lightAttackAnimation = imageResource.lightAttackAnimation;
        this.heavyAttackAnimation = imageResource.heavyAttackAnimation;
        this.rangeAttackAnimation = imageResource.rangeAttackAnimation;
        this.specialAttack1Animation = imageResource.specialAttack1Animation;
        this.specialAttack2Animation = imageResource.specialAttack2Animation;
        this.idleAnimation = imageResource.idleAnimation;
        this.jumpAnimation = imageResource.jumpAnimation;
        this.staticImage = null;
        this.forces = new ArrayList<Force>();
        
    }
    //Animation game figure constructor (backwards compatibility
    public GameFigure(double x, double y, double size, int animationLength, String name, boolean isGoodGuy, ImageResource imageResource) {
        this.hit = false;
        this.x = x;
        this.y = y;
        this.size = size;
        this.location = new Point2D.Double(x - (size/2), y-(size/2));
        health = 100;
 
        this.isGoodGuy = isGoodGuy;
        this.effectsManager = new EffectsManager();
 
        maxHealth = health;
 
        this.deathAnimation = imageResource.deathAnimation;
        this.runAnimation = imageResource.runAnimation;
        this.dashAnimation = imageResource.deathAnimation;
        this.lightAttackAnimation = imageResource.lightAttackAnimation ;
        this.heavyAttackAnimation =  imageResource.heavyAttackAnimation;
        this.rangeAttackAnimation = imageResource.rangeAttackAnimation;
        this.specialAttack1Animation = imageResource.specialAttack1Animation;
        this.specialAttack2Animation = imageResource.specialAttack2Animation;
        this.idleAnimation = imageResource.idleAnimation;
        this.jumpAnimation = imageResource.jumpAnimation;
        this.staticImage = null;
        
        this.diedFacingRight = false;
        
        this.loadAnimations(name);
    }
    //Prviate so that it can be called from constructor
    private void loadAnimations(String name){
        String baseDirectory = System.getProperty("user.dir");
        String separator = System.getProperty("file.separator");
        String imagePath = baseDirectory + separator + "images" + separator + name;
        

        String movePath = imagePath + separator + "Run";
        String idlePath = imagePath + separator + "Idle";
        String dashPath = imagePath + separator + "Dash"; 
        String jumpPath = imagePath + separator + "Jump";
        String deathPath = imagePath + separator + "Death";
        String lightAttackPath = imagePath + separator + "Light_Attack";
        String heavyAttackPath = imagePath + separator + "Heavy_Attack";
        String rangeAttackPath = imagePath + separator + "Range_Attack";
        String specialAttack1Path = imagePath + separator + "Special_Attack1";
        String specialAttack2Path = imagePath + separator + "Special_Attack2";
        
        
        Image img;
        //Move Animation
        if (fileExists(movePath)){        
            for(int i=0;i<runAnimation.length;i++){
                runAnimation[i] = getImage(movePath + separator + "Run_" + i + ".png");          
            }
        }
        
        //Idle Animation 
        if (fileExists(idlePath)){
            for(int i=0;i<idleAnimation.length;i++){
                idleAnimation[i] = getImage(idlePath + separator + "Idle_" + i + ".png");
            } 
        }
        
        //Evade Animation 
        if (fileExists(dashPath)){
            for(int i=0;i<dashAnimation.length;i++){
                dashAnimation[i] = getImage(dashPath + separator +  "Dash_" + i + ".png");
            }
        }
        //Jump Animation
        if(fileExists(jumpPath)){
            for(int i=0;i<jumpAnimation.length;i++){
                jumpAnimation[i] = getImage(jumpPath + separator + "Jump_" + i + ".png");
            }
        }
        //Light Attack Animation
        if (fileExists(lightAttackPath)){
            for(int i=0;i<lightAttackAnimation.length;i++){
                lightAttackAnimation[i] = getImage(lightAttackPath + separator + "Light_Attack_" + i + ".png");
            }
        }
        //Heavy Attack Right Animation
        if (fileExists(heavyAttackPath)){
            for(int i=0;i<heavyAttackAnimation.length;i++){
                heavyAttackAnimation[i] = getImage(heavyAttackPath + separator + "Heavy_Attack_" + i + ".png");
            }  
        }
        //Ranged Attack Animation
        if (fileExists(rangeAttackPath)){
            for(int i=0;i<rangeAttackAnimation.length;i++){
                rangeAttackAnimation[i] = getImage(rangeAttackPath + separator + "Range_Attack_" + i + ".png");
            }
        }
        //Special Attack 1 Animation
        if (fileExists(specialAttack1Path)){
            for(int i=0;i<specialAttack1Animation.length;i++){
                specialAttack1Animation[i] = getImage(specialAttack1Path + separator + "Special_Attack1_" + i + ".png");
            }
        }
        //Special Attack 2 Animation
        if (fileExists(specialAttack2Path)){
            for(int i=0;i<specialAttack2Animation.length;i++){
                specialAttack2Animation[i] = getImage(specialAttack2Path + separator + "Special_Attack2_" + i + ".png");
            }
        }
        //Death Animation
        if (fileExists(deathPath)){
            for(int i=0;i<deathAnimation.length;i++){
                deathAnimation[i] = getImage(deathPath + separator + "Dead_" + i + ".png");
            }
        }
    }
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
