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
public class VanishingStrike extends CombatState{
    
    int a;
    
    public VanishingStrike(GameFigure gameFigure, ArrayList<Observer> observers) {
        super(gameFigure, observers);
        motionState = gameFigure.mState;
        previousState = gameFigure.cState;
        a = 0;
    }

    @Override
    public void execute() {
        Kisara kis = (Kisara) this.gameFigure;
        Nen n = Main.gameData.nen;
        kis.image = kis.attack2;
        kis.setImage(kis.image);
        a = kis.getCount();
        //System.out.println("VanishingStrike");
        if (a == 0){
            if (n.x > gameFigure.x){
                kis.direction = true;
            }
            else{
                kis.direction = false;
            }
            a++;
            kis.setCount(a);
        }
        else{
            if(kis.direction){
                //kisara is to the left of nen
                //gameFigure.x += Math.abs(n.x - gameFigure.x) / 6;
                gameFigure.x += 16;
                if (a >= 25){
                    a = 0;
                    kis.setCount(a);
                    nextState("Default");
                }
                else{
                    a++;
                    kis.setCount(a);
                }
            }
            else{
                //gameFigure.x -= Math.abs(n.x - gameFigure.x) / 6;
                gameFigure.x -= 16;
                if (a >= 25){
                    a = 0;
                    kis.setCount(a);
                    nextState("Default");
                }
                else{
                    a++;
                    kis.setCount(a);
                }
            }
        }
    }

    @Override
    public void nextState(String s) {
        switch (s) {
            case "Default":
                gameFigure.mState = new Neutral(this.gameFigure, observers);
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
