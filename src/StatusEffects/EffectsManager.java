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
/**
 *
 * @author Garrett A. Clement
 */
public class StatusEffects {
    
    private HashMap<HitBox, ArrayList<StatusEffect>> effects;
    
    public StatusEffects(){
        effects = new HashMap<HitBox, ArrayList<StatusEffect>>();
    }
    
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
    
    public void addEffect(HitBox hitbox, StatusEffect statusEffect){
        if(effects.containsKey(hitbox))
            effects.get(hitbox).add(statusEffect.clone());
        else
        {
            ArrayList<StatusEffect> list = new ArrayList<>();
            list.add(statusEffect.clone());
            effects.put(hitbox, list);
        }
    }
    
    public boolean contains(HitBox hitbox, StatusEffect statusEffect)
    {
        boolean inEffect = false;
        if(effects.containsKey(hitbox))
            for(StatusEffect se : effects.get(hitbox))
                if(se.getClass().equals(statusEffect.getClass()))
                    inEffect = true;
        return inEffect;
    }
    
    //Remove any gameFigures that have no status effects
    private void clearExcess()
    {
        Iterator it = effects.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next(); 
            //it.remove(); // avoids a ConcurrentModificationException 
            if(((ArrayList<?>)pair.getValue()).isEmpty())
                effects.remove(effects.get(pair.getKey())); 
        }      
    }
}
