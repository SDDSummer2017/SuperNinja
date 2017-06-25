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
public class Throw extends CombatState{

    public Throw(GameFigure gameFigure, ArrayList<Observer> observers) {
        super(gameFigure, observers);
        motionState = gameFigure.mState;
        previousState = gameFigure.cState;
    }

    @Override
    public void execute() {
        Rai rai = (Rai) this.gameFigure;
        rai.image = rai.attack1;
        rai.setImage(rai.image);
        Nen n = Main.gameData.nen;
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
        gameFigure.mState = new Neutral(gameFigure, observers);
    }

    @Override
    public void nextState(String s) {
        if(s.equals("Default")){
            gameFigure.cState = new Default(this.gameFigure, observers);
        }
    }
    
}
