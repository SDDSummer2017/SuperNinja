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
        if(SwingUtilities.isLeftMouseButton(me) && nen.cState instanceof ThrowingMode )
           ((ThrowingMode)nen.cState).throwShuriken(me.getX(), me.getY());
        else if(SwingUtilities.isRightMouseButton(me))
           nen.cState.nextState("ThrowingMode");
    }

}
