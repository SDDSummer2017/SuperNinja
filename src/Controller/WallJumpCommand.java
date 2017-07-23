/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.GameFigure;
import Model.States.Nen.Jump;

/**
 *
 * @author Garrett A. Clement
 */
public class WallJumpCommand extends Command {

    public WallJumpCommand(int keyCode) {
        super(keyCode);
    }

    @Override
    public void execute(GameFigure gameFigure) { 
        
        if(gameFigure.mState instanceof Jump)
        {
            if(Main.gamePanel.handler.checkWallLeftJump())
                gameFigure.mState.nextState("WallJump");
            else if(Main.gamePanel.handler.checkWallRightJump())
            {
                System.out.println("Performing right wall jump");
                gameFigure.mState.nextState("WallJump");
            }
           }
    }
}
