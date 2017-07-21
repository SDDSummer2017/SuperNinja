/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States.Kisara;

import Controller.Main;
import Controller.TimerListener;
import EventHandling.Observer;
import Model.GameFigure;
import Model.HitBox;
import Model.Kisara;
import Model.Nen;
import Model.States.CombatState;
import StatusEffects.DamageEffect;
import java.util.ArrayList;

/**
 *
 * @author matlock
 */
public class ShadowStrike extends CombatState{

    public int a;
    private static final int DURATION = 1000;
    private static final int OUT = 225;
    private static final int IN = 450;
    private static final int DAMAGE = 10;
    private static final int DISPLACEMENTDISTANCE = 10;
    private int displacement;
    
    public ShadowStrike(GameFigure gameFigure, ArrayList<Observer> observers) {
        super(gameFigure, observers);
        motionState = gameFigure.mState;
        previousState = gameFigure.cState;
        a = 0;
        hitBox =  new HitBox(gameFigure.x + (gameFigure.size/2), (gameFigure.y + gameFigure.size / 2), 30, 10, gameFigure, new DamageEffect(gameFigure, DAMAGE ,5000));
        Main.gameData.addGameData(hitBox);
    }

    @Override
    public void execute() {
        Kisara kis = (Kisara) this.gameFigure;
        Nen n = Main.gameData.nen;
        kis.image = kis.attack1;
        kis.setImage(kis.image);
        if (System.currentTimeMillis() - initTime >= DURATION){
            if(Math.abs(n.x - gameFigure.x) < 100){
                nextState("VanishingStrike");
            }
            else{
                nextState("Default");
            }
        }
        else{
            if (n.x > gameFigure.x){
            direction = 1;
            }
            else{
                direction = 0;
            }
            if(direction > 0){
                gameFigure.x += 8;
                if(System.currentTimeMillis() - initTime  >= OUT && System.currentTimeMillis() - initTime <= IN)
                {
                    displacement += DISPLACEMENTDISTANCE;
                    hitBox.translate(gameFigure.x + (gameFigure.size/2) + displacement, gameFigure.y + (gameFigure.size / 2));  
                }
                else if(System.currentTimeMillis() - initTime > IN){ 
                    displacement -= DISPLACEMENTDISTANCE;
                    hitBox.translate(gameFigure.x + (gameFigure.size/2) + displacement, gameFigure.y + (gameFigure.size / 2));
                }
                else{
                    hitBox.translate(gameFigure.x + (gameFigure.size/2), gameFigure.y + (gameFigure.size / 2));
                }
            }
            else{
                gameFigure.x -= 8;
                if(System.currentTimeMillis() - initTime  >= OUT && System.currentTimeMillis() - initTime <= IN){
                    displacement += DISPLACEMENTDISTANCE;
                    hitBox.translate(gameFigure.x - displacement, gameFigure.y + gameFigure.size / 2);  
                } 
                else if(System.currentTimeMillis()  - initTime > IN){ 
                    displacement -= DISPLACEMENTDISTANCE;
                    hitBox.translate(gameFigure.x + displacement, gameFigure.y + (gameFigure.size / 2));
                }
                else{
                    hitBox.translate(gameFigure.x, gameFigure.y + (gameFigure.size / 2));
                }
            }
        }
    }

    @Override
    public void nextState(String s) {
        switch (s) {
            case "VanishingStrike":
                Main.gameData.removeGameData(hitBox);
                gameFigure.mState = new Neutral(this.gameFigure, observers);
                gameFigure.cState = new VanishingStrike(this.gameFigure, observers);
                break;
            case "Default":
                Main.gameData.removeGameData(hitBox);
                gameFigure.cState = new Default(this.gameFigure, observers);
                break;
            case "Hit":
                Main.gameData.removeGameData(hitBox);
                gameFigure.cState = new Hit(this.gameFigure, observers);
                break;
            default:
                break;
        }
    }
    
}
