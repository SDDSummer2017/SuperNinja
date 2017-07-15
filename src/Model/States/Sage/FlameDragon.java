/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States.Sage;

import Controller.Main;
import EventHandling.Observer;
import Model.FlameDragonSpell;
import Model.GameFigure;
import Model.HitBox;
import Model.Sage;
import Model.States.CombatState;
import StatusEffects.DamageEffect;
import java.util.ArrayList;

/**
 *
 * @author Garrett A. Clement
 */
public class FlameDragon extends CombatState {
 
        private double y;
        private double dx; 
        private int direction; 
        private final static int CAST_DURATION = 1500;
        private FlameDragonSpell spell; 
        private static double delay = 250;
        
        
    public FlameDragon(GameFigure gameFigure, ArrayList<Observer> observers) {
       super(gameFigure, observers);
 
       if(Main.gameData.nen.x < gameFigure.x)
           direction = -1;
       else
           direction = 1; 
       
       spell = new FlameDragonSpell(gameFigure.x, gameFigure.y, 50, direction);
       ((Sage)gameFigure).image = ((Sage)gameFigure).throwImage;
       Main.gameData.addGameData(spell);
    }

    @Override
    public void execute() {
 
         if(System.currentTimeMillis() - initTime <= CAST_DURATION)
         {
            if(System.currentTimeMillis() - delay >= 250)
               {
                   spell.increase();
                   delay = System.currentTimeMillis();
               }
         }else
             nextState("NeutralCombat");
        
    }

    @Override
    public void nextState(String s) {
        Main.gameData.removeGameData(hitBox);
        if(s.equals("NeutralCombat"))
            gameFigure.cState = new NeutralCombat(gameFigure, observers);
    }
    
}
