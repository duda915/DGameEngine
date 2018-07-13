package com.mdud.dengine.entity.player;

import com.mdud.dengine.entity.Entity;
import com.mdud.dengine.graphics.Vector2D;
import com.mdud.dengine.main.GamePanel;
import com.mdud.dengine.utility.collision.BoundingBox;
import com.mdud.dengine.utility.input.KeyHandler;
import com.mdud.dengine.utility.input.MouseHandler;

import java.awt.*;

public class Player extends Entity {

    public Player() {
        super("sprites/testsheetformatted.png", 64);
        setAnimationDelay(15);
        setMoveSpeed(2);
        setPosition(new Vector2D(GamePanel.width/2 - getEntitySize()/2, GamePanel.height/2 - getEntitySize()/2));
    }


    @Override
    public void render(Graphics2D graphics) {
        graphics.drawImage(animation.getAnimationFrame(spriteSheet.getSpriteArrayRow(currentAction)),
                null,(int) position.getX(),(int) position.getY());

        //for testing
        collisionBox.drawBoundingBox(graphics);
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
