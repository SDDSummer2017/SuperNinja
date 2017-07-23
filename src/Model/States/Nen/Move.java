/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States.Nen; 
import Controller.Main;
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
    private static final int MOVE_RATE = 10;

    public Move(GameFigure gameFigure, ArrayList<Observer> observers) {
        super(gameFigure, observers) ;
        time = System.currentTimeMillis();
        combatState = gameFigure.cState;
       
    }
    
    @Override
    public void execute() 
    { 
        int result;
       if(gameFigure.isFacingRight)
       {
           result = Main.gamePanel.handler.rightCollision(10);
           if(result == -1)
                gameFigure.x += MOVE_RATE;
           else
               gameFigure.x += result;
       }else
       {
           result = Main.gamePanel.handler.leftCollision(-10);
           if(result == -1)
               gameFigure.x += -MOVE_RATE;
           else
               gameFigure.x += result;
           
       } 
        
       if(System.currentTimeMillis() - time >= 500 )
       {
           this.notifyObservers();
           time = System.currentTimeMillis();
       }
    }
    
 
    @Override
    public void nextState(String s) {
  
        if(s.equals("NeutralMotion") && combatState instanceof NeutralCombat)
            gameFigure.mState = new NeutralMotion(gameFigure, observers);
        else if(s.equals("Jump" ) && combatState instanceof NeutralCombat)
            gameFigure.mState = new Jump(gameFigure, observers);
    
    }
    
}
