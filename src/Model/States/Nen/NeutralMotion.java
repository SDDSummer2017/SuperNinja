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
public class NeutralMotion extends MotionState {

    private long time = 0;
    private static final int DASH_WINDOW = 100;
    
    public NeutralMotion(GameFigure gameFigure, ArrayList<Observer> observers) {
        super(gameFigure, observers) ;
        time = System.currentTimeMillis();
    }

    @Override
    public void execute() {}

    @Override
    public void nextState(String s){
        if(s.equals("Move") && gameFigure.cState instanceof NeutralCombat && System.currentTimeMillis() - time <= DASH_WINDOW && previousState instanceof Move)
            gameFigure.mState = new Dash(gameFigure, observers);
        else if(s.equals("Move") && gameFigure.cState instanceof NeutralCombat)
           gameFigure.mState = new Move(gameFigure, observers);
        else if(s.equals("Jump") && gameFigure.cState instanceof NeutralCombat)
           gameFigure.mState = new Jump(gameFigure, observers);
        else if(s.equals("Crouch") && gameFigure.cState instanceof NeutralCombat)
            gameFigure.mState = new Crouch(gameFigure, observers);
        
       gameFigure.mState.notifyObservers();
        
        
    }
    
}
