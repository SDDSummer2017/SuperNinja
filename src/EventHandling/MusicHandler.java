/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventHandling;

import Controller.Main;
import java.io.File;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author abilb
 */
public class MusicHandler extends ResourceHandler implements Observer, Runnable {
    boolean flag = false; 
    private String music = "";
    private JFXPanel fxPanel;
    private Media media; 
     AudioClip mediaPlayer;
    public MusicHandler(String resourcePath) {
        super(resourcePath);
        fxPanel = new JFXPanel();
    }

    @Override
    public void onNotify(String string) {
        if(string.equals("Level One"))
        {
           music = "SoundEffects/Eternal Terminal.mp3";
               if(mediaPlayer != null){
                mediaPlayer.stop();
            }
           flag = true; 
           
        }
        else if(string.equals("Level Two"))
                {
                    music = "SoundEffects/Volatile Reaction.mp3";
                        if(mediaPlayer != null){
                        mediaPlayer.stop();
                        }
                        
                    flag = true; 
                    run();
               }
         
         
    }

    
    // cl is the ClassLoader for the current class, ie. CurrentClass.class.getClassLoader();
      
    @Override
    public void run() {
        
        
//        if(!music.isEmpty() && Main.gameData != null && flag == true){
//        media = new Media(new File(music).toURI().toString());
//        mediaPlayer = new AudioClip(media.getSource());
//        mediaPlayer.setVolume(0.1);
//        mediaPlayer.play();
//        flag = false; }
//        else 
        if(flag == true)
        {
            if(mediaPlayer != null){
                mediaPlayer.stop();
            }
            media = new Media(new File(music).toURI().toString());
            mediaPlayer = new AudioClip(media.getSource());
            mediaPlayer.setVolume(0.1);
            mediaPlayer.play(); 
             
        }
       
    }
}

      

