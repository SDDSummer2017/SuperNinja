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
import Model.Shuriken;
import Model.States.CombatState;
import Model.Terro;
import static java.awt.Color.RED;
import java.util.ArrayList;

/**
 *
 * @author matlock
 */
public class ShurikenThrow extends CombatState{

    private int a;
    private double nenX, nenY;
    private Nen nen;
    
    public ShurikenThrow(GameFigure gameFigure, ArrayList<Observer> observers) {
        super(gameFigure, observers);
        motionState = gameFigure.mState;
        previousState = gameFigure.cState;
        a = 0;
    }

    @Override
    public void execute() {
        Terro ter = (Terro) this.gameFigure;
        ter.image = ter.attack1;
        ter.setImage(ter.image);
        
        nen = Main.gameData.nen;
        nenX = nen.x;
        nenY = nen.y;
        
        
        //gameFigure.y -= GRAVITY;
        a = ter.getCount();
        if(a >= 10){
            Main.gameData.addGameData(new Shuriken(ter.x, ter.y, nenX, nenY, RED, false));
            Main.gameData.addGameData(new Shuriken(ter.x, ter.y, nenX + 50, nenY, RED, false));
            Main.gameData.addGameData(new Shuriken(ter.x, ter.y, nenX - 50, nenY, RED, false));
            /*Shuriken two = new Shuriken(ter.x, ter.y, nenX + 50, nenY, RED);
            Shuriken three = new Shuriken(ter.x, ter.y, nenX - 50, nenY, RED);*/
            a = 0;
            ter.setCount(a);
            nextState("Default");
        }
        else{
            gameFigure.y -= GameFigure.GRAVITY;
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
