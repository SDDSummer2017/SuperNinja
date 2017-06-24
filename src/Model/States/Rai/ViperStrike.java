/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States.Rai;

import Controller.Main;
import Controller.TimerListener;
import EventHandling.Observer;
import Model.GameFigure;
import Model.Nen;
import Model.Rai;
import Model.States.CombatState;
import java.util.ArrayList;

/**
 *
 * @author matlock
 */
public class ViperStrike extends CombatState {

    public TimerListener t;
    
    public ViperStrike(GameFigure gameFigure, ArrayList<Observer> observers) {
        super(gameFigure, observers);
        motionState = gameFigure.mState;
        previousState = gameFigure.cState;
        int a = 0;
        
    }

    @Override
    public void execute() {
        // this state is rais' first attack in his sequence chain
         //changes the images of the gamefigure depending on which state we are currently in 
        //alters the movement state to minimize the chance of errors
        Rai rai = (Rai) this.gameFigure;
        rai.image = rai.attack1;
        rai.setImage(rai.image);
        Nen n = Main.gameData.marine;
        int a = rai.getCount();
        
        /*        if (a >= 100 && (((n.x + n.size) <= gameFigure.x) && (n.x + n.size >= gameFigure.x - gameFigure.size / 2))
        || (n.x >= gameFigure.x + (gameFigure.size)) && (n.x <= (gameFigure.x + (gameFigure.size / 2) * 3))){
        this.nextState("SteelTwister");
        }
        else if (a >= 150){
        this.nextState("Default");
        }
        else{
        rai.setCount(a++);
        }*/
        //System.out.println("GameFigure.cState = " + previousState);
        //System.out.println("GameFigure.mState = " + motionState);
        if(a >= 10){
            System.out.println("SteelTwister state called");
            a = 0;
            rai.setCount(a);
            nextState("SteelTwister");
            System.out.println("Next input is SteelTwister");
            
        }
        else if(a >= 15){
            System.out.println("Default state called");
            a = 0;
            rai.setCount(a);
            nextState("Default");
            System.out.println("Next input is Default");
        }
        else{
            a++;
            rai.setCount(a);
        }
        gameFigure.mState = new Neutral(this.gameFigure, observers);
    }

    @Override
    public void nextState(String s) {
        System.out.println("Next input is " + s);
        if(s.equals("SteelTwister")){
            gameFigure.cState = new SteelTwister(this.gameFigure, observers);
        }
        else if(s.equals("Hit")){
            gameFigure.cState = new Hit(this.gameFigure, observers);
        }
        else if(s.equals("Default")){
            gameFigure.cState = new Default(this.gameFigure, observers);
        }
        /* attackBlocked will be implemented when nen Block state is implemented
        else if(s.equals("AttackBlocked") && previousState instanceof ViperStrike){
        gameFigure.cState = new AttackBlocked(gameFigure);
        }*/
        else{}
    }
    
}
