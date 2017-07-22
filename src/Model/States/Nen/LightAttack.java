
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States.Nen;

import Controller.Main;
import EventHandling.Observer;
import Model.HitBox;
import Model.GameFigure;
import Model.States.CombatState;
import StatusEffects.DamageEffect;
import java.util.ArrayList;
 

/**
 *
 * @author Garrett A. Clement
 */
public class LightAttack extends CombatState {
     
    HitBox hitBox; 
    private static final int DURATION = 500;
    private static final int MID_TIME = 150;
    private static final int HIGH_TIME = 300;  
    private static final int DAMAGE = 10;
    
    public LightAttack(GameFigure gameFigure, ArrayList<Observer> observers) {
        super(gameFigure, observers);
        gameFigure.damage = 10; 
        hitBox =  new HitBox(gameFigure.x + (gameFigure.size/2), gameFigure.y, 75, 10, gameFigure, new DamageEffect(gameFigure, DAMAGE ,5000));
        Main.gameData.addGameData(hitBox);
    }
    
    @Override
    public void execute() {    
        if(System.currentTimeMillis() - initTime >= DURATION)
        {
            Main.gameData.removeGameData(hitBox);
            nextState("NeutralCombat");
        }
        
        //Attack right
        if(direction > 0)
            if(System.currentTimeMillis() - initTime  >= MID_TIME && System.currentTimeMillis() - initTime <= HIGH_TIME)
            {
                hitBox.translate(gameFigure.x + (gameFigure.size/2) + 20, gameFigure.y + 30);  
            }
            else if(System.currentTimeMillis()  - initTime > HIGH_TIME) 
                hitBox.translate(gameFigure.x + (gameFigure.size/2), gameFigure.y + 60);  
            else
                hitBox.translate(gameFigure.x + (gameFigure.size/2), gameFigure.y);
        else
            if(System.currentTimeMillis() - initTime  >= MID_TIME && System.currentTimeMillis() - initTime <= HIGH_TIME) 
                hitBox.translate(gameFigure.x - 95 + (gameFigure.size/2), gameFigure.y + 30);  
            else if(System.currentTimeMillis()  - initTime > HIGH_TIME) 
                hitBox.translate(gameFigure.x - 75 + (gameFigure.size/2), gameFigure.y + 60);  
            else
                hitBox.translate(gameFigure.x - 75 + (gameFigure.size/2), gameFigure.y);
    }

    @Override
    public void nextState(String s) {
       if(s.equals("NeutralCombat"))
           gameFigure.cState = new NeutralCombat(gameFigure, observers);
       else if(s.equals("LightAttack") && System.currentTimeMillis() - initTime  >= MID_TIME && System.currentTimeMillis() - initTime <= HIGH_TIME)
       {
           gameFigure.cState = new Whirlwind(gameFigure, observers);
           Main.gameData.removeGameData(hitBox);
       }
    }
}