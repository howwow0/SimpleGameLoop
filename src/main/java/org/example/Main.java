package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;

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
                            shape.setY(shape.getY() - 3);
                            frame.repaint();
                            break;
                        }
                        case KeyEvent.VK_S: {
                            Shape shape = graphicPanel.getShapeOval();
                            shape.setY(shape.getY() + 3);
                            frame.repaint();
                            break;
                        }
                        case KeyEvent.VK_A: {
                            Shape shape = graphicPanel.getShapeOval();
                            shape.setX(shape.getX() - 3);
                            frame.repaint();
                            break;
                        }
                        case KeyEvent.VK_D: {
                            Shape shape = graphicPanel.getShapeOval();
                            shape.setX(shape.getX() + 3);
                            frame.repaint();
                            break;
                        }
                        case KeyEvent.VK_E: {
                            Shape bullet = graphicPanel.fire();
                            Shape shape = graphicPanel.getShapeOval();
                            new Thread(() -> {
                                shape.setX(shape.getX() - 3);
                                while (bullet.getX() < frame.getWidth()) {
                                    bullet.setX(bullet.getX() + 3);
                                    try {
                                        SwingUtilities.invokeAndWait(graphicPanel::repaint);
                                        Thread.sleep(1);
                                    } catch (InterruptedException | InvocationTargetException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                }

                                graphicPanel.removeBullet(bullet);
                            }).start();
                        }
                        default:
                            break;
                    }
                }
            });
            frame.add(graphicPanel);
            frame.pack();
            frame.setSize(900, 600);
            frame.setBackground(Color.white);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            Shape shape = graphicPanel.getShapeOval();
            shape.setX(200);
            shape.setY(200);
            shape.setSize(100);

            frame.setVisible(true);
        });
    }
}