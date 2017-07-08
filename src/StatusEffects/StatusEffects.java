/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StatusEffects;

import Model.GameFigure;
import Model.HitBox;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Garrett A. Clement
 */
public class StatusEffects {
    
    private HashMap<HitBox, ArrayList<StatusEffect>> effects;
    
    public void applyEffects(GameFigure gameFigure){

    //Processs all effects on the gameFigure.
    for(HitBox h : effects.keySet())
    {
        ArrayList<StatusEffect> effectList = effects.get(h);
        for(int i = 0; i < effectList.size(); i++)
        {
            //If effect is finished remove it else apply it to the game figure.
            if(effectList.get(i).isFinished())
                effectList.remove(effectList.get(i));
            else
                effectList.get(i).applyEffect(gameFigure);
        }
    }
    
    clearExcess();
    } 
    
    public boolean contains(GameFigure gameFigure)
    {
        return effects.keySet().contains(gameFigure);
    }
    
    //Remove any gameFigures that have no status effects
    private void clearExcess()
    {
        Iterator it = effects.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException 
            if(((ArrayList<?>)pair.getValue()).isEmpty())
                effects.remove(effects.get(pair.getKey())); 
        }      
    }
}
