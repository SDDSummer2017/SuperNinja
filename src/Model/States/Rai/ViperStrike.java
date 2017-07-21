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
public class ViperStrike extends CombatState {

    private static final int DURATION = 500;
    private static final int OUT = 225;
    private static final int IN = 450;
    private static final int DAMAGE = 10;
    private static final int DISPLACEMENTDISTANCE = 10;
    public TimerListener t;
    public int displacement;
    
    public ViperStrike(GameFigure gameFigure, ArrayList<Observer> observers) {
        super(gameFigure, observers);
        motionState = gameFigure.mState;
        previousState = gameFigure.cState;
        int a = 0;
        gameFigure.damage = DAMAGE;
        hitBox =  new HitBox(gameFigure.x + (gameFigure.size/2), (gameFigure.y + gameFigure.size / 2), 30, 10, gameFigure, new DamageEffect(gameFigure, DAMAGE ,1000));
        Main.gameData.addGameData(hitBox);
        direction = 0;
    }

    @Override
    public void execute() {
        // this state is rais' first attack in his sequence chain
         //changes the images of the gamefigure depending on which state we are currently in 
        //alters the movement state to minimize the chance of errors
        Rai rai = (Rai) this.gameFigure;
        rai.image = rai.attack1;
        rai.setImage(rai.image);
        Nen n = Main.gameData.nen;
        int a = rai.getCount();
        if (n.x > gameFigure.x){
            direction = 1;
        }
        else{
            direction = 0;
        }
        if(direction > 0)
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
        else{
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
        if((System.currentTimeMillis()  - initTime >= DURATION) && Math.abs(n.x - gameFigure.x) <= 50){
            a = 0;
            rai.setCount(a);
            nextState("SteelTwister");
            
        }
        else if(System.currentTimeMillis() - initTime >= DURATION){
            a = 0;
            rai.setCount(a);
            nextState("Default");
        }
        else{}
        gameFigure.mState = new Neutral(this.gameFigure, observers);
    }

    @Override
    public void nextState(String s) {
     //   System.out.println("Next input is " + s);
        if(s.equals("SteelTwister")){
            Main.gameData.removeGameData(hitBox);
            gameFigure.cState = new SteelTwister(this.gameFigure, observers);
        }
        else if(s.equals("Hit")){
            Main.gameData.removeGameData(hitBox);
            gameFigure.cState = new Hit(this.gameFigure, observers);
        }
        else if(s.equals("Default")){
            Main.gameData.removeGameData(hitBox);
            gameFigure.cState = new Default(this.gameFigure, observers);
        }
        /* attackBlocked will be implemented when nen Block state is implemented
        else if(s.equals("AttackBlocked") && previousState instanceof ViperStrike){
        gameFigure.cState = new AttackBlocked(gameFigure);
        }*/
        else{}
    }
    
}
