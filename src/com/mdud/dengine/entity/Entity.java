package com.mdud.dengine.entity;

import com.mdud.dengine.graphics.Animation;
import com.mdud.dengine.graphics.Sprite;
import com.mdud.dengine.graphics.Vector2D;
import com.mdud.dengine.utility.collision.BoundingBox;
import com.mdud.dengine.utility.input.KeyHandler;
import com.mdud.dengine.utility.input.MouseHandler;

import java.awt.*;

public abstract class Entity {

    //Graphics
    protected Sprite spriteSheet;
    protected Animation animation;
    protected Vector2D position;

    //Animated states
    protected final int UP = 0;
    protected final int DOWN = 1;
    protected final int LEFT = 2;
    protected final int RIGHT = 3;
    protected int currentAction = UP;

    //Entity controls
    protected boolean isWalkingUp = false;
    protected boolean isWalkingDown = false;
    protected boolean isWalkingLeft = false;
    protected boolean isWalkingRight = false;
    protected boolean isDoingAction = false;

    //Movement
    protected float moveSpeed = 5;

    //collision
    protected BoundingBox collisionBox;

    public Entity(String file, int spriteSize) {
        spriteSheet = new Sprite(file, spriteSize);
        animation = new Animation();
        animation.setFrames(spriteSheet.getSpriteArrayCols());
        position = new Vector2D(0,0);
        collisionBox = new BoundingBox(position, spriteSize);
        collisionBox.updateBox(position);
    }

    public Entity(String file, int spriteSize, int scaledSpriteSize) {
        spriteSheet = new Sprite(file, spriteSize, scaledSpriteSize);
        animation = new Animation();
        animation.setFrames(spriteSheet.getSpriteArrayCols());
        position = new Vector2D(0,0);
        collisionBox = new BoundingBox(position, scaledSpriteSize);
        collisionBox.updateBox(position);
    }

    public void input(MouseHandler mouseHandler, KeyHandler keyHandler){}

    public void handleCollision(BoundingBox objectBox) {

        float centerDiffX = collisionBox.getCenterPos().getX() - objectBox.getCenterPos().getX();
        float centerDiffY = collisionBox.getCenterPos().getY() - objectBox.getCenterPos().getY();
        float collisionDistance = collisionBox.getDistanceToSide() + objectBox.getDistanceToSide();

        float xCompensation = collisionDistance - Math.abs(centerDiffX);
        float yCompensation = collisionDistance - Math.abs(centerDiffY);


        if (xCompensation < yCompensation) {
            if(centerDiffX > 0)
                position.incrementX(xCompensation);
            else
                position.incrementX(-xCompensation);
        }
        else if(xCompensation == yCompensation) {
            // blocking between squares fix
        }
        else {
            if(centerDiffY > 0)
                position.incrementY(yCompensation);
            else
                position.incrementY(-yCompensation);
        }
        collisionBox.updateBox(position);




        /*
        //Old Version
        Vector2D tmp = position.copyVector();
        position.setX(committedPosition.getX());
        collisionBox.updateBox(position);
        if(!collisionBox.collides(objectBox)) {
            return;
        }
        position = tmp.copyVector();
        position.setY(committedPosition.getY());
        collisionBox.updateBox(position);
        if(!collisionBox.collides(objectBox)) {
            return;
        }

        position = committedPosition.copyVector();
        collisionBox.updateBox(position);
        */
    }

    public void update() {

        if(isWalkingLeft) {
            move(-moveSpeed, 0);
            currentAction = LEFT;
        } else if(isWalkingRight) {
            move(moveSpeed, 0);
            currentAction = RIGHT;
        }

        if(isWalkingUp) {
            move(0, -moveSpeed);
            currentAction = UP;
        }else if(isWalkingDown) {
            move(0, +moveSpeed);
            currentAction = DOWN;
        }

        if(isDoingAction) {
            animation.update();
            isDoingAction = false;
        } else {
            animation.stopAnimation();
        }

        //update collisionbox
        collisionBox.updateBox(position);

    }

    public void render(Graphics2D graphics) {
        graphics.drawImage(animation.getAnimationFrame(spriteSheet.getSpriteArrayRow(currentAction)),
                null,(int) position.getWorldX(),(int) position.getWorldY());

        //for testing
        collisionBox.drawBoundingBox(graphics);
    }

    public void setAnimationDelay(int delay) {
        animation.setDelay(delay);
    }

    public void setSpriteSheet(Sprite spriteSheet) {
        this.spriteSheet = spriteSheet;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public Vector2D getPosition() {
        return position;
    }

    public Animation getAnimation() {
        return animation;
    }

    public Sprite getSpriteSheet() {
        return spriteSheet;
    }

    public int getEntitySize() {
        return spriteSheet.getSpriteSize();
    }

    public void setMoveSpeed(int moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    protected void move(float xOffset, float yOffset) {
        position.setPosition(position.getX() + xOffset, position.getY() + yOffset);
        isDoingAction = true;
    }

    public BoundingBox getCollisionBox() {
        return collisionBox;
    }

}
