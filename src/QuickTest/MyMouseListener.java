/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickTest;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent; 

/**
 *
 * @author Garrett A. Clement
 */

public class MyMouseListener extends MouseAdapter {

    
    
    private Recording recording;
    public MyMouseListener(Recording recording){
        this.recording = recording;
    }
    
    @Override
    public void mouseClicked(MouseEvent e)
    {
       System.out.println("YOOOOOOOOOOO");
        recording.addAction(System.currentTimeMillis(), e.getButton());
    }
    
    @Override
    public void mouseDragged(MouseEvent e) { 
    }

    @Override
    public void mouseMoved(MouseEvent e) { 
    }

    
}

//public class MyMouseListener implements MouseMotionListener {
//
//    
//    
//    private Recording recording;
//    public MyMouseListener(Recording recording){
//        this.recording = recording;
//    }
//    
//    @Override
//    public void mouseDragged(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void mouseMoved(MouseEvent e) {
//        System.out.println("X: " + e.getPoint().getX() + ", Y:" + e.getPoint().getY());
//       // recording.addAction(e.getPoint());
//    }
//
//    
//}
