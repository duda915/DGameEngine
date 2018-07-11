package com.mdud.dengine.entity;

import com.mdud.dengine.graphics.Vector2D;
import com.mdud.dengine.utility.input.KeyHandler;
import com.mdud.dengine.utility.input.MouseHandler;

public class Player extends Entity {

    public Player() {
        super("sprites/testsheetformatted.png", 64);
        setAnimationDelay(15);
        setMoveSpeed(2);
        setPosition(new Vector2D(20, 20));
    }

    @Override
    public void input(MouseHandler mouseHandler, KeyHandler keyHandler) {
        if(keyHandler.keyUp.isDown())
        {
            isWalkingUp = true;
        } else {
            isWalkingUp = false;
        }
        if(keyHandler.keyDown.isDown())
        {
            isWalkingDown = true;
        } else {
            isWalkingDown = false;
        }
        if(keyHandler.keyLeft.isDown())
        {
            isWalkingLeft = true;
        } else {
            isWalkingLeft = false;
        }
        if(keyHandler.keyRight.isDown())
        {
            isWalkingRight = true;
        } else {
            isWalkingRight = false;
        }
    }
}
