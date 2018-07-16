package com.mdud.dengine.entity;

import com.mdud.dengine.graphics.Vector2D;

public class StandingDummy extends Entity {

    public StandingDummy() {
        super("sprites/testsheetformatted.png", 64, 64);
        currentAction = DOWN;
        setPosition(new Vector2D(120, 240));
    }


}
