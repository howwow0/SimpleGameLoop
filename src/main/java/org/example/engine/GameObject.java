package org.example.engine;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class GameObject implements Drawable {
    protected int x, y;
    protected int width, height;

}