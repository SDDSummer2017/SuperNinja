
package Model;

import EventHandling.Observer;
import EventHandling.PlayerHudHandler;
import Model.States.Nen.NeutralCombat;
import Model.States.Nen.NeutralMotion;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import View.GamePanel;
import EventHandling.SoundHandler; 
 
 
import EventHandling.Subject;
 
import Model.States.Nen.Dash;
import Model.States.Nen.GroundShatter;
import Model.States.Nen.Move;
import Model.States.Nen.Jump;
 
import Model.States.Nen.LightAttack;
import Model.States.Nen.HeavyAttack;
import Model.States.Nen.ThrowingMode;
import Model.States.Nen.Whirlwind;
import Physics.Velocity;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
public class Nen extends GameFigure implements Subject{
   
     
    private int jumpHeight = 0;
    private int dy = -7;
    private int hitBox_X = 0;
    private int hitBox_Y = 0;
    private int hitBox_width = 0;
    private int hitBox_height = 0;
    private final int idleAnimationDelay = 2;
    private int idleAnimationDelayCounter = 0;
      ArrayList<Observer> observers;
    private final SoundHandler soundHandler = new SoundHandler("");
    public boolean removeSelf = false; 
 
    public Nen(int x, int y, int size) {
       super(x, y, size,
                8,  // animation length
                "Nen", true); //name for animation image file path
        this.hitBox_width = size;
        this.hitBox_height = size;
        this.health = 100;
        mass = 60; 
        velocity = new Velocity();
        velocity.dx = 0;
        velocity.dy = 0;
        forces = new ArrayList<>();
        observers = new ArrayList<Observer>();
        mState = new NeutralMotion(this, new ArrayList<Observer>());
        cState = new NeutralCombat(this, new ArrayList<Observer>());
       
     
        mState.registerObserver(soundHandler);
        cState.registerObserver(soundHandler);
 
        movingRight = jump = movingLeft = false;
        String imagePath = System.getProperty("user.dir");
        // separator: Windows '\', Linux '/'
        String separator = System.getProperty("file.separator");

        staticImage = GameFigure.getImage(imagePath + separator + "images" + separator
                + "Nen.png");

        
    }

 @Override
    public void render(Graphics g) {
        
        Image frameImage;
        
        //DEATH 
        if(this.health <= 0){
            resetAnimationFrames("death");
            
            //Select frame image based on which direction Nen is facing
            if (deathFrameIndex == 0 ){
                diedFacingRight = isFacingRight;
            }
            frameImage = (diedFacingRight) ? deathAnimation[deathFrameIndex] : GameFigure.flipImageHorizontally(deathAnimation[deathFrameIndex]);
            
            g.drawImage(frameImage, (int) super.x, (int) super.y, (int) super.size, (int) super.size, null);
            deathFrameIndex = (deathFrameIndex == deathAnimation.length-1) ? deathFrameIndex : deathFrameIndex + 1;                 
        }
        //Ranged ATTACK
        else if(cState instanceof ThrowingMode){
            
            resetAnimationFrames("attack");
            //Check if we are actually thowing or just in "throwing mode". If we are actually throwing, we play the full throw animation, if we are 
            //just in throw mode we just loop on the first frame of the throw animation
            if (((ThrowingMode)cState).throwing == true){
                frameImage = (isFacingRight) ? rangeAttackAnimation[attackFrameIndex] : GameFigure.flipImageHorizontally(rangeAttackAnimation[attackFrameIndex]);
                attackFrameIndex = (attackFrameIndex == rangeAttackAnimation.length-1) ? 0 : attackFrameIndex + 1;
            }
            else{
               frameImage = (isFacingRight) ? rangeAttackAnimation[2] : GameFigure.flipImageHorizontally(rangeAttackAnimation[2]); 
            }
            
            
            g.drawImage(frameImage, (int) super.x, (int) super.y, (int) super.size, (int) super.size, null);
        }
        //LIGHT ATTACK
        else if(cState instanceof LightAttack){
            resetAnimationFrames("attack");
            
            //Select frame image based on which direction Nen is facing
            frameImage = (isFacingRight) ? lightAttackAnimation[attackFrameIndex] : GameFigure.flipImageHorizontally(lightAttackAnimation[attackFrameIndex]);
            
            g.drawImage(frameImage, (int) super.x, (int) super.y, (int) super.size, (int) super.size, null);
            attackFrameIndex = (attackFrameIndex == lightAttackAnimation.length-1) ? 0 : attackFrameIndex + 1;                 
        }
        //HEAVY ATTACK
        else if(cState instanceof HeavyAttack){
            resetAnimationFrames("attack");
            
            //Select frame image based on which direction Nen is facing
            frameImage = (isFacingRight) ? heavyAttackAnimation[attackFrameIndex] : GameFigure.flipImageHorizontally(heavyAttackAnimation[attackFrameIndex]);
            
            g.drawImage(frameImage, (int) super.x, (int) super.y, (int) super.size, (int) super.size, null);
            attackFrameIndex = (attackFrameIndex == heavyAttackAnimation.length-1) ? 0 : attackFrameIndex + 1;                      
        }
        //WhirlWind
        else if(cState instanceof Whirlwind){
            resetAnimationFrames("attack");
            
            //Select frame image based on which direction Nen is facing
            frameImage = (isFacingRight) ? specialAttack1Animation[attackFrameIndex] : GameFigure.flipImageHorizontally(specialAttack1Animation[attackFrameIndex]);
            
            g.drawImage(frameImage, (int) super.x, (int) super.y, (int) super.size, (int) super.size, null);
            attackFrameIndex = (attackFrameIndex == specialAttack1Animation.length-1) ? 0 : attackFrameIndex + 1;                      
        }
        //Earth Shatter
        else if(cState instanceof GroundShatter){
            resetAnimationFrames("attack");
            
            //Select frame image based on which direction Nen is facing
            frameImage = (isFacingRight) ? specialAttack2Animation[attackFrameIndex] : GameFigure.flipImageHorizontally(specialAttack2Animation[attackFrameIndex]);
            
            g.drawImage(frameImage, (int) super.x, (int) super.y, (int) super.size, (int) super.size, null);
            attackFrameIndex = (attackFrameIndex == specialAttack2Animation.length-1) ? 0 : attackFrameIndex + 1;                      
        }
        //RUN
        else if(mState instanceof Move)
        {
            //If we are moving, reset the idle animtion frame index
            resetAnimationFrames("move");
            
            //Select frame image based on which direction Nen is facing
            frameImage = (isFacingRight) ? runAnimation[moveFrameIndex] : GameFigure.flipImageHorizontally(runAnimation[moveFrameIndex]);
            
            g.drawImage(frameImage, (int) super.x, (int) super.y, (int) super.size, (int) super.size, null);
            moveFrameIndex = (moveFrameIndex == runAnimation.length-1) ? 0 : moveFrameIndex + 1;
        }
        //JUMP
        else if(mState instanceof Jump){
            resetAnimationFrames("jump");
                      
            //Select frame image based on which direction Nen is facing
            frameImage = (isFacingRight) ? jumpAnimation[jumpFrameIndex] : GameFigure.flipImageHorizontally(jumpAnimation[jumpFrameIndex]);
            
            g.drawImage(frameImage, (int) super.x, (int) super.y, (int) super.size, (int) super.size, null);
            jumpFrameIndex = (jumpFrameIndex == jumpAnimation.length-1) ? 0 : jumpFrameIndex + 1;
        }
        //DASH
        else if(mState instanceof Dash)
        {
            //If we are moving, reset the idle animtion frame index
            resetAnimationFrames("move");
            
            //Select frame image based on which direction Nen is facing
            frameImage = (isFacingRight) ? dashAnimation[moveFrameIndex] : GameFigure.flipImageHorizontally(dashAnimation[moveFrameIndex]);
            
            g.drawImage(frameImage, (int) super.x, (int) super.y, (int) super.size, (int) super.size, null);
            moveFrameIndex = (moveFrameIndex == dashAnimation.length-1) ? 0 : moveFrameIndex + 1;
        }
        else
        //IDLE
        {
            //If they are standing still we need to reset the frameCounter
            resetAnimationFrames("idle");

            //Select frame image based on which direction Nen is facing
            frameImage = (isFacingRight) ? idleAnimation[idleFrameIndex] : GameFigure.flipImageHorizontally(idleAnimation[idleFrameIndex]);
            
            g.drawImage(frameImage, (int) super.x, (int) super.y, (int) super.size, (int) super.size, null);
            if (idleAnimationDelayCounter == idleAnimationDelay){
               idleFrameIndex = (idleFrameIndex == idleAnimation.length-1) ? 0 : idleFrameIndex + 1;
               idleAnimationDelayCounter = 0;
            }
            else {
                idleAnimationDelayCounter++;
            }
        
        }

        g.setColor(Color.red);
        g.fillRect(3, GamePanel.CAMERA_HEIGHT - 102, 10, 100);

        g.setColor(Color.green);
        g.fillRect(3, GamePanel.CAMERA_HEIGHT - (int) this.health - 2, 10, (int) this.health);
        ((Graphics2D)g).draw(getCollisionBox());
    }
    
    private void resetAnimationFrames(String currentAnimation){
//        System.out.println("Animation Reset Called from: " + currentAnimation);
        if (!"idle".equals(currentAnimation))idleFrameIndex = 0;
        if (!"move".equals(currentAnimation))moveFrameIndex = 0;
        if (!"jump".equals(currentAnimation))jumpFrameIndex = 0;
        if (!"attack".equals(currentAnimation))attackFrameIndex = 0;
    }
    
    @Override
    public void update() {
 
        
        //super.update();
        
        //Eventually all this will be moved to gameFigure
        effectsManager.applyEffects(this);
        
        if(this.health <= 0) 
        {
            cState = new Model.States.Nen.Death(this, cState.observers);
        }   
        
        if(cState instanceof NeutralCombat)
            mState.execute();
        else
            cState.execute();
        if(this.removeSelf)
        {
            observers.clear(); 
        }
        notifyObservers();
    }

    public void translate(int dx, int dy) {
        if (super.x <= 0 && dx < 0) {
            dx = 0;
        }
        if (((super.x + super.size) >= GamePanel.CAMERA_WIDTH) && (dx > 0)) {
            dx = 0;
        }
        super.x += dx;
        super.y += dy;
    }
 
    public void resetNen() {
 
        this.health = 100;
        jump = movingLeft = movingRight = false;
        jumpHeight = 0;
        jump = false;
        dy = -7;
        super.y = GamePanel.CAMERA_HEIGHT - super.size;
        super.x = GamePanel.CAMERA_WIDTH/2;
    }

    public void setCollisionOffsetsAndSize(int x, int y, int width, int height)
    {
        hitBox_X = x;
        hitBox_Y = y;
        hitBox_width = width;
        hitBox_height = height;
    }

    @Override
    public String toString() {
        return super.x + ", " + super.y;
    }

    @Override
    public Rectangle2D.Double getCollisionBox() {
        return new Rectangle2D.Double(super.x + hitBox_X, super.y + hitBox_Y, hitBox_width, hitBox_height);
    }

    @Override
    public void registerObserver(Observer observer) {
       observers.add(observer);
    }

    @Override
    public void deregisterObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
         for(Observer o : observers)
         {
             if(o instanceof PlayerHudHandler)
             {
                 ((PlayerHudHandler) o).onNotify(this);
             } 
         }
         
    }

    @Override
    public void notifyObservers(String event) {
        for(Observer o : observers)
         {
            o.onNotify(event);
         }
    }

} 