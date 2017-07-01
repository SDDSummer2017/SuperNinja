/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States;

import EventHandling.Observer; 
import EventHandling.Subject;
import Model.GameFigure; 
import java.util.ArrayList;
import EventHandling.StateObserver;

/**
 *
 * @author Garrett A. Clement
 */
public abstract class State implements Subject {
    protected GameFigure gameFigure;
    protected ArrayList<Observer> observers; 
    
    public State(GameFigure gameFigure, ArrayList<Observer> observers)
    {
        this.gameFigure = gameFigure;
        this.observers  = observers; 
      
    }
    
     @Override 
    public void registerObserver(Observer observer)
    {
        this.observers.add(observer);
    }
    
    @Override 
    public void deregisterObserver(Observer observer)
    {
         this.observers.remove(observer);
    }
    
    @Override
    public void notifyObservers()
    {
        for(int i = 0; i < observers.size(); i++)
        {
            ((StateObserver)observers.get(i)).onNotify(this);
        }
    }
    
    @Override
    public void notifyObservers(String event)
    {
        for(int i = 0; i < observers.size(); i++)
        {
             observers.get(i).onNotify(event);
        }
        
    }
    
    public abstract void execute();
        
    public abstract void nextState(String s);
    
    public void interruptState(State s){}
    
}
