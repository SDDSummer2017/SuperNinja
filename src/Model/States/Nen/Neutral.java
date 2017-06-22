/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States.Nen;

import EventHandling.Observer;
import Model.GameFigure; 
import Model.States.State;
import java.util.ArrayList;

/**
 *
 * @author Garrett A. Clement
 */
public class Neutral extends State {

    public Neutral(GameFigure gameFigure, ArrayList<Observer> observers) {
        super(gameFigure, observers) ;
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
