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
import Model.States.Nen.Jump;
import Physics.Acceleration;
import Physics.Force;

/**
 *
 * @author abilb
 */
public class PhysicsHandler implements CollisionObserver {

    private Force antigravity = new Force(9, new Acceleration(0, -.49));

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
            
            if(gameFigureOne instanceof Platform)
            {
                
                if(gameFigureTwo instanceof Nen)
                {
                        
                    ((Nen) gameFigureTwo).velocity.dy = 0;
                   
                   
                     
                     
                     
                     if( ((Nen) gameFigureTwo).y  < ((Platform) gameFigureOne).y -64)
                        {
                                 
                        
                    
//                         
//                       
                         if(!((Nen) gameFigureTwo).forces.contains(antigravity) )
                         {
                             ((Nen) gameFigureTwo).forces.add(antigravity);
                         }
                         
                          System.out.println("Adding antigravity");
                            if(  ((Nen) gameFigureTwo).mState instanceof Jump && gameFigureTwo.airborn)
                                {     
                               gameFigureTwo.airborn = false;
                               
                               
                              
                                }
                        
                        
                        }
                     else
                     {
                         if(  gameFigureTwo.x < gameFigureOne.x)
                        {
                                 
                        
                         ((Nen) gameFigureTwo).x+= -35; 
                         
                         //           ((Nen) gameFigureTwo).forces.add(new Force(9, new Acceleration(-.49, 0 )));
                   
                              
                        
                        }
                        else if(gameFigureTwo.x < gameFigureOne.x + 128)
                            {
                    
                                   //((Nen) gameFigureTwo).forces.add(new Force(9, new Acceleration(0.49, 0 )));
                                   ((Nen) gameFigureTwo).x += 35;
                           
                            } 
                     
                     }
                     
                         //Landing Force Calculation Here
                         
                         
                        
                     
                      
                        
                     
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
