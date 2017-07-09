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
import Level.NinjaVillage;
import Level.TitleCard;
import Model.GameData;
import Model.GameFigure;
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
                    
                    gameData.level = new NinjaVillage(gameData); 
                    gameData.level.addGameData(gameData.nen);
                    // (((Renderable) new TitleCard(Main.gamePanel)));
                   
                }
        
    }
    
}
