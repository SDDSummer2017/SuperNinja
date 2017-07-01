/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States.Terro;

import Controller.Main;
import EventHandling.Observer;
import Model.GameFigure;
import Model.Nen;
import Model.States.MotionState;
import Model.Terro;
import View.GamePanel;
import java.util.ArrayList;

/**
 *
 * @author matlock
 */
public class Movement extends MotionState{

    public Movement(GameFigure gameFigure, ArrayList<Observer> observers) {
        super(gameFigure, observers);
        previousState = gameFigure.mState;
        combatState = gameFigure.cState;
    }

    @Override
    public void execute() {
        Nen n = Main.gameData.nen;
        Terro ter = (Terro) this.gameFigure;
        ter.image = ter.movement;
        ter.setImage(ter.image);
        boolean a = ter.getDirection(); //false for left true for right
        
        //add extra condition where the movement continues until it reaches the edge of the displayed window and 100pixels distance from Terro
        if(a){
            //Terro Moves to the Right
            if (gameFigure.x + 10 >= GamePanel.PWIDTH && Math.abs(n.x - gameFigure.x) < 100){
                nextState("Evade");
                ter.setDirection(false);
            }
            else if(gameFigure.x + 10 >= GamePanel.PWIDTH){
                gameFigure.x -= 10;
                ter.setDirection(false);
            }
            else{
                gameFigure.x += 10;
            }
        }
        else{
        //terro should be moving to the left
            if (gameFigure.x - 10 <= 0 && Math.abs(n.x - gameFigure.x) < 100){
                nextState("Evade");
                ter.setDirection(true);
            }
            else if(gameFigure.x - 10 >= 0){
                gameFigure.x += 10;
                ter.setDirection(true);
            }
            else{
                gameFigure.x -= 10;
            }
        }

        if(gameFigure.cState instanceof ShurikenThrow || gameFigure.cState instanceof Hit || gameFigure.cState instanceof WindmillShuriken){
            if (gameFigure.y >= 450){
                this.nextState("Neutral");
            }
            else{}
        }
    }

    @Override
    public void nextState(String s) {
        switch (s) {
            case "Hit":
                gameFigure.cState = new Hit(this.gameFigure, observers);
                break;
            case "Evade":
                gameFigure.mState = new Evade(this.gameFigure, observers);
                break;
            case "Neutral":
                gameFigure.mState = new Neutral(this.gameFigure, observers);
                break;
            default:
                break;
        }
    }
    
}
