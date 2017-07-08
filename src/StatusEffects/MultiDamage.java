///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package StatusEffects; 
//import Model.GameFigure; 
//
///**
// *
// * @author Garrett A. Clement
// */
//public class MultiDamage extends StatusEffect {
//
//    private String recordedState;
//    private long damageDelay;
//    private long lastDamaged;
//    public MultiDamage(){}
//    
//    public MultiDamage(GameFigure gameFigure,  int damage, int damageDelay) {
//        super(gameFigure, damage, damageDelay); 
//        this.damageDelay = damageDelay;
//        this.lastDamaged = System.currentTimeMillis() + damageDelay;
//        //recordedState = gameFigure.cState.previousState.toString();
//    }
//    
//    @Override
//    public void applyEffect(GameFigure target) {
//        
//        if(System.currentTimeMillis() - lastDamaged >= damageDelay)
//        {    
//            
//            isFinished = true; 
//            target.health -= damage;
//            lastDamaged = System.currentTimeMillis();
//            System.out.println(target.health);
//        }
//    }
//
//    @Override
//    public boolean isFinished() {
//        if(!recordedState.equals(gameFigure.cState.toString()))
//            return true;
//        else
//            return false;
//    }
//
//    @Override
//    public StatusEffect clone() {
//        MultiDamage clone = new MultiDamage();
//        return clone(clone);
//    }
//    
//    @Override
//    protected StatusEffect clone(StatusEffect clone){  
//        ((MultiDamage)clone).damageDelay = this.damageDelay;
//        ((MultiDamage)clone).lastDamaged = this.lastDamaged;
//        ((MultiDamage)clone).recordedState = this.recordedState;
//        
//        clone = super.clone(clone); 
//        return clone;
//    }
//
//    
//    
//}
