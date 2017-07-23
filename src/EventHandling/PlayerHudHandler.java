/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventHandling;

import Controller.Main;
import static Controller.Main.gamePanel;
import Level.NinjaTower;
import Level.NinjaVillage;
import Level.TitleCard;
import Model.GameData;
import Model.Nen;
import Model.States.Nen.Death;
import Model.States.Nen.NeutralCombat;
import View.HealthBar;
import java.util.ArrayList;

/**
 *
 * @author abilb
 */
public class PlayerHudHandler implements PlayerObserver {
    
    private GameData gameData; 
    private Nen nen; 
    private HealthBar healthBar;
    public PlayerHudHandler(GameData gameData, Nen nen)
    {
        this.gameData = gameData; 
        this.nen = nen; 
        this.healthBar = new HealthBar(Main.gamePanel); 
        gameData.addGameData(healthBar);
    }

    @Override
    public void onNotify(Nen nen) {
            
            if(!gameData.level.renderables.contains(healthBar)){gameData.addGameData(healthBar);} 
        
            if(nen.cState instanceof Death && nen.deathFrameIndex >= 9)
            {
                    
                     if(gameData.level instanceof NinjaVillage)
                     {
                         gameData.level = new NinjaVillage(gameData);
                     }
                     else if(gameData.level instanceof NinjaTower)
                     {
                         gameData.level = new NinjaTower(gameData);
                     }
                    gameData.level.addGameData(gameData.nen);
                    gameData.nen.x = gameData.level.nenStartX;
                    gameData.nen.y = gameData.level.nenStartY - 100;
                    nen.health = 100;
                    ArrayList<Observer> o = new ArrayList<Observer>();
                    o.add(new SoundHandler(""));
                    nen.cState = new NeutralCombat(nen, o); 
                    gameData.titleCard = new TitleCard(Main.gamePanel, "Game Over!");
                    gameData.addGameData(healthBar);
                  
                 
                    
            }
            else
            {
                healthBar.setHealth((int) nen.health);
            }
    }

    @Override
    public void onNotify(String string) {

    }
    
    
    
    
}
