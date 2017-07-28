/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import static Model.GameFigure.getImage;
import java.awt.Image;
import java.io.File;

/**
 *
 * @author abilb
 */
public class ImageResource {
    public  Image[] runAnimation, idleAnimation, crouchAnimation, jumpAnimation, dashAnimation, deathAnimation; 
    public  Image[] lightAttackAnimation, heavyAttackAnimation, rangeAttackAnimation;
    public  Image[] specialAttack1Animation, specialAttack2Animation;
    
    public ImageResource(String name, int animationLength)
    {
        this.deathAnimation = new Image[animationLength];
        this.runAnimation = new Image[animationLength];
        this.dashAnimation = new Image[animationLength];
        this.lightAttackAnimation = new Image[animationLength];
        this.heavyAttackAnimation = new Image[animationLength];
        this.rangeAttackAnimation = new Image[animationLength];
        this.specialAttack1Animation = new Image[animationLength];
        this.specialAttack2Animation = new Image[animationLength];
        this.idleAnimation = new Image[animationLength];
        this.jumpAnimation = new Image[animationLength];
        this.crouchAnimation = new Image[animationLength];
        loadAnimations(name);
    }
    private void loadAnimations(String name){
        String baseDirectory = System.getProperty("user.dir");
        String separator = System.getProperty("file.separator");
        String imagePath = baseDirectory + separator + "images" + separator + name;
        

        String movePath = imagePath + separator + "Run";
        String idlePath = imagePath + separator + "Idle";
        String crouchPath = imagePath + separator + "Crouch";
        String dashPath = imagePath + separator + "Dash"; 
        String jumpPath = imagePath + separator + "Jump";
        String deathPath = imagePath + separator + "Death";
        String lightAttackPath = imagePath + separator + "Light_Attack";
        String heavyAttackPath = imagePath + separator + "Heavy_Attack";
        String rangeAttackPath = imagePath + separator + "Range_Attack";
        String specialAttack1Path = imagePath + separator + "Special_Attack1";
        String specialAttack2Path = imagePath + separator + "Special_Attack2";
        
        
        Image img;
        //Move Animation
        if (fileExists(movePath)){        
            for(int i=0;i<runAnimation.length;i++){
                runAnimation[i] = getImage(movePath + separator + "Run_" + i + ".png");          
            }
        }
        
        //Idle Animation 
        if (fileExists(idlePath)){
            for(int i=0;i<idleAnimation.length;i++){
                idleAnimation[i] = getImage(idlePath + separator + "Idle_" + i + ".png");
            } 
        }
        
        //Crouch Animation 
        if (fileExists(crouchPath)){
            for(int i=0;i<crouchAnimation.length;i++){
                crouchAnimation[i] = getImage(crouchPath + separator + "Crouch_" + i + ".png");
            } 
        }
        
        //Evade Animation 
        if (fileExists(dashPath)){
            for(int i=0;i<dashAnimation.length;i++){
                dashAnimation[i] = getImage(dashPath + separator +  "Dash_" + i + ".png");
            }
        }
        //Jump Animation
        if(fileExists(jumpPath)){
            for(int i=0;i<jumpAnimation.length;i++){
                jumpAnimation[i] = getImage(jumpPath + separator + "Jump_" + i + ".png");
            }
        }
        //Light Attack Animation
        if (fileExists(lightAttackPath)){
            for(int i=0;i<lightAttackAnimation.length;i++){
                lightAttackAnimation[i] = getImage(lightAttackPath + separator + "Light_Attack_" + i + ".png");
            }
        }
        //Heavy Attack Right Animation
        if (fileExists(heavyAttackPath)){
            for(int i=0;i<heavyAttackAnimation.length;i++){
                heavyAttackAnimation[i] = getImage(heavyAttackPath + separator + "Heavy_Attack_" + i + ".png");
            }  
        }
        //Ranged Attack Animation
        if (fileExists(rangeAttackPath)){
            for(int i=0;i<rangeAttackAnimation.length;i++){
                rangeAttackAnimation[i] = getImage(rangeAttackPath + separator + "Range_Attack_" + i + ".png");
            }
        }
        //Special Attack 1 Animation
        if (fileExists(specialAttack1Path)){
            for(int i=0;i<specialAttack1Animation.length;i++){
                specialAttack1Animation[i] = getImage(specialAttack1Path + separator + "Special_Attack1_" + i + ".png");
            }
        }
        //Special Attack 2 Animation
        if (fileExists(specialAttack2Path)){
            for(int i=0;i<specialAttack2Animation.length;i++){
                specialAttack2Animation[i] = getImage(specialAttack2Path + separator + "Special_Attack2_" + i + ".png");
            }
        }
        //Death Animation
        if (fileExists(deathPath)){
            for(int i=0;i<deathAnimation.length;i++){
                deathAnimation[i] = getImage(deathPath + separator + "Dead_" + i + ".png");
            }
        }
    }
    
  public boolean fileExists(String path){
        return new File(path).exists();
    }
    
}
