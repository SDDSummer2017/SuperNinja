/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States.Rai;

import Controller.Main;
import EventHandling.Observer;
import Model.GameFigure;
import Model.Nen;
import Model.Rai;
import Model.States.CombatState;
import java.util.ArrayList;

/**
 *
 * @author matlock
 */
public class SteelTwister extends CombatState{

    public SteelTwister(GameFigure gameFigure, ArrayList<Observer> observers) {
        super(gameFigure, observers);
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
        Nen n = Main.gameData.nen;
        int a = rai.getCount();
    //    System.out.println("Rai.c = " + a);
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
        if(s.equals("Default")){
            gameFigure.cState = new Default(this.gameFigure, observers);
        }
    }
    
}
