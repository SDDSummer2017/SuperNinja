package Controller; 
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import Model.Nen; 
import View.KeyBindingWindow;
import java.util.ArrayList;
import java.util.HashMap; 


public class KeyController implements KeyListener {
     
    Nen nen = Main.gameData.nen;
    KeyBindingWindow window;
    ArrayList<Command> keyCommands = new ArrayList<>(); 
    
    public KeyController(){ 
        keyCommands.add(new LightAttackCommand(KeyEvent.VK_F));
        keyCommands.add(new HeavyAttackCommand(KeyEvent.VK_D));
        keyCommands.add(new MoveLeftCommand(KeyEvent.VK_LEFT));
        keyCommands.add(new MoveRightCommand(KeyEvent.VK_RIGHT));
        keyCommands.add(new JumpCommand(KeyEvent.VK_UP));
        keyCommands.add(new WallJumpCommand(KeyEvent.VK_SPACE));
        
    }
    @Override
    public void keyPressed(KeyEvent e) {   
        
            for(Command c : keyCommands) 
                if(e.getKeyCode() == c.getKeyCode())
                    c.execute(nen); 
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar() == 'f' || e.getKeyChar() == 'F')
                nen.cState.nextState("LightAttack"); 
    }
    

    @Override
    public void keyReleased(KeyEvent e) {
        for(Command c : keyCommands)
            c.release(nen);
        
        switch (e.getKeyCode()){
 
            case KeyEvent.VK_ESCAPE:
                if(window == null)
                    window = new KeyBindingWindow(this);
                else
                    window.setVisible(!window.isVisible()); 
        }
    } 
    
     public void rebindKeys(HashMap<Integer, Integer> newKeyCodes){
         for(Command c : keyCommands)  
             c.setKeyCode(newKeyCodes.get(c.keyCode));
    }
}
