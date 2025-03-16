package org.example.engine;

import javax.swing.*;
import java.awt.*;

public interface Drawable {
    void update(JPanel panel, Graphics g);
    void draw(JPanel panel, Graphics g);
    void clear(JPanel panel, Graphics g);
}