/*
 *Something you might need to look into is, if a box at the right edge of quad 2
 */


package Controller;

import Model.Collision; 
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;  
import java.awt.geom.Point2D;
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
    
    public QuadTree(int maxDepth, int objectCount, double originX, double originY, double width, double height){
        area = new Rectangle2D.Double(originX, originY, width, height);
        this.maxDepth = maxDepth;
        this.objectCount = objectCount;
        objects = new ArrayList<>();
        subdivisions = new ArrayList<>();
    }
    
    private QuadTree(int maxDepth, int objectCount, int depth, double originX, double originY, double width, double height){
        area = new Rectangle2D.Double(originX, originY, width, height);
        this.maxDepth = maxDepth;
        this.objectCount = objectCount;
        this.depth = depth;
        objects = new ArrayList<>();
        subdivisions = new ArrayList<>();
    }
    
    public void insert(Collision figure)
    {
        if(objects.size() >= objectCount && depth != maxDepth)
        {
            if(subdivisions.isEmpty())
            {
                subdivide();
                //Insert objects into proper subdivison
                for(Collision ob : objects)
                    for(QuadTree child : subdivisions)
                        if(child.contains(ob.getCollisionBox()))
                            child.insert(ob);
                
                for(QuadTree child : subdivisions)
                    if(child.contains(figure.getCollisionBox()))
                      child.insert(figure);
            }
            else
            {
                for(QuadTree child : subdivisions)
                    if(child.contains(figure.getCollisionBox()))
                        child.insert(figure);
            } 
        }
        else
            if(contains(figure.getCollisionBox()))
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
    public void clear()
    {
        subdivisions.clear();
        objects.clear();
    }
    public void renderTree(Graphics2D g2)
    {
        
        System.out.println("Level: " + depth);
        System.out.println("ObjectCount" + objects.size());
        System.out.println("");
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
        subdivisions.add(new QuadTree(maxDepth, objectCount, depth + 1, area.getCenterX(), area.getY(), area.getWidth()/2, area.getHeight()/2));        
        //top left subdivision
        subdivisions.add(new QuadTree(maxDepth, objectCount, depth + 1, area.getX(), area.getY(), area.getWidth()/2, area.getHeight()/2));
        
        //bottem left subdivision
        subdivisions.add(new QuadTree(maxDepth, objectCount, depth + 1, area.getX(), area.getCenterY(), area.getWidth()/2, area.getHeight()/2));
        //bottem rigt subdivision
        subdivisions.add(new QuadTree(maxDepth, objectCount, depth + 1, area.getCenterX(), area.getCenterY(), area.getWidth()/2, area.getHeight()/2));
        
    }
    
    private boolean contains(Rectangle2D.Double ob){
        boolean isContained = false;
        
        //top left corner
        Point2D.Double  p1 = new Point2D.Double(ob.getX(), ob.getY());
        //top right corner
        Point2D.Double p2 = new Point2D.Double(ob.getX() + ob.getWidth(), ob.getY());
        //bot left corner
        Point2D.Double p3 = new Point2D.Double(ob.getX(), ob.getY() + ob.getHeight());
        //bot right corner
        Point2D.Double p4 = new Point2D.Double(ob.getX() + ob.getWidth(), ob.getY() + ob.getHeight());
        
        if(area.contains(p1) || area.contains(p2) || area.contains(p3) || area.contains(p4))
            isContained = true;
        area.intersects(ob);
        return area.intersects(ob);
        
    }
    
}
