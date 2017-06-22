/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import static Model.Nen.getImage;

/**
 *
 * @author matlock
 */
public class Rai extends Enemy {
    
    public Rai(int x, int y, int size) {
        super(x, y, size);
        this.health = 120;
        String imagePath = System.getProperty("user.dir");
        String separator = System.getProperty("file.separator");
        super.attack1 = getImage(imagePath + separator + "images" + separator
                + "Rai_ViperStrike.png");
    }
    
}
