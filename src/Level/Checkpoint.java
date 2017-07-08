/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Level;

import EventHandling.Observer;
import EventHandling.Subject;
import java.util.ArrayList;

/**
 *
 * @author abilb
 */
public class Checkpoint extends Platform implements Subject{

    protected ArrayList<Observer> observers; 
    public Checkpoint(double x, double y) {
        super(x, y);
        this.observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void deregisterObserver(Observer observer) {
        this.observers.remove(observer); 
    }

    @Override
    public void notifyObservers() {
        for(Observer o : observers)
        {
            o.onNotify("Checkpoint");
        }
    }

    @Override
    public void notifyObservers(String event) {
         for(Observer o : observers)
        {
            o.onNotify(event);
        } 
    }
    
}
