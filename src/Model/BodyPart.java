package Model;


import Controller.Main;
import Model.HitBox;
import Model.Projectiles;
import StatusEffects.DamageEffect;
import StatusEffects.DotEffect;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Rectangle2D;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Garrett A. Clement
 */
public class BodyPart extends Projectiles {
    
    public BodyPart(double x, double y, double tx, double ty, Color color, boolean isGoodGuy, int d, double s) {
        super(x, y, tx, ty, color, isGoodGuy, d, s, GameData.fireballResource);
        hitbox = new HitBox(x, y, 50, 50, this, new DamageEffect(this, 30, 5000), new DotEffect(this, 2, 5000, 1000));
      
    } 
    
    @Override
    public void update(){
        
    }
    
    @Override
    public void render(Graphics g)
    {
        g.setColor(color); 
//        g.fillOval((int)x, (int)y, (int)(super.size), (int)(super.size));
        Image frameImage;

        //Select frame image based on which direction Nen is facing
        frameImage = (isFacingRight) ? runAnimation[moveFrameIndex] : GameFigure.flipImageHorizontally(runAnimation[moveFrameIndex]);

        g.drawImage(frameImage, (int) (int)(x - super.size), (int) (y - super.size/2), (int) super.size * 2, (int) super.size * 2, null);
        moveFrameIndex = (moveFrameIndex == runAnimation.length-1) ? 0 : moveFrameIndex + 1;
    }
    
    public void translate(double x, double y){
        this.x = x;
        this.y =y;
        hitbox.translate(x, y);    }
    
    @Override
    public Rectangle2D.Double getCollisionBox() {
        return hitbox.getCollisionBox();
    }
   
}
