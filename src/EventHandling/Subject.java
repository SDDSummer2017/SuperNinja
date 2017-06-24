/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventHandling;

/**
 *
 * @author abilb
 */
public interface Subject {
    
    public void registerObserver(Observer observer);
    public void deregisterObserver(Observer observer);
    public void notifyObservers();
    public void notifyObservers(String event);
}
