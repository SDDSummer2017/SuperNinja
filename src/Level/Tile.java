/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Level;

import Model.Renderable;
import java.awt.Graphics;

/**
 *
 * @author abilb
 */
public class Tile implements Renderable   {
    
    //The tile model is intended to impliment the proxy pattern and the Flyweight Pattern.
    //If an animated tile image is there, then the animation plays. 
    //Else it plays a simple static image. 
    private int x;
    private int y; 
    private TileModel tileModel;
    private AnimatedTileModel animatedTileModel; 
    
    public Tile(int x, int y, TileModel tileModel)
    {
        this.x = x; 
        this.y = y; 
        this.tileModel = tileModel; 
    }
    
    
    public Tile(int x, int y, TileModel tileModel, AnimatedTileModel animatedTileModel)
    {
        this.x = x; 
        this.y = y; 
        this.tileModel = tileModel; 
        this.animatedTileModel = animatedTileModel;
    }
    
    
    public void render(Graphics g) {
       
                if(animatedTileModel != null)
                {
                    animatedTileModel.render(g);
                }
                else
                {
                    g.drawImage(tileModel.getTileImage(), x, y, null);
                }
                
    }

   
     
    
    
    
}
