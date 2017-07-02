package Controller;

import Model.Dummy; 
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseController implements MouseListener {

    private double targetX, targetY, originX, originY;


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
  
        Main.gameData.enemies.add(new Dummy(me.getX(), me.getY(), 20));
//        targetX = me.getX();
//        targetY = me.getY();
//        originX = Main.gameData.nen.x + Main.gameData.nen.size/2;
//        originY = Main.gameData.nen.y + Main.gameData.nen.size/2;
//        Main.gameData.addNenBullet(originX, originY, targetX, targetY, Color.yellow);
 
    }

}
