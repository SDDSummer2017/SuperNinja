/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States.Nen; 
import EventHandling.Observer;
import Model.GameFigure;
import Model.States.CombatState;
import Model.States.MotionState;
import java.util.ArrayList;

/**
 *
 * @author Garrett A. Clement
 */
public class Move extends MotionState {

    CombatState combatState; 
    
    public Move(GameFigure gameFigure, ArrayList<Observer> observers) {
        super(gameFigure, observers) ;
        combatState = gameFigure.cState;
    }
    
    @Override
    public void execute() 
    { 
        if(gameFigure.isFacingRight)
            gameFigure.x += 10;
       else
            gameFigure.x -= 10;  
    }
 
    @Override
    public void nextState(String s) {
  
        if(s.equals("NeutralMotion") && combatState instanceof NeutralCombat)
            gameFigure.mState = new NeutralMotion(gameFigure, observers);
        else if(s.equals("Jump" ) && combatState instanceof NeutralCombat)
            gameFigure.mState = new Jump(gameFigure, observers);
    
    }
    
}
