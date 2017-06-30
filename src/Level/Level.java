/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Level;

import Model.GameFigure;
import Model.Renderable;
import Model.Updateable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author abilb
 */
public abstract class Level implements Renderable, Updateable {
    int width; 
    int height; 
    //TODO: 2d Array of Tiles 
    public List<Tile> tiles; 
    public List<GameFigure> terrain; 
    public List<GameFigure> allies; 
    public List<GameFigure> enemies; 
    
    
}
