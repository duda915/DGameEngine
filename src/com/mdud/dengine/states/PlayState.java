package com.mdud.dengine.states;

import com.mdud.dengine.utility.KeyHandler;
import com.mdud.dengine.utility.MotionTester;
import com.mdud.dengine.utility.MouseHandler;

import java.awt.*;

public class PlayState extends GameState {
    @Override
    public void update() {
        MotionTester.updatePos(640, 360);
    }

    @Override
    public void input(MouseHandler mouseHandler, KeyHandler keyHandler) {

    }

    @Override
    public void render(Graphics2D graphics) {
        graphics.setColor(new Color(207, 40, 43));
        graphics.fillRect(MotionTester.getPosX(), MotionTester.getPosY(), 25,25);
    }
}
