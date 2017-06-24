/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States;

import Model.GameFigure;  

/**
 *
 * @author Garrett A. Clement
 */
public abstract class CombatState extends State {
    protected long initTime;
    protected long comboWindow;
    protected CombatState previousState;
    protected MotionState motionState;
    
    public CombatState(GameFigure gameFigure) {
        super(gameFigure);
        initTime = System.currentTimeMillis();
        previousState = gameFigure.cState;
        motionState = gameFigure.mState;
    }
}
