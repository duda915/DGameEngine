package com.mdud.dengine.entity;

import com.mdud.dengine.graphics.Vector2D;
import com.mdud.dengine.main.GamePanel;
import com.mdud.dengine.utility.collision.BoundingBox;
import com.mdud.dengine.utility.input.KeyHandler;
import com.mdud.dengine.utility.input.MouseHandler;

import java.awt.*;


public class Player extends Entity {

    public Player() {
        super("sprites/characterspritesheet.png", 32, 72);

        setAnimationDelay(15);
        setMoveSpeed(3);
//        setPosition(new Vector2D(GamePanel.width/2 - getEntitySize()/2, GamePanel.height/2 - getEntitySize()/2));
        setPosition(new Vector2D(1000, 1200));
        collisionBox.setBoxSize(48);
        position.setWorldPos(position.getX() - GamePanel.width/2 + getEntitySize()/2,
                position.getY() - GamePanel.height/2 + getEntitySize()/2);
        collisionBox.updateBox(position);
    }


    @Override
    public void update() {
        super.update();
    }

    @Override
    public void handleCollision(BoundingBox objectBox) {
        super.handleCollision(objectBox);
        position.setWorldPos(position.getX() - GamePanel.width/2 + getEntitySize()/2,
                position.getY() - GamePanel.height/2 + getEntitySize()/2);
    }

    @Override
    public void render(Graphics2D graphics) {
        graphics.drawImage(animation.getAnimationFrame(spriteSheet.getSpriteArrayRow(currentAction)),
                null,(int) GamePanel.width/2 - getEntitySize()/2,(int) GamePanel.height/2 - getEntitySize()/2);

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

    @Override
    protected void move(float xOffset, float yOffset) {
        super.move(xOffset, yOffset);
        position.setWorldPos(position.getX() - GamePanel.width/2 + getEntitySize()/2,
                position.getY() - GamePanel.height/2 + getEntitySize()/2);
    }
}
