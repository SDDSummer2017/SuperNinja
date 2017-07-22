package Model.States.Nen;

import EventHandling.Observer;
import Model.GameFigure;
import Model.States.MotionState;
import Physics.Acceleration;
import Physics.Force;
import View.GamePanel;
import java.util.ArrayList;

/**
 *
 * @author Garrett A. Clement
 */
public class Jump extends MotionState {
    private boolean init = true; 
    private boolean isMoving = false;
    private boolean jumpMade;
    private final int JUMP_LIMIT = 200;
    private int jumpHeight = 1;
    private int dy = 20;
    private Force jumpForce; 
    public Jump(GameFigure gameFigure, ArrayList<Observer> observers) {
        super(gameFigure, observers);
        gameFigure.airborn = true;
        jumpMade = false; 
        if(previousState instanceof Move)
            isMoving = true;
        if(!(previousState instanceof WallJump))
            jumpForce = new Force(gameFigure.mass, new Acceleration( 0 , -.75));
    } 

    @Override
    public void execute() {
        
        if(gameFigure.airborn == false && jumpMade == false){
            gameFigure.forces.add(jumpForce);
            gameFigure.airborn = true; 
            jumpMade = true; 
        }
        
        if(gameFigure.airborn == false && jumpMade == true )
        {
            
            this.nextState("JumpMade");
        }

        if(isMoving)
            move();
    }

    public void move(){
        if(gameFigure.isFacingRight)
            gameFigure.x += 10;
        else
            gameFigure.x -= 10;
    }
    
    @Override
    public void nextState(String s) {
        if(s.equals("Move"))
            isMoving = true;
        else if(s.equals("NeutralMotion"))
            isMoving = false;
        if(s.equals("JumpMade") && isMoving){
            gameFigure.mState = new Move(gameFigure, observers);
            this.notifyObservers("landed");
        }
        else if(s.equals("JumpMade")){
            gameFigure.mState = new NeutralMotion(gameFigure, observers);
            this.notifyObservers("landed");
        }
        else if(s.equals("WallJump"))
        {
            if(gameFigure.isFacingRight)
                gameFigure.mState = new WallJump(gameFigure, observers, -1);
            else
                gameFigure.mState = new WallJump(gameFigure, observers, 1);
        }
            
    }
    
}

