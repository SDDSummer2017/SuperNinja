/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import EventHandling.Observer;
import Model.States.CombatState;
import Model.States.MotionState;
import Model.States.Terro.Default;
import Model.States.Terro.Jump;
import Model.States.Terro.Neutral;
import Model.States.Terro.ShurikenThrow;
import Model.States.Terro.WindmillShuriken;
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
public class Terro extends Enemy{
    
   public int c; // used as a display count for placeholders only
   public boolean direction; //used in movement to toggle movement towards Nen or away
   public int countDelay; // used as a delay counter so Terro isnt constantly attacking 
   ArrayList<Observer> observers = new ArrayList<>();
   
    public Terro(double x, double y, double size) {
        super(x, y, size);
        
        
        super.mState = new Neutral(this, observers);
        super.cState = new Default(this, observers);
        this.health = 80;
        this.maxHealth = health;
        //used
        this.countDelay = 0;
        //false for left true for right in movementState
        this.direction = false;
        
        //System.out.println("mState = " + super.mState);
        //System.out.println("cState = " + super.cState);
        
        String imagePath = System.getProperty("user.dir");
        String separator = System.getProperty("file.separator");
        super.hit = getImage(imagePath + separator + "images" + separator
                + "Terro_Hit.png");
        super.attack1 = getImage(imagePath + separator + "images" + separator
                + "TerroShurikenThrow.png");
        super.attack2 = getImage(imagePath + separator + "images" + separator
                + "TerroWindmillShuriken.png");
        super.movement = getImage(imagePath + separator + "images" + separator
                + "TerroMovement.png");
        super.block = getImage(imagePath + separator + "images" + separator
                + "TerroEvade.png");
        super.neutral = getImage(imagePath + separator + "images" + separator
                + "TerroNeutral.png");
        super.throwImage = getImage(imagePath + separator + "images" + separator
                + "TerroJump.png");
        //super.staticImage = getImage(imagePath + separator + "images" + separator
        //        + "TerroStatic.png");
    }
    //the following delayCount functions are used to delay the actions of Terro for attack
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
        g.drawImage(image, (int) super.x, (int) super.y, (int) super.size, (int) super.size, null);
        super.render(g);
    }

    @Override
    public void update() {
        //System.out.println("Terro x location: " + super.x);
        super.update();
        mState.execute();
        cState.execute();
        //System.out.println("MSTATE: " + mState + ", CSTATE: " + cState);
        
        /*if (direction){
        super.x += 2;
        }
        
        else{
        super.x -= 2;
        }*/
        
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

