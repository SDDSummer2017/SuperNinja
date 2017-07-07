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
import Model.States.MotionState;
import java.util.ArrayList;

/**
 *
 * @author matlock
 */
public class Neutral extends MotionState{

    public Neutral(GameFigure gameFigure, ArrayList<Observer> observers)  {
        super(gameFigure, observers);
        previousState = gameFigure.mState;
        combatState = gameFigure.cState;
    }

    @Override
    public void execute() {
        Rai rai = (Rai) this.gameFigure;
        rai.image = rai.neutral;
        rai.setImage(rai.image);
        if (this.gameFigure.cState instanceof Default){
            this.nextState("Movement");
        }
    }

    @Override
    public void nextState(String s) {
        if(s.equals("Movement")){
            this.gameFigure.mState = new Movement(this.gameFigure, observers);
        }
    
       
    }
    
}
