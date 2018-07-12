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
    private Sprite spriteSheet;
    private Animation animation;
    private Vector2D position;

    //Animated states
    private final int UP = 0;
    private final int DOWN = 1;
    private final int LEFT = 2;
    private final int RIGHT = 3;
    private int currentAction = UP;

    //Entity controls
    protected boolean isWalkingUp = false;
    protected boolean isWalkingDown = false;
    protected boolean isWalkingLeft = false;
    protected boolean isWalkingRight = false;
    protected boolean isDoingAction = false;

    //Movement
    private float moveSpeed = 5;

    //collision
    protected BoundingBox collisionBox;
    protected Vector2D comittedPosition;

    public Entity(String file, int spriteSize) {
        spriteSheet = new Sprite(file, spriteSize);
        animation = new Animation();
        animation.setFrames(spriteSheet.getSpriteArrayCols());
        position = new Vector2D(0,0);
        collisionBox = new BoundingBox(position, spriteSize);
        comittedPosition = new Vector2D();
    }

    public abstract void input(MouseHandler mouseHandler, KeyHandler keyHandler);

    public abstract void handleCollision(Entity entity);

    protected void commitPosition() {
        comittedPosition = position.copyVector();
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
                null,(int) position.getX(),(int) position.getY());

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

    private void move(float xOffset, float yOffset) {
        position.setPosition(position.getX() + xOffset, position.getY() + yOffset);
        isDoingAction = true;
    }

}
