package org.example;

import org.example.engine.GameLoop;
import org.example.engine.GameObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main {
    private static GameLoop gameLoop;

    public static void main(String[] args) throws IOException {
        CopyOnWriteArrayList<GameObject> gameObjects = new CopyOnWriteArrayList<>();

        JFrame frame = new JFrame("Moving Shape with Debug Info");
        JPanel panel = getJPanel(gameObjects);
        frame.add(panel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        gameLoop = new GameLoop() {
            @Override
            protected void update() {
                for (GameObject obj : gameObjects) {
                    obj.update(panel, panel.getGraphics());
                }
            }

            @Override
            protected void render() {
                panel.repaint();
            }
        };
        Image image = ImageIO.read(new File(System.getProperty("user.dir") + "/src/main/java/resource/dvd.png"));
        DVDMovable dvdMovable = new DVDMovable(250, 125, 100, 100, 1, 1, image);
        gameObjects.add(dvdMovable);
        // Запускаем игровой цикл
        gameLoop.start();
    }

    private static JPanel getJPanel(CopyOnWriteArrayList<GameObject> gameObjects) {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Отрисовка всех объектов
                for (GameObject obj : gameObjects) {
                    obj.draw(this, g);
                }

                // Отрисовка служебной информации
                g.setColor(Color.BLACK);
                g.drawString("FPS: " + gameLoop.getCurrentFPS(), 10, 20);
                g.drawString("DeltaTime: " + gameLoop.getDeltaTime(), 10, 40);
                g.drawString("Objects: " + gameObjects.size(), 10, 60);
            }
        };
        panel.setBackground(Color.WHITE);
        return panel;
    }
}