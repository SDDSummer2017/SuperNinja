/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickTest;
 
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.FlowLayout; 
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Garrett A. Clement
 */
public class QuickTest {
    private static Thread t;
    private static Recording recording;
    
    
    public static void main(String[] args){
        
        JFrame f = new JFrame();
        f.setSize(600, 600);
        JPanel jp = new JPanel();
        jp.setLayout(new FlowLayout());
        JButton b = new JButton("Record");
        JButton c = new JButton("Stop");
        JButton k = new JButton("Play");
        JButton test = new JButton("Test");
        
    
        Dimension d = new Dimension(50,50);
        
        b.setSize(d);
        c.setSize(d);
        k.setSize(d);
        test.setSize(d);
        
//        k.addActionListener(e ->{play();});
//        c.addActionListener(e ->{stopRecording();});
//        b.addActionListener(e -> {startRecording(jp);});
//        test.addActionListener(e ->{System.out.println("Testing!");});

        jp.add(b);
        jp.add(c);
        jp.add(k);
        jp.add(test);
        f.add(jp);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }
    
        public static void startRecording(JPanel j){
            Robot ro;
        try {

            recording = new Recording(j);
            recording.startRecording();
                        ro = new Robot();

//            ro.mouseMove(69, 162);
//            ro.mousePress(InputEvent.BUTTON1_MASK);
//            ro.mouseRelease(InputEvent.BUTTON1_MASK);
        } catch (AWTException ex) {
            Logger.getLogger(QuickTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        }

        public static void stopRecording(){
            recording.stopRecording();
        }
        
        
        public static void play(){
            recording.play();
        }
}

