package View;

import Controller.KeyController;
import Controller.Main;
import Controller.MouseController;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class MainWindow extends JFrame {

    public static JTextField message;
    public static JButton startButton;
   


    public MainWindow() {

        Container c = getContentPane();

        message = new JTextField("<Start>, then Shoot or Drag to kill enemies");
        message.setEditable(false);
        c.add(message, "North");

        c.add(Main.gamePanel, "Center");

        MouseController mouseController = new MouseController();    
        Main.gamePanel.addMouseListener(mouseController);
       
        KeyController keyController = new KeyController();
        this.addKeyListener(keyController);
    }

}
