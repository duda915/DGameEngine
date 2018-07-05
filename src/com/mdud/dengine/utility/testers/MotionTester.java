package com.mdud.dengine.utility.testers;

import com.mdud.dengine.graphics.Vector2D;

public class MotionTester {
    private Vector2D positionVector;

    public MotionTester() {
        positionVector = new Vector2D();
    }

    public void updatePos(int boundX, int boundY) {
        positionVector.incrementPosition(1, 1);

        if(positionVector.getX() > boundX)
            positionVector.setX(0);
        if(positionVector.getY() > boundY)
            positionVector.setY(0);
    }

    public void updatePos(int xIncrement, int yIncrement, int boundX, int boundY) {
        positionVector.incrementPosition(xIncrement, yIncrement);

        if(positionVector.getX() > boundX || positionVector.getX() < 0)
            positionVector.setX(0);
        if(positionVector.getY() > boundY || positionVector.getY() < 0)
            positionVector.setY(0);
    }

    public void setPos(int x, int y) {
        positionVector.setPosition(x, y);
    }

    public int getPosX() {
        return positionVector.getX();
    }

    public int getPosY() {
        return positionVector.getY();
    }
}
