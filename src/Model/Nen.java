package Model;

import EventHandling.Observer;
import Model.States.Nen.NeutralCombat;
import Model.States.Nen.NeutralMotion;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import View.GamePanel;
import EventHandling.SoundHandler; 
import Model.States.Nen.Move;
import Model.States.Nen.Jump;
 
import Model.States.Nen.LightAttack;
//import Model.States.Nen.HeavyAttack;
import Physics.Velocity;
import java.util.ArrayList;
public class Nen extends GameFigure {
   
    private int jumpHeight = 0;
    private int dy = -7;
    private final SoundHandler soundHandler = new SoundHandler("");
 
    
    private int moveFrameIndex , idleFrameIndex, idleFrameDelayCount, jumpFrameIndex;
    private  int moveAnimationLength, idleAnimationLength, jumpAnimationLength;
    public boolean jump, movingLeft, movingRight;
    
 
    public Nen(int x, int y, int size) {
        super(x, y, size,
                10,  //move animation length
                10,  //idle animation length
                10,  //jump animation length
                10,  //attack right
                10,  //attack left
                "Nen"); //name for animation image file path
        this.health = 100;
        mass = 60; 
        velocity = new Velocity();
        velocity.dx = 0;
        velocity.dy = 0;
        forces = new ArrayList<>();
        ArrayList<Observer> observers = new ArrayList<Observer>();
        mState = new NeutralMotion(this, observers);
        cState = new NeutralCombat(this, observers);
       
     
        mState.registerObserver(soundHandler);
        cState.registerObserver(soundHandler);
 
        movingRight = jump = movingLeft = false;
        String imagePath = System.getProperty("user.dir");
        // separator: Windows '\', Linux '/'
        String separator = System.getProperty("file.separator");

        staticImage = GameFigure.getImage(imagePath + separator + "images" + separator
                + "Nen.png");

        
    }

    @Override
    public void render(Graphics g) {
        
        if(cState instanceof LightAttack){
            moveFrameIndex = 0;
            idleFrameIndex = 0;
            idleFrameDelayCount = 0;
            jumpFrameIndex = 0;
            if (isFacingRight){
                g.drawImage(lightAttackRightAnimation[attackFrameIndex], (int) super.x, (int) super.y, (int) super.size, (int) super.size, null);
                attackFrameIndex = (attackFrameIndex == lightAttackRightAnimation.length-1) ? 0 : attackFrameIndex + 1;
            }else
            {
                g.drawImage(lightAttackLeftAnimation[attackFrameIndex], (int) super.x, (int) super.y, (int) super.size, (int) super.size, null);
                attackFrameIndex = (attackFrameIndex == lightAttackLeftAnimation.length-1) ? 0 : attackFrameIndex + 1;
            }                     
        }
//        else if(cState instanceof HeavyAttack){
//            moveFrameIndex = 0;
//            idleFrameIndex = 0;
//            idleFrameDelayCount = 0;
//            jumpFrameIndex = 0;
//            if (isFacingRight){
//                g.drawImage(heavyAttackRightAnimation[attackFrameIndex], (int) super.x, (int) super.y, (int) super.size, (int) super.size, null);
//                attackFrameIndex = (attackFrameIndex == heavyAttackRightAnimation.length-1) ? 0 : attackFrameIndex + 1;
//            }else
//            {
//                g.drawImage(heavyAttackLeftAnimation[attackFrameIndex], (int) super.x, (int) super.y, (int) super.size, (int) super.size, null);
//                attackFrameIndex = (attackFrameIndex == heavyAttackLeftAnimation.length-1) ? 0 : attackFrameIndex + 1;
//            }                     
//        }
        else if(mState instanceof Move)
        {
            //If we are moving, reset the idle animtion frame index
            idleFrameIndex = 0;
            jumpFrameIndex = 0;
            idleFrameDelayCount = 0;
            attackFrameIndex = 0;
            if(isFacingRight){
                g.drawImage(moveRightAnimation[moveFrameIndex], (int) super.x, (int) super.y, (int) super.size, (int) super.size, null);
                //If we've reached the end of the animation reset frameCounter to zero, otherwise increment it.
                moveFrameIndex = (moveFrameIndex == moveRightAnimation.length-1) ? 0 : moveFrameIndex + 1;           
            }else 
            {
                g.drawImage(moveLeftAnimation[moveFrameIndex], (int) super.x, (int) super.y, (int) super.size, (int) super.size, null);
                moveFrameIndex = (moveFrameIndex == moveLeftAnimation.length-1) ? 0 : moveFrameIndex + 1;   
            }
        }
        else if(mState instanceof Jump){
            moveFrameIndex = 0;
            idleFrameIndex = 0;
            idleFrameDelayCount = 0;
            attackFrameIndex = 0;
            g.drawImage(jumpAnimation[jumpFrameIndex], (int) super.x, (int) super.y, (int) super.size, (int) super.size, null);
            jumpFrameIndex = (jumpFrameIndex == jumpAnimation.length-1) ? 0 : jumpFrameIndex + 1;
        }
        else
        {
            //If they are standing still we need to reset the frameCounter
            moveFrameIndex = 0;               
            jumpFrameIndex = 0;
            attackFrameIndex = 0;
            g.drawImage(idleAnimation[idleFrameIndex], (int) super.x, (int) super.y, (int) super.size, (int) super.size, null);
            
            if (idleFrameDelayCount == 1){
                idleFrameIndex = (idleFrameIndex == idleAnimation.length-1) ? 0 : idleFrameIndex + 1; 
                idleFrameDelayCount = 0;
            }else{
                idleFrameDelayCount++;
            }
        
        }

        g.setColor(Color.red);
        g.fillRect(3, GamePanel.CAMERA_HEIGHT - 102, 10, 100);

        g.setColor(Color.green);
        g.fillRect(3, GamePanel.CAMERA_HEIGHT - (int) this.health - 2, 10, (int) this.health);
    }
    
    @Override
    public void update() {
 
        if(cState instanceof NeutralCombat)
            mState.execute();
        else
            cState.execute();
       
        
    }

 
 
 
    public void translate(int dx, int dy) {
        if (super.x <= 0 && dx < 0) {
            dx = 0;
        }
        if (((super.x + super.size) >= GamePanel.CAMERA_WIDTH) && (dx > 0)) {
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
        super.y = GamePanel.CAMERA_HEIGHT - super.size;
        super.x = GamePanel.CAMERA_WIDTH/2;
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
