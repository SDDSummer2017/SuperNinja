/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventHandling;

/**
 *
 * @author abilb
 */

//Abstract class used to implement the activation of resources on certain events. 
public abstract class ResourceHandler {
    
    private final String resourcePath;
    
    public ResourceHandler(String resourcePath)
        {
            this.resourcePath = resourcePath; 
        }
    
}
