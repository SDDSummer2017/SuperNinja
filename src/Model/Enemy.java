package Model;

import Controller.Main;
import Model.States.CombatState;
import Model.States.MotionState;
import Model.States.State;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import View.GamePanel;

public abstract class Enemy extends GameFigure {

    public State mstate;
    public State cstate;
    
    public static final int STATE_NEUTRAL = 1;
    public static final int STATE_MOVEMENT = 2;
    public static final int STATE_DEFAULT = 0;
    public static final int STATE_HIT = 3;
    public static final int STATE_BLOCK = 4;
    public static final int STATE_ATTACK1 = 5;
    public static final int STATE_ATTACK2 = 6;
    public static final int STATE_THROW = 7;
    public static final int STATE_IS_THROWN = 8;
    public static final int STATE_JUMP = 9;
    
    public Image attack1;
    public Image attack2;
    public Image block;
    public Image hit;
    public Image movement;
    public Image neutral;
    public Image is_Thrown;
    public Image throwImage;
    public Image staticImage;
    
    public int dx;
    public int dy;
    public int updateCount = 0;
    public int index;
    public int moveState;
    public int comState;
    public Image image;

    public Enemy(double x, double y, double size) {
        super(x, y, size);
        image = null;
    }

    public void setMState(MotionState state){}
    
    public void setCState(CombatState state){}
    
    public void setIndex(int i){}
    
    public int getIndex(){return 0;}
    
    public int getX(){return 0;}
    
    public int getY(){return 0;}
    
    public MotionState getMState(){return null;}
    
    public CombatState getCState(){return null;}
    
    public void setImage(Image i){}
    
    public static Image getImage(String fileName){return null;}
 
    @Override
    public void render(Graphics g) {}

    @Override
    public void update() {}

    @Override
    public Rectangle2D.Double getCollisionBox() {
        return new Rectangle2D.Double(super.x, super.y, super.size, super.size);
    }
}
