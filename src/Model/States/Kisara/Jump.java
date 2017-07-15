/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States.Kisara;

import EventHandling.Observer;
import Model.GameFigure;
import Model.States.MotionState;
import java.util.ArrayList;

/**
 *
 * @author matlock
 */
public class Jump extends MotionState{

    public Jump(GameFigure gameFigure, ArrayList<Observer> observers) {
        super(gameFigure, observers);
        previousState = gameFigure.mState;
        combatState = gameFigure.cState;
    }

    @Override
    public void execute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void nextState(String s) {
        switch (s) {
            case "SmokeBomb":
                gameFigure.cState = new SmokeBomb(this.gameFigure, observers);
                break;
            case "Hit":
                gameFigure.cState = new Hit(this.gameFigure, observers);
                break;
            case "VanishingStrike":
                gameFigure.cState = new VanishingStrike(this.gameFigure, observers);
                break;
            case "ShadowStrike":
                gameFigure.cState = new ShadowStrike(this.gameFigure, observers);
                break;
            default:
                break;
        }
        
        gameFigure.cState.notifyObservers();
        gameFigure.mState.notifyObservers();
    }
    
}
