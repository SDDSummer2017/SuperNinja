/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States.Rai;

import Controller.Main;
import Controller.TimerListener;
import Model.GameFigure;
import Model.Nen;
import Model.Rai;
import Model.States.CombatState;

/**
 *
 * @author matlock
 */
public class ViperStrike extends CombatState {

    public TimerListener t;
    
    public ViperStrike(GameFigure gameFigure) {
        super(gameFigure);
        motionState = gameFigure.mState;
        previousState = gameFigure.cState;
        
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
        System.out.println("Rai.c = " + a);
        if (a >= 100 && (((n.x + n.size) <= gameFigure.x) && (n.x + n.size >= gameFigure.x - gameFigure.size / 2))
            || (n.x >= gameFigure.x + (gameFigure.size)) && (n.x <= (gameFigure.x + (gameFigure.size / 2) * 3))){
            this.nextState("SteelTwister");
        }
        else if (a >= 150){
            this.nextState("Default");
        }
        else{
            rai.setCount(a++);
        }
        gameFigure.mState = new Neutral(gameFigure);
    }

    @Override
    public void nextState(String s) {
        if(s.equals("SteelTwister") && previousState instanceof ViperStrike){
            gameFigure.cState = new SteelTwister(gameFigure);
        }
        else if(s.equals("Hit") && previousState instanceof ViperStrike){
            gameFigure.cState = new Hit(gameFigure);
        }
        else if(s.equals("Default") && previousState instanceof ViperStrike){
            gameFigure.cState = new Default(gameFigure);
        }
        /* attackBlocked will be implemented when nen Block state is implemented
        else if(s.equals("AttackBlocked") && previousState instanceof ViperStrike){
        gameFigure.cState = new AttackBlocked(gameFigure);
        }*/
        else{}
    }
    
}
