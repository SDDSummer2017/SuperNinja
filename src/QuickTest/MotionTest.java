/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickTest;

import java.awt.MouseInfo;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Garrett A. Clement
 */

public class MotionTest extends Thread {
    private static Recording recording;
    public void MotionTest(Recording recording){
        this.recording = recording;
    }
       @Override
       public void run() {
           try {
          //     recording.addAction(MouseInfo.getPointerInfo().getLocation());
               this.wait(200);
           } catch (InterruptedException ex) {
               Logger.getLogger(MotionTest.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
}
