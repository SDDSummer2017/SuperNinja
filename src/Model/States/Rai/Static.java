/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States.Rai;

import Controller.Main;
import Model.GameFigure;
import Model.Nen;
import Model.Rai;
import Model.States.MotionState;

/**
 *
 * @author matlock
 */
public class Static extends MotionState{

    public Static(GameFigure gameFigure) {
        super(gameFigure);
        previousState = gameFigure.mState;
        combatState = gameFigure.cState;
    }

    @Override
    public void execute() {
        Nen n = Main.gameData.marine;
        Rai rai = (Rai) this.gameFigure;
        rai.image = rai.movement;
        rai.setImage(rai.image);
        combatState = new Default(gameFigure);
        /*if ((rai.x - n.x) <= Main.gamePanel.width){
        this.nextState("Neutral");
        }*/
    }

    @Override
    public void nextState(String s) {
        if(s.equals("Neutral") && previousState instanceof Static){
            gameFigure.mState = new Neutral(gameFigure);
        }
    }
    
}
