/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States.Nen;

import Model.GameFigure;
import Model.States.MotionState;

/**
 *
 * @author Garrett A. Clement
 */
public class NeutralMotion extends MotionState {

    public NeutralMotion(GameFigure gameFigure) {
        super(gameFigure);
        
    }

    @Override
    public void execute() {}

    @Override
    public void nextState(String s){
        if(s.equals("Move") && gameFigure.cState instanceof NeutralCombat)
           gameFigure.mState = new Move(gameFigure);
        if(s.equals("Jump") && gameFigure.cState instanceof NeutralCombat)
           gameFigure.mState = new Jump(gameFigure);
    }
    
}
