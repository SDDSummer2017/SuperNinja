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
import Model.States.CombatState;
import java.util.ArrayList;

/**
 *
 * @author matlock
 */
public class Default extends CombatState{

    public Default(GameFigure gameFigure, ArrayList<Observer> observers) {
        super(gameFigure, observers);
        motionState = gameFigure.mState;
        previousState = gameFigure.cState;
    }

    @Override
    public void execute() {
        //in the event that the combat state is in default setting then the movement
        //state should have priority and will dictate the animation sequence
        Nen n = Main.gameData.nen;
        if(Math.abs(n.x - gameFigure.x) <= 25){
            this.nextState("Throw");
        }
        else if(Math.abs(n.x - gameFigure.x) > 25 && Math.abs(n.x - gameFigure.x) <= 50){
            this.nextState("ViperStrike");
        }
        else{}
    }

    @Override
    public void nextState(String s) {
        if(s.equals("Hit")){
            gameFigure.cState = new Hit(this.gameFigure, observers);
        }
        else if(s.equals("Throw")){
            gameFigure.cState = new Throw(this.gameFigure, observers);
        }
        else if(s.equals("ViperStrike")){
            gameFigure.cState = new ViperStrike(this.gameFigure, observers);
            
        }
        else{}
        /* not yet testable and therefore will be implemented later 
        else if(s.equals("IsThrown") && previousState instanceof Default){
        gameFigure.cState = new IsThrown(gameFigure);
        }*/
        gameFigure.cState.notifyObservers();
    }
    
}