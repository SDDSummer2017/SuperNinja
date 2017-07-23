/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author drose
 */
public class AnimationStore {
    
    public final int animationLength = 8;
    
    //Nen
    public final Image[] runAnimation_Nen, idleAnimation_Nen, jumpAnimation_Nen, dashAnimation_Nen, deathAnimation_Nen; 
    public final Image[] lightAttackAnimation_Nen, heavyAttackAnimation_Nen, rangeAttackAnimation_Nen;
    public final Image[] specialAttack1Animation_Nen, specialAttack2Animation_Nen;    
    //Rai
    public final Image[] runAnimation_Rai, idleAnimation_Rai, deathAnimation_Rai; 
    public final Image[] lightAttackAnimation_Rai, heavyAttackAnimation_Rai, rangeAttackAnimation_Rai;
    //Kisara
    public final Image[] runAnimation_Kisara, idleAnimation_Kisara, deathAnimation_Kisara, jumpAnimation_Kisara; 
    public final Image[] lightAttackAnimation_Kisara, heavyAttackAnimation_Kisara, rangeAttackAnimation_Kisara;
    //Sage
    public final Image[] runAnimation_Sage, idleAnimation_Sage, deathAnimation_Sage; 
    public final Image[] lightAttackAnimation_Sage, heavyAttackAnimation_Sage, rangeAttackAnimation_Sage;
    //Terro
    public final Image[] runAnimation_Terro, idleAnimation_Terro, deathAnimation_Terro, jumpAnimation_Terro; 
    public final Image[] lightAttackAnimation_Terro, heavyAttackAnimation_Terro, rangeAttackAnimation_Terro;
    //Projectiles
    public final Image[] shurikenAnimation, windmillShurikenAnimation, fireBallAnimation, earthShatterAnimation, kunaiAnimation;
    
    
    public AnimationStore(){
        //Nen
        String name = "Nen";        
        this.runAnimation_Nen = loadAnimations(name, "Run");
        this.jumpAnimation_Nen = loadAnimations(name, "Jump");
        this.idleAnimation_Nen = loadAnimations(name, "Idle");
        this.deathAnimation_Nen = loadAnimations(name, "Dead");
        this.lightAttackAnimation_Nen = loadAnimations(name, "Light_Attack");
        this.heavyAttackAnimation_Nen = loadAnimations(name, "HeavyAttack");
        this.rangeAttackAnimation_Nen = loadAnimations(name, "Range_Attack");
        this.dashAnimation_Nen = loadAnimations(name, "Dash");
        this.specialAttack1Animation_Nen = loadAnimations(name, "Special_Attack1");
        this.specialAttack2Animation_Nen = loadAnimations(name, "Special_Attack2");
        //Rai
        name = "Rai";        
        this.runAnimation_Rai = loadAnimations(name, "Run");
        this.idleAnimation_Rai = loadAnimations(name, "Idle");
        this.deathAnimation_Rai = loadAnimations(name, "Dead");
        this.lightAttackAnimation_Rai = loadAnimations(name, "Light_Attack");
        this.heavyAttackAnimation_Rai = loadAnimations(name, "HeavyAttack");
        this.rangeAttackAnimation_Rai = loadAnimations(name, "Range_Attack");
        //Kisara
        name = "Kisara";        
        this.runAnimation_Kisara = loadAnimations(name, "Run");
        this.jumpAnimation_Kisara = loadAnimations(name, "Jump");
        this.idleAnimation_Kisara = loadAnimations(name, "Idle");
        this.deathAnimation_Kisara = loadAnimations(name, "Dead");
        this.lightAttackAnimation_Kisara = loadAnimations(name, "Light_Attack");
        this.heavyAttackAnimation_Kisara = loadAnimations(name, "HeavyAttack");
        this.rangeAttackAnimation_Kisara = loadAnimations(name, "Range_Attack");
        //Sage
        name = "Sage";        
        this.runAnimation_Sage = loadAnimations(name, "Run");
        this.idleAnimation_Sage = loadAnimations(name, "Idle");
        this.deathAnimation_Sage = loadAnimations(name, "Dead");
        this.lightAttackAnimation_Sage = loadAnimations(name, "Light_Attack");
        this.heavyAttackAnimation_Sage = loadAnimations(name, "HeavyAttack");
        this.rangeAttackAnimation_Sage = loadAnimations(name, "Range_Attack");
        //Terro
        name = "Terro";        
        this.runAnimation_Terro = loadAnimations(name, "Run");
        this.jumpAnimation_Terro = loadAnimations(name, "Jump");
        this.idleAnimation_Terro = loadAnimations(name, "Idle");
        this.deathAnimation_Terro = loadAnimations(name, "Dead");
        this.lightAttackAnimation_Terro = loadAnimations(name, "Light_Attack");
        this.heavyAttackAnimation_Terro = loadAnimations(name, "HeavyAttack");
        this.rangeAttackAnimation_Terro = loadAnimations(name, "Range_Attack");
        //Projectiles
        this.shurikenAnimation = loadAnimations("Shuriken", "Run");
        this.windmillShurikenAnimation = loadAnimations("WindMillShuriken", "Run");
        this.fireBallAnimation = loadAnimations("Fireball", "Run");
        this.earthShatterAnimation = loadAnimations("EarthShatter", "Run");
        this.kunaiAnimation = loadAnimations("Kunai", "Run");
    }
    private Image[] loadAnimations(String name, String animationType){
        
        Image[] animation = new Image[animationLength];
        String baseDirectory = System.getProperty("user.dir");
        String separator = System.getProperty("file.separator");
        String imagePath = baseDirectory + separator + "images" + separator + name + separator + "animationType";
        
        //Move Animation
        if (fileExists(imagePath)){        
            for(int i=0;i<animation.length;i++){
                animation[i] = getImage(imagePath + separator + animationType + "_" + i + ".png");          
            }
        }
        return animation;
    }
    public boolean fileExists(String path){
        return new File(path).exists();
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
