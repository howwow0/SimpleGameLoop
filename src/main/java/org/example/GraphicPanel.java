package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class GraphicPanel extends JPanel {

    public Shape getShapeOval() {
        return shapeOval;
    }

    public Shape fire() {
        Shape bullet = new Shape();
        bullet.setX(shapeWhiteOval.getX());
        bullet.setY(shapeWhiteOval.getY());
        bullet.setSize(shapeWhiteOval.getSize());
        this.bullets.add(bullet);
        return bullet;
    }

    Shape shapeOval = new Shape();
    Shape shapeSquare = new Shape();
    Shape shapeWhiteOval = new Shape();

    java.util.List<Shape> bullets = new CopyOnWriteArrayList<>();
    int offset = 10;

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        shapeSquare.setX(shapeOval.getX() + shapeOval.getSize() - offset);
        shapeSquare.setY(shapeOval.getY() + shapeOval.getSize() / 4);
        shapeSquare.setSize(shapeOval.getSize() / 2);

        shapeWhiteOval.setX(shapeSquare.getX() + shapeSquare.getSize() / 2);
        shapeWhiteOval.setY(shapeSquare.getY());
        shapeWhiteOval.setSize(shapeSquare.getSize());

        g.setColor(Color.black);
        g.fillRect(shapeSquare.getX(), shapeSquare.getY(), shapeSquare.getSize(), shapeSquare.getSize());

        g.setColor(Color.white);
        g.fillOval(shapeWhiteOval.getX(), shapeWhiteOval.getY(), shapeWhiteOval.getSize(), shapeWhiteOval.getSize());

        g.setColor(Color.red);
        g.fillOval(shapeOval.getX(), shapeOval.getY(), shapeOval.getSize(), shapeOval.getSize());

        if (bullets != null && !bullets.isEmpty()) {
            g.setColor(Color.green);
            for (Shape bullet : bullets)
                g.fillOval(bullet.getX(), bullet.getY(), bullet.getSize(), bullet.getSize());
        }
    }


    public void removeBullet(Shape shape) {
        bullets.remove(shape);
    }
}
