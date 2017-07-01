/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventHandling;

import java.io.File;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author abilb
 */
public class MusicHandler extends ResourceHandler implements Observer, Runnable {
    private String music;
    private JFXPanel fxPanel;
    public MusicHandler(String resourcePath) {
        super(resourcePath);
        fxPanel = new JFXPanel();
    }

    @Override
    public void onNotify(String string) {
        if(string.equals("Level One"))
        {
           music = "SoundEffects/Eternal Terminal.mp3";
        }
    }    
    // cl is the ClassLoader for the current class, ie. CurrentClass.class.getClassLoader();
      
    @Override
    public void run() {
        
        
        if(!music.isEmpty()){
        final Media media = new Media(new File(music).toURI().toString());
        final MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(0.2);
        mediaPlayer.play();}
    }
}

      

