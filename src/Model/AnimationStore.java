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
    public static Image[] runAnimation_Nen, idleAnimation_Nen, jumpAnimation_Nen, dashAnimation_Nen, deathAnimation_Nen; 
    public static Image[] lightAttackAnimation_Nen, heavyAttackAnimation_Nen, rangeAttackAnimation_Nen;
    public static Image[] specialAttack1Animation_Nen, specialAttack2Animation_Nen;    
    //Rai
    public static Image[] runAnimation_Rai, idleAnimation_Rai, deathAnimation_Rai; 
    public static Image[] lightAttackAnimation_Rai, heavyAttackAnimation_Rai, rangeAttackAnimation_Rai;
    //Kisara
    public static Image[] runAnimation_Kisara, idleAnimation_Kisara, deathAnimation_Kisara, jumpAnimation_Kisara; 
    public static Image[] lightAttackAnimation_Kisara, heavyAttackAnimation_Kisara, rangeAttackAnimation_Kisara;
    //Sage
    public static Image[] runAnimation_Sage, idleAnimation_Sage, deathAnimation_Sage; 
    public static Image[] lightAttackAnimation_Sage, heavyAttackAnimation_Sage, rangeAttackAnimation_Sage;
    //Terro
    public static Image[] runAnimation_Terro, idleAnimation_Terro, deathAnimation_Terro, jumpAnimation_Terro; 
    public static Image[] lightAttackAnimation_Terro, heavyAttackAnimation_Terro, rangeAttackAnimation_Terro;
    //Projectiles
    public static Image[] shurikenAnimation, windmillShurikenAnimation, fireBallAnimation, earthShatterAnimation, kunaiAnimation;
    
    
    public AnimationStore(){
        //Nen
        String name = "Nen";        
        AnimationStore.runAnimation_Nen = loadAnimations(name, "Run");
        AnimationStore.jumpAnimation_Nen = loadAnimations(name, "Jump");
        AnimationStore.idleAnimation_Nen = loadAnimations(name, "Idle");
        
//        AnimationStore.deathAnimation_Nen = new Image[animationLength];
//        fillAnimations(AnimationStore.deathAnimation_Nen, name, "Dead");
        
        AnimationStore.deathAnimation_Nen = loadAnimations(name, "Dead");
        AnimationStore.lightAttackAnimation_Nen = loadAnimations(name, "Light_Attack");
        AnimationStore.heavyAttackAnimation_Nen = loadAnimations(name, "HeavyAttack");
        AnimationStore.rangeAttackAnimation_Nen = loadAnimations(name, "Range_Attack");
        AnimationStore.dashAnimation_Nen = loadAnimations(name, "Dash");
        AnimationStore.specialAttack1Animation_Nen = loadAnimations(name, "Special_Attack1");
        AnimationStore.specialAttack2Animation_Nen = loadAnimations(name, "Special_Attack2");
        //Rai
        name = "Rai";        
        AnimationStore.runAnimation_Rai = loadAnimations(name, "Run");
        AnimationStore.idleAnimation_Rai = loadAnimations(name, "Idle");
        AnimationStore.deathAnimation_Rai = loadAnimations(name, "Dead");
        AnimationStore.lightAttackAnimation_Rai = loadAnimations(name, "Light_Attack");
        AnimationStore.heavyAttackAnimation_Rai = loadAnimations(name, "HeavyAttack");
        AnimationStore.rangeAttackAnimation_Rai = loadAnimations(name, "Range_Attack");
        //Kisara
        name = "Kisara";        
        AnimationStore.runAnimation_Kisara = loadAnimations(name, "Run");
        AnimationStore.jumpAnimation_Kisara = loadAnimations(name, "Jump");
        AnimationStore.idleAnimation_Kisara = loadAnimations(name, "Idle");
        AnimationStore.deathAnimation_Kisara = loadAnimations(name, "Dead");
        AnimationStore.lightAttackAnimation_Kisara = loadAnimations(name, "Light_Attack");
        AnimationStore.heavyAttackAnimation_Kisara = loadAnimations(name, "HeavyAttack");
        AnimationStore.rangeAttackAnimation_Kisara = loadAnimations(name, "Range_Attack");
        //Sage
        name = "Sage";        
        AnimationStore.runAnimation_Sage = loadAnimations(name, "Run");
        AnimationStore.idleAnimation_Sage = loadAnimations(name, "Idle");
        AnimationStore.deathAnimation_Sage = loadAnimations(name, "Dead");
        AnimationStore.lightAttackAnimation_Sage = loadAnimations(name, "Light_Attack");
        AnimationStore.heavyAttackAnimation_Sage = loadAnimations(name, "HeavyAttack");
        AnimationStore.rangeAttackAnimation_Sage = loadAnimations(name, "Range_Attack");
        //Terro
        name = "Terro";        
        AnimationStore.runAnimation_Terro = loadAnimations(name, "Run");
        AnimationStore.jumpAnimation_Terro = loadAnimations(name, "Jump");
        AnimationStore.idleAnimation_Terro = loadAnimations(name, "Idle");
        AnimationStore.deathAnimation_Terro = loadAnimations(name, "Dead");
        AnimationStore.lightAttackAnimation_Terro = loadAnimations(name, "Light_Attack");
        AnimationStore.heavyAttackAnimation_Terro = loadAnimations(name, "HeavyAttack");
        AnimationStore.rangeAttackAnimation_Terro = loadAnimations(name, "Range_Attack");
        //Projectiles
        AnimationStore.shurikenAnimation = loadAnimations("Shuriken", "Run");
        AnimationStore.windmillShurikenAnimation = loadAnimations("WindMillShuriken", "Run");
        AnimationStore.fireBallAnimation = loadAnimations("Fireball", "Run");
        AnimationStore.earthShatterAnimation = loadAnimations("EarthShatter", "Run");
        AnimationStore.kunaiAnimation = loadAnimations("Kunai", "Run");
        
        System.out.println("Animation_Store Loaded");
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
        System.out.println("Loaded: " + name + ", " + animationType);
        return animation;
    }
    private void fillAnimations(Image[] animation, String name, String animationType){
        
        String baseDirectory = System.getProperty("user.dir");
        String separator = System.getProperty("file.separator");
        String imagePath = baseDirectory + separator + "images" + separator + name + separator + animationType;
        
        //Move Animation     
        for(int i=0;i<animation.length;i++){
            animation[i] = getImage(imagePath + separator + animationType + "_" + i + ".png");          
        }
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
