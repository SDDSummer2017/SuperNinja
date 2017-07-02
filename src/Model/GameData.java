package Model;

import Controller.Main;
import Controller.TimerListener;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.Timer;
import View.GamePanel;  
import java.util.Random;

public class GameData {

    private final int SIZE = 50;
    public final List<GameFigure> enemys; 
    public final List<GameFigure> allies;
    public final List<GameFigure> enemyBullets;
    public final Nen nen;
 
    public Timer enemyTimer, bossTimer;
 
    public TimerListener timerListener;
    public Thread gameThread; 
    private final int nenSize = 75;
    public final List<GameFigure> enemySpawn;
    public GameData() {
        enemys = Collections.synchronizedList(new ArrayList<GameFigure>()); 
        enemyBullets = Collections.synchronizedList(new ArrayList<GameFigure>());
        allies = Collections.synchronizedList(new ArrayList<GameFigure>());
        enemySpawn = Collections.synchronizedList(new ArrayList<GameFigure>());
        
        timerListener = new TimerListener();
        enemyTimer = new Timer(5000, timerListener);
        enemyTimer.setInitialDelay(3000);
        
        bossTimer = new Timer(3000, timerListener);
        bossTimer.setRepeats(false);
        
        gameThread = new Thread(Main.animator);
        

        /*enemys.add(new Dummy(300, 400, 5));
        enemys.add(new Dummy(500, 400, 5));
        enemys.add(new Dummy(0, 300, 5));
        enemys.add(new Dummy(250, 250, 5));*/
        
        nen = new Nen(GamePanel.CAMERA_WIDTH / 2, GamePanel.CAMERA_HEIGHT - nenSize, nenSize);

       
        enemys.add(new Rai((GamePanel.CAMERA_WIDTH), GamePanel.CAMERA_HEIGHT - 90, 100));  
        enemys.add(new Terro(0, GamePanel.CAMERA_WIDTH - 90, 100));
        
    } 
    
    public void addEnemy(int n) {
        Random r = new Random();
         synchronized (enemys) {
            for (int i = 0; i < n; i++) {
                enemys.add(new Rai(r.nextInt(GamePanel.CAMERA_WIDTH),
                        r.nextInt(GamePanel.CAMERA_HEIGHT), SIZE));
            }

        }
    }
    
    public void addNenBullet(double x1, double y1, double x2, double y2, Color color) {
        synchronized (allies) {
                allies.add(new Shuriken(x1, y1, x2, y2, color));
        }
    }
    
    public synchronized void addHitBox(GameFigure hitBox){
        synchronized (enemys)
        {
            enemys.add(hitBox); 
        }
    }
    
    public void addAlly(GameFigure ally) {
        synchronized (allies) {
                allies.add(ally);
        }
    }
    
    public void removeAlly(GameFigure ally) {
        synchronized (allies) {
                allies.remove(ally);
        }
    }
    
    public void addEnemyBullet(double x1, double y1, double x2, double y2, Color color) {
        synchronized (enemyBullets) { 
            enemyBullets.add(new EnemyBullet(x1, y1, x2, y2, color));
        }
    }

    public void update() { 

        
        nen.update();
        
        synchronized (enemys) {
            for (GameFigure f : enemys) {
                f.update(); 
            }
        }
        
        synchronized (allies) {
            for (GameFigure a : allies)
                a.update();
        } 
        
        synchronized (enemyBullets) {
            for (GameFigure a : enemyBullets)
                a.update();
        } 
    }
}
      

