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
public class LightAttack extends CombatState {

    public LightAttack(GameFigure gameFigure) {
        super(gameFigure);
    }

    @Override
    public void execute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void nextState(String s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
