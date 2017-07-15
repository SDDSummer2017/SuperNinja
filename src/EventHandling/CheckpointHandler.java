/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventHandling;

import Controller.Animator;
import Controller.Main;
import static Controller.Main.animator;
import static Controller.Main.gameData;
import static Controller.Main.gamePanel;
import Level.Checkpoint;
import Level.NinjaTower;
import Level.NinjaVillage;
import Level.Spawner;
import Level.TitleCard;
import Model.GameData;
import Model.GameFigure;
import Model.Kisara;
import Model.Rai;
import Model.Terro;
import View.GamePanel;
import view.Camera;

/**
 *
 * @author abilb
 */
public class CheckpointHandler implements Observer{

    private GameData gameData;  
     
    public CheckpointHandler(GameData gameData)
    {
        this.gameData = gameData;
    }
    
    @Override
    public void onNotify(String string) {
        if(string.equals("Checkpoint"))
        {
            System.out.println("The player has reached a checkpoint");
            //TODO: Write checkpoint to file or memory location.

        }
        if(string.equals("Level Complete"))
                {
                    //TODO: add to Renderables Title Card
                    
                    gameData.level = new NinjaTower(gameData); 
                    gameData.level.addGameData(gameData.nen);
                    gameData.nen.x = gameData.level.nenStartX;
                    gameData.nen.y = gameData.level.nenStartY;
                    gameData.titleCard = new TitleCard(gamePanel);
                    gameData.notifyObservers("Level Two");
                   
                }
        
    }
    
    public void onNotify(Checkpoint checkpoint)
    {
        
        if(checkpoint instanceof Spawner)
        {
            if(((Spawner)checkpoint).spawned == false){
            if(((Spawner)checkpoint).enemy == Rai.class)
            {
                gameData.level.addGameData(new Rai(((Spawner)checkpoint).x + 35, ((Spawner)checkpoint).y, 100));
            }
            else if(((Spawner)checkpoint).enemy  == Terro.class)
            {
                gameData.level.addGameData(new Terro(((Spawner)checkpoint).x + 35, ((Spawner)checkpoint).y, 100));
            }
            else if(((Spawner)checkpoint).enemy  == Kisara.class)
            {
                gameData.level.addGameData(new Kisara(((Spawner)checkpoint).x + 35, ((Spawner)checkpoint).y, 100));
            }
            }
            ((Spawner)checkpoint).spawned = true; 
        }
         
    }
    
}
