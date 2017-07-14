/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States.Kisara;

import Controller.Main;
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
public class VanishingStrike extends CombatState{
    
    int a;
    private static final int DURATION = 1000;
    private static final int VANISH = 0;
    private static final int OUT = 300;
    private static final int IN = 700;
    private static final int DAMAGE = 10;
    private static final int DISPLACEMENTDISTANCE = 10;
    private int displacement;
    
    
    public VanishingStrike(GameFigure gameFigure, ArrayList<Observer> observers) {
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
        kis.image = null;
        kis.setImage(kis.image);
        
        if (a == 0){
            if (n.x > gameFigure.x){
                kis.direction = true;
            }
            else{
                kis.direction = false;
            }
            a++;
            kis.setCount(a);
        }
        if (System.currentTimeMillis() - initTime >= DURATION){
            nextState("Default");
        }
        else{
            if(kis.direction){
                gameFigure.x += 22;
                if(System.currentTimeMillis() - initTime >= VANISH && System.currentTimeMillis() - initTime <= OUT){
                    gameFigure.x -= 30;
                    kis.image = kis.throwImage;
                }
                if(System.currentTimeMillis() - initTime > OUT && System.currentTimeMillis() - initTime <= IN){
                    displacement += DISPLACEMENTDISTANCE;
                    hitBox.translate(gameFigure.x + (gameFigure.size/2), gameFigure.y + (gameFigure.size / 2));
                    kis.image = null;
                    kis.setImage(kis.image);
                }
                else if(System.currentTimeMillis() - initTime > IN){ 
                    displacement -= DISPLACEMENTDISTANCE;
                    hitBox.translate(gameFigure.x + (gameFigure.size/2), gameFigure.y + (gameFigure.size / 2));
                    kis.image = kis.attack2;
                    kis.setImage(kis.image);
                }
                else{
                    hitBox.translate(gameFigure.x + (gameFigure.size/2), gameFigure.y + (gameFigure.size / 2));
                }
            }
            else{
                gameFigure.x -= 22;
                if(System.currentTimeMillis() - initTime >= VANISH && System.currentTimeMillis() - initTime <= OUT){
                    gameFigure.x += 30;
                    kis.image = kis.throwImage;
                }
                if(System.currentTimeMillis() - initTime  > OUT && System.currentTimeMillis() - initTime <= IN){
                    displacement += DISPLACEMENTDISTANCE;
                    hitBox.translate(gameFigure.x - displacement, gameFigure.y + gameFigure.size / 2);
                    kis.image = null;
                    kis.setImage(kis.image);
                } 
                else if(System.currentTimeMillis()  - initTime > IN){ 
                    displacement -= DISPLACEMENTDISTANCE;
                    hitBox.translate(gameFigure.x + displacement, gameFigure.y + (gameFigure.size / 2));
                    kis.image = kis.attack2;
                    kis.setImage(kis.image);
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
            case "Default":
                Main.gameData.removeGameData(hitBox);
                gameFigure.mState = new Neutral(this.gameFigure, observers);
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
