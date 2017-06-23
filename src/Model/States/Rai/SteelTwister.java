/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States.Rai;

import Controller.Main;
import Model.GameFigure;
import Model.Nen;
import Model.Rai;
import Model.States.CombatState;

/**
 *
 * @author matlock
 */
public class SteelTwister extends CombatState{

    public SteelTwister(GameFigure gameFigure) {
        super(gameFigure);
        motionState = gameFigure.mState;
        previousState = gameFigure.cState;
    }

    @Override
    public void execute() {
        //incorporate a count in Rai that can have a function for reset this will act
        // as a timer for the meantime so that we can see the animation and state change
        //working but will allow for danny to finetune to the animation later.
        Rai rai = (Rai) this.gameFigure;
        rai.image = rai.attack2;
        rai.setImage(rai.image);
        Nen n = Main.gameData.marine;
        int a = rai.getCount();
        if (a >= 100){
            this.nextState("Default");
        }
        else{
            a++;
            rai.setCount(a);
        }
    }

    @Override
    public void nextState(String s) {
        if(s.equals("Default") && previousState instanceof SteelTwister){
            gameFigure.cState = new Default(gameFigure);
        }
    }
    
}
