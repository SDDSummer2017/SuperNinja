/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States.Nen;

import EventHandling.Observer;
import Model.GameFigure;
import Model.States.CombatState;
import java.util.ArrayList;

/**
 *
 * @author matlock
 */
public class Hit extends CombatState{

    public Hit(GameFigure gameFigure, ArrayList<Observer> observers) {
        super(gameFigure, observers);
    }

    @Override
    public void execute() {
        if (this.gameFigure.health <= 0){
            nextState("Death");
        }
        else if ((System.currentTimeMillis() - initTime >= 2000)){
            nextState("Neutral");
        }
        else{
        //apply a knockback
            if(this.gameFigure.isFacingRight){
                this.gameFigure.x -= 2;
            }
            else{
                this.gameFigure.x += 2;
            }
            
            this.gameFigure.mState = new NeutralMotion(this.gameFigure, observers);
        }
    }

    @Override
    public void nextState(String s) {
        switch (s) {
            case "Neutral":
                gameFigure.mState = new NeutralMotion(this.gameFigure, observers);
                gameFigure.cState = new NeutralCombat(this.gameFigure, observers);
                break;
            case "Death":
                gameFigure.cState = new Death(this.gameFigure, observers);
                break;
            default:
                break;
        }
    }
    
}
