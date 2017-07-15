 
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States.Nen;

import EventHandling.Observer;
import Model.GameFigure;
import Model.States.CombatState;
import java.util.ArrayList;

/**
 *
 * @author Garrett A. Clement
 */
public class NeutralCombat extends CombatState {

    private static int THROWING_MODE_DELAY = 3000;
    private long throwCoolDown;
    
     public NeutralCombat(GameFigure gameFigure, ArrayList<Observer> observers) {
        super(gameFigure, observers) ;
        gameFigure.damage = 0;  
        throwCoolDown = System.currentTimeMillis(); 
        
        
    }

    @Override
    public void execute() {
        
    }
 
    @Override
    public void nextState(String s) {
        if(s.equals("LightAttack"))
            gameFigure.cState = new LightAttack(gameFigure, this.observers);
        else if(s.equals("HeavyAttack"))
            gameFigure.cState = new HeavyAttack(gameFigure, this.observers);
        else if(s.equals("ThrowingMode") && System.currentTimeMillis() - throwCoolDown > THROWING_MODE_DELAY)
            gameFigure.cState = new ThrowingMode(gameFigure, this.observers);
        
        gameFigure.cState.notifyObservers();
    }
}
 
