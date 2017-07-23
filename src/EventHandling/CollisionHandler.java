/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventHandling;

import Controller.Main;
import Controller.QuadTree;
import Level.Checkpoint;
import Level.Platform;
import Level.VictoryCheckPoint;
import Model.Collision;
import Model.GameFigure;
import Model.HitBox;
import Model.Nen;
import Model.Shuriken;
import Model.Terro;
import StatusEffects.StatusEffect;
import static View.GamePanel.WORLD_HEIGHT;
import static View.GamePanel.WORLD_WIDTH;
import java.util.ArrayList;

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
    
        
      public boolean checkWallLeftJump(){
        QuadTree jump = new QuadTree(7, 5, 0, 0, WORLD_WIDTH, WORLD_HEIGHT);
        GameFigure nen = Main.gameData.nen;
        for(GameFigure gf : Main.gameData.level.terrain)
            jump.insert(gf);
        jump.insert(nen);
        
        ArrayList<Collision> possibleJumps = jump.getList(nen);
        
       
        for(Collision c : possibleJumps)
        {  
            if(Math.abs(c.getCollisionBox().getX() - (nen.x + nen.getCollisionBox().width )) <= 20 && 
                    nen.y  >=  c.getCollisionBox().getY() && 
                    nen.y  <= c.getCollisionBox().getY() + c.getCollisionBox().height)
                if(!(c instanceof Nen))
                    return true;
           
        }
        
            return false;
       }
      
        public boolean checkWallRightJump(){
            QuadTree jump = new QuadTree(7, 5, 0, 0, WORLD_WIDTH, WORLD_HEIGHT);
            GameFigure nen = Main.gameData.nen;
            for(GameFigure gf : Main.gameData.level.terrain)
                jump.insert(gf);
            jump.insert(nen);

            ArrayList<Collision> possibleJumps = jump.getList(nen);


            for(Collision c : possibleJumps)
            {  
                if(Math.abs(c.getCollisionBox().getX() + c.getCollisionBox().getWidth() - nen.x) <= 20 && 
                        nen.y  >=  c.getCollisionBox().getY() && 
                        nen.y  <= c.getCollisionBox().getY() + c.getCollisionBox().height)
                    if(!(c instanceof Nen))
                        return true;

            }
                return false;
        }
        
            public int rightCollision(int xInc){
        QuadTree jump = new QuadTree(7, 5, 0, 0, WORLD_WIDTH, WORLD_HEIGHT);
        GameFigure nen = Main.gameData.nen;
        for(GameFigure gf : Main.gameData.level.terrain)
            jump.insert(gf);
        jump.insert(nen);
        
        ArrayList<Collision> possibleJumps = jump.getList(nen); 
        
        for(Collision c : possibleJumps)
        { 
            if(!(c instanceof Nen))
                if(nen.x + nen.getCollisionBox().getWidth() + xInc  >= c.getCollisionBox().getX() &&
                        nen.x + xInc <= c.getCollisionBox().getX() + c.getCollisionBox().getWidth() &&
                        nen.y + nen.getCollisionBox().height - 5   > c.getCollisionBox().getY() && 
                        nen.y  <= c.getCollisionBox().getY() + c.getCollisionBox().height - 5)
                {
                    if(c instanceof Checkpoint)
                        ((Checkpoint)c).notifyObservers();
                    return -1 *(int)(nen.x + nen.getCollisionBox().getWidth() - c.getCollisionBox().getX()) - 1;
                            
                }
        }
        
        
        return -1;
    }
    
        public int leftCollision(int xInc){
        QuadTree jump = new QuadTree(7, 5, 0, 0, WORLD_WIDTH, WORLD_HEIGHT);
        GameFigure nen = Main.gameData.nen;
        for(GameFigure gf : Main.gameData.level.terrain)
            jump.insert(gf);
        jump.insert(nen);
        
        ArrayList<Collision> possibleJumps = jump.getList(nen); 
        
        for(Collision c : possibleJumps)
        {
            if(!(c instanceof Nen))
                if(nen.x + xInc  <= c.getCollisionBox().getX() + c.getCollisionBox().width &&
                        nen.x + xInc >= c.getCollisionBox().getX() &&
                        nen.y + nen.getCollisionBox().height - 5 >=  c.getCollisionBox().getY()  && 
                        nen.y  <= c.getCollisionBox().getY() + c.getCollisionBox().height - 5)
                {
                        if(c instanceof Checkpoint)
                            ((Checkpoint)c).notifyObservers();
                        return (int)(c.getCollisionBox().getX() + c.getCollisionBox().getWidth() - nen.x) + 1;
                }
        }
        
        
        return -1;
    }
    
}
