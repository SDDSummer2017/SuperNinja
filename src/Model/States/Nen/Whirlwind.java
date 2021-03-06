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
public class Whirlwind extends CombatState {

    private static final int TRAVEL_DISTANCE =  100;
    private static final int TRAVEL_RATE = 5;
    private boolean rightStrike;
    private int distance = 0;
    private HitBox hitBox;
    private long soundDelay;
    private static final long SOUND_DELAY = 200;
    public Whirlwind(GameFigure gameFigure, ArrayList<Observer> observers) {
        super(gameFigure, observers);
        
        if(gameFigure.isFacingRight)
        {
            rightStrike = true;
            hitBox = new HitBox(gameFigure.x - 20, gameFigure.y, 50, 10, gameFigure, new DamageEffect(gameFigure, 10, 250));
        }
        else 
            hitBox = new HitBox(gameFigure.x - 55, gameFigure.y, 50, 10, gameFigure, new DamageEffect(gameFigure, 10, 250));
        Main.gameData.addGameData(hitBox);
        
    }
    @Override
    public void execute() {
        
        if(TRAVEL_DISTANCE <= distance)
        {
            nextState("Finished");
         //   Main.gameData.removeAlly(hitBox);
        }else
        { 
            distance += TRAVEL_RATE;
            if(gameFigure.isFacingRight) 
                gameFigure.x += TRAVEL_RATE; 
            else 
                gameFigure.x -= TRAVEL_RATE;
            
            if(rightStrike)
                hitBox.translate(gameFigure.x + 20 + gameFigure.size/2, gameFigure.y + gameFigure.size/2);
            else
                hitBox.translate(gameFigure.x - 65 + gameFigure.size/2, gameFigure.y + gameFigure.size/2); 
            
            if(System.currentTimeMillis() - soundDelay >= SOUND_DELAY)
            { 
                this.notifyObservers("Whirlwind");
                soundDelay = System.currentTimeMillis();
            }  
                rightStrike = !rightStrike;
        }
        
    }

    @Override
    public void nextState(String s) { 
        if(s.equals("Finished"))
        {
            gameFigure.cState = new NeutralCombat(gameFigure, observers);
            Main.gameData.removeGameData(hitBox);
        }
    }
    
    public void strikeRihgt(){
        
    } 
    
}
