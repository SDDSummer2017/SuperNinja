/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Main;
import Model.Nen;
import Model.Renderable;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author abilb
 */
public class HealthBar implements Renderable{

    private int x;
    private int y;
    private int health; 
    private GamePanel gamePanel;  
    public HealthBar(GamePanel gamePanel)
    {
        x = 35;
        y = 35; 
        this.gamePanel = gamePanel;
        
    }
   
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHealth() {
   
        return health;
        
    }

    public void setHealth(int health) {
        if(gamePanel != null)
        {
            
              y = gamePanel.camera.camY;
              x = gamePanel.camera.camX;
        }
        else{gamePanel = Main.gamePanel;}
      
        this.health = health;
    }
   
            
    @Override
    public void render(Graphics g) {
        g.setColor(Color.magenta);

        g.fillRect(x, y, 8 * health, 10);
    }
    
    
}
