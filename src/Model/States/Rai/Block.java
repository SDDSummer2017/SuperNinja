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
public class Block extends CombatState{

    public Block(GameFigure gameFigure) {
        //previous state was hit the current state that rai is in should be block
        super(gameFigure);
        motionState = gameFigure.mState;
        previousState = gameFigure.cState;
    }

    @Override
    public void execute() {
        //changes the images of the gamefigure depending on which state we are currently in 
        //alters the movement state to minimize the chance of errors
        Rai rai = (Rai) this.gameFigure;
        rai.image = rai.block;
        rai.setImage(rai.image);
        gameFigure.mState = new Neutral(gameFigure);
        int a = rai.getCount();
        if (a >= 10){
            a = 0;
            rai.setCount(a);
            this.nextState("Default");
        }
        else{
            a++;
            rai.setCount(a);
        }
    }

    @Override
    public void nextState(String s) {
        //only next state for combat is the default state which will again signal to 
        //the motion state that it can enter into the next state and retain focus
        if(s.equals("Default")){
            gameFigure.cState = new Default(this.gameFigure);
        }
        else {}
    }
    
}
