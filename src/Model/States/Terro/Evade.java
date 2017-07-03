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
public class Evade extends MotionState{
    /* goal Have Terro jump onto the edge of the screen as though it were a wall and jump again off the imaginary edge
       so that theoretically his jump will be higher than Nen in a single jump terro should land 120 pixels away from the edge
    */
    private boolean onWall;
    private boolean left;
    private double q;
    private int a;
    private int dy = 20;
    private int delayCount;
    
    
    public Evade(GameFigure gameFigure, ArrayList<Observer> observers) {
        super(gameFigure, observers);
        previousState = gameFigure.mState;
        combatState = gameFigure.cState;
        onWall = false;
        left = false;        
        a = 0;
        gameFigure.airborn = true;
    }

    @Override
    public void execute() {
        Terro ter = (Terro) this.gameFigure;
        ter.image = ter.block;
        ter.setImage(ter.image);
        Nen n = Main.gameData.nen;
        delayCount = ter.getDelayCount();
        //put Terro on the wall
        a = ter.getCount();
        if(!onWall){
            if(a > 0){
                if(a < 6){
                    this.gameFigure.y -= dy;
                    a++;
                    ter.setCount(a);
                }
                else{
                    a = 0;
                    ter.setCount(a);
                    onWall = true;
                }
            }
            else{
                if(gameFigure.x + 50 >= n.x + 400){
                    q = Math.abs(n.x + 500 - gameFigure.x);
                    //System.out.println("Distance from terro.x to gamePanel width " + q);
                    this.gameFigure.x += q;
                    this.gameFigure.y -= 5 * dy;
                    left = false;
                    a++;
                    ter.setCount(a);
                }
                else{
                    // gameFigure.x should be within 10 pixels of 0
                    left = true;
                    q = Math.abs(gameFigure.x - (n.x - 500));
                    this.gameFigure.x -= q;
                    this.gameFigure.y -= 5 * dy;
                    a++;
                    ter.setCount(a);
                }
            }
        }
        else{
            a = ter.getCount();
            if(!left){
                //means Terro is on the right side of the panel
                if(a >= 12){
                    //System.out.println("Evade 1");
                    gameFigure.airborn = true;
                    a = 0;
                    ter.setCount(a);
                    nextState("Neutral");
                }
                else{
                    //System.out.println("Evade 2");
                    this.gameFigure.y -= dy;
                    this.gameFigure.x -= 35;
                    a++;
                    ter.setCount(a);
                }
            }
            else{
                //means that Terro is on the left side of the panel
                if(a >= 12){
                    //System.out.println("Neutral called");
                    a = 0;
                    ter.setCount(a);
                    gameFigure.airborn = true;
                    nextState("Neutral");
                    
                }
                else{
                    //System.out.println("a = " + a);
                    this.gameFigure.x += 35;
                    this.gameFigure.y -= dy;
                    a++;
                    ter.setCount(a);
                }
            }
        }
    }

    @Override
    public void nextState(String s) {
        switch (s) {
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
