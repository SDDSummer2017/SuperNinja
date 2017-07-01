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
public class Dash extends MotionState {

    private final static int MAX_DISTANCE = 125;
    private int distance = 0;
    
    public Dash(GameFigure gameFigure, ArrayList<Observer> observers) {
        super(gameFigure, observers);
    }

    
    @Override
    public void execute() { 
        if(distance >= MAX_DISTANCE)
            nextState("End");
        else
        {
            distance += 25;
            if(gameFigure.isFacingRight)
                gameFigure.x += 25;
            else
                gameFigure.x -= 25;
        }
    }

    @Override
    public void nextState(String s) { 
        if(s.equals("End"))
            gameFigure.mState = new NeutralMotion(gameFigure, observers);
        
    }
    
}
