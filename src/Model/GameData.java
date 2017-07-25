package Model;

import Controller.Main;
import Controller.TimerListener;
import EventHandling.CheckpointHandler;
import EventHandling.PhysicsHandler;
import EventHandling.CollisionObserver;
import EventHandling.MusicHandler;
import EventHandling.Observer;
import EventHandling.PlayerHudHandler;
import EventHandling.SoundHandler;
import EventHandling.Subject;
import Level.Fireball;
import Level.Level;
import Level.NinjaTower;
import Level.NinjaVillage;
import Level.TitleCard;
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
    public Nen nen;
 
    public Timer enemyTimer, bossTimer;
    public TitleCard titleCard; 
    public TimerListener timerListener;
    public Thread gameThread;
    public final int nenSize = 70;
    public Level level; 
    private Force gravity;
    private Force lfriction; 
    Rectangle2D cb; 
    private final Force rfriction;
    private CheckpointHandler checkpointHandler;
    public Thread musicThread; 
    public static ImageResource terroResource = new ImageResource("Terro", 8); 
    public static ImageResource kisaraResource = new ImageResource("Kisara", 8);    
    public static ImageResource raiResource = new ImageResource("Rai", 8);   
    public static ImageResource sageResource = new ImageResource("Sage", 8);  
    public static ImageResource projectileResource = new ImageResource("Shuriken", 8);      
    public static ImageResource shockWaveResource = new ImageResource("EarthShatter", 8);
    public static ImageResource nenResource = new ImageResource("Nen", 8);
    public static ImageResource windmillResource = new ImageResource("WindMillShuriken", 8);   
    
    public GameData()  {
      
        
        lfriction = new Force(.05, new Acceleration(0, -1)); 
        rfriction = new Force(.05, new Acceleration(0, 1)); 
        gravity = new  Force(9, new Acceleration(0, .49));
 
        timerListener = new TimerListener();
        enemyTimer = new Timer(5000, timerListener);
        enemyTimer.setInitialDelay(3000);
        gameThread = new Thread(Main.animator);
        level = new NinjaVillage(this); 
        nen = new Nen(level.nenStartX, level.nenStartY - nenSize, nenSize);
        addGameData(nen);
        observers = new ArrayList<Observer>(); 
        this.registerObserver(new PhysicsHandler());
        

        
    
 
     

 
        MusicHandler m = new MusicHandler("");
        musicThread = new Thread(m);
        musicThread.start();
        this.registerObserver(m);
        this.registerObserver(new SoundHandler(""));
        this.notifyObservers("Level One");
        nen.registerObserver(new PlayerHudHandler(this, nen));
        
       


       
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
    

//    public void addNenBullet(double x1, double y1, double x2, double y2, Color color) {
// 
//        addGameData(new Shuriken(x1, y1, x2, y2, color, true));
//        this.notifyObservers("Shuriken");
//       
//    }

    @Override
    public void update() {
        
        if(titleCard == null){
        
        level.addGameData();
        level.removeGameData();
        
        nen.forces.clear();
        nen.forces.add(gravity);
        for(Updateable g : level.updatables)
        {
            if(g instanceof Fireball)
            {
                ((Fireball) g).forces.clear();
                ((Fireball) g).forces.add(gravity);
            }
        }
        

        
        
        if(nen.velocity.dx > 0)
        {
                 nen.forces.add(lfriction);
        }
        else if(nen.velocity.dx < 0)
        {
                  nen.forces.add(rfriction);
        }

 
        synchronized (nen){
            cb = nen.getCollisionBox();
            cb.setRect(cb.getX() + nen.velocity.dx, cb.getY() + nen.velocity.dy, cb.getHeight(), cb.getWidth());
            for(GameFigure terrain : level.terrain)

            if(cb.intersects(terrain.getCollisionBox()))
            {
                this.notityObservers(terrain, nen);
            }}
 
//        synchronized (enemies) {
//            for (GameFigure f : enemies) {
//                f.update(); 
//            }
//        }
        
//        synchronized (allies) {
//       
           //Refactor this into quad tree 
           synchronized (nen){
                    cb = nen.getCollisionBox();
                    cb.setRect(cb.getX() + nen.velocity.dx, cb.getY() + nen.velocity.dy, cb.getHeight(), cb.getWidth());
                    for(GameFigure terrain : level.terrain){
                    
                        if(cb.intersects(terrain.getCollisionBox()))
                        {
                            this.notityObservers(terrain, nen);
                         
                        }
                        
                    }
                    
                    
 
            }
         
       // nen.update();
        level.update();
        nen.calculatePhysics();
        }
        else
        {
            titleCard.iterable++;
            if(titleCard.iterable > 100)
            {
                titleCard = null;
            }
        }
    
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
        if(titleCard != null)
        {
            titleCard.render(g);
            //level.render(g); 
        }
        else{
            level.render(g); 
        }
        
        
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
      

