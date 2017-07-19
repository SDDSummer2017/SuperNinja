package Controller;

import Model.Nen;
import Model.States.Nen.ThrowingMode;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.SwingUtilities;

public class MouseController implements MouseListener {

    private double targetX, targetY, originX, originY; 
    private Nen nen = Main.gameData.nen;

    @Override
    public void mouseClicked(MouseEvent me) {   
    }
    
    @Override
    public void mouseReleased(MouseEvent me) {
    }

 
    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
  
////        Main.gameData.enemies.add(new Dummy(me.getX(), me.getY(), 20));
//<<<<<<< HEAD
    if(SwingUtilities.isLeftMouseButton(me) && nen.cState instanceof ThrowingMode )
       ((ThrowingMode)nen.cState).throwShuriken(me.getX(), me.getY());
    else if(SwingUtilities.isRightMouseButton(me))
       nen.cState.nextState("ThrowingMode"); 
//=======
//        targetX = me.getX();
//        targetY = me.getY();
//        originX = Main.gameData.nen.x + Main.gameData.nen.size/2;
//        originY = Main.gameData.nen.y + Main.gameData.nen.size/2;
//        Main.gameData.addGameData(new Shuriken(originX, originY, targetX, targetY, Color.yellow, true, 10, 3)); 
//>>>>>>> refs/remotes/origin/Matlock_Sprint4
 
    }

}
