/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventHandling;

import Model.GameFigure;
import Model.States.State;
import java.io.File;
import java.net.URL;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
  
/**
 *
 * @author abilb
 */
public class SoundHandler extends ResourceHandler implements CollisionObserver, StateObserver{
    //Must find resource path. This may change later if we impliment a flywieght pattern, in which case the resource paths may 
    //may reference an in-memory location. 
    
    public SoundHandler(String resourcePath) {
        super(resourcePath);
        JFXPanel fxPanel = new JFXPanel();
    }

    
    //Our onNotify methods take a variety of arguments for different situations.
    //Add conditions inside these and play the appropriate sounds inside theses methods. 
    
    @Override
    public void onNotify(GameFigure gameFigureOne, GameFigure gameFigureTwo) {
         System.out.println("Two objects have collided, and now we will play a sound" );
    }

    @Override
    public void onNotify(State state) {
         System.out.println("An object has entered the: " + state.getClass().toString() + " state: playing a sound");
         
//         if(state.getClass() == Model.States.Nen.Move.class){
//         this.playSound("SoundEffects/Footsteps.WAV");
//         }
//         
         if(state.getClass() == Model.States.Nen.Jump.class){
         this.playSound("SoundEffects/Ninja Jump 1.WAV");}
         
         if(state.getClass() == Model.States.Nen.LightAttack.class){
         this.playSound("SoundEffects/ninja_whoosh.mp3");}
//         
//         if(state.getClass() == Model.States.Nen.NeutralCombat.class){
//         this.playSound("SoundEffects/Breath.WAV");}
//         
         
    
    }

    @Override
    public void onNotify(String string) {
        System.out.println(string);
        
        if("landed".equals(string))
        {
            this.playSound("SoundEffects/ninja_damage.mp3");
        }
        
    }

    
    // Use this method to play sounds inside the on notify methods. 
    
    private void playSound(String sound)
    {
        
    // cl is the ClassLoader for the current class, ie. CurrentClass.class.getClassLoader();
     
        final Media media = new Media(new File(sound).toURI().toString());
        final MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(0.2);
        mediaPlayer.play();
}

     
}
