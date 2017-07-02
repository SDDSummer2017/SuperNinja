/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States.Terro;

import EventHandling.Observer;
import Model.GameFigure;
import Model.States.CombatState;
import Model.Terro;
import java.util.ArrayList;

/**
 *
 * @author matlock
 */
public class Default extends CombatState{

    int a;
    
    public Default(GameFigure gameFigure, ArrayList<Observer> observers) {
        super(gameFigure, observers);
        motionState = gameFigure.mState;
        previousState = gameFigure.cState;
        a = 0;
    }

    @Override
    public void execute() {
        Terro ter = (Terro) this.gameFigure;
        
        a = ter.getDelayCount();
        if(a >= 60){
            if (gameFigure.mState instanceof Evade){
                if(!ter.airborn){
                    a = 0;
                    ter.setDelayCount(a);
                    nextState("WindmillShuriken");
                }
            }
            else if(!(ter.mState instanceof Evade)){
                a = 0;
                ter.setDelayCount(a);
                nextState("Jump");
            }
        }
        else{
            a++;
            ter.setDelayCount(a);
        }
    }

    @Override
    public void nextState(String s) {
        switch (s) {
            case "Jump":
                gameFigure.mState = new Jump(this.gameFigure, observers);
                break;
            case "Hit":
                gameFigure.cState = new Hit(this.gameFigure, observers);
                break;
            case "WindmillShuriken":
                gameFigure.cState = new WindmillShuriken(this.gameFigure, observers);
                break;
            default:
                break;
        }
    }
}
