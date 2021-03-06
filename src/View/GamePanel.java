 
package View;

import Controller.Main;
import Controller.QuadTree;
 
import EventHandling.CollisionHandler;
import Level.Checkpoint;
 
 
import Model.Collision;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;
import Model.GameFigure;
import Model.Nen;
import view.Camera;
import static Model.Nen.getImage;
 
 
import java.awt.Graphics2D;
 
import java.util.ArrayList;

public class GamePanel extends JPanel {
    
    public static final int CAMERA_WIDTH = 1000;
    public static final int CAMERA_HEIGHT = 740;
    
    public static int WORLD_WIDTH = 10000;
    public static int WORLD_HEIGHT =  2590; ;
    public boolean show;
    public Camera camera;
    

    // off screen rendering
    private Graphics graphics;
    private Image doubleBufferImage = null;
    private Image bgImage;
    private QuadTree tree;
    public CollisionHandler handler  = new CollisionHandler();
    public GamePanel() {
        setBackground(Color.blue);
        setPreferredSize(new Dimension(CAMERA_WIDTH, CAMERA_HEIGHT));
        String imagePath = System.getProperty("user.dir");
        String separator = System.getProperty("file.separator");
        bgImage = getImage(imagePath + separator + "images" + separator
                + "Background.jpg");
        camera = new Camera(WORLD_WIDTH, WORLD_HEIGHT, CAMERA_WIDTH, CAMERA_HEIGHT);
        tree = new QuadTree(7, 5, 0, 0, WORLD_WIDTH, WORLD_HEIGHT);
         
        
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
 
       
        Main.gameData.render(graphics);

        checkCollisions();
        if(show)
            tree.renderTree((Graphics2D)graphics);
        tree.clear();
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
 

    
    public void checkCollisions()
    {
 
        for(Collision c : Main.gameData.level.collisions)
            tree.insert(c);
        
        ArrayList<Collision> list = null;
         
        for(Collision c1 : Main.gameData.level.collisions)
        {
          list = tree.getList(c1);
          for(Collision c2 : list)
             if(c1.getCollisionBox().intersects(c2.getCollisionBox()))
                 handler.onNotify(c1, c2);
        }
 
    }
    
        
}
 
