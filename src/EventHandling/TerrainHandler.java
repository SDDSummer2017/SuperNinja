/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventHandling;

import Model.GameFigure;
import Model.Nen;
import Model.Terrain.Platform;
import Model.Terrain.Trap;

/**
 *
 * @author abilb
 */
public class TerrainHandler implements CollisionObserver {

    @Override
    public void onNotify(GameFigure gameFigureOne, GameFigure gameFigureTwo) {
         
        //player effected functionality
        if(gameFigureOne instanceof Nen || gameFigureTwo instanceof Nen)
        {
            
            if(gameFigureOne instanceof Trap || gameFigureTwo instanceof Trap)
            {
                //Game Logic Here
            }
            
            if(gameFigureOne instanceof Platform || gameFigureTwo instanceof Platform)
            {
                if(gameFigureOne instanceof Nen)
                {
                    ((Nen) gameFigureOne).airborn = false; 
                }
                {
                    ((Nen) gameFigureTwo).airborn = false; 
                }      
            }
        }
        
        
        
        
    }

    @Override
    public void onNotify(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
