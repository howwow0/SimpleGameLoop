package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main extends JFrame {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            Main frame = new Main();
            GraphicPanel graphicPanel = new GraphicPanel();
            graphicPanel.setBackground(Color.white);

            frame.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_W: {
                            Shape shape = graphicPanel.getShapeOval();
                            shape.setY(shape.getY() - 2);
                            frame.repaint();
                            break;
                        }
                        case KeyEvent.VK_S: {
                            Shape shape = graphicPanel.getShapeOval();
                            shape.setY(shape.getY() + 2);
                            frame.repaint();
                            break;
                        }
                        case KeyEvent.VK_A: {
                            Shape shape = graphicPanel.getShapeOval();
                            shape.setX(shape.getX() - 2);
                            frame.repaint();
                            break;
                        }
                        case KeyEvent.VK_D: {
                            Shape shape = graphicPanel.getShapeOval();
                            shape.setX(shape.getX() + 2);
                            frame.repaint();
                            break;
                        }
                        case KeyEvent.VK_E: {
                            Shape shape = graphicPanel.fire();
                                shape.setX(shape.getX() + 1);
                                graphicPanel.repaint();
                            try {
                                Thread.sleep(1);
                            } catch (InterruptedException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                        default:
                            break;
                    }
                }
            });
            frame.add(graphicPanel);
            frame.pack();
            frame.setSize(800, 600);
            frame.setBackground(Color.white);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            Shape shape = graphicPanel.getShapeOval();
            shape.setX(frame.getWidth() / 2);
            shape.setY(frame.getHeight() / 2);
            shape.setSize(100);

            frame.setVisible(true);
        });
    }
}