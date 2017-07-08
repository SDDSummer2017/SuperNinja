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
import Physics.Acceleration;
import Physics.Force;

import java.awt.Color;
import java.util.ArrayList; 
import javax.swing.Timer;
import View.GamePanel;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import java.util.Random;

public class GameData implements Subject, Updateable, Renderable  {

    private final int FORCE_OF_GRAVITY = 5; 
    private final int SIZE = 50;
 
    public ArrayList<Observer> observers; 
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
        this.registerObserver(new SoundHandler(""));
        this.notifyObservers("Level One");
       
    



       
        //enemies.add(new Rai((GamePanel.CAMERA_WIDTH / 2), GamePanel.CAMERA_HEIGHT - 90, 100));
        //     enemys.add(new Rai(0, GamePanel.PHEIGHT - 90, 100));
    } 
    
    public void addEnemy(int n) {
        Random r = new Random(); 
            for (int i = 0; i < n; i++) {
                addGameData(new Rai(r.nextInt(GamePanel.CAMERA_WIDTH),
                        r.nextInt(GamePanel.CAMERA_HEIGHT), SIZE));
        }
    }
    

    public void addNenBullet(double x1, double y1, double x2, double y2, Color color) {
 
        addGameData(new Shuriken(x1, y1, x2, y2, color));
        this.notifyObservers("Shuriken");
       
    }

    @Override
    public void update() {
        
        level.addGameData();
        level.removeGameData();
        
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
 
//        synchronized (bullets) {
//            for (GameFigure b : bullets) {
//                b.update();
//                if(b.hit==true 
//                        || b.x < 0
//                        || b.x > GamePanel.CAMERA_WIDTH
//                        || b.y > GamePanel.CAMERA_HEIGHT
//                        || b.y <0)deadBullets.add(b);
//            }
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
    
    public void addGameData(Object ob)
    {
        level.addGameData(ob); 
    }
    
    public void removeGameData(Object ob)
    {
        level.removeGameData(ob);
    }
    
    public void addEnemyBullet(GameFigure e)
    {
        level.addGameData(e);
    }
   
    
  
}
      

