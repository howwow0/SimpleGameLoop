package org.example;

import org.example.engine.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class DVDMovable extends GameObject {

    private double velocityX;
    private double velocityY;
    private Image dvdLogo;

    public DVDMovable(int x, int y, int width, int height, double velocityX, double velocityY, Image dvdLogo) {
        super(x, y, width, height);
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.dvdLogo = dvdLogo;
    }

    @Override
    public void update(JPanel panel, Graphics g) {
        // Обновляем позицию логотипа
        x += (int) velocityX;
        y += (int) velocityY;

        // Проверяем столкновения с границами панели
        if (x <= 0 || x + width >= panel.getWidth()) {
            velocityX = -velocityX;
            changeImageColor(getRandomColor()); // Меняем цвет при столкновении с вертикальными границами
        }
        if (y <= 0 || y + height >= panel.getHeight()) {
            velocityY = -velocityY;
            changeImageColor(getRandomColor()); // Меняем цвет при столкновении с горизонтальными границами
        }
    }

    @Override
    public void draw(JPanel panel, Graphics g) {
        // Отрисовываем логотип
        g.drawImage(dvdLogo, x, y, width, height, null);
    }

    @Override
    public void clear(JPanel panel, Graphics g) {
        // Очищаем область, где был логотип
        g.setColor(panel.getBackground());
        g.fillRect(x, y, width, height);
    }

    private void changeImageColor(Color newColor) {
        BufferedImage bufferedImage = new BufferedImage(
                dvdLogo.getWidth(null), dvdLogo.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(dvdLogo, 0, 0, null);
        g2d.dispose();

        // Меняем цвет изображения
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = bufferedImage.getRGB(x, y);
                Color color = new Color(pixel, true);

                if (color.getAlpha() != 0) {
                    int red = newColor.getRed();
                    int green = newColor.getGreen();
                    int blue = newColor.getBlue();
                    int alpha = color.getAlpha();

                    Color newPixelColor = new Color(red, green, blue, alpha);
                    bufferedImage.setRGB(x, y, newPixelColor.getRGB());
                }
            }
        }

        dvdLogo = bufferedImage;
    }

    private Color getRandomColor() {
        Random random = new Random();
        Color color;
        do {
            int r = random.nextInt(256);
            int g = random.nextInt(256);
            int b = random.nextInt(256);
            color = new Color(r, g, b);
        } while (color.equals(Color.WHITE));
        return color;
    }
}