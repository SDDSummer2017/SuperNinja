/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.Main;  
import StatusEffects.DamageEffect;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Garrett A. Clement
 */

public class ShockWave extends GameFigure {

    private static final int SHATTER_DISTANCE = 300;
    private static final int SHATTER_HIGHT = 300;
    private static final int SHATTER_GROWTH = 0;
    private boolean isRising;
    private int shatter_x;
    private int shatter_y;
    private int shatter_h;
    private int shatter_w;
    private int cumulativeDistance;
    private int direction;
    private boolean isFinished;
    
    public ShockWave(double x, double y, int width, int height, int direction, boolean isGoodGuy) {
        super(x, y, width + height, isGoodGuy);
        shatter_x = (int)x;
        shatter_y = (int)y;
        shatter_h = height;
        shatter_w = width;
        this.direction = direction;
        hitbox =  new HitBox(shatter_x, shatter_y, shatter_w, shatter_h, this, new DamageEffect(this, 40 ,5000));
     //  cState = new Shattering(this, null,shatter_x, shatter_y, shatter_w, shatter_h, direction);
      
        Main.gameData.addGameData(hitbox);
    }
    
    @Override
    public void update(){
         
        shatter_x += 10 * direction;
        cumulativeDistance += 10; 

        if(SHATTER_DISTANCE/2 == cumulativeDistance)
            isRising = true;
        else if(shatter_h <= SHATTER_HIGHT && !isRising)
        {
            shatter_h += 20;
            shatter_y -= 20;
        }else
        {
            shatter_y += 20;
            shatter_h -= 20;
        }
        
        if(cumulativeDistance <= SHATTER_DISTANCE)
        { 
            hitbox.translate(shatter_x, shatter_y, shatter_w, shatter_h);
        }else{
            Main.gameData.removeGameData(hitbox);
            isFinished = true;
        }
    }
    
    public boolean isFinished(){
        return isFinished;
    }

    //May need to be changed
    @Override
    public Rectangle2D.Double getCollisionBox() { 
        return hitbox.getCollisionBox();
    }

    @Override
    public void render(Graphics g) { 
    }
    
}
