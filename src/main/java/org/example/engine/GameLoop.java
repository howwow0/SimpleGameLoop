package org.example.engine;

import lombok.Getter;

public class GameLoop implements Runnable {
    private static final int FPS = 500;
    private boolean running = false;
    private int fpsCounter = 0;
    @Getter
    private int currentFPS = 0;
    @Getter
    private double deltaTime = 0;
    private long lastFPSCheck = System.currentTimeMillis();

    public void start() {
        running = true;
        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        deltaTime = 0;
        long lastTime = System.nanoTime();
        double nsPerFrame = 1_000_000_000.0 / FPS;

        while (running) {
            long now = System.nanoTime();
            deltaTime += (now - lastTime) / nsPerFrame;
            lastTime = now;

            while (deltaTime >= 1) {
                update();
                deltaTime--;
                fpsCounter++;
            }

            render();

            if (System.currentTimeMillis() - lastFPSCheck >= 1000) {
                currentFPS = fpsCounter;
                fpsCounter = 0;
                lastFPSCheck = System.currentTimeMillis();
            }
        }
    }

    protected void update() {
        // Логика обновления
    }

    protected void render() {
        // Логика отрисовки
    }
}