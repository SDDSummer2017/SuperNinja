/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States.Kisara;

import Controller.Main;
import EventHandling.Observer;
import Model.GameFigure;
import Model.Kisara;
import Model.Nen;
import Model.States.CombatState;
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
        Kisara kis = (Kisara) this.gameFigure;
        Nen n = Main.gameData.nen;
        
        a = kis.getDelayCount();
        if (a >= 20 && Math.abs(n.x - gameFigure.x) < 100){
            a = 0;
            kis.setDelayCount(a);
            nextState("SmokeBomb");
        }
        if(a >= 40){
            if (Math.abs(n.x - gameFigure.x) < 70){
                a = 0;
                kis.setDelayCount(a);
                nextState("VanishingStrike");
            }
            else if(Math.abs(n.x - gameFigure.x) > 100 && Math.abs(n.x - gameFigure.x) <= 150){
                if ((kis.isFacingRight) && n.isFacingRight){
                    a = 0;
                    kis.setDelayCount(a);
                    nextState("ShadowStrike");
                }
                else if (!(kis.isFacingRight) && !(n.isFacingRight)){
                    a = 0;
                    kis.setDelayCount(a);
                    nextState("ShadowStrike");
                }
                else{}
            }
        }
        else{
            a++;
            kis.setDelayCount(a);
        }
    }

    @Override
    public void nextState(String s) {
        switch (s) {
            case "SmokeBomb":
                gameFigure.cState = new SmokeBomb(this.gameFigure, observers);
                gameFigure.mState = new Neutral(this.gameFigure, observers);
                break;
            case "Hit":
                gameFigure.cState = new Hit(this.gameFigure, observers);
                gameFigure.mState = new Neutral(this.gameFigure, observers);
                break;
            case "VanishingStrike":
                gameFigure.mState = new Neutral(this.gameFigure, observers);
                gameFigure.cState = new VanishingStrike(this.gameFigure, observers);
                break;
            case "ShadowStrike":
                gameFigure.cState = new ShadowStrike(this.gameFigure, observers);
                gameFigure.mState = new Neutral(this.gameFigure, observers);
                break;
            default:
                break;
        }
    }
}
