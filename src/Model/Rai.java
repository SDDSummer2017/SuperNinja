/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import static Model.Nen.getImage;
import Model.States.CombatState;
import Model.States.Rai_States.Movement;
import static com.sun.org.apache.bcel.internal.Repository.instanceOf;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author matlock
 */
public class Rai extends Enemy {
    
    public Rai(double x, double y, double size) {
        super(x, y, size);
        super.mstate = Model.States.Rai_States.Neutral;
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
        
    }
    
    
}
