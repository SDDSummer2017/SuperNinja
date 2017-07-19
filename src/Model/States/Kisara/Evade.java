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
import Model.States.MotionState;
import java.util.ArrayList;

/**
 *
 * @author matlock
 */
public class Evade extends MotionState{

    int a;
    public Evade(GameFigure gameFigure, ArrayList<Observer> observers) {
        super(gameFigure, observers);
        previousState = gameFigure.mState;
        combatState = gameFigure.cState;
        a = 0;
    }

    @Override
    public void execute() {
       Kisara kis = (Kisara) this.gameFigure;
        Nen n = Main.gameData.nen;
        kis.image = kis.block;
        kis.setImage(kis.image);
        a = kis.getCount();
        if(gameFigure.x < n.x){
            //Kisara is to the left of nen
            if(n.isFacingRight && a > 20){
                nextState("VanishingStrike");
            }
            else{
                nextState("SmokeBomb");
            }
        }
        else{
            //Kisara is to the right of nen{
            if(!(n.isFacingRight) && a > 20){
                nextState("VanishingStrike");
            }
            else{
                nextState("SmokeBomb");
            }
        }
        
    }

    @Override
    public void nextState(String s) {
        switch (s) {
            case "VanishingStrike":
                gameFigure.cState = new VanishingStrike(this.gameFigure, observers);
                gameFigure.mState = new Neutral(this.gameFigure, observers);
                break;
            case "Hit":
                gameFigure.cState = new Hit(this.gameFigure, observers);
                gameFigure.mState = new Neutral(this.gameFigure, observers);
                break;
            case "SmokeBomb":
                gameFigure.cState = new SmokeBomb(this.gameFigure, observers);
                gameFigure.mState = new Neutral(this.gameFigure, observers);
                break;    
            default:
                break;
                
        }
        gameFigure.cState.notifyObservers();
        gameFigure.mState.notifyObservers();
    }
    
}
