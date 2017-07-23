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
import Model.Collision;
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
        // System.out.println("We are in the PhysicsHandler");
        
        
        
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
                         
                          //System.out.println("Adding antigravity");
                            if(  ((Nen) gameFigureTwo).mState instanceof Jump && gameFigureTwo.airborn)
                                {     
                               gameFigureTwo.airborn = false;
                               
                               
                              
                                }
                        
                        
                        }
                     else
                     {
                         if(gameFigureTwo.x < gameFigureOne.x && withinBox(gameFigureTwo, gameFigureOne))
                        {
                                 ((Nen) gameFigureTwo).velocity.dx =0; 
                                
                                 gameFigureTwo.x = gameFigureOne.getCollisionBox().getX() - gameFigureTwo.getCollisionBox().width ;
                         
                         
                                //((Nen) gameFigureTwo).forces.add(new Force(9, new Acceleration(-.49, 0 )));
                              //((Nen) gameFigureTwo).x -= 35;
                           
                        
                        }
                        else if(gameFigureTwo.x < gameFigureOne.x + 128 && withinBox(gameFigureTwo, gameFigureOne))
                            {
                     
                                
                               //  gameFigureTwo.x = gameFigureOne.getCollisionBox().getX() + gameFigureTwo.getCollisionBox().width + ((Nen) gameFigureTwo).getCollisionBox().width ;
                                  // ((Nen) gameFigureTwo).forces.add(new Force(9, new Acceleration(0.49, 0 )));
                                  //((Nen) gameFigureTwo).x += 35;
                           
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
        
    }

    public boolean withinBox(GameFigure g1, GameFigure g2){
        if(!(g1 instanceof Nen) && !(g2 instanceof Nen ))
            return false;
        
        GameFigure tmp = g1;
        if(g2 instanceof Nen)
        {
            g1 = g2;
            g2 = g1;
        }
        
        if(g1.getCollisionBox().getY() <= g2.getCollisionBox().getHeight() + g2.getCollisionBox().getY() -15
                && g1.getCollisionBox().getY() + g1.getCollisionBox().getHeight() >= g2.getCollisionBox().getY())
            return true;
        else
            return false; 
    }
//    public boolean withinBox(GameFigure g1, GameFigure g2){
//        if(!(g1 instanceof Nen) && !(g2 instanceof Nen ))
//            return false;
//        
//        GameFigure tmp = g1;
//        if(g2 instanceof Nen)
//        {
//            g1 = g2;
//            g2 = g1;
//        }
//        
//        if(g1.getCollisionBox().getY() <= g2.getCollisionBox().getHeight() + g2.getCollisionBox().getY() -15
//                && g1.getCollisionBox().getY() >= g2.getCollisionBox().getY())
//            return true;
//        else
//            return false;
//        
//        
//        
//        
//    }
    
    @Override
    public void onNotify(Collision object1, Collision object2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
