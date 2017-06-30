/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventHandling;

import Model.GameFigure;
import Model.Nen;
import Level.Platform;
import Level.Trap;
import Physics.Acceleration;
import Physics.Force;

/**
 *
 * @author abilb
 */
public class PhysicsHandler implements CollisionObserver {

      Force antigravity = new Force(9, new Acceleration(0, -.49));
    
    @Override
    public void onNotify(GameFigure gameFigureOne, GameFigure gameFigureTwo) {
         System.out.println("We are in the PhysicsHandler");
        //player effected functionality
        if(gameFigureOne instanceof Nen || gameFigureTwo instanceof Nen)
        {
            
            if(gameFigureOne instanceof Trap || gameFigureTwo instanceof Trap)
            {
                //Game Logic Here
            }
            
            if(gameFigureOne instanceof Platform || gameFigureTwo instanceof Platform)
            {
                
                if(gameFigureTwo instanceof Nen)
                {
                    
                     
                   
                     
                     if(!((Nen) gameFigureTwo).forces.contains(antigravity))
                     {
                         ((Nen) gameFigureTwo).forces.add(antigravity);
                          System.out.println("Adding antigravity");
                     
                     }
                        
                     
                }
                { 
                    
                }      
            }
        }
        
        
        
        
    }

    @Override
    public void onNotify(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
