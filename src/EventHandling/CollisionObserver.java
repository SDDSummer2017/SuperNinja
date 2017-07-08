/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventHandling;

import Model.Collision;
import Model.GameFigure;

/**
 *
 * @author abilb
 */

public interface CollisionObserver extends Observer {

    public void onNotify(GameFigure gameFigureOne, GameFigure gameFigureTwo); 
    
    public void onNotify(Collision object1, Collision object2);
}
