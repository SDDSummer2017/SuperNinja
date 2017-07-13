/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States.Sage;

import Controller.Main;
import EventHandling.Observer;
import Model.GameFigure;
import Model.States.MotionState;
import Model.States.Terro.Neutral;
import java.util.ArrayList;

/**
 *
 * @author Garrett A. Clement
 */
public class Teleport extends MotionState {

    private double distance;
    public Teleport(GameFigure gameFigure, ArrayList<Observer> observers) {
        super(gameFigure, observers);
        if(Main.gameData.nen.x > gameFigure.x)
            distance = -250;
        else
            distance = 250;
        System.out.print("Teleport state");
            
    }

    @Override
    public void execute() {
        if(System.currentTimeMillis() - time >= 250)
        {
            gameFigure.x += distance;
            nextState("NeutralMotion");
        }
    }

    @Override
    public void nextState(String s) {
        
        gameFigure.mState = new NeutralMotion(gameFigure, observers);
    }
    
    
    
    
}
