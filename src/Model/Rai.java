/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import EventHandling.Observer;
import Model.States.CombatState;
import Model.States.MotionState;
import Model.States.Rai.Default;
import Model.States.Rai.Movement;
import Model.States.Rai.Neutral;
import Model.States.Rai.SteelTwister;
import Model.States.Rai.ViperStrike;
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
public class Rai extends Enemy {
    public int c; // used as a display count for placeholders only
    
    public Rai(double x, double y, double size) {
        super(x, y, size, 8, "Rai");
        
        ArrayList<Observer> observers = new ArrayList<>();
        super.mState = new Neutral(this, observers);
        super.cState = new Default(this, observers);
        this.health = 120;
        
        //System.out.println("mState = " + super.mState);
        //System.out.println("cState = " + super.cState);
        
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
        //LIGHT ATTACK (Steel Twister)
        else if(cState instanceof SteelTwister){
            resetAnimationFrames("attack");
            
            //Select frame image based on which direction Nen is facing
            frameImage = (isFacingRight) ? lightAttackAnimation[attackFrameIndex] : GameFigure.flipImageHorizontally(lightAttackAnimation[attackFrameIndex]);
            
            g.drawImage(frameImage, (int) super.x, (int) super.y, (int) super.size, (int) super.size, null);
            attackFrameIndex = (attackFrameIndex == lightAttackAnimation.length-1) ? 0 : attackFrameIndex + 1;                 
        }
        //HEAVY ATTACK (Viper Strike)
        else if(cState instanceof ViperStrike){
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
    
    }
    private void resetAnimationFrames(String currentAnimation){
        if (!"idle".equals(currentAnimation))idleFrameIndex = 0;
        if (!"move".equals(currentAnimation))moveFrameIndex = 0;
        if (!"jump".equals(currentAnimation))jumpFrameIndex = 0;
        if (!"attack".equals(currentAnimation))attackFrameIndex = 0;
    }
    
    // the following count functions are only used for the placeholders until animation
    // is implemented.
    
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

    @Override
    public void update() {
        //System.out.println("Rai.mState = " + mState + ",   Rai.cState = " + cState);
        super.update(); 
        mState.execute();
        cState.execute();
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
