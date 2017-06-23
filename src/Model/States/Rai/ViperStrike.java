/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States.Rai;

import Model.GameFigure;
import Model.Rai;
import static Model.Rai.getImage;
import Model.States.CombatState;
import java.awt.Image;

/**
 *
 * @author matlock
 */
public class ViperStrike extends CombatState {

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
