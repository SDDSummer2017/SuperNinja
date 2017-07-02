package Model;

import Controller.Main;
import Controller.TimerListener;
import EventHandling.PhysicsHandler;
import EventHandling.CollisionObserver;
import EventHandling.MusicHandler;
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
import java.awt.Polygon;
import java.awt.geom.Rectangle2D;

import java.util.Random;

public class GameData implements Subject, Updateable, Renderable  {

    private final int FORCE_OF_GRAVITY = 5; 
    private final int SIZE = 50;
    public final List<GameFigure> enemies;
    public final List<GameFigure> deadEnemys;
    public final List<GameFigure> bullets;
    public final List<GameFigure> enemyBullets;
    public final List<GameFigure> allies;
    public ArrayList<Observer> observers;
    public final List<GameFigure> deadBullets;
    public final Nen nen;
 
    public Timer enemyTimer, bossTimer;
  
    public TimerListener timerListener;
    public Thread gameThread;
    private final int nenSize = 70;
    public Level level; 
    private Force gravity;
    private Force lfriction; 
    Rectangle2D cb; 
    private final Force rfriction;
    public GameData()  {
        lfriction = new Force(.05, new Acceleration(0, -1)); 
        rfriction = new Force(.05, new Acceleration(0, 1)); 
        gravity = new  Force(9, new Acceleration(0, .49));
        enemies = Collections.synchronizedList(new ArrayList<GameFigure>());
        deadEnemys = Collections.synchronizedList(new ArrayList<GameFigure>());
        bullets = Collections.synchronizedList(new ArrayList<GameFigure>());
        deadBullets = Collections.synchronizedList(new ArrayList<GameFigure>());
        allies = Collections.synchronizedList(new ArrayList<GameFigure>());
        //enemySpawn = Collections.synchronizedList(new ArrayList<GameFigure>());
        enemyBullets = Collections.synchronizedList(new ArrayList<GameFigure>());
        timerListener = new TimerListener();
        enemyTimer = new Timer(5000, timerListener);
        enemyTimer.setInitialDelay(3000);
        gameThread = new Thread(Main.animator);
        level = new NinjaVillage(); 
       
       
       
        observers = new ArrayList<Observer>(); 
        this.registerObserver(new PhysicsHandler());
        

        
        nen = new Nen(GamePanel.CAMERA_WIDTH / 2, GamePanel.CAMERA_HEIGHT - nenSize, nenSize);

        MusicHandler m = new MusicHandler("");
        Thread thread = new Thread(m);
        thread.start();
        this.registerObserver(m);
        this.notifyObservers("Level One");
       
    



       
        //enemies.add(new Rai((GamePanel.CAMERA_WIDTH / 2), GamePanel.CAMERA_HEIGHT - 90, 100));
        //     enemys.add(new Rai(0, GamePanel.PHEIGHT - 90, 100));
    } 
    
    public void addEnemy(int n) {
        Random r = new Random();
         synchronized (enemies) {
            for (int i = 0; i < n; i++) {
                level.enemies.add(new Rai(r.nextInt(GamePanel.CAMERA_WIDTH),
                        r.nextInt(GamePanel.CAMERA_HEIGHT), SIZE));


            }

        }
    }
    

    public void addNenBullet(double x1, double y1, double x2, double y2, Color color) {
        synchronized (allies) {
                level.allies.add(new Shuriken(x1, y1, x2, y2, color));
        }
    }
    
    public synchronized void addHitBox(GameFigure hitBox){
        synchronized (enemies)
        {
            level.enemies.add(hitBox); 
        }
    }
    
    public void addAlly(GameFigure ally) {
        synchronized (allies) {
                level.allies.add(ally);
        }
    }
    
    public void removeAlly(GameFigure ally) {
        synchronized (allies) {
                level.allies.remove(ally);
        }
    }
     

    @Override
    public void update() {
        
        nen.forces.clear();
        nen.forces.add(gravity);
        
        if(nen.velocity.dx > 0)
        {
                 nen.forces.add(lfriction);
        }
        else if(nen.velocity.dx < 0)
        {
                  nen.forces.add(rfriction);
        }

//        synchronized (enemies) {
//            for (GameFigure f : enemies) {
//                f.update(); 
//            }
//        }
        
//        synchronized (allies) {
//            for (GameFigure a : allies)
//                a.update();
//        } 
        synchronized (bullets) {
            for (GameFigure b : bullets) {
                b.update();
                if(b.hit==true 
                        || b.x < 0
                        || b.x > GamePanel.CAMERA_WIDTH
                        || b.y > GamePanel.CAMERA_HEIGHT
                        || b.y <0)deadBullets.add(b);
            }
        }       
        synchronized (level.enemies) {
            for (GameFigure f : enemies) {
              
                if(f.health <=0)deadEnemys.add(f); 
            }
        }
        
//        synchronized (enemies){
//            synchronized (allies){
//                for(GameFigure ally : allies)
//                    for(GameFigure enemy : enemies)
//                    if(ally.getCollisionBox().intersects(enemy.getCollisionBox()))
//                    {
//                       // System.out.println("Health: " + enemy.health);
//                        if(enemy.cState instanceof Block == false)
//                            enemy.health -= 5;
//                        //System.out.println("HEALTH: "  + enemy.health);
//                        enemy.cState.nextState("Hit");
//                        if(enemy.health <= 0)
//                            deadEnemys.add(enemy);
//                    }
//            }
//            
//            synchronized (allies){
//                for(GameFigure ally : allies)
//                    for(GameFigure enemy : enemies)
//                    if(ally.getCollisionBox().intersects(enemy.getCollisionBox()))
//                    {
//                       // System.out.println("Health: " + enemy.health);
//                        if(enemy.cState instanceof Block == false)
//                            enemy.health -= 5;
//                        //System.out.println("HEALTH: "  + enemy.health);
//                        enemy.cState.nextState("Hit");
//                        if(enemy.health <= 0)
//                            deadEnemys.add(enemy);
//                    }
//            }
//            
//           
//            
//         
//            
//
//        }
  
           synchronized (nen){
                    cb = nen.getCollisionBox();
                    cb.setRect(cb.getX() + nen.velocity.dx, cb.getY() + nen.velocity.dy, cb.getHeight(), cb.getWidth());
                    for(GameFigure terrain : level.terrain)
                    
                    if(cb.intersects(terrain.getCollisionBox()))
                    {
                        this.notityObservers(terrain, nen);
                         
                    }
            }
        
        enemies.removeAll(deadEnemys);
        bullets.removeAll(deadBullets);
        deadBullets.clear();
        deadEnemys.clear();
        int bulletCount = bullets.size();
        
        nen.update();
        level.update();
        nen.calculatePhysics();
        
    
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
       for(Observer o : observers)
       {
           o.onNotify(event);
       }
    }

    @Override
    public void render(Graphics g) {
        level.render(g);
        //Rectangle2D r = cb.getBounds2D();
        
        //g.drawRect((int) r.getX(), (int) r.getY(), (int) r.getHeight(), (int) r.getWidth());
        
    }
    
    public void addEnemyBullet(GameFigure e)
    {
        synchronized(allies)
        {
            level.allies.add(e);
        }
    }
   
    
  
}
      

