/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickTest;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author Garrett A. Clement
 */
public class Recording {
    
    private Map<Long, ArrayList<Object>> actions = new ConcurrentHashMap();
    Thread t;
    public Recording(JPanel j){
        
         MyMouseListener list = new MyMouseListener(this);
         j.addMouseListener(list);
//       j.addMouseMotionListener(list);
       //MotionTest t = new MotionTest();
       t = new Thread(new Runnable() {
           @Override
           public void run() {
               while(true)
               {
               // System.out.println(MouseInfo.getPointerInfo().getLocation().getX() + ", " + MouseInfo.getPointerInfo().getLocation().getY());
                addAction(System.currentTimeMillis(), MouseInfo.getPointerInfo().getLocation());
                   try {
                       Thread.sleep(100);
                   } catch (InterruptedException ex) {
                       Logger.getLogger(Recording.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
           }
       }); 
    }
    
    public void addAction(long time, Object ob){
       if(actions.get(time) == null) {
            ArrayList<Object> list = new ArrayList<>();
            list.add(MouseInfo.getPointerInfo().getLocation());
            actions.put(time, list);

       } else 
            actions.get(time).add(MouseInfo.getPointerInfo().getLocation());
    }
    
    public Object getAction(){
        System.out.println("Yo");
    return null;   
    // return actions.poll();
    }
    
    public void startRecording(){
        t.start();
    }
    
    public void stopRecording(){
        t.stop();
    }
    
    
    public void play(){
            try{   
                Robot robot = new Robot();   
                ArrayList<Long>  times = new ArrayList<>();
                for(Long l : actions.keySet())
                    times.add(l);
                
                Collections.sort(times);
                for(Long time : times)
                {
                    ArrayList<Object> events = actions.get(time);
                        for(Object event : events)
                        {
                            if(event instanceof Point)
                            {
                                Point cp = (Point)event;
                                robot.mouseMove((int)cp.getX(), (int)cp.getY());
                            }else if(event instanceof Integer) 
                            {
                                int k = (int)event; 
                                if(k == 1)
                                {
                                    robot.mousePress(InputEvent.BUTTON1_MASK); 
                                    robot.mouseRelease(InputEvent.BUTTON1_MASK);
                                }
                            }
                    }
                        
                    //Delay realtime
                    int index = times.indexOf(time);
                    if(index != times.size()-1)
                    {
                        try {
                            Thread.sleep(times.get(index + 1) - time);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Recording.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

            } catch (AWTException ex) {
                Logger.getLogger(QuickTest.class.getName()).log(Level.SEVERE, null, ex); 
            Logger.getLogger(Recording.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
//    
//    public void play(){
//            try{   
//                Robot robot = new Robot();  
//                while(true)
//                {
//                    Point cp = (Point)actions.poll();
//                    if(cp == null)
//                        break;
//                    robot.mouseMove((int)cp.getX(), (int)cp.getY());
//
//                    try {
//                        Thread.sleep(50);
//                    } catch (InterruptedException ex) {
//                        Logger.getLogger(QuickTest.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//
//            } catch (AWTException ex) {
//                Logger.getLogger(QuickTest.class.getName()).log(Level.SEVERE, null, ex);
//            }
//    }   
 }