package com.mdud.dengine.states;

import com.mdud.dengine.utility.KeyHandler;
import com.mdud.dengine.utility.MotionTester;
import com.mdud.dengine.utility.MouseHandler;

import java.awt.*;

public class PlayState extends GameState {
    @Override
    public void update() {

    }

    @Override
    public void input(MouseHandler mouseHandler, KeyHandler keyHandler) {
        if(keyHandler.keyUp.isDown())
            MotionTester.updatePos(0,-5, 640,360);
        if(keyHandler.keyDown.isDown())
            MotionTester.updatePos(0,5, 640,360);
        if(keyHandler.keyLeft.isDown())
            MotionTester.updatePos(-5,0, 640,360);
        if(keyHandler.keyRight.isDown())
            MotionTester.updatePos(5,0, 640,360);
        if(mouseHandler.lButtonDown())
            MotionTester.setPos(mouseHandler.getMouseX(), mouseHandler.getMouseY());

    }

    @Override
    public void render(Graphics2D graphics) {
        graphics.setColor(new Color(207, 40, 43));
        graphics.fillRect(MotionTester.getPosX(), MotionTester.getPosY(), 25,25);
    }
}
