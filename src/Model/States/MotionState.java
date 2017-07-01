/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States;

import EventHandling.Observer;
import Model.GameFigure; 
import java.util.ArrayList;
/**
 *
 * @author Garrett A. Clement
 */
public abstract class MotionState extends State  {
    protected MotionState previousState;
    protected CombatState combatState;
    protected long time = System.currentTimeMillis(); 
    
    public MotionState(GameFigure gameFigure, ArrayList<Observer> observers) {
        super(gameFigure, observers);
        previousState = gameFigure.mState;
        combatState = gameFigure.cState;
    }
    
   
}
