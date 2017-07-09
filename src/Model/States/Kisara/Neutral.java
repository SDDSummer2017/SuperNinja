/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States.Kisara;

import EventHandling.Observer;
import Model.GameFigure;
import Model.Kisara;
import Model.States.MotionState;
import java.util.ArrayList;
import Model.States.Kisara.Default;

/**
 *
 * @author matlock
 */
public class Neutral extends MotionState{

    public Neutral(GameFigure gameFigure, ArrayList<Observer> observers) {
        super(gameFigure, observers);
        previousState = gameFigure.mState;
        combatState = gameFigure.cState;
    }

    @Override
    public void execute() {
        Kisara kis = (Kisara) this.gameFigure;
        kis.image = kis.neutral;
        kis.setImage(kis.image);
        
        if(!(gameFigure.cState instanceof Model.States.Kisara.Default)){}
        else{
            this.nextState("Movement");
        }
    }

    @Override
    public void nextState(String s){
        switch (s) {
            case "Movement":
                gameFigure.mState = new Movement(this.gameFigure, observers);
                break;
            case "Hit":
                gameFigure.cState = new Hit(this.gameFigure, observers);
                break;
            default:
                break;
        }
    }
    
}
