package Model;

import Controller.Main;
import Controller.TimerListener;
import EventHandling.PhysicsHandler;
import EventHandling.CollisionObserver;
import EventHandling.Observer;
import EventHandling.SoundHandler;
import EventHandling.Subject;
import Level.Level;
import Level.NinjaVillage;
import Model.States.Rai.Block;
import Level.Platform;
import Physics.Acceleration;
import Physics.Force;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.Timer;
import View.GamePanel;
import View.MainWindow;
import java.awt.Graphics;
import java.util.Random;

public class GameData implements Subject, Updateable, Renderable  {

    private final int FORCE_OF_GRAVITY = 5; 
    private final int SIZE = 50;
    public final List<GameFigure> enemies;
    public final List<GameFigure> deadEnemys;
    public final List<GameFigure> bullets;
    public final List<GameFigure> allies;
    public ArrayList<Observer> observers;
    public final List<GameFigure> deadBullets;
    public final Nen nen;
    private Rai rai;
    public Timer enemyTimer, bossTimer;
    public long time = 0;
    public TimerListener timerListener;
    public Thread gameThread;
    private final int nenSize = 75;
    private Level level; 
    private Force gravity;

    public GameData() {
        gravity = new  Force(9, new Acceleration(0, .49));
        enemies = Collections.synchronizedList(new ArrayList<GameFigure>());
        deadEnemys = Collections.synchronizedList(new ArrayList<GameFigure>());
        bullets = Collections.synchronizedList(new ArrayList<GameFigure>());
        deadBullets = Collections.synchronizedList(new ArrayList<GameFigure>());
        allies = Collections.synchronizedList(new ArrayList<GameFigure>());
        timerListener = new TimerListener();
        enemyTimer = new Timer(5000, timerListener);
        enemyTimer.setInitialDelay(3000);
        gameThread = new Thread(Main.animator);
        level = new NinjaVillage(); 

       
       
       //enemys.add(new Dummy(250, 250, 5));
        
        nen = new Nen(GamePanel.PWIDTH / 2, GamePanel.PHEIGHT - nenSize, nenSize);
        observers = new ArrayList<Observer>(); 
        this.registerObserver(new PhysicsHandler());
    
    }


    public void addEnemy(int n) {
        Random r = new Random();
         synchronized (enemies) {
            for (int i = 0; i < n; i++) {
                enemies.add(new Rai(r.nextInt(GamePanel.PWIDTH),
                        r.nextInt(GamePanel.PHEIGHT), SIZE));
            }

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

     
     

    public void update() {
        
        if(nen.forces.contains(gravity) == false)
        {
            nen.forces.add(gravity);
        }
        
        
        
        level.update();
        
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
        synchronized (level.enemies) {
            for (GameFigure f : enemies) {
              
                if(f.health <=0)deadEnemys.add(f); 
            }
        }
        
        synchronized (enemies){
            synchronized (allies){
                for(GameFigure ally : allies)
                    for(GameFigure enemy : enemies)
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
            
            synchronized (allies){
                for(GameFigure ally : allies)
                    for(GameFigure enemy : enemies)
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
            
           
            
            synchronized (nen){
                
                    for(GameFigure terrain : level.terrain)
                    if(nen.getCollisionBox().intersects(terrain.getCollisionBox()))
                    {
                        this.notityObservers(terrain, nen);
                         
                    }
            }
            
               nen.calculatePhysics();
        }
  
        enemies.removeAll(deadEnemys);
        bullets.removeAll(deadBullets);
        deadBullets.clear();
        deadEnemys.clear();
        int bulletCount = bullets.size();
        nen.update();
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

    @Override
    public void render(Graphics g) {
        level.render(g);
    }
}
      

