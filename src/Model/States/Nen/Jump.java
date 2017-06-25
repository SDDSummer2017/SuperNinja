<<<<<<< HEAD
 
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States.Nen;

import EventHandling.Observer;
import Model.GameFigure;
import Model.States.MotionState;
import View.GamePanel;
import java.util.ArrayList;

/**
 *
 * @author Garrett A. Clement
 */
public class Jump extends MotionState {

    private boolean isMoving = false;
    private boolean jumpMade;
    private final int JUMP_LIMIT = 100;
    private int jumpHeight = 1;
    private int dy = 15;
    public Jump(GameFigure gameFigure, ArrayList<Observer> observers) {
        super(gameFigure, observers) ;
        gameFigure.airborn = true;
    }

    @Override
    public void execute() {
        if(JUMP_LIMIT >= jumpHeight)
        { 
            jumpMade = true;
            gameFigure.y -= dy;
        }else if(jumpMade && !gameFigure.airborn)
            nextState("JumpMade"); 
 
        jumpHeight += dy;
        
        if(isMoving)
            move();
    }

    public void move(){
        if(gameFigure.isFacingRight)
            gameFigure.x += 5;
        else
            gameFigure.x -= 5;
    }
    
    @Override
    public void nextState(String s) {
        if(s.equals("Move"))
            isMoving = true;
        else if(s.equals("NeutralMotion"))
            isMoving = false;
        if(s.equals("JumpMade") && isMoving)
            gameFigure.mState = new Move(gameFigure, observers);
        else if(s.equals("JumpMade")){
            gameFigure.mState = new NeutralMotion(gameFigure, observers);
             gameFigure.mState.notifyObservers("landed");
        }
      if(!s.equals("Move")){
        gameFigure.mState.notifyObservers();
      }
        
    }
    
    
}

=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States.Nen;

import Model.GameFigure;
import Model.States.MotionState;
import View.GamePanel;

/**
 *
 * @author Garrett A. Clement
 */
public class Jump extends MotionState {

    private boolean isMoving = false;
    private boolean jumpMade;
    private final int JUMP_LIMIT = 200;
    private int jumpHeight = 1;
    private int dy = 20;
    
    public Jump(GameFigure gameFigure) {
        super(gameFigure);
        gameFigure.airborn = true;
        if(previousState instanceof Move)
            isMoving = true;
    } 

    @Override
    public void execute() {
        if(JUMP_LIMIT >= jumpHeight)
        { 
            jumpMade = true;
            gameFigure.y -= dy;
        }else if(jumpMade && !gameFigure.airborn)
            nextState("JumpMade"); 
 
        jumpHeight += dy;
        
        if(isMoving)
            move();
    }

    public void move(){
        if(gameFigure.isFacingRight)
            gameFigure.x += 5;
        else
            gameFigure.x -= 5;
    }
    
    @Override
    public void nextState(String s) {
        if(s.equals("Move"))
            isMoving = true;
        else if(s.equals("NeutralMotion"))
            isMoving = false;
        if(s.equals("JumpMade") && isMoving)
            gameFigure.mState = new Move(gameFigure);
        else if(s.equals("JumpMade"))
            gameFigure.mState = new NeutralMotion(gameFigure);
    }
    
}
>>>>>>> refs/remotes/origin/StateImplementation
