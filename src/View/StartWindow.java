
package View;

import Controller.ButtonListener;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


public class StartWindow extends JFrame {
    
    public static JButton startButton;
    public final JTextArea gameControlsTextArea, gameRulesTextArea;
    public final String gameRules, gameControls;
    
    public StartWindow(){
       
        
        ButtonListener buttonListener = new ButtonListener();
        startButton = new JButton("Start");
        startButton.addActionListener(buttonListener);
        
        
        gameControls = "•Left/right arrow keys to move the Marine.\n\n"
                + "•Up arrow to jump.\n\n"
                + "•Click in the game window to shoot.";
        
        gameRules = " •A Mutalisk will spawn every few seconds\n\n"
                + " •Once the first 10 Mutalisks have been killed the Boos will spawn\n\n"
                + " •Mutalisks continue to spawn during Boss fight\n\n"
                + " •If the Boss dies you win\n\n"
                + " •If you die...well...you loose";

        gameControlsTextArea = new JTextArea(gameControls);
        gameControlsTextArea.setEditable(false);
        gameControlsTextArea.setBorder(new TitledBorder(new EtchedBorder(), "Controls"));
        
        gameRulesTextArea = new JTextArea(gameRules);
        gameRulesTextArea.setEditable(false);
        gameRulesTextArea.setBorder(new TitledBorder(new EtchedBorder(), "Rules"));
      
        Container pane = getContentPane();
        pane.add(gameControlsTextArea, "North");
        pane.add(gameRulesTextArea, "Center");
        pane.add(startButton, "South");
        
    }
    
}
