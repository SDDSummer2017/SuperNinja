/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States.Rai;

import Model.GameFigure;
import Model.Rai;
import Model.States.MotionState;

/**
 *
 * @author matlock
 */
public class Movement extends MotionState{

    public Movement(GameFigure gameFigure) {
        super(gameFigure);
        previousState = gameFigure.mState;
        combatState = gameFigure.cState;
    }

    @Override
    public void execute() {
        // this state is rais' first attack in his sequence chain
         //changes the images of the gamefigure depending on which state we are currently in 
        //alters the movement state to minimize the chance of errors
        if()
        Rai rai = (Rai) this.gameFigure;
        rai.image = rai.attack1;
        rai.setImage(rai.image);
        gameFigure.mState = new Neutral(gameFigure);
    }

    @Override
    public void nextState(String s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
