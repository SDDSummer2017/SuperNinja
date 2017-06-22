/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States.Nen;

import Model.GameFigure;
import Model.States.MotionState;
import View.GamePanel;

/**
 *
 * @author Garrett A. Clement
 */
public class Jump extends Move {

    private boolean isMoving = false;
    private boolean jumpMade;
    private final int JUMP_LIMIT = 100;
    private int jumpHeight = 1;
    private int dy = 7;
    public Jump(GameFigure gameFigure) {
        super(gameFigure);
        
    }

    @Override
    public void execute() {
        super.execute();
        System.out.println("JUMP HEIGHT:" + jumpHeight);
        System.out.println("nen: " + gameFigure.y);
        System.out.println("gamePanel: " + gameFigure.size);
        if(JUMP_LIMIT >= jumpHeight)
        { 
            jumpMade = true;
            gameFigure.y -= dy;
        }else if(jumpMade && gameFigure.y >= 500)
            nextState("JumpMade"); 
        else
            gameFigure.y += dy;
        jumpHeight += dy;
        
        if(isMoving)
            move();
    }

    public void move(){
        System.out.println("I am here yeahhhh");
        if(gameFigure.isFacingRight)
            gameFigure.x += 5;
        else
            gameFigure.x -= 5;
    }
    
    @Override
    public void nextState(String s) {
        if(s.equals("Move"))
            isMoving = !isMoving;
        if(s.equals("JumpMade") && isMoving)
            gameFigure.mState = new Move(gameFigure);
        else if(s.equals("JumpMade"))
            gameFigure.mState = new NeutralMotion(gameFigure);
    }
    
}
