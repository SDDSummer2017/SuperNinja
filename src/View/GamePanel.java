package View;

import Controller.Main;
import Controller.QuadTree; 
import Model.Collision;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;
import Model.GameFigure;
import static Model.Nen.getImage;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel {
    QuadTree tree;
    public static final int PWIDTH = 1000; // size of the game panel
    public static final int PHEIGHT = 540;


    // off screen rendering
    private Graphics graphics;
    private Image doubleBufferImage = null;
    private Image bgImage;

    public GamePanel() {
        setBackground(Color.blue);
        setPreferredSize(new Dimension(PWIDTH, PHEIGHT));
        String imagePath = System.getProperty("user.dir");
        String separator = System.getProperty("file.separator");
        bgImage = getImage(imagePath + separator + "images" + separator
                + "Background.jpg");
        tree = new QuadTree(5, 2, 0, 0, 500, 500); 
    }

    /*
    First layer of "double-render": Rendering background and game figures dbimage 
    */
    public void gameRender() {
        if (doubleBufferImage == null) {
            doubleBufferImage = createImage(PWIDTH, PHEIGHT);
            if (doubleBufferImage == null) {
                System.out.println("dbImage is null");
                return;
            } else {
                graphics = doubleBufferImage.getGraphics();
            }
        }
        //Render Background
        graphics.drawImage(bgImage , 0, 0, GamePanel.PWIDTH, GamePanel.PHEIGHT, null);
        Main.gameData.nen.render(graphics);
        //Redner the enemys
        synchronized (Main.gameData.enemys) {
            for (GameFigure f : Main.gameData.enemys) {
                f.render(graphics);
            }
        }
 
        synchronized (Main.gameData.allies){
            for(GameFigure a : Main.gameData.allies)
                a.render(graphics);
        }
        
        List<GameFigure> allies = Main.gameData.allies;
        List<GameFigure> enemies = Main.gameData.enemys;
        
        
        synchronized(allies)
        {
            synchronized(enemies)
            {
                for(GameFigure a : allies)
                    tree.insert(a);
                for(GameFigure e : enemies)
                    tree.insert(e);
                
                ArrayList<Collision> list = null;
                for(GameFigure a : allies)
                {
                    list = tree.getList(a);
                    for(Collision obj : list)
                        if(obj.hashCode() != a.hashCode() && a.getCollisionBox().intersects(obj.getCollisionBox()))
                        {
                            enemies.remove((GameFigure)obj);
                        }
                }
                
            }
        }
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
}
