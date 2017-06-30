/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Level;

import java.awt.Image;

/**
 *
 * @author abilb
 */
public class TileModel {
    private final Image tileImage;
     
    public TileModel(Image tileImage) 
    {
       this.tileImage = tileImage; 
    }
    
    public Image getTileImage()
    {
        return this.tileImage;
    }
    
   
}
