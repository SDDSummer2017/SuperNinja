/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Level;

 
import Model.Collision;
 
import EventHandling.CheckpointHandler;
import Model.GameData;
 
import Model.GameFigure;
import Model.Renderable;
import Model.Updateable; 
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.ObjDoubleConsumer;

/**
 *
 * @author abilb
 */
public abstract class Level implements Renderable, Updateable {
    public int nenStartX; 
    public int nenStartY;
    public int width; 
    public int height; 
    protected GameData gameData; 
    protected CheckpointHandler checkpointHandler;
    //TODO: 2d Array of Tiles 
    public List<Tile> tiles; 
 
    public List<GameFigure> terrain;  
    
    public List<Collision> collisions;
    public List<Updateable> updatables;
    public List<Renderable> renderables;
    public List<Object> addables;
    public List<Object> removables;
     
    public void addGameData(Object ob){
        addables.add(ob);
    }
 
    
 
    
    public void addGameData(){
        for(Object ob : addables)
        {
        if(ob instanceof Collision)
            collisions.add((Collision)ob);
        
        if(ob instanceof Updateable)
            updatables.add((Updateable)ob);
        
        if(ob instanceof Renderable)
            renderables.add((Renderable)ob);
        }
        addables.clear();
    } 

    
    public  void removeGameData(Object ob){
        removables.add(ob);
    }
    
    public void removeGameData(){
        for(Object ob : removables)
        {
            if(ob instanceof Collision)
                collisions.remove((Collision)ob);

            if(ob instanceof Updateable)
                updatables.remove((Updateable)ob);

            if(ob instanceof Renderable)
                renderables.remove((Renderable)ob);
        }
        removables.clear();
    }
}
