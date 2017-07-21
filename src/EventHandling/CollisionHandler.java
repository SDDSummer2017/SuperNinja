/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventHandling;

import Controller.Main;
import Level.Checkpoint;
import Level.Platform;
import Level.VictoryCheckPoint;
import Model.Collision;
import Model.GameFigure;
import Model.HitBox;
import Model.Nen;
import Model.Shuriken;
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
       if(object1 instanceof HitBox && object2 instanceof GameFigure){ 
            applyStatusEffects((GameFigure)object2, (HitBox)object1);
            try {
                if(((GameFigure)object2).isGoodGuy && !((HitBox)object1).isGoodGuy){
                    ((GameFigure)object2).cState.nextState("Hit");
                }
                else if (!((GameFigure)object2).isGoodGuy && ((HitBox)object1).isGoodGuy){
                    ((GameFigure)object2).cState.nextState("Hit");
                }
           } catch (Exception e) {} 
       }
       else if(object1 instanceof GameFigure && object2 instanceof HitBox){
            applyStatusEffects((GameFigure)object1, (HitBox)object2);
            try{
                if(((GameFigure)object1).isGoodGuy && !((HitBox)object2).isGoodGuy){
                    ((GameFigure)object1).cState.nextState("Hit");
                }
                else if (!((GameFigure)object1).isGoodGuy && ((HitBox)object2).isGoodGuy){
                    ((GameFigure)object1).cState.nextState("Hit");
                }
            } catch(Exception e){}
        }
       
       if(object1 instanceof VictoryCheckPoint && object2 instanceof Nen)
       {
           ((VictoryCheckPoint) object1).notifyObservers();
       }
       if(object1 instanceof Shuriken && object2 instanceof Platform)
       {
           Main.gameData.level.removables.add(object1);
            Main.gameData.level.removables.add(((Shuriken) object1).hitbox);
       }
       if(object1 instanceof Checkpoint && object2 instanceof Nen)
       {
            ((Checkpoint)object1).notifyObservers();
       }
        
       
    }

    @Override
    public void onNotify(String string) {}
    
    private void applyStatusEffects(GameFigure gameFigure, HitBox hitbox)
    {
        //if statement to makesure no friendly fire occurs 
        if((!gameFigure.isGoodGuy && hitbox.gameFigure.isGoodGuy) || (gameFigure.isGoodGuy && !hitbox.gameFigure.isGoodGuy))
            for(StatusEffect se : hitbox.statusEffects)
                if(!gameFigure.effectsManager.contains(hitbox, se)) 
                    gameFigure.effectsManager.addEffect(hitbox, se);
    }
    
}
