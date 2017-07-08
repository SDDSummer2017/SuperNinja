/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Level;

import Model.Renderable;
import View.GamePanel;
import java.awt.Graphics;

/**
 *
 * @author abilb
 */
public class TitleCard implements Renderable {

    GamePanel gamePanel;
    
    public TitleCard(GamePanel gamePanel)
    {
        this.gamePanel = gamePanel; 
    }
    
    @Override
    public void render(Graphics g) {
         g.drawRect(gamePanel.camera.camX, gamePanel.camera.camY, Main, 0);
    }
    
}
