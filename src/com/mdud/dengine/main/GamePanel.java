package com.mdud.dengine.main;

import com.mdud.dengine.utility.FPSMeter;
import com.mdud.dengine.utility.Lagger;
import com.mdud.dengine.utility.MotionTester;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable{
    //Window Sizing
    private int width;
    private int height;

    //Thread
    private Thread thread;

    //GameLoop condition
    private Boolean running = false;

    //Graphics
    private BufferedImage bufferedGameImage;
    private Graphics2D mainGameGraphics;



    public GamePanel(int width, int height) {
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocus();
    }

    @Override
    public void addNotify() {
        super.addNotify();

        if(thread == null) {
            thread = new Thread(this, "GameThread");
            thread.start();
        }
    }

    public void init() {
        running = true;
        bufferedGameImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        mainGameGraphics = (Graphics2D) bufferedGameImage.getGraphics();

    }

    @Override
    public void run() {
        init();

        long timeNow;
        long lastRenderTime;
        long lastUpdateTime = System.nanoTime();
        final double UPS = 120.0; // Updates Per Second
        final double UPDATE_TICK = 1000000000 / UPS;
        final double UPDATE_LIMIT = 20; // In case of really slow render
        int updatesCount = 0;

        while (running) {
            timeNow = System.nanoTime();
            updatesCount = 0;

            while(((timeNow - lastUpdateTime) > UPDATE_TICK) && updatesCount < UPDATE_LIMIT) {
                update();
                lastUpdateTime += UPDATE_TICK;
                updatesCount++;
            }

            if(timeNow - lastUpdateTime > UPDATE_TICK) {
                lastUpdateTime = (long) (timeNow - UPDATE_TICK); // Synchronization, slow render fix
            }

            render();
            draw();

            Lagger.lag(0);

            lastRenderTime = System.nanoTime();
            FPSMeter.measure(timeNow, lastRenderTime);
        }
    }

    public void update(){
        MotionTester.updatePos(width, height);
    }

    public void render() {
        mainGameGraphics.setColor(new Color(64,64,64));
        mainGameGraphics.fillRect(0,0, width, height);
        mainGameGraphics.setColor(new Color(207, 40, 43));
        mainGameGraphics.fillRect(MotionTester.getPosX(), MotionTester.getPosY(), 25, 55);

        mainGameGraphics.setColor(new Color(255, 255, 255));
        mainGameGraphics.drawString(FPSMeter.getMeasurement().toString() + "FPS", 15, 15);
    }

    public void draw() {
        Graphics updateGraphics = getGraphics();
        updateGraphics.drawImage(bufferedGameImage, 0, 0, width, height, null);
        updateGraphics.dispose();

    }

}
