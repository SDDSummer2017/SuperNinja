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
public class MultiDamageEffect extends StatusEffect {

    
    public MultiDamageEffect(GameFigure gameFigure, int damage, int duration) {
        super(gameFigure, damage, duration);
    }

    @Override
    public void applyEffect(GameFigure target) { 
    }

    @Override
    public boolean isFinished() { 
        return true; 
    }
    
}
