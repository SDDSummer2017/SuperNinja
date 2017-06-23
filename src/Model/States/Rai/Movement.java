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
import Model.Rai;
import Model.States.MotionState;

/**
 *
 * @author matlock
 */
public class Movement extends MotionState{

    public Movement(GameFigure gameFigure) {
        super(gameFigure);
        previousState = gameFigure.mState;
        combatState = gameFigure.cState;
    }

    @Override
    public void execute() {
        // this state is rais' first attack in his sequence chain
         //changes the images of the gamefigure depending on which state we are currently in 
        //alters the movement state to minimize the chance of errors
        Nen n = Main.gameData.marine;
        if(combatState instanceof Default){
            Rai rai = (Rai) this.gameFigure;
            rai.image = rai.movement;
            rai.setImage(rai.image);
            if (n.x < gameFigure.x){
                rai.x -= 10;
            }
            else if(n.x >= gameFigure.x + gameFigure.size){ 
                rai.x += 10;
            }
        }
        else{
            this.nextState("Neutral");
        }
    }

    @Override
    public void nextState(String s) {
         if(s.equals("Neutral") && previousState instanceof Movement){
            gameFigure.mState = new Neutral(gameFigure);
        }
    }
    
}
