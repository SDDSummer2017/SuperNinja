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
public class MoveLeftCommand extends Command {

    public MoveLeftCommand(int keyCode) {
        super(keyCode);
    }

    @Override
    public void execute(GameFigure gameFigure) {
        gameFigure.mState.nextState("Move");
        gameFigure.isFacingRight = false;
    }
    
}
