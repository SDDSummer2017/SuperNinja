/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Physics;

/**
 *
 * @author abilb
 */
public class Force {
    public double mass; 
    public Acceleration acceleration; 
    
    public Force(double mass, Acceleration acceleration)
    {
        this.mass = mass; 
        this.acceleration = acceleration;
    }
    
    public int dvx()
    {
        return (int) (this.mass * this.acceleration.dvx);
    }
    
    public int dvy()
    {
        return (int) (this.mass * this.acceleration.dvy);
    }
    
}
