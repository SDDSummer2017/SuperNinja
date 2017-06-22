/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import static Model.Nen.getImage;
import Model.States.CombatState;
import Model.States.Rai_States.Movement;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author matlock
 */
public class Rai extends Enemy {
    
    public Rai(int x, int y, int size) {
        super(x, y, size);
        super.state = "Neutral";
        this.health = 120;
        String imagePath = System.getProperty("user.dir");
        String separator = System.getProperty("file.separator");
        super.attack1 = getImage(imagePath + separator + "images" + separator
                + "Rai_ViperStrike.png");
        super.attack2 = getImage(imagePath + separator + "images" + separator
                + "Rai_SteelTwister.png");
        super.movement = getImage(imagePath + separator + "images" + separator
                + "Rai_Movement.png");
        super.block = getImage(imagePath + separator + "images" + separator
                + "Rai_Block.png");
        super.neutral = getImage(imagePath + separator + "images" + separator
                + "Rai_Neutral.png");
        super.throwImage = getImage(imagePath + separator + "images" + separator
                + "Rai_Throw.png");
        super.staticImage = getImage(imagePath + separator + "images" + separator
                + "Rai_Static.png");
    }
    
    @Override
    public void render(Graphics g) {
    if (super.cState instanceOf Movement){}
    }
    
    @Override
    public void changeState(){}

    @Override
    public void update() {}

    @Override
    public Rectangle2D.Double getCollisionBox() {
        return new Rectangle2D.Double(super.x, super.y, super.size, super.size);
    }
}
