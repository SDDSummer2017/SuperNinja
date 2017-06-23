/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States.Rai;

import Model.GameFigure;
import Model.States.CombatState;

/**
 *
 * @author matlock
 */
public class Default extends CombatState{

    public Default(GameFigure gameFigure) {
        super(gameFigure);
        motionState = gameFigure.mState;
        previousState = gameFigure.cState;
    }

    @Override
    public void execute() {
        //in the event that the combat state is in default setting then the movement
        //state should have priority and will dictate the animation sequence
    }

    @Override
    public void nextState(String s) {
        //the Default state leads to 4 of the additional states which have other states branch off
        if(s.equals("Hit") && previousState instanceof Default){
            gameFigure.cState = new Hit(gameFigure);
        }
        else if(s.equals("Throw") && previousState instanceof Default){
            gameFigure.cState = new Throw(gameFigure);
        }
        else if(s.equals("ViperStrike") && previousState instanceof Default){
            gameFigure.cState = new ViperStrike(gameFigure);
        }
        else{}
        /* not yet testable and therefore will be implemented later 
        else if(s.equals("IsThrown") && previousState instanceof Default){
        gameFigure.cState = new IsThrown(gameFigure);
        }*/

    }
    
}
