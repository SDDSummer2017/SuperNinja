/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Terrain;

import Model.Renderable;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author abilb
 */
public class AnimatedTileModel implements Renderable {
    
    private ArrayList<TileModel> tileModels; 
    private int x;
    private int y; 
    private int iterable; 
    public AnimatedTileModel(int x, int y, ArrayList<TileModel> tileModels) {
        this.x = x;
        this.y = y; 
        this.tileModels = tileModels; 
    }

    @Override
    public void render(Graphics g) { 
        
        g.drawImage(tileModels.get(iterable).getTileImage() , x, y, null);
        
        if(iterable < tileModels.size())
        {
            iterable = 0; 
        }
        else
        {
            iterable++; 
        }
    }
    
  
}
