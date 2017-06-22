/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States.Nen;

import Model.GameFigure; 
import Model.States.State;

/**
 *
 * @author Garrett A. Clement
 */
public class Neutral extends State {

    public Neutral(GameFigure gameFigure){
        super(gameFigure);
    }
    
    @Override
    public void execute() {
        System.out.println("I am in neutral");
    }
 
    @Override
    public void nextState(String keyInput) {
        
        
//                
//        if(keyInput.equals("Move")){
//        
//        }else if(keyInput.equals("Hit"))
//        {
//            
//        }else if(keyInput.equals("Atttack"))
//        { 
//        }
    }

}
