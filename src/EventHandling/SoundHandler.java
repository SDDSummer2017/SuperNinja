/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventHandling;

import Model.GameFigure;
import java.net.URL;
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
    }

    
    //Our onNotify methods take a variety of arguments for different situations.
    //Add conditions inside these and play the appropriate sounds inside theses methods. 
    
    @Override
    public void onNotify(GameFigure gameFigureOne, GameFigure gameFigureTwo) {
         System.out.print("Two objects have collided, and now we will play a sound" );
    }

    @Override
    public void onNotify(State state) {
         System.out.print("An object has changed state, and now we are going to play a sound.");
    }

    @Override
    public void onNotify(String string) {
        System.out.print(string);
    }

    
    // Use this method to play sounds inside the on notify methods. 
    
    private void playSound(String sound)
    {
        
    // cl is the ClassLoader for the current class, ie. CurrentClass.class.getClassLoader();
        URL file = SoundHandler.class.getResource(sound);
        final Media media = new Media(file.toString());
        final MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
}
    
}
