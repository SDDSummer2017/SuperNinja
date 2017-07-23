/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States.Nen;

import EventHandling.Observer;
import Model.GameFigure;
import Model.States.MotionState;
import Physics.Acceleration;
import Physics.Force;
import java.util.ArrayList;

/**
 *
 * @author Garrett A. Clement
 */
public class WallJump extends MotionState {
 
    private final static int Y_MAX = 200; 
    private int yDistance = 0;
    private Force jumpForce;
    private boolean applyOnce = false;
    private static long hangTime = 100;
    
    public WallJump(GameFigure gameFigure, ArrayList<Observer> observers, int direction) {
        super(gameFigure, observers);
        System.out.println("WallJumped Performed");
        gameFigure.velocity.dx = 0;
        gameFigure.velocity.dy = 0;
        gameFigure.forces.clear();
        this.direction = direction;
        jumpForce = new Force(gameFigure.mass, new Acceleration( -.2 * direction * -1 , -.75));
        
        
    }

    @Override
    public void execute() { 
//        gameFigure.x += 10 * direction;
//        gameFigure.y -= 20;  
        
        if(yDistance >= Y_MAX)
            nextState("Jump");
        if(System.currentTimeMillis() - time >= hangTime)
        {
            yDistance += 20;
            if(!applyOnce)
            {
                gameFigure.forces.add(jumpForce);
                applyOnce = true;
            }
        }
        else
        {
            gameFigure.velocity.dx = 0;
            gameFigure.velocity.dy = 0;
        }
    }

    @Override
    public void nextState(String s) {
        if(s.equals("Jump"))
        {
            gameFigure.forces.clear();  
            gameFigure.velocity.dx = 0;
            gameFigure.velocity.dy = 0;
            gameFigure.mState = previousState;
        }
        if(s.equals("NeutralMotion"))
            previousState.nextState("NeutralMotion");
        else if(s.equals("Move"))
            previousState.nextState("Move");
    }
    
}
