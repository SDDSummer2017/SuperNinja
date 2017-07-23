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
public class DotEffect extends StatusEffect {

    private int damageInterval;
    private long lastApplied;
    
    public DotEffect(GameFigure gameFigure, int damage, int duration, int damageInterval) {
        super(gameFigure, damage, duration);
        this.damageInterval = damageInterval;
        lastApplied = 0;
    }

    @Override
    public void applyEffect(GameFigure target) { 
        if(System.currentTimeMillis() - timeApplied > duration)
            isFinished = true;
        else if((System.currentTimeMillis() - lastApplied) >= damageInterval)
        { 
            lastApplied = System.currentTimeMillis();
            target.health -= damage; 
        }
    }

    @Override
    public boolean isFinished() { 
        return isFinished;
    }
    
        @Override
    public StatusEffect clone() {
        DotEffect clone = new DotEffect(gameFigure, damage, duration, 0);
        return clone(clone);
    }
    
    @Override
    public StatusEffect clone(StatusEffect clone){
        ((DotEffect)clone).damageInterval = this.damageInterval;
        return super.clone(clone);
    }
    
}
