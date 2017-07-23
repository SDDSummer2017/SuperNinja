package Controller;

import javax.swing.JFrame;
import Model.GameData;
import View.GamePanel;
import View.MainWindow;
import View.StartWindow;
import View.GameOverWindow;

//This is a comment
public class Main {

    public static GamePanel gamePanel;
    public static GameData gameData;
    public static Animator animator;
    public static StartWindow startWindow;
    public static GameOverWindow gameOverWindow;
    public static JFrame mainWindow;
    public static void main(String[] args) {

        animator = new Animator();
        gameData = new GameData();
        gamePanel = new GamePanel();
        
        mainWindow = new MainWindow();
        mainWindow.setTitle("The_Game");
        mainWindow.setSize(1000, 600);
        mainWindow.setLocation(100, 25);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setFocusable(true);
        mainWindow.requestFocusInWindow();
        mainWindow.setVisible(true);
        
        startWindow = new StartWindow();
        startWindow.setSize(400,350);
        startWindow.setLocation(325,200);
        startWindow.setVisible(true);
        
        gameOverWindow = new GameOverWindow();
        gameOverWindow.setSize(400,350);
        gameOverWindow.setLocation(325,200);
        gameOverWindow.setVisible(false);
        
    }
}