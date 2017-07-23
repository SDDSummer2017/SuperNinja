/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import EventHandling.Observer;
import static Model.GameFigure.GRAVITY;
import Model.States.CombatState;
import Model.States.MotionState;
import Model.States.Kisara.Default;
import Model.States.Kisara.Movement;
import Model.States.Kisara.Neutral;
import Model.States.Kisara.ShadowStrike;
import Model.States.Kisara.VanishingStrike;
import View.GamePanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author matlock
 */
public class Kisara extends Enemy{
    
   public int c; // used as a display count for placeholders only
   public boolean direction; //used in movement to toggle movement towards Nen or away
   public int countDelay; // used as a delay counter so Terro isnt constantly attacking 
   ArrayList<Observer> observers = new ArrayList<>();

    public Kisara(double x, double y, double size) {
        super(x, y, size, 8, "Kisara");
        
        
        super.mState = new Neutral(this, observers);
        super.cState = new Default(this, observers);
        this.health = 80;
        this.maxHealth = health;
        this.countDelay = 0;
        
        //false for left true for right in movementState
        this.direction = false;
        
        String imagePath = System.getProperty("user.dir");
        String separator = System.getProperty("file.separator");
       
        super.hit = getImage(imagePath + separator + "images" + separator
        + "Kisara_Hit.png");
        super.attack1 = getImage(imagePath + separator + "images" + separator
                + "Kisara_ShadowStrike.png");
        super.attack2 = getImage(imagePath + separator + "images" + separator
                + "Kisara_VanishingStrike.png");
        super.movement = getImage(imagePath + separator + "images" + separator
                + "Kisara_Movement.png");
        super.block = getImage(imagePath + separator + "images" + separator
                + "Kisara_SmokeBomb.png");
        super.neutral = getImage(imagePath + separator + "images" + separator
                + "Kisara_Neutral.png");
        super.throwImage = getImage(imagePath + separator + "images" + separator
                + "Kisara_Jump.png");
        //super.staticImage = getImage(imagePath + separator + "images" + separator
        //        + "TerroStatic.png");
    }
    
    //the following delayCount functions are used to delay the actions of Kisaras' for attack
    public int getDelayCount(){
        return this.countDelay;
    }
    
    public void setDelayCount(int i){
        this.countDelay = i;
    }
    
    public void setDirection(boolean a){
        this.direction = a;
    }
    // the following count functions are used to delay Terro for movement
    public int getCount(){
        return this.c;
    }
    
    public void setCount(int i){
        this.c = i;
    }
    
    @Override
    public void setMState(MotionState state){
        this.mState = state;
    }
    
    @Override
    public void setCState(CombatState state){
        this.cState = state;
    }
    
    @Override
    public void setIndex(int i){
        this.index =i;
    }
    
    @Override
    public int getIndex(){
        return super.index;
    }
    
    @Override
    public int getX(){
        return (int) super.x;
    }
    
    @Override
    public int getY(){
        return (int) super.y;
    }
    
    @Override
    public MotionState getMState(){
        return this.mState;
    }
    
    @Override
    public CombatState getCState(){
        return this.cState;
    }
    
    @Override
    public void setImage(Image i){
        this.image = i;
    }
    public boolean getDirection(){
        return direction;
    }
    
    @Override
    public void render(Graphics g) {
        
        Image frameImage;
        
        super.render(g);
        
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
        //LIGHT ATTACK (Shadow Strike)
        else if(cState instanceof ShadowStrike){
            resetAnimationFrames("attack");
            
            //Select frame image based on which direction Nen is facing
            frameImage = (isFacingRight) ? lightAttackAnimation[attackFrameIndex] : GameFigure.flipImageHorizontally(lightAttackAnimation[attackFrameIndex]);
            
            g.drawImage(frameImage, (int) super.x, (int) super.y, (int) super.size, (int) super.size, null);
            attackFrameIndex = (attackFrameIndex == lightAttackAnimation.length-1) ? 0 : attackFrameIndex + 1;                 
        }
        //HEAVY ATTACK (Vanishing Strike)
        else if(cState instanceof VanishingStrike){
            resetAnimationFrames("attack");
            
            //Select frame image based on which direction Nen is facing
            frameImage = (isFacingRight) ? heavyAttackAnimation[attackFrameIndex] : GameFigure.flipImageHorizontally(heavyAttackAnimation[attackFrameIndex]);
            
            g.drawImage(frameImage, (int) super.x, (int) super.y, (int) super.size, (int) super.size, null);
            attackFrameIndex = (attackFrameIndex == heavyAttackAnimation.length-1) ? 0 : attackFrameIndex + 1;                      
        }
        else if(mState instanceof Movement)
        {
            //If we are moving, reset the idle animtion frame index
            idleFrameIndex = 0;
            jumpFrameIndex = 0;
            attackFrameIndex = 0;
            
            //Select frame image based on which direction Nen is facing
            frameImage = (isFacingRight) ? runAnimation[moveFrameIndex] : GameFigure.flipImageHorizontally(runAnimation[moveFrameIndex]);
            
            g.drawImage(frameImage, (int) super.x, (int) super.y, (int) super.size, (int) super.size, null);
            moveFrameIndex = (moveFrameIndex == runAnimation.length-1) ? 0 : moveFrameIndex + 1;
        }

        else
        {
            //If they are standing still we need to reset the frameCounter
            moveFrameIndex = 0;               
            jumpFrameIndex = 0;
            attackFrameIndex = 0;

            //Select frame image based on which direction Nen is facing
            frameImage = (isFacingRight) ? idleAnimation[idleFrameIndex] : GameFigure.flipImageHorizontally(idleAnimation[idleFrameIndex]);
            
            g.drawImage(frameImage, (int) super.x, (int) super.y, (int) super.size, (int) super.size, null);
            idleFrameIndex = (idleFrameIndex == idleAnimation.length-1) ? 0 : idleFrameIndex + 1;
        
        }
      
        
//        g.setColor(Color.red);
//        g.fillRect(3, GamePanel.CAMERA_HEIGHT- 102, 10, 100);
//        g.setColor(Color.green);
//        g.fillRect(3, GamePanel.CAMERA_HEIGHT - (int) this.health - 2, 10, (int) this.health);
    }
    private void resetAnimationFrames(String currentAnimation){
        if (!"idle".equals(currentAnimation))idleFrameIndex = 0;
        if (!"move".equals(currentAnimation))moveFrameIndex = 0;
        if (!"jump".equals(currentAnimation))jumpFrameIndex = 0;
        if (!"attack".equals(currentAnimation))attackFrameIndex = 0;
    }
    @Override
    public void update() {
        super.update();
        this.mState.execute();
        this.cState.execute();
        
        if(this.y <= 450){
            this.y += GRAVITY;
        }
        else{
            airborn = false;
        }
    }

    @Override
    public Rectangle2D.Double getCollisionBox() {
        return new Rectangle2D.Double(super.x, super.y, super.size, super.size);
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
    
    @Override
    public String toString() {
        return super.x + ", " + super.y;
    }
    
}

