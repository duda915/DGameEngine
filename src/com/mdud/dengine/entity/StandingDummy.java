package com.mdud.dengine.entity;

import com.mdud.dengine.graphics.Vector2D;

public class StandingDummy extends Entity {

    public StandingDummy() {
        super("sprites/npctest.png", 32, 64);
        currentAction = DOWN;
        setPosition(new Vector2D(1400, 1000));
    }


}
