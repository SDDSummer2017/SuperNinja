/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States.Rai;

import Model.GameFigure;
import Model.Rai;
import Model.States.CombatState;

/**
 *
 * @author matlock
 */
public class Hit extends CombatState{

    public Hit(GameFigure gameFigure) {
        super(gameFigure);
        motionState = gameFigure.mState;
        previousState = gameFigure.cState;
    }

    @Override
    public void execute() {
        //changes the images of the gamefigure depending on which state we are currently in 
        //alters the movement state to minimize the chance of errors
        Rai rai = (Rai) this.gameFigure;
        rai.image = rai.hit;
        rai.setImage(rai.image);
        gameFigure.mState = new Neutral(gameFigure);
    }

    @Override
    public void nextState(String s) {
        //the next state should only be block it will act as a behaviour for rai
        //and an added feature to allow the player to gain momentum when fighting 
        //against him
        if(s.equals("Block") && previousState instanceof Hit){
            gameFigure.cState = new Block(gameFigure);
        }
        else {}
    }
    
}
