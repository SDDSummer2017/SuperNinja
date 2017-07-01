/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States.Terro;

import EventHandling.Observer;
import Model.GameFigure;
import Model.States.MotionState;
import Model.Terro;
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
        Terro ter = (Terro) this.gameFigure;
        ter.image = ter.block;
        ter.setImage(ter.throwImage);
        
        if(gameFigure.y <= 300){
            nextState("Neutral");
        }
        
        else{
            gameFigure.y -= 20;
        }
    }

    @Override
    public void nextState(String s) {
        switch (s) {
            case "Neutral":
                gameFigure.mState = new Neutral(this.gameFigure, observers);
                gameFigure.cState = new ShurikenThrow(this.gameFigure, observers);
                break;
            case "Hit":
                gameFigure.cState = new Hit(this.gameFigure, observers);
                break;
            default:
                break;
        }
    }
    
}
