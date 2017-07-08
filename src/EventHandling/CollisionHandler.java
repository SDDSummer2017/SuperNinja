/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventHandling;

import Model.Collision;
import Model.GameFigure;
import Model.HitBox;
import StatusEffects.StatusEffect;

/**
 *
 * @author Garrett A. Clement
 */
public class CollisionHandler implements CollisionObserver {
 
   
    @Override
    public void onNotify(GameFigure gameFigureOne, GameFigure gameFigureTwo) { 
    }

    @Override
    public void onNotify(Collision object1, Collision object2) {  
        
        // this if statment block detects collision between any gameFigure and hitbox
       if(object1 instanceof HitBox && object2 instanceof GameFigure) 
           applyStatusEffects((GameFigure)object2, (HitBox)object1);
       
       else if(object1 instanceof GameFigure && object2 instanceof HitBox)
           applyStatusEffects((GameFigure)object1, (HitBox)object2); 
    }

    @Override
    public void onNotify(String string) {}
    
    private void applyStatusEffects(GameFigure gameFigure, HitBox hitbox)
    {
        //if statement to makesure no friendly fire occurs 
        if((!gameFigure.isGoodGuy && !hitbox.gameFigure.isGoodGuy) || !((gameFigure.isGoodGuy && hitbox.gameFigure.isGoodGuy)))
            for(StatusEffect se : hitbox.statusEffects)
                if(!gameFigure.statusEffects.contains(se)) 
                    gameFigure.statusEffects.add(se);  
    }
    
}
