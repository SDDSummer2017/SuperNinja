/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States.Nen;

import EventHandling.Observer;
import Model.GameFigure;
import Model.Nen;
import Model.States.MotionState;
import java.util.ArrayList;

/**
 *
 * @author Garrett A. Clement
 */
public class Crouch extends MotionState {

    public Crouch(GameFigure gameFigure, ArrayList<Observer> observers) {
        super(gameFigure, observers); 
       ((Nen)gameFigure).setCollisionOffsetsAndSize(0, + (int)gameFigure.size/2, (int)gameFigure.size, (int)gameFigure.size/2);
    }

    @Override
    public void execute() { 
        
    }

    @Override
    public void nextState(String s) { 
        if(s.equals("Stand"))
        { 
            ((Nen)gameFigure).setCollisionOffsetsAndSize(0, 0, (int)gameFigure.size, (int)gameFigure.size);
            gameFigure.mState = new NeutralMotion(this.gameFigure, this.observers);
        }
    }
    
}
