/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventHandling;

import Model.GameFigure;

/**
 *
 * @author abilb
 */
public interface CollisionObserver {
    //Check test
    public void onNotify(GameFigure gameFigureOne, GameFigure gameFigureTwo); 
}
