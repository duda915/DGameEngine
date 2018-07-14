package com.mdud.dengine.entity;

import com.mdud.dengine.graphics.Vector2D;
import com.mdud.dengine.utility.input.KeyHandler;
import com.mdud.dengine.utility.input.MouseHandler;

public class WalkingDummy extends Entity {
    private int counter;

    public WalkingDummy() {
        super("sprites/testsheetformatted.png", 64, 32);
        setPosition(new Vector2D(120, 120));
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
        }
        else if(counter < 200) {
            isWalkingLeft = true;
            isWalkingRight = false;
        }
        else {
            counter = 0;
        }

    }
}
