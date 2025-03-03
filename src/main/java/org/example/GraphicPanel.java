package org.example;

import javax.swing.*;
import java.awt.*;

public class GraphicPanel extends JPanel {

    public Shape getShapeOval() {
        return shapeOval;
    }

    public Shape fire(){
        Shape bullet = new Shape();
        bullet.setX(shapeWhiteOval.getX());
        bullet.setY(shapeWhiteOval.getY());
        bullet.setSize(shapeWhiteOval.getSize());
        this.bullet = bullet;
        return this.bullet;
    }

    Shape shapeOval = new Shape();
    Shape shapeSquare = new Shape();
    Shape shapeWhiteOval = new Shape();

    Shape bullet;
    int offset = 10;

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        shapeSquare.setX(shapeOval.getX() + shapeOval.getSize() - offset);
        shapeSquare.setY(shapeOval.getY() + shapeOval.getSize() / 4);
        shapeSquare.setSize(shapeOval.getSize() / 2);

        shapeWhiteOval.setX(shapeSquare.getX() + shapeSquare.getSize()/2);
        shapeWhiteOval.setY(shapeSquare.getY());
        shapeWhiteOval.setSize(shapeSquare.getSize());

        g.setColor(Color.black);
        g.fillRect(shapeSquare.getX(), shapeSquare.getY(), shapeSquare.getSize(), shapeSquare.getSize());

        g.setColor(Color.white);
        g.fillOval(shapeWhiteOval.getX(), shapeWhiteOval.getY(), shapeWhiteOval.getSize(), shapeWhiteOval.getSize());

        g.setColor(Color.red);
        g.fillOval(shapeOval.getX(), shapeOval.getY(), shapeOval.getSize(), shapeOval.getSize());

        if(bullet != null){
            g.setColor(Color.red);
            g.fillOval(bullet.getX(), bullet.getY(), bullet.getSize(), bullet.getSize());
        }
    }


}
