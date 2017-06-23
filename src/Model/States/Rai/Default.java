/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States.Rai;

import Controller.Main;
import Model.GameData;
import Model.GameFigure;
import Model.Nen;
import Model.States.CombatState;

/**
 *
 * @author matlock
 */
public class Default extends CombatState{

    public Default(GameFigure gameFigure) {
        super(gameFigure);
        motionState = gameFigure.mState;
        previousState = gameFigure.cState;
    }

    @Override
    public void execute() {
        //in the event that the combat state is in default setting then the movement
        //state should have priority and will dictate the animation sequence
        Nen n = Main.gameData.marine;
        //System.out.println("Rai.x = " + gameFigure.x);
        
        if(n.x < gameFigure.x + 125 && n.x > gameFigure.x){
            this.nextState("Throw");
        }
        else if(n.x > gameFigure.x - 50 && n.x < gameFigure.x){
            this.nextState("ViperStrike");
        }
        /*if (((n.x + n.size) < gameFigure.x && (n.x + n.size) >= (gameFigure.x - 25))
        || (n.x >= gameFigure.x + gameFigure.size) && (n.x <= gameFigure.x + 25)){
        this.nextState("Throw");
        }
        else if((((n.x + n.size) < gameFigure.x) && (n.x + n.size >= gameFigure.x - 50))
        || (n.x >= gameFigure.x + (gameFigure.size)) && (n.x <= (gameFigure.x + 50))){
        this.nextState("ViperStrike");
        }*/
        else{}
    }

    @Override
    public void nextState(String s) {
        //the Default state leads to 4 of the additional states which have other states branch off
        System.out.println("Rai.state = " + gameFigure.cState);
        if(s.equals("Hit") && previousState instanceof Default){
            gameFigure.cState = new Hit(gameFigure);
        }
        else if(s.equals("Throw") && previousState instanceof Default){
            gameFigure.cState = new Throw(gameFigure);
        }
        else if(s.equals("ViperStrike") && previousState instanceof Default){
            gameFigure.cState = new ViperStrike(gameFigure);
        }
        else{}
        /* not yet testable and therefore will be implemented later 
        else if(s.equals("IsThrown") && previousState instanceof Default){
        gameFigure.cState = new IsThrown(gameFigure);
        }*/

    }
    
}
