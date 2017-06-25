package Model;

import EventHandling.Observer;
import Model.States.Nen.NeutralCombat;
import Model.States.Nen.NeutralMotion;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import View.GamePanel;
import EventHandling.SoundHandler; 
import java.util.ArrayList;
public class Nen extends GameFigure {
   
    private final Image launcherImage;
    private final Image[] moveRightAnimation, moveLeftAnimation;
    private int jumpHeight = 0;
    private int dy = -7;

     
   
    private final SoundHandler soundHandler = new SoundHandler("");

    private int frameIndex;
    private final int animationLength;
    public boolean jump, movingLeft, movingRight;
    
    public Nen(int x, int y, int size) {
        super(x, y, size);
        this.health = 100;
        
        ArrayList<Observer> observers = new ArrayList<Observer>();
        mState = new NeutralMotion(this, observers);
        cState = new NeutralCombat(this, observers);
        
        mState.registerObserver(soundHandler);
        cState.registerObserver(soundHandler);
        movingRight = jump = movingLeft = false;
        String imagePath = System.getProperty("user.dir");
        // separator: Windows '\', Linux '/'
        String separator = System.getProperty("file.separator");

        launcherImage = getImage(imagePath + separator + "images" + separator
                + "Nen.png");
        
        frameIndex = 0;
        animationLength = 7;
        moveRightAnimation = new Image[animationLength]; 
        moveLeftAnimation = new Image[animationLength]; 
        for(int i=0;i<moveRightAnimation.length;i++){
            moveRightAnimation[i] = getImage(imagePath + separator + "images" + separator
                + "NenRight" + i + ".jpg");
            
            moveLeftAnimation[i] = getImage(imagePath + separator + "images" + separator
                + "NenLeft" + i + ".jpg");
        }
        
    }

    @Override
    public void render(Graphics g) {
        
                
        if(movingRight){
            g.drawImage(moveRightAnimation[frameIndex], (int) super.x, (int) super.y, (int) super.size, (int) super.size, null);
            //If we've reached the end of the animation reset frameCounter to zero, otherwise increment it.
            frameIndex = (frameIndex == animationLength-1) ? 0 : frameIndex + 1;           
        }else if (movingLeft){
            g.drawImage(moveLeftAnimation[frameIndex], (int) super.x, (int) super.y, (int) super.size, (int) super.size, null);
            frameIndex = (frameIndex == animationLength-1) ? 0 : frameIndex + 1;   
        }
        else{
            g.drawImage(launcherImage, (int) super.x, (int) super.y, (int) super.size, (int) super.size, null);
            //If they are standing still we need to reset the frameCounter
            frameIndex = 0;
        }

        g.setColor(Color.red);
        g.fillRect(3, GamePanel.PHEIGHT - 102, 10, 100);

        g.setColor(Color.green);
        g.fillRect(3, GamePanel.PHEIGHT - (int) this.health - 2, 10, (int) this.health);
    }
    
    @Override
    public void update() {
 
        if(cState instanceof NeutralCombat)
            mState.execute();
        else
            cState.execute();
        gravity();
    }

 
    public void gravity(){
        if(y <= 450)
           y += 8; 
        else
            airborn = false;
    }
 

    public void translate(int dx, int dy) {
        if (super.x <= 0 && dx < 0) {
            dx = 0;
        }
        if (((super.x + super.size) >= GamePanel.PWIDTH) && (dx > 0)) {
            dx = 0;
        }
        super.x += dx;
        super.y += dy;
    }

    public void resetNen() {
 
        this.health = 100;
        jump = movingLeft = movingRight = false;
        jumpHeight = 0;
        jump = false;
        dy = -7;
        super.y = GamePanel.PHEIGHT - super.size;
        super.x = GamePanel.PWIDTH/2;
    }

    public static Image getImage(String fileName) {
        Image image = null;
        try {
            image = ImageIO.read(new File(fileName));
        } catch (IOException ioe) {
            System.out.println("Error: Cannot open image:" + fileName);
            JOptionPane.showMessageDialog(null, "Error: Cannot open image:" + fileName);
        }
        return image;
    }

    @Override
    public String toString() {
        return super.x + ", " + super.y;
    }

    @Override
    public Rectangle2D.Double getCollisionBox() {
        return new Rectangle2D.Double(super.x, super.y, super.size, super.size);
    }

}
