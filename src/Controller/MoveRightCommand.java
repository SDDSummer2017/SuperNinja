/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.GameFigure;

/**
 *
 * @author Garrett A. Clement
 */
public class MoveRightCommand extends Command {

    public MoveRightCommand(int keyCode) {
        super(keyCode);
    }

    @Override
    public void execute(GameFigure gameFigure) { 
         gameFigure.isFacingRight = true;
         gameFigure.mState.nextState("Move");
    }
    
}
