package View;

import Controller.Main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;
import Model.GameFigure;
import view.Camera;
import static Model.Nen.getImage;

public class GamePanel extends JPanel {

    public static final int CAMERA_WIDTH = 1000;
    public static final int CAMERA_HEIGHT = 540;
    
    public static final int WORLD_WIDTH = 10000;
    public static final int WORLD_HEIGHT = 540;
    public Camera camera;
    

    // off screen rendering
    private Graphics graphics;
    private Image doubleBufferImage = null;
    private Image bgImage;
   

    public GamePanel() {
        setBackground(Color.blue);
        setPreferredSize(new Dimension(CAMERA_WIDTH, CAMERA_HEIGHT));
        String imagePath = System.getProperty("user.dir");
        String separator = System.getProperty("file.separator");
        bgImage = getImage(imagePath + separator + "images" + separator
                + "Background.jpg");
        camera = new Camera(WORLD_WIDTH, WORLD_HEIGHT, CAMERA_WIDTH, CAMERA_HEIGHT);
    }

    /*
    First layer of "double-render": Rendering background and game figures dbimage 
    */
    public void gameRender() {
        if (doubleBufferImage == null) {
            doubleBufferImage = createImage(CAMERA_WIDTH, CAMERA_HEIGHT);
            if (doubleBufferImage == null) {
                System.out.println("dbImage is null");
                return;
            } else {
                graphics = doubleBufferImage.getGraphics();
            }
        }
        camera.update(CAMERA_WIDTH, CAMERA_HEIGHT);
        graphics.translate(-camera.camX + camera.offsetHelperX,-camera.camY + camera.offsetHelperY);
        graphics.clearRect(0, 0, WORLD_WIDTH, WORLD_HEIGHT);
        //Render Background
        graphics.drawImage(bgImage , 0, 0, GamePanel.CAMERA_WIDTH, GamePanel.CAMERA_HEIGHT, null);
        Main.gameData.nen.render(graphics);
        //Redner the enemys
        synchronized (Main.gameData.enemys) {
            for (GameFigure f : Main.gameData.enemys) {
                f.render(graphics);
            }
        }
        //Render the bullets
        synchronized (Main.gameData.bullets) {
            for (GameFigure b : Main.gameData.bullets) {
                b.render(graphics);
            }
        }
        
        synchronized (Main.gameData.allies){
            for(GameFigure a : Main.gameData.allies)
                a.render(graphics);
        }
    }

    // use active rendering to put the buffered image on-screen
    public void printScreen() {
        Graphics g;
        try {
            g = this.getGraphics();
            if ((g != null) && (doubleBufferImage != null)) {
                g.drawImage(doubleBufferImage, 0, 0, null);
            }
            Toolkit.getDefaultToolkit().sync();  // sync the display on some systems
            if (g != null) {
                g.dispose();
            }
        } catch (Exception e) {
            System.out.println("Graphics error: " + e);
        }
    }
}
