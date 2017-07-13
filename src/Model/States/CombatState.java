/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States;

import EventHandling.Observer;
import Model.GameFigure;  
import Model.HitBox;
import java.util.ArrayList;

/**
 *
 * @author Garrett A. Clement
 */

public abstract class CombatState extends State {
    protected long initTime;
    protected long comboWindow;
    protected CombatState previousState;
    protected MotionState motionState;
    protected HitBox hitBox;
    protected int direction;
    
    public CombatState(GameFigure gameFigure, ArrayList<Observer> observers) {
        super(gameFigure,observers);
        initTime = System.currentTimeMillis();
        previousState = gameFigure.cState;
        motionState = gameFigure.mState;
        
        if(gameFigure.isFacingRight)
            direction = 1;
        else
            direction = -1;
        
    }
}
