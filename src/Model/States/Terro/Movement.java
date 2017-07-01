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
        a = ter.getDirection(); //false for left true for right
        
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
                if (gameFigure.x + 50 >= GamePanel.PWIDTH - 150 && Math.abs(n.x - gameFigure.x + gameFigure.size) <= 50){
                //System.out.println("Terro is heading Right");
                    ter.setDirection(false);
                    nextState("Evade");
                }
                else if(gameFigure.x + 10 >= GamePanel.PWIDTH - 150){//point of inflection
                    //System.out.println("Terro is heading Right");
                    gameFigure.x -= 10;
                    ter.setDirection(false);
                }
                else if(Math.abs(n.x - gameFigure.x) <= 50){
                    gameFigure.x -= 10;
                    ter.setDirection(false);
                }
                else{
                    //System.out.println("Terro is heading Right");
                    //System.out.println("Math.abs(n.x - gameFigure.x) <= 100 : " + Math.abs(n.x - 50 - gameFigure.x));
                    gameFigure.x += 10;
                }
            }
            else{
            //terro should be moving to the left
                if (gameFigure.x - 50 <= 150 && Math.abs(n.x - n.size - gameFigure.x + 50) <= 50){
                    ter.setDirection(true);
                    nextState("Evade");
                    //System.out.println("Terro is heading Left");
                }
                else if(gameFigure.x - 10 <= 20){
                    gameFigure.x += 10;
                    ter.setDirection(true);
                    //System.out.println("Terro is heading Left");
                }
                else if(Math.abs(n.x - gameFigure.x + gameFigure.size) <= 50){ //point of inflection
                    gameFigure.x += 10;
                    ter.setDirection(true);
                }
                else{
                    //System.out.println("Terro is heading Left");
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
