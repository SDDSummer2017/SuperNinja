/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import EventHandling.Observer;
import Model.States.Sage.Chant;
import Model.States.Sage.FlameDragon;
import Model.States.Sage.NeutralMotion;
import Model.States.Sage.SoulFlame;
import Model.States.Sage.Teleport;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
public class Sage extends Enemy {
    public int c; // used as a display count for placeholders only
    
    public Sage(double x, double y, double size) {
        super(x, y, size, 8, "Sage");
        
        ArrayList<Observer> observers = new ArrayList<>();
        super.mState = new NeutralMotion(this, observers);
        super.cState = new Chant(this, observers);
        this.health = 70; 
        this.maxHealth = health;
        String imagePath = System.getProperty("user.dir");
        String separator = System.getProperty("file.separator");
        super.attack1 = getImage(imagePath + separator + "images" + separator
                + "ViperStrike.png");
        super.attack2 = getImage(imagePath + separator + "images" + separator
                + "SteelTwister.png");
        super.movement = getImage(imagePath + separator + "images" + separator
                + "Movement.png");
        super.block = getImage(imagePath + separator + "images" + separator
                + "Block.png");
        super.neutral = getImage(imagePath + separator + "images" + separator
                + "Neutral.png");
        super.throwImage = getImage(imagePath + separator + "images" + separator
                + "Throw.png");
        super.staticImage = getImage(imagePath + separator + "images" + separator
                + "Static.png");
    }
    
    @Override
    public void render(Graphics g) {
        g.drawImage(image, (int) super.x, (int) super.y, (int) super.size, (int) super.size, null);
        super.render(g); 
        
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
        //LIGHT ATTACK (Steel Twister)
        else if(cState instanceof SoulFlame){
            resetAnimationFrames("attack");
            
            //Select frame image based on which direction Nen is facing
            frameImage = (isFacingRight) ? lightAttackAnimation[attackFrameIndex] : GameFigure.flipImageHorizontally(lightAttackAnimation[attackFrameIndex]);
            
            g.drawImage(frameImage, (int) super.x, (int) super.y, (int) super.size, (int) super.size, null);
            attackFrameIndex = (attackFrameIndex == lightAttackAnimation.length-1) ? 0 : attackFrameIndex + 1;                 
        }
        //HEAVY ATTACK (Viper Strike)
        else if(cState instanceof FlameDragon){
            resetAnimationFrames("attack");
            
            //Select frame image based on which direction Nen is facing
            frameImage = (isFacingRight) ? heavyAttackAnimation[attackFrameIndex] : GameFigure.flipImageHorizontally(heavyAttackAnimation[attackFrameIndex]);
            
            g.drawImage(frameImage, (int) super.x, (int) super.y, (int) super.size, (int) super.size, null);
            attackFrameIndex = (attackFrameIndex == heavyAttackAnimation.length-1) ? 0 : attackFrameIndex + 1;                      
        }
        else if(mState instanceof Teleport)
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

    @Override
    public void update() {
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
     
}
