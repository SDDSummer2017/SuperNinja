/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States.Sage;

import EventHandling.Observer;
import Model.GameFigure;
import Model.Sage;
import Model.States.CombatState;
import java.util.ArrayList;

/**
 *
 * @author Garrett A. Clement
 */
public class NeutralCombat extends CombatState {
    private double DELAY = 250;
    
    public NeutralCombat(GameFigure gameFigure, ArrayList<Observer> observers) {
        super(gameFigure, observers);
        ((Sage)gameFigure).image = ((Sage)gameFigure).staticImage;
    }

    @Override
    public void execute() {
        if(System.currentTimeMillis() - initTime >= DELAY)
            nextState("Chant");
        
    }

    @Override
    public void nextState(String s) { 
        if(s.equals("Chant"))
            gameFigure.cState = new Chant(gameFigure, observers);
        gameFigure.cState.notifyObservers();
    }
    
}
