/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import EventHandling.Observer;
import Model.States.Sage.Chant;
import Model.States.Sage.NeutralMotion;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author matlock
 */
public class Sage extends Enemy {
    public int c; // used as a display count for placeholders only
    
    public Sage(double x, double y, double size) {
        super(x, y, size);
        
        ArrayList<Observer> observers = new ArrayList<>();
        super.mState = new NeutralMotion(this, observers);
        super.cState = new Chant(this, observers);
        this.health = 70; 
        
        String imagePath = System.getProperty("user.dir");
        String separator = System.getProperty("file.separator");
        super.attack1 = getImage(imagePath + separator + "images" + separator
                + "ViperStrike.png");
        super.attack2 = getImage(imagePath + separator + "images" + separator
                + "SteelTwister.png");
        super.movement = getImage(imagePath + separator + "images" + separator
                + "Movement.png");
        super.block = getImage(imagePath + separator + "images" + separator
                + "Block.png");
        super.neutral = getImage(imagePath + separator + "images" + separator
                + "Neutral.png");
        super.throwImage = getImage(imagePath + separator + "images" + separator
                + "Throw.png");
        super.staticImage = getImage(imagePath + separator + "images" + separator
                + "Static.png");
    }
    
    @Override
    public void render(Graphics g) {
        g.drawImage(image, (int) super.x, (int) super.y, (int) super.size, (int) super.size, null);
       super.render(g); 
    }

    @Override
    public void update() {
        super.update();  
        mState.execute();
        cState.execute();
    }

    @Override
    public Rectangle2D.Double getCollisionBox() {
        return new Rectangle2D.Double(super.x, super.y, super.size, super.size);
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
     
}
