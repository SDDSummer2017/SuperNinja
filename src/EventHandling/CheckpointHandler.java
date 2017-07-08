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
public class CheckpointHandler implements Observer{

    @Override
    public void onNotify(String string) {
        if(string.equals("Checkpoint"))
        {
            System.out.println("The player has reached a checkpoint");
            //TODO: Write checkpoint to file or memory location.

        }
    }
    
}
