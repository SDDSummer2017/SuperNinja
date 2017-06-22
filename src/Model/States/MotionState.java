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
public abstract class MotionState extends State {
    protected MotionState previousState;
    protected CombatState combatState;
    
    public MotionState(GameFigure gameFigure) {
        super(gameFigure);
    }
}
