package Model.States.Nen;

import Controller.Main;
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
    private boolean isMoving = false;
    private boolean jumpMade; 
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
        int result   ;     
        
        if(gameFigure.isFacingRight)
        {
            result = Main.gamePanel.handler.rightCollision(10);
            if(result == -1)
                gameFigure.x += 10;
            else
                gameFigure.x += result; 
        }else
        {
            result = Main.gamePanel.handler.leftCollision(-10);
            if(result == -1)
                gameFigure.x += -10;
            else
                gameFigure.x += result;
        }
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
            jumpMade = true;
            gameFigure.airborn = true;
            if(gameFigure.isFacingRight)
                gameFigure.mState = new WallJump(gameFigure, observers, -1);
            else
                gameFigure.mState = new WallJump(gameFigure, observers, 1);
        }
            
    }
    
}

