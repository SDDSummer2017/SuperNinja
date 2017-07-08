package Controller;

import Model.Dummy;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import Model.Nen;
import Model.States.Nen.Crouch;
import Model.States.Nen.Jump;
import Model.States.Nen.Move;
import java.util.Random;


public class KeyController implements KeyListener {
    //It worked
    Nen nen = Main.gameData.nen;
 
   
    @Override
    public void keyPressed(KeyEvent e) {   
        switch (e.getKeyCode()){
            case  KeyEvent.VK_LEFT:
                    nen.isFacingRight = false;
                
               if(nen.mState.getClass() != Move.class)
                {
                     nen.mState.nextState("Move");
                }
                
                break;
            case KeyEvent.VK_RIGHT:
                nen.isFacingRight = true;
               
                if(nen.mState.getClass() != Move.class)
                {
                     nen.mState.nextState("Move");
                }
                break;
            case KeyEvent.VK_UP:
                if(nen.mState.getClass() != Jump.class)
                {
                     nen.mState.nextState("Jump");
                }
                break;
            
            case KeyEvent.VK_DOWN:
                if(nen.mState.getClass() != Crouch.class)
                {
                     nen.mState.nextState("Crouch");
                }
                break;
            case KeyEvent.VK_S: 
                    Random rand = new Random();
                    int  n = rand.nextInt(500) + 1;
                    Main.gameData.addGameData(new Dummy(n, n, 25)); 
                
                break;
                
            case KeyEvent.VK_F:
                if(nen.cState.getClass() != Model.States.Nen.LightAttack.class)
                {
                     nen.cState.nextState("LightAttack");
                }
               
                break;
            case KeyEvent.VK_D:
                if(nen.cState.getClass() != Model.States.Nen.LightAttack.class)
                {
                     nen.cState.nextState("HeavyAttack");
                }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar() == 'f' || e.getKeyChar() == 'F')
                nen.cState.nextState("LightAttack");
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case  KeyEvent.VK_LEFT:
                nen.mState.nextState("NeutralMotion");
                break;
            case KeyEvent.VK_RIGHT:
                nen.mState.nextState("NeutralMotion");
                break;        
            case KeyEvent.VK_DOWN:
                nen.mState.nextState("NeutralMotion");
                break;
        }
    }
}
