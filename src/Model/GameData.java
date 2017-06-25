package Model;

import Controller.Main;
import Controller.TimerListener;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.swing.Timer;
import View.GamePanel;
import View.MainWindow;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

public class GameData {

    private final int SIZE = 50;
    public final List<GameFigure> enemys;
    public final List<GameFigure> deadEnemys;
    public final List<GameFigure> bullets;
    public final List<GameFigure> allies;
    
    public final List<GameFigure> deadBullets;
    public final Nen nen;
    public Timer mutaTimer, bossTimer;
    public TimerListener timerListener;
    public Thread gameThread;
    public Boss boss;
    private boolean bossSpawned = false;

    public void setBossSpawned(boolean bossSpawned) {
        this.bossSpawned = bossSpawned;
    }

    public GameData() {
        enemys = Collections.synchronizedList(new ArrayList<GameFigure>());
        deadEnemys = Collections.synchronizedList(new ArrayList<GameFigure>());
        bullets = Collections.synchronizedList(new ArrayList<GameFigure>());
        deadBullets = Collections.synchronizedList(new ArrayList<GameFigure>());
        allies = Collections.synchronizedList(new ArrayList<GameFigure>());
        timerListener = new TimerListener();
        mutaTimer = new Timer(5000, timerListener);
        mutaTimer.setInitialDelay(3000);
        
        bossTimer = new Timer(3000, timerListener);
        bossTimer.setRepeats(false);
        
        gameThread = new Thread(Main.animator);
        
<<<<<<< HEAD
        marine = new Nen(GamePanel.PWIDTH / 2, GamePanel.PHEIGHT - 90, 90);
        //enemys.add(new Rai(GamePanel.PWIDTH, GamePanel.PHEIGHT -90, 100));
        
        this.addMutalisk(SIZE);
       
=======
        nen = new Nen(GamePanel.PWIDTH / 2, GamePanel.PHEIGHT - 90, 90);
       enemys.add(new Dummy(300, 400, 5));
       enemys.add(new Dummy(500, 400, 5));
       enemys.add(new Dummy(0, 300, 5));
       enemys.add(new Dummy(250, 250, 5)); 
>>>>>>> refs/remotes/origin/StateImplementation
    }

    public void addMutalisk(int n) {
        synchronized (enemys) {
            enemys.add(new Rai((GamePanel.PWIDTH), GamePanel.PHEIGHT - 90, 100));
            enemys.add(new Rai(0, GamePanel.PHEIGHT - 90, 100));
        }
    }
    
    public void addBoss(){
        synchronized (enemys){
            boss = new Boss(30, 30, 250, 115);
            enemys.add(boss);
        }
    }
    public void addMarineBullet(double x1, double y1, double x2, double y2, Color color) {
        synchronized (bullets) {
                bullets.add(new Shuriken(x1, y1, x2, y2, color));
        }
    }
    
    public void addEnemyBullet(double x1, double y1, double x2, double y2, Color color) {
        synchronized (bullets) {
                bullets.add(new EnemyBullet(x1, y1, x2, y2, color));
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

    public void checkGameCondition(){
        if(enemys.isEmpty() && !mutaTimer.isRunning() && !bossSpawned){
            bossSpawned = true;
            addBoss();
            mutaTimer.restart();
        }
        if (nen.health <= 0){
            Main.animator.running = false;
            Main.gameOverWindow.setOutcomeText("loose");
            Main.gameOverWindow.setVisible(true);
        }
        if ((boss != null) && (boss.health <= 0)){
            Main.animator.running = false;
            boss = null;
            Main.gameOverWindow.setOutcomeText("win");
            Main.gameOverWindow.setVisible(true);
        }
            
    }
    
    
    public void update() { 
        nen.update();
        
        synchronized (bullets) {
            for (GameFigure b : bullets) {
                b.update();
                if(b.hit==true 
                        || b.x < 0
                        || b.x > GamePanel.PWIDTH
                        || b.y > GamePanel.PHEIGHT
                        || b.y <0)deadBullets.add(b);
            }
        }       
        synchronized (enemys) {
            for (GameFigure f : enemys) {
                f.update();
                if(f.health <=0)deadEnemys.add(f); 
            }
        }
        
        synchronized (enemys){
            synchronized (allies){
                for(GameFigure ally : allies)
                    for(GameFigure enemy : enemys)
                    if(ally.getCollisionBox().intersects(enemy.getCollisionBox()))
                    {
                        enemy.health -= 10;
                        System.out.println("HEALTH: "  + enemy.health);
                        if(enemy.health <= 0)
                            deadEnemys.add(enemy);
                    }
            }
        }
  
        enemys.removeAll(deadEnemys);
        bullets.removeAll(deadBullets);
        deadBullets.clear();
        deadEnemys.clear();
        int bulletCount = bullets.size();
//        MainWindow.message.setText("Bullets:" + bulletCount + mutaTimer.isRunning());
        checkGameCondition();
    }
}
      

