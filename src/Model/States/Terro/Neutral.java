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
public class Neutral extends MotionState{

    int a; 
    int q;
    public Neutral(GameFigure gameFigure, ArrayList<Observer> observers) {
        super(gameFigure, observers);
        previousState = gameFigure.mState;
        combatState = gameFigure.cState;
        a = 0;
        q = 0;
    }

    @Override
    public void execute() {
        Terro ter = (Terro) this.gameFigure;
        ter.image = ter.neutral;
        ter.setImage(ter.image);
        a = ter.getCount();
        if (previousState instanceof Evade){
            if (!ter.airborn){
                a = 0;
                ter.setCount(a);
                this.nextState("Movement");
            }
            else{
                q += ter.GRAVITY / 2;
                gameFigure.y += q;
                a++;
                ter.setCount(a);
                if(gameFigure.y >= 450){
                    gameFigure.y = 450;
                    ter.airborn = false;
                }
            }
        }
        else{
            if(gameFigure.cState instanceof ShurikenThrow || gameFigure.cState instanceof Hit || gameFigure.cState instanceof WindmillShuriken){}
            else{
                this.nextState("Movement");
            }
        }
    }

    @Override
    public void nextState(String s) {
        switch (s) {
            case "Movement":
                gameFigure.mState = new Movement(this.gameFigure, observers);
                break;
            case "Hit":
                gameFigure.cState = new Hit(this.gameFigure, observers);
                break;
            default:
                break;
        }
    }
    
}
