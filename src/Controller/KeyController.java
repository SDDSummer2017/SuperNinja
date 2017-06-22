package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import Model.Nen;


public class KeyController implements KeyListener {
    Nen nen = Main.gameData.marine;
    @Override
    public void keyPressed(KeyEvent e) {   
        switch (e.getKeyCode()){
            case  KeyEvent.VK_LEFT:
                nen.isFacingRight = false;
                nen.mState.nextState("Move");
                break;
            case KeyEvent.VK_RIGHT:
                nen.isFacingRight = true;
                nen.mState.nextState("Move");
                break;
            case KeyEvent.VK_UP:
                nen.mState.nextState("Jump");
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
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
        }
    }
}
