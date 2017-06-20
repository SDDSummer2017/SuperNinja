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
public interface StateObserver extends Observer {
    
    public void onNotify(State state); 
}
