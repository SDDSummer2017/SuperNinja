package Model;

import Controller.Main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import static Model.Nen.getImage;
import View.GamePanel;

public abstract class Enemy extends GameFigure {

    public Image attack1;
    public Image attack2;
    public Image block;
    public Image hit;
    public Image movement;
    public Image neutral;
    public Image is_Thrown;
    
    public int dx;
    public int dy;
    public int updateCount = 0;
    public final Random random;

    /**
     *
     * @param x
     * @param y
     * @param size
     */
    public Enemy(int x, int y, int size) {}
    
    public void shoot(){
        double targetX = Main.gameData.marine.x + Main.gameData.marine.size/2;
        double targetY = Main.gameData.marine.y;
        if(random.nextBoolean())
            Main.gameData.addEnemyBullet(super.x + super.size/2, super.y + super.size, targetX, targetY, Color.red);
    
    }
    
    @Override
    public void render(Graphics g) {}

    @Override
    public void update() {
        // Random movement 
        this.updateCount++;
        if(this.updateCount == 15){
            shoot();
            dx = (random.nextBoolean()) ? 2:-2;
            dy = (random.nextBoolean()) ? 2:-2;
            this.updateCount = 0;
        }
        super.x += dx;
        super.y += dy;

        if (super.x + size > GamePanel.PWIDTH) {
            dx = -dx;
            super.x = GamePanel.PWIDTH - size;
        } else if (super.x - size < 0) {
            dx = -dx;
            super.x = size;
        }

        if (super.y + size > GamePanel.PHEIGHT - 120) {
            dy = -dy;
            super.y = GamePanel.PHEIGHT - 120 - size;
        } else if (super.y - size < 0) {
            dy = -dy;
            super.y = size;
        }
    }

    @Override
    public Rectangle2D.Double getCollisionBox() {
        return new Rectangle2D.Double(super.x, super.y, super.size, super.size);
    }
}
