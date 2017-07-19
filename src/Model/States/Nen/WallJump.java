/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States.Nen;

import EventHandling.Observer;
import Model.GameFigure;
import Model.States.MotionState;
import java.util.ArrayList;

/**
 *
 * @author Garrett A. Clement
 */
public class WallJump extends MotionState {
 
    private final static int Y_MAX = 200; 
    private int yDistance = 0;
    
    
    
    public WallJump(GameFigure gameFigure, ArrayList<Observer> observers, int direction) {
        super(gameFigure, observers);
        System.out.println("WallJumped Performed");
        this.direction = direction;
    }

    @Override
    public void execute() {
        
        gameFigure.x += 10 * direction;
        gameFigure.y -= 20; 
        yDistance += 20;
        if(yDistance >= Y_MAX)
            nextState("Jump");
        
    }

    @Override
    public void nextState(String s) {
        if(s.equals("Jump"))
        {
            gameFigure.forces.clear(); 
            gameFigure.mState = previousState;
        }
    }
    
}
