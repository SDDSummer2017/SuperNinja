/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.States.CombatState;
import Model.States.MotionState;
import Model.States.Rai.Default;
import Model.States.Rai.Neutral;
import View.GamePanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author matlock
 */
public class Rai extends Enemy {
    public int c; // used as a display count for placeholders only
    
    public Rai(double x, double y, double size) {
        super(x, y, size);
        super.mState = new Neutral(this);
        super.cState = new Default(this);
        this.health = 120;
        //System.out.println("mState = " + super.mState);
        //System.out.println("cState = " + super.cState);
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
    public void render(Graphics g) {
        g.drawImage(image, (int) super.x, (int) super.y, (int) super.size, (int) super.size, null);
        g.setColor(Color.red);
        g.fillRect(3, GamePanel.PHEIGHT - 102, 10, 100);
        g.setColor(Color.green);
        g.fillRect(3, GamePanel.PHEIGHT - (int) this.health - 2, 10, (int) this.health);
    }

    @Override
    public void update() {
        //System.out.println("Rai.mState = " + mState + ",   Rai.cState = " + cState);
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
