/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import static Model.Nen.getImage;
import Model.States.CombatState;
import Model.States.MotionState;
import Model.States.Rai_States.Default;
import Model.States.Rai_States.Neutral;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author matlock
 */
public class Rai extends Enemy {
    
    public Rai(double x, double y, double size) {
        super(x, y, size);
        super.mState = new Neutral(this);
        super.cState = new Default(this);
        this.health = 120;
        this.comState = STATE_DEFAULT;
        this.moveState = STATE_NEUTRAL;
        
        String imagePath = System.getProperty("user.dir");
        String separator = System.getProperty("file.separator");
        super.attack1 = getImage(imagePath + separator + "images" + separator
                + "Rai_ViperStrike.png");
        super.attack2 = getImage(imagePath + separator + "images" + separator
                + "Rai_SteelTwister.png");
        super.movement = getImage(imagePath + separator + "images" + separator
                + "Rai_Movement.png");
        super.block = getImage(imagePath + separator + "images" + separator
                + "Rai_Block.png");
        super.neutral = getImage(imagePath + separator + "images" + separator
                + "Rai_Neutral.png");
        super.throwImage = getImage(imagePath + separator + "images" + separator
                + "Rai_Throw.png");
        super.staticImage = getImage(imagePath + separator + "images" + separator
                + "Rai_Static.png");
    }
    @Override
    public void setMState(MotionState state){}
    
    @Override
    public void setCState(CombatState state){}
    
    @Override
    public void setIndex(int i){}
    
    @Override
    public int getIndex(){return super.index;}
    
    @Override
    public int getX(){return (int) super.x;}
    
    @Override
    public int getY(){return (int) super.y;}
    
    @Override
    public MotionState getMState(){return this.mState;}
    
    @Override
    public CombatState getCState(){return this.cState;}
    
    @Override
    public void setImage(){}
    
    @Override
    public void render(Graphics g) {}

    @Override
    public void update() {}

    @Override
    public Rectangle2D.Double getCollisionBox() {
        return new Rectangle2D.Double(super.x, super.y, super.size, super.size);
    }
}
