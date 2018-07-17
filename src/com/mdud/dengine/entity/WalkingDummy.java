package com.mdud.dengine.entity;

import com.mdud.dengine.graphics.Vector2D;
import com.mdud.dengine.utility.input.KeyHandler;
import com.mdud.dengine.utility.input.MouseHandler;

public class WalkingDummy extends Entity {
    private int counter;

    public WalkingDummy() {
        super("sprites/npctest.png", 32, 32);
        setPosition(new Vector2D(1600, 1000));
        setMoveSpeed(1);
        setAnimationDelay(15);
        counter = 0;
    }


    @Override
    public void update() {
        super.update();
        counter++;

        if(counter < 100) {
            isWalkingLeft = false;
            isWalkingRight = true;
            isWalkingUp = false;

        }
        else if(counter < 200) {
            isWalkingLeft = true;
            isWalkingRight = false;
            isWalkingUp = false;
        } else if (counter < 300) {
            isWalkingLeft = false;
            isWalkingRight = false;
            isWalkingUp = true;
        } else {
            counter = 0;
        }

    }
}
