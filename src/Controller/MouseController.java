package Controller;

import java.awt.Color;
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
//        targetX = me.getX();
//        targetY = me.getY();
//        originX = Main.gameData.marine.x + Main.gameData.marine.size/2;
//        originY = Main.gameData.marine.y + Main.gameData.marine.size/2;
//        Main.gameData.addMarineBullet(originX, originY, targetX, targetY, Color.yellow);
    }

}
