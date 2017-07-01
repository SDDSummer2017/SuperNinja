/*
 *Something you might need to look into is, if a box at the right edge of quad 2
 */


package Controller;

import Model.Collision; 
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;  
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 *
 * @author Garrett A. Clement
 */
public class QuadTree {
    
    private int maxDepth;
    private int depth;
    private int objectCount;
    private Rectangle.Double area; 
    private ArrayList<Collision> objects;
    private ArrayList<QuadTree> subdivisions;
    
    public QuadTree(int maxDepth, int objectCount, double originX, double originY, double size){
        area = new Rectangle2D.Double(originX, originY, size, size);
        this.maxDepth = maxDepth;
        this.objectCount = objectCount;
        objects = new ArrayList<>();
        subdivisions = new ArrayList<>();
    }
    
    private QuadTree(int maxDepth, int objectCount, int depth, double originX, double originY, double size){
        area = new Rectangle2D.Double(originX, originY, size, size);
        this.maxDepth = maxDepth;
        this.objectCount = objectCount;
        this.depth = depth;
        objects = new ArrayList<>();
        subdivisions = new ArrayList<>();
    }
    
    public void insert(Collision figure)
    {
        System.out.println(objects.size());
        if(objects.size() >= objectCount && depth != maxDepth)
        {
            subdivide();
            //Insert objects into proper subdivison
            for(Collision ob : objects)
                for(QuadTree child : subdivisions)
                    if(child.contains(ob.getCollisionBox()))
                        child.insert(ob);
            objects.clear();
        }
        else
            objects.add(figure);
    }           
    
    public ArrayList<Collision> getList(Collision ob)
    {
        ArrayList<Collision> list = new ArrayList<>();
        getList(ob, list);
        return list;
    }
    
    private void getList(Collision ob, ArrayList<Collision> list){
        
            if(!subdivisions.isEmpty())
            {
                for(QuadTree sub : subdivisions)
                    if(sub.contains(ob.getCollisionBox()))
                        sub.getList(ob, list);
            }else{
                for(Collision object : objects)
                    list.add(object);
            }
    }
    
    public void renderTree(Graphics2D g2)
    {
            g2.setColor(Color.RED);
            g2.draw(area);
            g2.setColor(Color.BLACK);
        if(!subdivisions.isEmpty())
        {
       
            for(QuadTree sub : subdivisions)
                 sub.renderTree(g2);
        }
    }
    
    public void subdivide(){
        
        //Create subdivisions
        //top right subdivision
        subdivisions.add(new QuadTree(maxDepth, objectCount, ++depth, area.getCenterX(), area.getY(), area.getWidth()/2));        
        //top left subdivision
        subdivisions.add(new QuadTree(maxDepth, objectCount, ++depth, area.getX(), area.getY(), area.getWidth()/2));
        
        //bottem left subdivision
        subdivisions.add(new QuadTree(maxDepth, objectCount, ++depth, area.getX(), area.getCenterY(), area.getWidth()/2));
        //bottem rigt subdivision
        subdivisions.add(new QuadTree(maxDepth, objectCount, ++depth, area.getCenterX(), area.getCenterY(), area.getWidth()/2));
        
    }
    
    private boolean contains(Rectangle2D.Double ob){
        return area.intersects(ob);
    }
    
}
