package Model;

import Controller.Main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import static Model.Marine.getImage;
import View.GamePanel;

public class Mutalisk extends GameFigure {

    private final Image mutaliskImage;
    private int dx;
    private int dy;
    private int updateCount = 0;
    private final Random random;


    public Mutalisk(int x, int y, int size) {
        super(x, y, size);
        this.health = 50;
        this.random = new Random();
        String imagePath = System.getProperty("user.dir");
        String separator = System.getProperty("file.separator");
        mutaliskImage = getImage(imagePath + separator + "images" + separator
                + "Mutalisk.jpg");

    }
    
    public void shoot(){
        double targetX = Main.gameData.marine.x + Main.gameData.marine.size/2;
        double targetY = Main.gameData.marine.y;
        if(random.nextBoolean())
            Main.gameData.addEnemyBullet(super.x + super.size/2, super.y + super.size, targetX, targetY, Color.red);
    
    }
    
    @Override
    public void render(Graphics g) {
        g.drawImage(mutaliskImage, (int)super.x, (int)super.y, (int)super.size, (int)super.size, null);
        
        g.setColor(Color.red);
        g.fillRect((int)x, (int)y, (int)super.size, 2); 
        
        g.setColor(Color.green);
        g.fillRect((int)x, (int)y, (int)health, 2);       
        
    }

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
