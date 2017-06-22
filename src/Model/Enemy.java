package Model;

import Controller.Main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import View.GamePanel;

public abstract class Enemy extends GameFigure {

    public Image attack1;
    public Image attack2;
    public Image block;
    public Image hit;
    public Image movement;
    public Image neutral;
    public Image is_Thrown;
    public Image throwImage;
    public Image staticImage;
    
    public int dx;
    public int dy;
    public int updateCount = 0;
    final Image image;
    public String state;

    public Enemy(double x, double y, double size) {
        super(x, y, size);
        image = null;
    }

    public void changeState(){}
    
    @Override
    public void render(Graphics g) {}

    @Override
    public void update() {}

    @Override
    public Rectangle2D.Double getCollisionBox() {
        return new Rectangle2D.Double(super.x, super.y, super.size, super.size);
    }
}
