/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States.Rai;

import Controller.Main;
import Controller.TimerListener;
import EventHandling.Observer;
import Model.GameFigure;
import Model.HitBox;
import Model.Nen;
import Model.Rai;
import Model.States.CombatState;
import StatusEffects.DamageEffect;
import java.util.ArrayList;

/**
 *
 * @author matlock
 */
public class SteelTwister extends CombatState{

    private static final int DURATION = 500;
    private static final int DAMAGE = 10;
    public TimerListener t;
    public int displacement;
    public boolean flip;
    
    public SteelTwister(GameFigure gameFigure, ArrayList<Observer> observers) {
        super(gameFigure, observers);
        motionState = gameFigure.mState;
        previousState = gameFigure.cState;
        hitBox =  new HitBox(gameFigure.x + (gameFigure.size/2), (gameFigure.y + gameFigure.size / 2), 30, 10, gameFigure, new DamageEffect(gameFigure, DAMAGE ,5000));
        Main.gameData.addGameData(hitBox);
        flip = false;
    }

    @Override
    public void execute() {
        Rai rai = (Rai) this.gameFigure;
        rai.image = rai.attack2;
        rai.setImage(rai.image);
        Nen n = Main.gameData.nen;
        if (flip){
            flip = !flip;
            hitBox.translate(gameFigure.x + (gameFigure.size), gameFigure.y + (gameFigure.size / 2));
        }
        else{
            flip = !flip;
            hitBox.translate(gameFigure.x - gameFigure.size / 4, gameFigure.y + (gameFigure.size / 2));
        }
        
        if((System.currentTimeMillis()  - initTime >= DURATION) && Math.abs(n.x - gameFigure.x) <= 50){
            nextState("Default");
        }
    }

    @Override
    public void nextState(String s) {
        if(s.equals("Default")){
            Main.gameData.removeGameData(hitBox);
            gameFigure.cState = new Default(this.gameFigure, observers);
        }
    }
    
}
