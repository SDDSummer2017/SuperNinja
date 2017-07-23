/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States.Rai;

import EventHandling.Observer;
import Model.GameFigure;
import Model.States.CombatState;
import java.util.ArrayList;

/**
 *
 * @author matlock
 */
public class Death extends CombatState{

    public Death(GameFigure gameFigure, ArrayList<Observer> observers) {
        super(gameFigure, observers);
    }

    @Override
    public void execute() {}

    @Override
    public void nextState(String s) {}
    
}
