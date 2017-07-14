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
import Model.ProjectileSmokeBomb;
import Model.States.CombatState;
import static java.awt.Color.BLUE;
import java.util.ArrayList;

/**
 *
 * @author matlock
 */
public class SmokeBomb extends CombatState{

    public SmokeBomb(GameFigure gameFigure, ArrayList<Observer> observers) {
        super(gameFigure, observers);
        motionState = gameFigure.mState;
        previousState = gameFigure.cState;
    }

    @Override
    public void execute() {
        Kisara kis = (Kisara) this.gameFigure;
        Nen n = Main.gameData.nen;
        kis.image = kis.throwImage;
        kis.setImage(kis.image);
        System.out.println("SmokeBomb");
        if (n.x > gameFigure.x){
            //kisara is to the left of nen
            Main.gameData.addGameData(new ProjectileSmokeBomb(kis.x, kis.y, kis.x + 10, kis.y + 90, BLUE, false, 10, 3));
            gameFigure.x -= 2 * Math.abs(n.x - gameFigure.x);
            nextState("Default");
        }
        else{
            Main.gameData.addGameData(new ProjectileSmokeBomb(kis.x, kis.y, kis.x - 10, kis.y + 90, BLUE, false, 10, 3));
            gameFigure.x += 2 * Math.abs(n.x - gameFigure.x);
            nextState("Default");
        }
    }

    @Override
    public void nextState(String s) {
        switch (s) {
            case "Default":
                gameFigure.cState = new Default(this.gameFigure, observers);
                gameFigure.mState = new Neutral(this.gameFigure, observers);
                break;
            case "Hit":
                gameFigure.cState = new Hit(this.gameFigure, observers);
                break;
            default:
                break;
        }
    }
    
}
