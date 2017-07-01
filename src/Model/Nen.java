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
import Model.States.Nen.Move;
import Model.States.Nen.Jump;
import java.util.ArrayList;
public class Nen extends GameFigure {
   
    private final Image launcherImage;
    private final Image[] moveRightAnimation, moveLeftAnimation, idleAnimation, jumpAnimation;
    private int jumpHeight = 0;
    private int dy = -7;
    private final SoundHandler soundHandler = new SoundHandler("");

    private int moveFrameIndex, idleFrameIndex, idleFrameDelayCount, jumpFrameIndex;
    private final int moveAnimationLength, idleAnimationLength, jumpAnimationLength;
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
        
        idleFrameDelayCount = 0;
        idleFrameIndex = 0;
        moveFrameIndex = 0;
        jumpFrameIndex = 0;
        moveAnimationLength = 7;
        idleAnimationLength = 3;
        jumpAnimationLength = 3;
        moveRightAnimation = new Image[moveAnimationLength]; 
        moveLeftAnimation = new Image[moveAnimationLength]; 
        idleAnimation = new Image[idleAnimationLength]; 
        jumpAnimation = new Image[jumpAnimationLength];
        //Build Animation Arrays
        for(int i=0;i<moveAnimationLength;i++){
            moveRightAnimation[i] = getImage(imagePath + separator + "images" + separator
                + "NenRight" + i + ".jpg");
            
            moveLeftAnimation[i] = getImage(imagePath + separator + "images" + separator
                + "NenLeft" + i + ".jpg");
        }
        for(int i=0;i<idleAnimationLength;i++){
            idleAnimation[i] = getImage(imagePath + separator + "images" + separator
                + "Ren_Standing_Animation_" + i + ".png");
        }
        for(int i=0;i<jumpAnimationLength;i++){
            jumpAnimation[i] = getImage(imagePath + separator + "images" + separator
                + "Ren_Jump_Animation_" + i + ".png");
        }
        
    }

    @Override
    public void render(Graphics g) {
        
        if(mState instanceof Move)
        {
            //If we are moving, reset the idle animtion frame index
            idleFrameIndex = 0;
            jumpFrameIndex = 0;
            idleFrameDelayCount = 0;
            if(isFacingRight){
                g.drawImage(moveRightAnimation[moveFrameIndex], (int) super.x, (int) super.y, (int) super.size, (int) super.size, null);
                //If we've reached the end of the animation reset frameCounter to zero, otherwise increment it.
                moveFrameIndex = (moveFrameIndex == moveAnimationLength-1) ? 0 : moveFrameIndex + 1;           
            }else 
            {
                g.drawImage(moveLeftAnimation[moveFrameIndex], (int) super.x, (int) super.y, (int) super.size, (int) super.size, null);
                moveFrameIndex = (moveFrameIndex == moveAnimationLength-1) ? 0 : moveFrameIndex + 1;   
            }
        }
        else if(mState instanceof Jump){
            moveFrameIndex = 0;
            idleFrameIndex = 0;
            idleFrameDelayCount = 0;
            g.drawImage(jumpAnimation[jumpFrameIndex], (int) super.x, (int) super.y, (int) super.size, (int) super.size, null);
            jumpFrameIndex = (jumpFrameIndex == jumpAnimationLength-1) ? 0 : jumpFrameIndex + 1;
        }
        else
        {
            //If they are standing still we need to reset the frameCounter
            moveFrameIndex = 0;               
            jumpFrameIndex = 0;  
            g.drawImage(idleAnimation[idleFrameIndex], (int) super.x, (int) super.y, (int) super.size, (int) super.size, null);
            
            if (idleFrameDelayCount == 3){
                idleFrameIndex = (idleFrameIndex == idleAnimationLength-1) ? 0 : idleFrameIndex + 1; 
                idleFrameDelayCount = 0;
            }else{
                idleFrameDelayCount++;
            }
        
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
           y += GRAVITY; 
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
