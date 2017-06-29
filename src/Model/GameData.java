package Model;

import Controller.Main;
import Controller.TimerListener;
import EventHandling.TerrainHandler;
import EventHandling.CollisionObserver;
import EventHandling.Observer;
import EventHandling.SoundHandler;
import EventHandling.Subject;
import Model.States.Rai.Block;
import Model.Terrain.Platform;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.Timer;
import View.GamePanel;
import View.MainWindow;
import java.util.Random;

public class GameData implements Subject  {

    private final int SIZE = 50;
    public final List<GameFigure> enemys;
    public final List<GameFigure> deadEnemys;
    public final List<GameFigure> bullets;
    public final List<GameFigure> allies;
    public final List<Observer> observers;
    public final List<GameFigure> deadBullets;
    public final Nen nen;
    private Rai rai;
    public Timer enemyTimer, bossTimer;
    public long time = 0;
    public TimerListener timerListener;
    public Thread gameThread;
    public Boss boss;
    private boolean bossSpawned = false;
    private final int nenSize = 75;

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
        enemyTimer = new Timer(5000, timerListener);
        enemyTimer.setInitialDelay(3000);
        
        bossTimer = new Timer(3000, timerListener);
        bossTimer.setRepeats(false);
        
        gameThread = new Thread(Main.animator);
        

       
       allies.add(new Platform(250, 250, 5));
       //enemys.add(new Dummy(250, 250, 5));

        nen = new Nen(GamePanel.PWIDTH / 2, GamePanel.PHEIGHT - nenSize, nenSize);

       rai = new Rai((GamePanel.PWIDTH), GamePanel.PHEIGHT - 90, 100);
              enemys.add(rai);
           // enemys.add(new Rai(0, GamePanel.PHEIGHT - 90, 100));
           
           
       //Get event handlers for game logic. 
       observers = new ArrayList<>(); 
       
       observers.add(new SoundHandler("")); 
       observers.add((Observer) new TerrainHandler());
    }


    public void addEnemy(int n) {
        Random r = new Random();
         synchronized (enemys) {
            for (int i = 0; i < n; i++) {
                enemys.add(new Rai(r.nextInt(GamePanel.PWIDTH),
                        r.nextInt(GamePanel.PHEIGHT), SIZE));
            }

        }
    }
    
    public void addBoss(){
        synchronized (enemys){
            boss = new Boss(30, 30, 250, 115);
            enemys.add(boss);
        }
    }
    public void addNenBullet(double x1, double y1, double x2, double y2, Color color) {
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
        
        if(enemys.isEmpty() && !enemyTimer.isRunning() && !bossSpawned){
            bossSpawned = true;
            addBoss();
            enemyTimer.restart();
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
       
//        if(System.currentTimeMillis() - time >= 1000)
//        {
//            time = System.currentTimeMillis();
//            System.out.println("NEN: "  + "X: "  + nen.x + " Y:" + nen.y);
//            System.out.println("RAI: "  + "X: "  + rai.x + " Y:" + rai.y);
//            double dis = rai.x - nen.x;
//            System.out.println("DISTANCE: "  + "X: " + dis);
//        }
        
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
                       // System.out.println("Health: " + enemy.health);
                        if(enemy.cState instanceof Block == false)
                            enemy.health -= 5;
                        //System.out.println("HEALTH: "  + enemy.health);
                        enemy.cState.nextState("Hit");
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
        MainWindow.message.setText("Bullets:" + bulletCount + enemyTimer.isRunning());
        checkGameCondition();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer); 
    }

    @Override
    public void deregisterObserver(Observer observer) {
        observers.remove(observer); 
    }

    @Override
    public void notifyObservers() {
        
    }

    public void notityObservers(GameFigure gameFigure1, GameFigure gameFigure2) 
    {
         for(Observer o : observers)
         {
             if( o instanceof CollisionObserver){
                    ((CollisionObserver) o).onNotify(gameFigure1, gameFigure2);
             }
         }
    }
    
    @Override
    public void notifyObservers(String event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
      

