/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States;

import Model.GameFigure;
import View.GamePanel;

/**
 *
 * @author Garrett A. Clement
 */
public abstract class State {
    protected GameFigure gameFigure;
       
    public State(GameFigure gameFigure)
    {
        this.gameFigure = gameFigure;
    }
    
    public abstract void execute();
        
    public abstract void nextState(String s);
    
    public void interruptState(State s){}
    
}
