package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import View.StartWindow;
import View.GameOverWindow;

public class ButtonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent ae) {
     
        if (ae.getSource() == StartWindow.startButton) {     
            
            Main.gameData.gameThread.start();
            Main.gameData.mutaTimer.start();
            Main.startWindow.setVisible(false);
        } 
        else if(ae.getSource() == GameOverWindow.restartButton){      
            Main.gameOverWindow.setVisible(false);
            Main.gameData.marine.resetMarine();
            
            Main.gameData.setBossSpawned(false);
            //Main.gameData.boss.resetHealth();
            Main.animator.running = true;
            Main.gameData.timerListener.mutaCount = 0;
            Main.gameData.gameThread = new Thread(Main.animator);
            Main.gameData.gameThread.start();
            Main.gameData.mutaTimer.restart();
            
        }
   
    }
}
