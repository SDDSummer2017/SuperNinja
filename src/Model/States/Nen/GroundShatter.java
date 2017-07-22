/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States.Nen;

import Controller.Main;
import EventHandling.Observer;
import Model.GameFigure;
import Model.HitBox;
import Model.States.CombatState;
import StatusEffects.DamageEffect;
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
    
    public GroundShatter(GameFigure gameFigure, ArrayList<Observer> observers) {
        super(gameFigure, observers);
        shatter_x = (int)(gameFigure.x + gameFigure.size);
        shatter_y = (int)(gameFigure.y + gameFigure.size);
        shatter_h = 10;
        shatter_w = 30;
        hitBox =  new HitBox(shatter_x, shatter_y, shatter_w, shatter_h, gameFigure, new DamageEffect(gameFigure, 40 ,5000));
        if(Main.gameData.nen.isFacingRight)
            direction = 1;
        else
            direction = -1;
        Main.gameData.addGameData(hitBox);
        
        
    }

    @Override
    public void execute() { 
    
        shatter_x += 10 * direction;
        cumulativeDistance += 10; 

        if(SHATTER_DISTANCE/2 == cumulativeDistance)
            isRising = true;
        else if(shatter_h <= SHATTER_HIGHT && !isRising)
        {
            shatter_h += 20;
            shatter_y -= 20;
        }else
        {
            shatter_y += 20;
            shatter_h -= 20;
        }
        
        if(cumulativeDistance <= SHATTER_DISTANCE)
        { 
            hitBox.translate(shatter_x, shatter_y, shatter_w, shatter_h);
        }else{
            Main.gameData.removeGameData(hitBox);
            nextState("NeutralCombat");
            this.notifyObservers("HeavyAttackFinished");
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
