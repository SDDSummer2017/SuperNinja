
package View;

import Controller.ButtonListener;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class GameOverWindow extends JFrame {
   
    public static JButton restartButton;
    JTextArea gameOverTextArea;
    String gameOverText;
    
    public GameOverWindow(){
        
        gameOverText = "No outcome set";
        restartButton = new JButton("Restart");
        ButtonListener buttonListener = new ButtonListener();
        restartButton.addActionListener(buttonListener);
        
        gameOverTextArea = new JTextArea(gameOverText);
        gameOverTextArea.setEditable(false);
        gameOverTextArea.setBorder(new TitledBorder(new EtchedBorder(), "Game Over"));
        
        
        Container pane = getContentPane();
        pane.add(gameOverTextArea, "Center");
        pane.add(restartButton, "South");
    }
    public void setOutcomeText(String outcome){
        switch(outcome){
            case "win":gameOverText = "You Are Victorious!";break;
            case "loose":gameOverText = "You Have Been Defeated!";break;        
        }
        gameOverTextArea.setText(gameOverText);
    }
}
