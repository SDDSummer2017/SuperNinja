/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States.Rai;

import EventHandling.Observer;
import Model.GameFigure;
import Model.Rai;
import Model.States.CombatState;
import java.util.ArrayList;

/**
 *
 * @author matlock
 */
public class Hit extends CombatState{

    public Hit(GameFigure gameFigure, ArrayList<Observer> observers) {
        super(gameFigure, observers);
        motionState = gameFigure.mState;
        previousState = gameFigure.cState;
    }

    @Override
    public void execute() {
        //changes the images of the gamefigure depending on which state we are currently in 
        //alters the movement state to minimize the chance of errors
        Rai rai = (Rai) this.gameFigure;
        rai.image = rai.hit;
        rai.setImage(rai.image);
        int a = rai.getCount();
        if (a >= 10){
            a = 0;
            rai.setCount(a);
            this.nextState("Block");
        }
        else{
            a++;
            rai.setCount(a);
        }
        gameFigure.mState = new Neutral(gameFigure, observers);
    }

    @Override
    public void nextState(String s) {
        //the next state should only be block it will act as a behaviour for rai
        //and an added feature to allow the player to gain momentum when fighting 
        //against him
        if(s.equals("Block")){
            gameFigure.cState = new Block(this.gameFigure, observers);
        }
        else {}
    }
    
}
