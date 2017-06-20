package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerListener implements ActionListener {

    public int mutaCount;
    
    public TimerListener(){
        mutaCount = 0;
    }
  
    @Override
    public void actionPerformed(ActionEvent ae) {      
//        if(mutaCount == 0){
//            Main.gameData.addMutalisk(4);
//            mutaCount = 4;
//        }
//        else if(mutaCount == 16){
//            Main.gameData.addMutalisk(1);
//            Main.gameData.mutaTimer.stop();  
//        }
//        else{
//            Main.gameData.addMutalisk(4);
//            mutaCount+=4;   
//            }
    }   
}
