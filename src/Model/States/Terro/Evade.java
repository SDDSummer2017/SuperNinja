/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States.Terro;

import EventHandling.Observer;
import Model.GameFigure;
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
        ter.image = ter.throwImage;
        ter.setImage(ter.image);
        delayCount = ter.getDelayCount();
        //put Terro on the wall
        if(!onWall){
            a = ter.getCount();
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
                if(gameFigure.x + 10 >= GamePanel.PWIDTH){
                    q = Math.abs(GamePanel.PWIDTH - gameFigure.x); 
                    this.gameFigure.x += q;
                    this.gameFigure.y -= dy;
                    left = false;
                    a++;
                    ter.setCount(a);
                }
                else{
                    // gameFigure.x should be within 10 pixels of 0
                    left = true;
                    q = gameFigure.x;
                    this.gameFigure.x -= q;
                    this.gameFigure.y -= dy;
                    a++;
                    ter.setCount(a);
                }
            }
        }
        else{
            a = ter.getCount();
            if(!left){
                //means Terro is on the right side of the panel
                if(a > 12){
                    nextState("Neutral");
                    a = 0;
                    ter.setCount(a);
                }
                else{
                    this.gameFigure.y -= dy;
                    this.gameFigure.x -= 10;
                    a++;
                    ter.setCount(a);
                }
            }
            else{
                //means that Terro is on the left side of the panel
                if(a > 12){
                    nextState("Neutral");
                    a = 0;
                    ter.setCount(a);
                }
                else{
                    this.gameFigure.x += 10;
                    this.gameFigure.y -= dy;
                    a = ter.c++;
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
