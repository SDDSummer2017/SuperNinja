 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.States.Nen;

import Controller.Main;
import EventHandling.Observer;
import Model.HitBox;
import Model.GameFigure;
import Model.States.CombatState;
import java.util.ArrayList;
 

/**
 *
 * @author Garrett A. Clement
 */
public class LightAttack extends CombatState {
    private static final long DURATION = 500; 
    HitBox hitBox;
    private int midX;
    private int midRange = 150;
    private int lowRange = 300;
    public LightAttack(GameFigure gameFigure, ArrayList<Observer> observers) {
        super(gameFigure, observers);
        gameFigure.damage = 10;
        hitBox =  new HitBox(gameFigure.x + (gameFigure.size/2), gameFigure.y, 75, 10);
       
       // hitBox =  new AttackHitBox(gameFigure.x + (gameFigure.size/2), gameFigure.y + (gameFigure.size/2));
        
        Main.gameData.addAlly(hitBox);
    }

    @Override
    public void execute() {   
       
        if(System.currentTimeMillis() - initTime >= DURATION)
        {
            Main.gameData.removeAlly(hitBox);
            nextState("NeutralCombat");
        }
        else if(System.currentTimeMillis() - initTime  >= midRange && System.currentTimeMillis() - initTime <= lowRange) 
            hitBox.translate(gameFigure.x + (gameFigure.size/2) + 20, gameFigure.y + 30);  
        else if(System.currentTimeMillis()  - initTime > lowRange) 
            hitBox.translate(gameFigure.x + (gameFigure.size/2), gameFigure.y + 60);  
        else
            hitBox.translate(gameFigure.x + (gameFigure.size/2), gameFigure.y);
        
    }

    @Override
    public void nextState(String s) {
       if(s.equals("NeutralCombat"))
       {
           gameFigure.cState = new NeutralCombat(gameFigure, this.observers);
       }
       
       gameFigure.cState.notifyObservers();
    }

//    public LightAttack(GameFigure gameFigure) {
//        super(gameFigure);
//        gameFigure.damage = 50;
//        gameFigure.animation = "LightAttack1";
//    }
//
//    @Override
//    public void execute() {
//        
//      
//        
//    }
//    if(initX >= 100)
//        nextState("NeutralState");
//       nextState("");
//
//    @Override
//    public void nextState(String s) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//    }
//    
//    @Override  
//    public void translate(int dx, int dy) {
//        if (gameFigure.x <= 0 && dx < 0) {
//            dx = 0;
//        }
//        if (((gameFigure.x + gameFigure.size) >= GamePanel.PWIDTH) && (dx > 0)) {
//            dx = 0;
//        }
//        gameFigure.x += dx;
//        gameFigure.y += dy;
//    }
//
//    @Override
//    public void nextState(String s) {
////        
////         if(s.equals("Attack2") || comboWIndow)
////               gameFigure.setState(new Attack3(gameFigure));
////         else if(s.equals("NeautralState"))
////             gameFigure.state = new NeautralState;
//    }
//    
}
 
