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
public class ShadowStrike extends CombatState{

    int a;
    
    public ShadowStrike(GameFigure gameFigure, ArrayList<Observer> observers) {
        super(gameFigure, observers);
        motionState = gameFigure.mState;
        previousState = gameFigure.cState;
        a = 0;
    }

    @Override
    public void execute() {
        Kisara kis = (Kisara) this.gameFigure;
        Nen n = Main.gameData.nen;
        kis.image = kis.attack1;
        kis.setImage(kis.image);
        a = kis.getCount();
        //System.out.println("ShadowStrike");
        if (a >= 20){
            a = 0;
            kis.setCount(a);
            if(Math.abs(n.x - gameFigure.x) < 100){
                nextState("VanishingStrike");
            }
            else{
                nextState("Default");
            }
        }
        else{
            if (n.x > gameFigure.x){
                gameFigure.x += 8;
            }
            else{
                gameFigure.x -= 8;
            }
            a++;
            kis.setCount(a);
        }
    }

    @Override
    public void nextState(String s) {
        switch (s) {
            case "VanishingStrike":
                gameFigure.mState = new Neutral(this.gameFigure, observers);
                gameFigure.cState = new VanishingStrike(this.gameFigure, observers);
                break;
            case "Default":
                gameFigure.cState = new Default(this.gameFigure, observers);
                break;
            case "Hit":
                gameFigure.cState = new Hit(this.gameFigure, observers);
                break;
            default:
                break;
        }
    }
    
}
