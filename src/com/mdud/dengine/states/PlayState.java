package com.mdud.dengine.states;

import com.mdud.dengine.graphics.Vector2D;
import com.mdud.dengine.graphics.font.Font;
import com.mdud.dengine.utility.input.KeyHandler;
import com.mdud.dengine.utility.testers.MotionTester;
import com.mdud.dengine.utility.input.MouseHandler;

import java.awt.*;


public class PlayState extends GameState {

    // Initializings
    private MotionTester simpleMotionTester;

    public PlayState() {
        simpleMotionTester = new MotionTester();
    }

    @Override
    public void update() {
        simpleMotionTester.updatePos(640, 360);
    }

    @Override
    public void input(MouseHandler mouseHandler, KeyHandler keyHandler) {
        if(keyHandler.keyUp.isDown())
            simpleMotionTester.updatePos(0,-5, 640,360);
        if(keyHandler.keyDown.isDown())
            simpleMotionTester.updatePos(0,5, 640,360);
        if(keyHandler.keyLeft.isDown())
            simpleMotionTester.updatePos(-5,0, 640,360);
        if(keyHandler.keyRight.isDown())
            simpleMotionTester.updatePos(5,0, 640,360);
        if(mouseHandler.lButtonDown())
            simpleMotionTester.setPos(mouseHandler.getMouseX(), mouseHandler.getMouseY());
    }

    @Override
    public void render(Graphics2D graphics) {
        graphics.setColor(new Color(207, 40, 43, 125));
        graphics.fillRect(simpleMotionTester.getPosX(), simpleMotionTester.getPosY(), 25,25);
    }
}
