package com.mdud.dengine.main;

import com.mdud.dengine.graphics.Vector2D;
import com.mdud.dengine.graphics.font.Font;
import com.mdud.dengine.states.GameStateManager;
import com.mdud.dengine.utility.input.KeyHandler;
import com.mdud.dengine.utility.input.MouseHandler;
import com.mdud.dengine.utility.timing.FPSMeter;
import com.mdud.dengine.utility.timing.Sleeper;

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
    private GameStateManager gsm;

    //InputHandlers
    private MouseHandler mouseHandler;
    private KeyHandler keyHandler;

    //Custom Sprite Font - com.mdud.dengine.graphics.font.Font
    private Font fpsCounterFont;

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

        mouseHandler = new MouseHandler(this);
        keyHandler = new KeyHandler(this);

        gsm = new GameStateManager();

        fpsCounterFont = new Font("fonts/testfont.png", 20);
        // sprawdzic bufferedfilterop

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

            render();
            draw();

            //Lag Tester
            Sleeper.sleep(0);

            //Crude FPS Limiter - about 125 FPS Hardcoded
            FPSMeter.measure(timeNow, System.nanoTime());
            while(FPSMeter.getMeasurement() > TARGET_FPS) {
                Thread.yield(); // frees processor from working
                Sleeper.sleep(1);
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

        //Rendering FPS Counter
        fpsCounterFont.drawString(mainGameGraphics, String.format("%.2f", FPSMeter.getStableMeasurement()) + "FPS",
                new Vector2D(15, 15), 15);

        /*
        mainGameGraphics.setFont(new Font("CenturyGothic", Font.PLAIN, 12));
        mainGameGraphics.setColor(new Color(255, 255, 255));
        mainGameGraphics.drawString(String.format("%.2f", FPSMeter.getStableMeasurement()) + "FPS", 15, 15);
        */
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
