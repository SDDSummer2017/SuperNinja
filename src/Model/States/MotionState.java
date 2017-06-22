/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States;

import Model.GameFigure;
import Model.States.State;

/**
 *
 * @author Garrett A. Clement
 */
public abstract class MotionState extends State {

    public MotionState(GameFigure gameFigure) {
        super(gameFigure);
    }
}