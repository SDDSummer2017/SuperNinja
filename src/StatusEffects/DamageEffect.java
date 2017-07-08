/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StatusEffects;

import Model.GameFigure;

/**
 *
 * @author Garrett A. Clement
 */
public class DamageEffect extends StatusEffect {

    public DamageEffect(GameFigure gameFigure,  int damage, int duration) {
        super(gameFigure, damage, duration);
    }

    @Override
    public void applyEffect(GameFigure target) {  
        if(isFinished)
            return;
        target.health -= damage;
        isFinished = true; 
        
        
    }

    @Override
    public boolean isFinished() {
        
        if(System.currentTimeMillis() - timeApplied >= duration)
        {
            isFinished = false;
            return true;
        }
        else
            return false;
    }
    
}
