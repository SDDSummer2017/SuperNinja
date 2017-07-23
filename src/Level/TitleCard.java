/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Level;

import Controller.Main;
import Model.Renderable;
import View.GamePanel;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author abilb
 */
public class TitleCard implements Renderable {

    GamePanel gamePanel;
    String message; 
    public int iterable = 0;
    public TitleCard(GamePanel gamePanel, String message)
    {
        this.gamePanel = gamePanel; 
        this.message = message;
    }
    
    @Override
    public void render(Graphics g) {
         
      
         g.setColor(Color.BLACK);
         g.fillRect(gamePanel.camera.camX, gamePanel.camera.camY,  gamePanel.getWidth(), gamePanel.getHeight()); 
         g.setColor(Color.yellow);
         g.drawString(message, gamePanel.camera.camX + (gamePanel.getWidth()/2), gamePanel.camera.camY  + (gamePanel.getHeight()/2));
         iterable++;
         
           
    }
    
}
