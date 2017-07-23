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
        keyCommands.add(new CrouchCommand(KeyEvent.VK_DOWN));
    }
    @Override
    public void keyPressed(KeyEvent e) {   
        
            for(Command c : keyCommands) 
                if(e.getKeyCode() == c.getKeyCode())
                    c.execute(nen); 
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    

    @Override
    public void keyReleased(KeyEvent e) {
        for(Command c : keyCommands)
            if(e.getKeyCode() == c.getKeyCode() && c instanceof MoveLeftCommand 
               || e.getKeyCode() == c.keyCode && c instanceof MoveRightCommand
                || e.getKeyCode() == c.keyCode && c instanceof CrouchCommand )
                c.release(nen);
                
        
        switch (e.getKeyCode()){
 
            case KeyEvent.VK_ESCAPE:
                if(window == null)
                {
                    window = new KeyBindingWindow(this);
;                   placeWindow(window);
                }else
                {
                    window.setVisible(!window.isVisible()); 
                    placeWindow(window);
                }
        }
    } 
    
     public void rebindKeys(HashMap<Integer, Integer> newKeyCodes){
         for(Command c : keyCommands)  
             c.setKeyCode(newKeyCodes.get(c.keyCode));
    }
     
     private void placeWindow(KeyBindingWindow window){
                 window.setLocation(Main.mainWindow.size().width/2  - window.getSize().width/2,
       Main.mainWindow.size().height/2 - window.getSize().height/2);
//        window.setLocation(Main.gamePanel.size().width/2 - window.getSize().width/2,
//       Main.gamePanel.size().height/2 - window.getSize().height/2);
     }
}
