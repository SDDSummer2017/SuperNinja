/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States;

import EventHandling.Observer;
import Model.GameFigure;
import java.util.ArrayList;

/**
 *
 * @author Garrett A. Clement
 */
public class Death extends CombatState {

    public Death(GameFigure gameFigure, ArrayList<Observer> observers) {
        super(gameFigure, observers);
    }
 

    @Override
    public void execute() { 
    }

    @Override
    public void nextState(String s) { 
    }
    
}
