/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States.Sage;

import Controller.Main;
import EventHandling.Observer;
import Model.GameFigure;
import Model.Sage;
import Model.States.CombatState;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Garrett A. Clement
 */ 

public class Chant extends CombatState{

    private final static int SOUL_FLAME_CHANT_TIME = 5000;
   // private final static int FLAME_CANNON_CHANT_TIME = 2000;
    private final static int FLAME_DRAGON_CHANT_TIME = 8000;
    private long chantTime;
    
    private enum Spell {SOUL_FLAME, FLAME_CANNON, FLAME_DRAGON, CHANT}; 
    private Spell nextSpell;
    
    
    public Chant(GameFigure gameFigure, ArrayList<Observer> observers) {
        super(gameFigure, observers);
        determineNextSpell();
        ((Sage)gameFigure).image = ((Sage)gameFigure).staticImage;
    }

    @Override
    public void execute() { 
         ((Sage)gameFigure).image = ((Sage)gameFigure).staticImage;
  
        if(nextSpell == Spell.FLAME_DRAGON)
        {
          if(System.currentTimeMillis() - initTime >= FLAME_DRAGON_CHANT_TIME)
              nextState("FlameDragon");
        }
        else if(nextSpell == Spell.SOUL_FLAME)        
            if(System.currentTimeMillis() - initTime >= SOUL_FLAME_CHANT_TIME)
            {
                if(nextSpell == Spell.SOUL_FLAME)
                    nextState("SoulFlame");
            }
         
         
//        if(nextSpell == Spell.FLAME_DRAGON)
//        {
//          if(System.currentTimeMillis() - initTime >= FLAME_DRAGON_CHANT_TIME)
//              nextState("FlameDragon");
//        }
//        else if(nextSpell == Spell.FLAME_CANNON)
//        {
//            if(System.currentTimeMillis() - initTime >= FLAME_CANNON_CHANT_TIME)
//                nextState("FlameCannon");
//            
//        }else if(nextSpell == Spell.SOUL_FLAME)        
//            if(System.currentTimeMillis() - initTime >= SOUL_FLAME_CHANT_TIME)
//            {
//                if(canCastFlameCannon())
//                    nextSpell = Spell.FLAME_CANNON;
//                else if(nextSpell == Spell.SOUL_FLAME)
//                    nextState("SoulFlame");
//            }
         
    }

    @Override
    public void nextState(String s) { 
        System.out.println("Casting " + s);
        if(s.equals("SoulFlame"))
            gameFigure.cState =  new SoulFlame(gameFigure, observers);
        else if(s.equals("FlameCannon"))
            gameFigure.cState = new FlameCannon(gameFigure, observers);
        else if(s.equals("FlameDragon"))
            gameFigure.cState = new FlameDragon(gameFigure, observers);
    }
    
    private void determineNextSpell(){
        Random rand = new Random(); 
        int  n = rand.nextInt(10) + 1;
        
        if(n >= 7)
            nextSpell = Spell.FLAME_DRAGON;
        else
            nextSpell = Spell.SOUL_FLAME; 
    }
    
    private boolean canCastFlameCannon(){
           if(Math.abs(Math.abs(Main.gameData.nen.x) - Math.abs(gameFigure.x)) <= 75 
             && Math.abs(Math.abs(Main.gameData.nen.y) - Math.abs(gameFigure.y)) <= 75)
               return true;
           else
               return false;
    }
}
