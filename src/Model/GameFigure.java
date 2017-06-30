package Model;

import Model.States.CombatState;
import Model.States.MotionState;
import Physics.Acceleration;
import Physics.Force;
import Physics.Velocity;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public abstract class GameFigure implements Collision, Renderable, Updateable {

    public double x; // for a faster access
    public double y;
    public double size;
    public double health;
    public double damage;
    public Rectangle2D.Double collisionBox;
    public boolean isFacingRight;
    public CombatState cState;
    public MotionState mState;
    public boolean airborn;
    public Point2D.Double location;
    public static final int GRAVITY = 8;
    public boolean hit;
    protected double mass; 
    public Acceleration acceleration;
    public Velocity velocity; 
    public ArrayList<Force> forces;
    public GameFigure(double x, double y, double size) {
        this.hit = false;
        this.x = x;
        this.y = y;
        this.size = size;
        this.location = new Point2D.Double(x - (size/2), y-(size/2));
    }
    
    public Point2D.Double getLocation(){
        return location;
    }

    public void calculatePhysics()
    {
        
       //ApplyForce
       for(Force f : forces)
       {
        velocity.dx += f.dvx();
        velocity.dy += f.dvy(); 
       }
      
       
        
       
       
       //Apply Velocity
       x += velocity.dx; 
       y += velocity.dy; 
       
    }
     
    


    

    
    
}
