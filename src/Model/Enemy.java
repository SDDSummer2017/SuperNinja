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
    public static final int STATE_ALIVE = 1;
    public static final int STATE_DYING = 2;
    public static final int STATE_DONE = 0;
    public static final int STATE_COOLDOWN = 3;
    public static final int STATE_SPAWN = 4;
    public static final int STATE_HIT = 5;
    public static final int STATE_ULTIMATE = 6;
    public static final int STATE_FIRED = 7;
    public static final int STATE_ULTIMATE2 = 8;
    public static final int STATE_ULTIMATE3 = 9;
    public static final int STATE_ULTIMATE4 = 10;
    public static final int STATE_ULTIMATE5 = 11;
    
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
    final Image image;

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
