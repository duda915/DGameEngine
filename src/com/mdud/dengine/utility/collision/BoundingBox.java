package com.mdud.dengine.utility.collision;

import com.mdud.dengine.graphics.Vector2D;

import java.awt.*;

//Square Box
public class BoundingBox {
    private Vector2D centerPos;
    private int size;
    private float distanceToSide;

    public BoundingBox(Vector2D position, int size) {
        this.size = size;
        distanceToSide = size/2;
        centerPos = new Vector2D(position.getX() + size/2 , position.getY() + size/2);

    }

    public boolean collides(BoundingBox box) {
        Vector2D boxPos = box.getCenterPos();
        float boxDistCorner = box.getDistanceToSide();

        if(checkCollision(centerPos.getX(), distanceToSide, boxPos.getX(), boxDistCorner) &&
                checkCollision(centerPos.getY(), distanceToSide, boxPos.getY(), boxDistCorner))
            return true;

        return false;
    }


    //collides() helpers
    private static boolean checkCollision(float ownCoordinate, float ownDistanceToSide,
                                          float boxCoordinate, float boxDistanceToSide) {
        //first corner
        if(boxCoordinate - boxDistanceToSide >= ownCoordinate - ownDistanceToSide &&
                boxCoordinate - boxDistanceToSide <= ownCoordinate + ownDistanceToSide)
            return true;
        //second corner
        else if(boxCoordinate + boxDistanceToSide >= ownCoordinate - ownDistanceToSide &&
                boxCoordinate + boxDistanceToSide <= ownCoordinate + ownDistanceToSide)
            return true;
        else
            return false;
    }

    public void updateBox(Vector2D position) {
        centerPos = new Vector2D(position.getX() + size/2 , position.getY() + size/2);
    }

    public void drawBoundingBox(Graphics2D graphics2D) {
        graphics2D.setColor(new Color(207, 40, 43));
        graphics2D.drawRect((int) (centerPos.getX() - distanceToSide), (int) (centerPos.getY() - distanceToSide),
                size, size);
    }


    public Vector2D getCenterPos() {
        return centerPos;
    }

    public float getDistanceToSide() {
        return distanceToSide;
    }
}
