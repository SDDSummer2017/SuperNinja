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
import java.util.ArrayList;

/**
 *
 * @author Garrett A. Clement
 */
public class NeutralMotion extends MotionState {
    
    private double trackedHp;
    
    public NeutralMotion(GameFigure gameFigure, ArrayList<Observer> observers) {
        super(gameFigure, observers);
        trackedHp = gameFigure.health;
        boolean isHurt;
        System.out.println("Neutral");
    }

    @Override
    public void execute() {
        
       //System.out.println(Math.abs(Main.gameData.nen.x - gameFigure.x));
       
        if(gameFigure.health < trackedHp  && Math.abs(Main.gameData.nen.x - gameFigure.x) <= 75 && gameFigure.cState instanceof NeutralCombat)
        {
            System.out.println("t " + trackedHp + ", f" + gameFigure.health);
            nextState("Teleport");
        }
        
        if(gameFigure.cState instanceof NeutralCombat)
            trackedHp = gameFigure.health;
        
        
    }

    @Override
    public void nextState(String s) { 
        if(s.equals("Teleport"))
            gameFigure.mState = new Teleport(gameFigure, observers);
    }
    
}
