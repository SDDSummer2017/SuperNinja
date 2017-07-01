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
        boolean a;
        a= ter.getDirection(); //false for left true for right
        
        //add extra condition where the movement continues until it reaches the edge of the displayed window and 100pixels distance from Terro
        if (ter.airborn){}
        else{
            if(a){
                //Terro Moves to the Right
                /*if (gameFigure.x + 10 >= 850 && Math.abs(n.x - gameFigure.x) < 100){
                System.out.println("Take 1");
                nextState("Evade");
                ter.setDirection(false);
                }*/
                if (gameFigure.x + 10 >= GamePanel.PWIDTH - 150 && Math.abs(n.x - gameFigure.x) < 100){
                //System.out.println("Take 1");
                    nextState("Evade");
                    ter.setDirection(false);
                }
                else if(gameFigure.x + 10 >= GamePanel.PWIDTH - 150 || Math.abs(n.x - gameFigure.x) < 100){
                    gameFigure.x -= 10;
                    ter.setDirection(false);
                }
                else{
                    //System.out.println("Take 3");
                    gameFigure.x += 10;
                }
            }
            else{
            //terro should be moving to the left
            /*System.out.println("Direction = " + a);
            System.out.println("Terro.x = " + gameFigure.x);
            System.out.println("GamePanel.pWidth = " + GamePanel.PWIDTH);
            System.out.println("Math.abs(n.x - gameFigure.x) = " + Math.abs(n.x - gameFigure.x));*/
                if (gameFigure.x - 10 <= 50 && Math.abs(n.x - gameFigure.x) < 100){
                    //System.out.println("Take 4");
                    nextState("Evade");
                    ter.setDirection(true);
                }
                else if(gameFigure.x - 10 <= 50){
                    //System.out.println("Take 5");
                    gameFigure.x += 10;
                    ter.setDirection(true);
                }
                else if(Math.abs(n.x - gameFigure.x) < 100){
                    gameFigure.x += 10;
                    ter.setDirection(true);
                }
                else{
                    //System.out.println("Take 6");
                    gameFigure.x -= 10;
                }
            }
        }
            /*if (gameFigure.y >= 450){
            this.nextState("Neutral");
            }*/
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
