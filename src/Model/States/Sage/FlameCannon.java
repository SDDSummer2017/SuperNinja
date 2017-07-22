/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States.Sage;

import Controller.Main;
import EventHandling.Observer;
import Model.GameFigure;
import Model.States.CombatState;
import java.util.ArrayList;

/**
 *
 * @author Garrett A. Clement
 */
public class FlameCannon extends CombatState{

    
    private final static int TRANSITION_DELAY = 1000;
    private final static int MAX_SPELLS = 9;
    private final static int WAVE1_TIME = 500;
    private final static int WAVE2_TIME = 1000;
    private final static int WAVE3_TIME = 1500;
    private double tx;
    private double ty;
    
    //Volley wave 1 5 balls wave 2 3 wave3 1
    public FlameCannon(GameFigure gameFigure, ArrayList<Observer> observers) {
        super(gameFigure, observers);
        tx = Main.gameData.nen.x;
        ty = Main.gameData.nen.y;
    }

    @Override
    public void execute() {
        nextState("NeutralCombat");
    }

    @Override
    public void nextState(String s) {
        if(s.equals("NeutralCombat"))
            gameFigure.cState = new NeutralCombat(gameFigure, observers);
    }
    
}
