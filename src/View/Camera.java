/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Controller.Main;
import Model.GameFigure;

 
public class Camera  {
    int worldSizeX; 
    int worldSizeY;
    int viewportSizeX;
    int viewportSizeY; 
    int offsetMaxX;
    int offsetMaxY;
    int offsetMinX;
    int offsetMinY;
    public int offsetHelperX = 0;
    public int offsetHelperY = 0; 
    public int camX;
    public int camY; 
    GameFigure Nen;
            
    public Camera(int worldSizeX, int worldSizeY, int viewportSizeX, int viewportSizeY)
    {
        this.Nen = Main.gameData.nen;

        this.offsetMaxX = worldSizeX - viewportSizeX;
        this.offsetMaxY = worldSizeY - viewportSizeY;
        offsetMinX = 0;
        offsetMinY = 0;

    }
   public void move(int x, int y)
   {
       this.camX = x;
       this.camY = y; 


   } 
   public void update(int width, int height)
    {    offsetHelperX = camX;
         offsetHelperY = camY; 
         this.camX = (int) (Nen.x - width / 2)  ;
         this.camY = (int) (Nen.y - height / 2)  ;

         if(camX > offsetMaxX)
         {
             camX = offsetMaxX;
         }
         else if(camX < offsetMinX)
         {
             camX = offsetMinX; 

         }

           if(camY > offsetMaxY)
         {
             camY = offsetMaxY;
         }
         else if(camY < offsetMinY)
         {
             camY = offsetMinY; 

         }

    }   
}