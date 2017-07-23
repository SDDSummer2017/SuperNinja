/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States.Nen;

import Controller.Main;
import EventHandling.Observer;
import Model.GameFigure;
import Model.ShockWave;
import Model.States.CombatState;
import java.util.ArrayList;

/**
 *
 * @author Garrett A. Clement
 */
public class GroundShatter extends CombatState {

    private static final int SHATTER_DISTANCE = 300;
    private static final int SHATTER_HIGHT = 300;
    private static final int SHATTER_GROWTH = 0;
    private boolean isRising;
    private int shatter_x;
    private int shatter_y;
    private int shatter_h;
    private int shatter_w;
    private int cumulativeDistance;
    private ShockWave shatter ;
    public GroundShatter(GameFigure gameFigure, ArrayList<Observer> observers) {
        super(gameFigure, observers);
//        shatter_x = (int)(gameFigure.x + gameFigure.size);
//        shatter_y = (int)(gameFigure.y + gameFigure.size);
//        shatter_h = 10;
//        shatter_w = 30;
  
        if(Main.gameData.nen.isFacingRight)
            direction = 1;
        else
            direction = -1;
        shatter = new ShockWave((int)(gameFigure.x + gameFigure.size), (int)(gameFigure.y + gameFigure.size), 
                30, 10, direction, true);

        Main.gameData.addGameData(shatter);
        
        
    }

    @Override
    public void execute() {  
         
        if(!shatter.isFinished())
            shatter.update();
        else{
             Main.gameData.removeGameData(shatter);
             nextState("NeutralCombat");
        }
    } 
    
    @Override
    public void nextState(String s) { 
        if(s.equals("NeutralCombat"))
        { 
            gameFigure.cState = new NeutralCombat(gameFigure, observers);
        }
    
    }
}
