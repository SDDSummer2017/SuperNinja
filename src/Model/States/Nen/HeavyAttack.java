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
import java.util.ArrayList;

/**
 *
 * @author Garrett A. Clement
 */
public class HeavyAttack extends CombatState {

    private final static int BACK_STEP = 15;
    private final static int FRONT_STEP = 50;
    private final static int BACK_STEP_RATE = 2;
    private final static int FRONT_STEP_RATE = 5;
    private final static int CHARGE_DURATION = 500;
    private final static int PAUSE_DURATION = 200; 
    private final static int ATTACK_HEIGHT = 20;
    private final static int ATTACK_WIDTH = 100;
    
    private int backStep = 0;
    private int frontStep = 0;
    private boolean isFrontStep; 
    
    private long time = 0;
    public HeavyAttack(GameFigure gameFigure, ArrayList<Observer> observers) {
        super(gameFigure, observers); 
        hitBox = new HitBox(0, 0, 0, 0);
        Main.gameData.allies.add(hitBox);
        gameFigure.damage = 30;
        
    }

    @Override
    public void execute() { 
            if(isFrontStep && frontStep >= FRONT_STEP)
            {
                if(System.currentTimeMillis() - time >= PAUSE_DURATION)
                {
                    nextState("Finished");
                     this.notifyObservers("HeavyAttackFinished");
                    Main.gameData.allies.remove(hitBox);
                }
            }else if(isFrontStep)
            {
                frontStep += FRONT_STEP_RATE;
                gameFigure.x += FRONT_STEP_RATE * direction;
                createHitBox();
                if(frontStep >= FRONT_STEP)
                    time = System.currentTimeMillis();
                
       
            }else if(BACK_STEP > backStep)
            {
                backStep += BACK_STEP_RATE;
                gameFigure.x += BACK_STEP_RATE * Math.negateExact(direction);
                if(BACK_STEP > backStep)
                    time = System.currentTimeMillis();
            }else if(!isFrontStep)
            {
                if(System.currentTimeMillis() - time >= CHARGE_DURATION){
                    isFrontStep = true;
                    this.notifyObservers("FrontStep");
                }
            } 
    }
    
    public void createHitBox(){
       
        Main.gameData.allies.remove(hitBox);
        if(direction > 0)
            hitBox = new HitBox(gameFigure.x + gameFigure.size/2, gameFigure.y + gameFigure.size/2, ATTACK_WIDTH, ATTACK_HEIGHT);
        else 
            hitBox = new HitBox(gameFigure.x + gameFigure.size/2 - ATTACK_WIDTH, gameFigure.y + gameFigure.size/2, ATTACK_WIDTH, ATTACK_HEIGHT);
        Main.gameData.allies.add(hitBox);
    }
 
    public boolean getFrontStep(){
        return isFrontStep;
    }
    
    @Override
    public void nextState(String s) { 
        
        if(s.equals("Finished"))
            gameFigure.cState = new NeutralCombat(gameFigure, observers);
        
    } 
}
