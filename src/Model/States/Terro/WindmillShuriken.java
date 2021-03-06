/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States.Terro;

import Controller.Main;
import EventHandling.Observer;
import Model.GameFigure;
import static Model.GameFigure.GRAVITY;
import Model.Nen;
import Model.ProjectileWindmillShuriken;
import Model.Shuriken;
import Model.States.CombatState;
import Model.Terro;
import static java.awt.Color.BLUE;
import static java.awt.Color.RED;
import java.util.ArrayList;

/**
 *
 * @author matlock
 */
public class WindmillShuriken extends CombatState{
    
    private int a;
    private double nenX, nenY;
    private Nen nen;
    
    
    public WindmillShuriken(GameFigure gameFigure, ArrayList<Observer> observers) {
        super(gameFigure, observers);
        motionState = gameFigure.mState;
        previousState = gameFigure.cState;
    }

    @Override
    public void execute() {
        Terro ter = (Terro) this.gameFigure;
        ter.image = ter.attack2;
        ter.setImage(ter.image);
    
        nen = Main.gameData.nen;
        nenX = nen.x;
        nenY = nen.y;
        
        
        gameFigure.y -= GRAVITY;
        a = ter.getCount();
        if(a >= 10){
            Main.gameData.addGameData(new ProjectileWindmillShuriken(ter.x, ter.y, nenX, nenY, BLUE, false, 30, 30));
            nextState("Default");
        }
        else{
            a++;
            ter.setCount(a);
        }
    }

    @Override
    public void nextState(String s) {
        switch (s) {
            case "Default":
                gameFigure.cState = new Default(this.gameFigure, observers);
                break;
            case "Neutral":
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
