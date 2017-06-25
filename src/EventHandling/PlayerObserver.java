/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventHandling;

import Model.Nen;

/**
 *
 * @author abilb
 */

//This class is used to observer the player class. Don't use this to observer the state or just state changes.

public interface PlayerObserver extends Observer {
    
    
    public void onNotify(Nen nen);
}
