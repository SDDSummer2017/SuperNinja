/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.Main;
import static Controller.Main.gameData;
import StatusEffects.DamageEffect;
import View.GamePanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import javax.vecmath.Vector2f;

/**
 *
 * @author matlock
 */
public class ProjectileWindmillShuriken extends Projectiles{
    
    public boolean direction;
    public boolean finnished;
    public double secondX;
    public double secondY;
    
    public ProjectileWindmillShuriken(double x, double y, double tx, double ty, Color color, boolean isGoodGuy, int d, double s) {
        super(x, y, tx, ty, color, isGoodGuy, d, s, GameData.windmillResource);
        finnished = false;
        this.targetX = tx;
        this.targetY = ty;
        this.color = color;
        this.damage = 30;
        this.size = 30;
        this.currentLocation = new Vector2f((float)super.x, (float)super.y);
        this.targetPath = new Vector2f((float)targetX,(float)targetY);
        secondY = ty;
        Nen n = Main.gameData.nen;
        targetPath.sub(currentLocation);
        targetPath.scale((float) Math.sqrt((double)(GamePanel.CAMERA_HEIGHT * GamePanel.CAMERA_HEIGHT) *
                (double)(GamePanel.CAMERA_WIDTH * GamePanel.WIDTH)));
        targetPath.normalize();
        targetPath.scale(moveDistance);
        
        hitbox = new HitBox(x, y, (int)super.size * 2, (int)super.size * 2, this, new DamageEffect(this, 10, 5000));
        Main.gameData.addGameData(hitbox);
        if (n.x < this.x){
            direction = true;
        }
        else{
            direction = false;
        }
        
    }
    
    @Override
    public void render(Graphics g) {
        g.setColor(color);
       // g.fillOval((int)x, (int)y, (int)super.size * 2, (int)super.size * 2);
        Image frameImage;

        //Select frame image based on which direction Nen is facing
        frameImage = (isFacingRight) ? runAnimation[moveFrameIndex] : GameFigure.flipImageHorizontally(runAnimation[moveFrameIndex]);

        g.drawImage(frameImage, (int) super.x, (int) super.y, (int) super.size * 2, (int) super.size * 2, null);
        moveFrameIndex = (moveFrameIndex == runAnimation.length-1) ? 0 : moveFrameIndex + 1;
    }
    
    public void setDirection(boolean b){
        this.direction = b;
    }
    
    @Override
    public void update() {       
        Nen n = Main.gameData.nen;
       //System.out.println("Windmill.y = " + super.y);
        currentLocation.add(targetPath);
        
        super.x = currentLocation.x;
        super.y = currentLocation.y;
        hitbox.translate(super.x, super.y);
        
        if (super.y >= secondY){
            if (direction && !finnished){
                this.currentLocation = new Vector2f((float)super.x, (float)super.y);
                this.targetPath = new Vector2f((float)super.x - 500,(float)super.y);
                this.targetPath.sub(currentLocation);
                this.targetPath.scale((float) Math.sqrt((double)(GamePanel.CAMERA_HEIGHT * GamePanel.CAMERA_HEIGHT) *
                (double)(GamePanel.CAMERA_WIDTH * GamePanel.WIDTH)));
                this.targetPath.normalize();
                this.targetPath.scale(moveDistance);
            }
            else if(!direction && !finnished){
                this.currentLocation = new Vector2f((float)super.x, (float)super.y);
                this.targetPath = new Vector2f((float)super.x + 500,(float)super.y);
                this.targetPath.sub(currentLocation);
                this.targetPath.scale((float) Math.sqrt((double)(GamePanel.CAMERA_HEIGHT * GamePanel.CAMERA_HEIGHT) *
                (double)(GamePanel.CAMERA_WIDTH * GamePanel.WIDTH)));
                this.targetPath.normalize();
                this.targetPath.scale(moveDistance);
            }
            else if(finnished){
                super.health = 0;
                Main.gameData.removeGameData(hitbox);
                gameData.removeGameData(this);
            }
            else{}
        }
        
        if (super.x >= n.x + 500 || super.x <= n.x - 500){
            finnished = true;
        }
    }

    @Override
    public Rectangle2D.Double getCollisionBox() {
        return new Rectangle2D.Double(super.x, super.y, super.size * 2, super.size * 2);
    }
    
}
