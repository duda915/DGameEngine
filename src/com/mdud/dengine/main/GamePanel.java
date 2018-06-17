package com.mdud.dengine.main;

import com.mdud.dengine.states.GameStateManager;
import com.mdud.dengine.utility.*;

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

    //GameStateManager
    private GameStateManager gsm = new GameStateManager();

    //InputHandlers
    private MouseHandler mouseHandler;
    private KeyHandler keyHandler;

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

        mouseHandler = new MouseHandler();
        keyHandler = new KeyHandler();

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
        final double TARGET_FPS = 150;
        int updatesCount;

        while (running) {
            timeNow = System.nanoTime();
            updatesCount = 0;

            while(((timeNow - lastUpdateTime) > UPDATE_TICK) && updatesCount < UPDATE_LIMIT) {
                update();
                input(mouseHandler, keyHandler);
                lastUpdateTime += UPDATE_TICK;
                updatesCount++;
            }

            if(timeNow - lastUpdateTime > UPDATE_TICK) {
                lastUpdateTime = (long) (timeNow - UPDATE_TICK); // Synchronization, slow render fix
            }

            input(mouseHandler, keyHandler);
            render();
            draw();

            Lagger.lag(0);

            // Crude FPS Limiter - about 125 FPS Hardcoded
            FPSMeter.measure(timeNow, System.nanoTime());
            while(FPSMeter.getMeasurement() > TARGET_FPS) {
                Thread.yield();
                Lagger.lag(1);
                FPSMeter.measure(timeNow, System.nanoTime());
            }

            lastRenderTime = System.nanoTime();
            FPSMeter.measureStable(timeNow, lastRenderTime);
        }
    }

    private void update(){
        //GameStateManager
        gsm.update();
    }

    private void render() {
        mainGameGraphics.setColor(new Color(64,64,64));
        mainGameGraphics.fillRect(0,0, width, height);

        //GameStateManager
        gsm.render(mainGameGraphics);

        //Rendering FPS
        mainGameGraphics.setFont(new Font("CenturyGothic", Font.PLAIN, 12));
        mainGameGraphics.setColor(new Color(255, 255, 255));
        mainGameGraphics.drawString(String.format("%.2f", FPSMeter.getStableMeasurement()) + "FPS", 15, 15);
    }

    private void draw() {
        Graphics updateGraphics = getGraphics();
        updateGraphics.drawImage(bufferedGameImage, 0, 0, width, height, null);
        updateGraphics.dispose();

    }

    private void input(MouseHandler mouseHandler, KeyHandler keyHandler) {
        gsm.input(mouseHandler, keyHandler);
    }

}
