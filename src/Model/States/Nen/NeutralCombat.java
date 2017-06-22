/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States.Nen;

import Model.GameFigure;
import Model.States.CombatState;

/**
 *
 * @author Garrett A. Clement
 */
public class NeutralCombat extends CombatState {

    public NeutralCombat(GameFigure gameFigure) {
        super(gameFigure);
        
    }

    @Override
    public void execute() {
        
    }
 
    @Override
    public void nextState(String s) {
        if(s.equals("LightAttack"))
            gameFigure.cState = new LightAttack(gameFigure);
        
    }
    
}
