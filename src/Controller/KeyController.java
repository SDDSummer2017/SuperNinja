package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import Model.Marine;


public class KeyController implements KeyListener {
    Marine marine = Main.gameData.marine;
    @Override
    public void keyPressed(KeyEvent e) {   
        switch (e.getKeyCode()){
            case  KeyEvent.VK_LEFT:
                marine.movingLeft = true;
                break;
            case KeyEvent.VK_RIGHT:
                marine.movingRight = true;
                break;
            case KeyEvent.VK_UP:
                marine.jump = true;
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
                marine.movingLeft = false;
                break;
            case KeyEvent.VK_RIGHT:
                marine.movingRight = false;
                break;        
        }
    }
}
