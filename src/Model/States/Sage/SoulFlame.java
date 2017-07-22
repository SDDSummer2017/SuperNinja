/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States.Sage;

import Controller.Main;
import EventHandling.Observer;
import Model.Enemy;
import Model.GameFigure;
import Model.Sage;
import Model.SoulFlameSpell;
 
import Model.States.CombatState; 
import java.util.ArrayList;

/**
 *
 * @author Garrett A. Clement
 */
public class SoulFlame extends CombatState {
 
    private final static int TRANSITION_DELAY = 1000;
    
    
    public SoulFlame(GameFigure gameFigure, ArrayList<Observer> observers) {
        super(gameFigure, observers); 
        ((Enemy)gameFigure).image = ((Enemy)gameFigure).neutral; 
        Main.gameData.addGameData(new SoulFlameSpell(gameFigure.x , gameFigure.y, Main.gameData.nen.x + Main.gameData.nen.size/2, Main.gameData.nen.y + Main.gameData.nen.size/2));
        ((Sage)gameFigure).image = ((Sage)gameFigure).throwImage;
    }

    @Override
    public void execute() {  
        if(System.currentTimeMillis() - initTime >= TRANSITION_DELAY)
            nextState("Chant");
        
    }

    @Override
    public void nextState(String s) { 
        if(s.equals("Chant"))
            gameFigure.cState = new NeutralCombat(gameFigure, observers);
    }
    
}
