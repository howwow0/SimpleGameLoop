package org.example;

import org.example.engine.GameObject;

import javax.swing.*;
import java.awt.*;

public class CustomShape extends GameObject {

    private int velocityX;
    private int velocityY;

    public CustomShape(int x, int y, int width, int height, int velocityX, int velocityY) {
        super(x, y, width, height);
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    @Override
    public void update(JPanel panel, Graphics g) {
        x += velocityX;
        y += velocityY;

        if (x <= 0 || x + width >= panel.getWidth()) {
            velocityX = -velocityX;
        }
        if (y <= 0 || y + height >= panel.getHeight()) {
            velocityY = -velocityY;
        }
    }

    @Override
    public void draw(JPanel panel, Graphics g) {

        int squareSize = width / 2;
        int squareX = x + width - squareSize / 6;
        int squareY = y + (height - squareSize) / 2;
        g.setColor(Color.BLACK);
        g.fillRect(squareX, squareY, squareSize, squareSize);

        g.setColor(Color.WHITE);
        int circleX = squareSize / 2 + squareX;

        g.fillOval(circleX, squareY, squareSize, squareSize);

        g.setColor(Color.RED);
        g.fillOval(x, y, width, height);
    }

    @Override
    public void clear(JPanel panel, Graphics g) {
        g.setColor(panel.getBackground());
        g.fillRect(x, y, width, height);

        // Очищаем область, занимаемую квадратом и внутренним кругом
        int squareSize = width / 2;
        int squareX = x + width - squareSize / 6;
        int squareY = y + (height - squareSize) / 2;

        // Очищаем квадрат
        g.fillRect(squareX, squareY, squareSize, squareSize);

        // Очищаем внутренний круг
        int circleX = squareSize / 2 + squareX;
        g.fillOval(circleX, squareY, squareSize, squareSize);
    }
}