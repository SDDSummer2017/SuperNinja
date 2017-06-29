/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Terrain;

import Model.GameFigure;
import java.util.ArrayList;

/**
 *
 * @author abilb
 */
public class Level {
    int width; 
    int height; 
    //TODO: 2d Array of Tiles
    ArrayList<GameFigure> terrain; 
    ArrayList<GameFigure> allies; 
    ArrayList<GameFigure> enemies; 
    
    
}
