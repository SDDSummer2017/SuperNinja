package Model;

import Controller.Main;
import Model.States.CombatState;
import Model.States.MotionState;
import Model.States.State; 
import View.GamePanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.util.Random; 

public abstract class Enemy extends GameFigure {

    public State mstate;
    public State cstate;
    
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

    public Enemy(double x, double y, double size, ImageResource imageResource) {
        super(x, y, size, false, imageResource);
        image = null;
    }
    //


    public void setMState(MotionState state){}
    
 
    public void setCState(CombatState state){}
 
    public void shoot(){
        double targetX = Main.gameData.nen.x + Main.gameData.nen.size/2;
        double targetY = Main.gameData.nen.y;
        Random random = new Random();
//        if(random.nextBoolean())
//            Main.gameData.addEnemyBullet(super.x + super.size/2, super.y + super.size, targetX, targetY, Color.red);
 
    } 
    public void setIndex(int i)
    {
       this.index = i; 
    }
    
    public int getIndex(){return 0;}
    
    public int getX(){return 0;}
    
    public int getY(){return 0;}
    
    public MotionState getMState(){return null;}
    
    public CombatState getCState(){return null;}
    
    public void setImage(Image i){}
    
    public static Image getImage(String fileName){return null;}
 
    @Override
    public void render(Graphics g) 
    {
        
        if(health < maxHealth){
        g.setColor(Color.red);
        g.fillRect((int) (x + 100), (int) (y), 10, (int)this.maxHealth);
        g.setColor(Color.green);
        g.fillRect((int) (x + 100), (int) (y), 10, (int) this.health);
        }
    }

    @Override
    public void update() { super.update();}

    @Override
    public Rectangle2D.Double getCollisionBox() {
        return new Rectangle2D.Double(super.x, super.y, super.size, super.size);
    }
}
