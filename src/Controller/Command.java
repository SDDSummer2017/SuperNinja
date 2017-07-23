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
public abstract class Command {
    
    public int keyCode;
    
    public Command(int keyCode){
        this.keyCode = keyCode;
    };
    
    public abstract void execute(GameFigure gameFigure);
    
    public void release(GameFigure gameFigure){
        gameFigure.mState.nextState("NeutralMotion");
    }
    
    public void setKeyCode(int keyCode){
        this.keyCode = keyCode;
    }
    
    public int getKeyCode(){
        return keyCode; 
    }
    
}
