 
package View;

import Controller.Main;
import Controller.QuadTree;
import Level.Platform;
import Model.Collision;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;
import Model.GameFigure;
import Model.HitBox;
import Model.Nen;
import view.Camera;
import static Model.Nen.getImage;
import Model.Shuriken;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

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
    private QuadTree tree;

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
        Main.gameData.nen.render(graphics);
        //Redner the enemys
        
        Main.gameData.render(graphics);
        
        synchronized (Main.gameData.enemies) {
            for (GameFigure f : Main.gameData.enemies) {
                f.render(graphics);
            }
        } 
        
        synchronized (Main.gameData.allies){
            for(GameFigure a : Main.gameData.allies)
                a.render(graphics);
        }
        
        List<GameFigure> allies = Main.gameData.level.allies;
        List<GameFigure> enemies = Main.gameData.level.enemies;
        List<GameFigure> enemyBullets = Main.gameData.enemyBullets;
        List<GameFigure> alliedBullets = Main.gameData.bullets; 
        List<GameFigure> terrain = Main.gameData.level.terrain;
        synchronized(allies)
        {
            synchronized(enemies)
            {
                for(GameFigure a : allies)
                    tree.insert(a);
                for(GameFigure e : enemies)
                    tree.insert(e);
                for(GameFigure eb : enemyBullets )
                {
                    tree.insert(eb);
                }
                for(GameFigure ab : alliedBullets)
                {
                    tree.insert(ab);
                }
                for(GameFigure t : terrain)
                {
                    tree.insert(t);
                }
                
                ArrayList<Collision> list = null;
                
                for(GameFigure eb: allies)
                {
                    list = tree.getList(eb);
                    for(Collision obj : list)
                    {
                        
                        if(obj.hashCode() != eb.hashCode() && eb.getCollisionBox().intersects(obj.getCollisionBox()))
                        { 
                            
                           
                                
                            if(eb instanceof Shuriken && obj instanceof Platform)
                            {
                               Main.gameData.level.remove.add(eb);
                                
                                
                               
                            }
                            
                           
                            
                            
                        }
                    }
                }
                
                for(GameFigure a : allies)
                {
                    
                    list = tree.getList(a);
                    for(Collision obj : list)
                        if(obj.hashCode() != a.hashCode() && a.getCollisionBox().intersects(obj.getCollisionBox()))
                        { 
                            
                            if(obj instanceof HitBox &&  a instanceof Nen)
                                ((Nen)a).health -= ((HitBox)obj).gameFigure.damage;
                            else if(a instanceof HitBox && obj instanceof GameFigure)
                                ((GameFigure)obj).health -= ((HitBox)a).gameFigure.damage;
                            
                            if(obj instanceof GameFigure && ((GameFigure)obj).health <= 0)
                                enemies.remove((GameFigure)obj);
                                
                           
                            if(a instanceof Nen && ((Nen)a).health <= 0)
                                allies.remove(a);
                            
                            
                        }
                }
                
            }
        }
//        tree.renderTree((Graphics2D)graphics);
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
 
