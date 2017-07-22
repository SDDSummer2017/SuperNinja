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

    public static void main(String[] args) {

        animator = new Animator();
        gameData = new GameData();
        gamePanel = new GamePanel();
        
        JFrame game = new MainWindow();
        game.setTitle("The_Game");
        game.setSize(1000, 600);
        game.setLocation(100, 25);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setFocusable(true);
        game.requestFocusInWindow();
        game.setVisible(true);
        
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