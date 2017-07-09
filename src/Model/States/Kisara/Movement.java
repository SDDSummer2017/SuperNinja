/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States.Kisara;

import Controller.Main;
import EventHandling.Observer;
import Model.GameFigure;
import Model.Kisara;
import Model.Nen;
import Model.States.MotionState;
import java.util.ArrayList;

/**
 *
 * @author matlock
 */
public class Movement extends MotionState{

    boolean moveAway;
    
    public Movement(GameFigure gameFigure, ArrayList<Observer> observers) {
        super(gameFigure, observers);
        previousState = gameFigure.mState;
        combatState = gameFigure.cState;
        moveAway = true;
    }

    @Override
    public void execute() {
        Kisara kis = (Kisara) this.gameFigure;
        Nen n = Main.gameData.nen;
        kis.image = kis.movement;
        kis.setImage(kis.image);
        if (combatState instanceof Default){
            if (Math.abs(n.x - gameFigure.x) <= 100){
                nextState("Evade");
            }
            else if(Math.abs(n.x - gameFigure.x) >= 150){
                if (n.x > gameFigure.x){
                    kis.isFacingRight = true;
                    if (!(n.isFacingRight) && Math.abs(n.x - gameFigure.x) <= 300 && moveAway){
                        gameFigure.x -= 8;
                        if (Math.abs(n.x - gameFigure.x) >= 250){
                            moveAway = false;
                        }
                    }
                    else{
                        gameFigure.x += 8;
                        if(Math.abs(n.x - gameFigure.x) <= 170){
                            moveAway = true;
                        }
                    }
                }
                else{
                    kis.isFacingRight = false;
                    if (n.isFacingRight && Math.abs(n.x - gameFigure.x) <= 300 && moveAway){
                        gameFigure.x += 8;
                        if (Math.abs(n.x - gameFigure.x) >= 250){
                            moveAway = false;
                        }
                    }
                    else{
                        gameFigure.x -= 8;
                        if(Math.abs(n.x - gameFigure.x) <= 170){
                            moveAway = true;
                        }
                    }
                }
            }
        }
        else{
            nextState("Neutral");
        } 
    }

    @Override
    public void nextState(String s) {
        switch (s) {
            case "Evade":
                gameFigure.mState = new Evade(this.gameFigure, observers);
                break;
            case "Hit":
                gameFigure.cState = new Hit(this.gameFigure, observers);
                break;
            case "Neutral":
                gameFigure.mState = new Neutral(this.gameFigure, observers);
                break;
            default:
                break;
        }
    }
    
}
