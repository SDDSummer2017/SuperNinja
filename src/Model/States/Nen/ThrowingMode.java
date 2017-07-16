package Model.States.Nen;


import Controller.Main;
import EventHandling.Observer;
import Model.GameFigure;
import Model.Shuriken;
import Model.States.CombatState;
import Model.States.Nen.NeutralCombat;
import java.awt.Color;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 

/**
 *
 * @author Garrett A. Clement
 */
public class ThrowingMode extends CombatState{

    private final static int MAX_THROWS = 5;
    private final static int THROW_DELAY = 250;
    private static int COOL_DOWN = 3000;
    private static long lastEntered;
     
    private int throwCount = 0;
    private long lastThrown; 
    
    
    public ThrowingMode(GameFigure gameFigure, ArrayList<Observer> observers) {
        super(gameFigure, observers);
        lastEntered = System.currentTimeMillis();
    }

    @Override
    public void execute() { 
        if(throwCount >= 5)
            nextState("ThrowingMode");
    }
    
    public void throwShuriken(int tx, int ty){
        if(throwCount > 5)
            return;
        
        if(System.currentTimeMillis() - lastThrown >= THROW_DELAY)
        {
            lastThrown = System.currentTimeMillis();
            throwCount++; 
            Main.gameData.addGameData(new Shuriken(gameFigure.x + gameFigure.size/2, 
                   gameFigure.y + gameFigure.size/2, tx, ty, Color.yellow, true, 10, 3)); 
            
        }
    }
    
    public boolean getCoolDown
        (){
        if(System.currentTimeMillis() - lastEntered >= COOL_DOWN)
            return true;
        else
            return false;
    }

    @Override
    public void nextState(String s) { 
        if(s.equals("ThrowingMode"))
            gameFigure.cState = new NeutralCombat(gameFigure, observers);
        
    }
    
}
