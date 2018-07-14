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
        centerPos = new Vector2D(position.getWorldX() + size/2 , position.getWorldY() + size/2);

    }

    public boolean collides(BoundingBox box) {
        return horizontalCollision(box) && verticalCollision(box);
    }


    //collides() helpers
    public boolean horizontalCollision(BoundingBox box) {
        return Math.abs(box.getCenterPos().getWorldX() - centerPos.getWorldX()) < distanceToSide + box.distanceToSide;
    }

    public boolean verticalCollision(BoundingBox box) {
        return Math.abs( box.getCenterPos().getWorldY() - centerPos.getWorldY()) < distanceToSide + box.distanceToSide;
    }

    public void updateBox(Vector2D position) {
        centerPos = new Vector2D(position.getX() + size/2 , position.getY() + size/2);
    }

    public void drawBoundingBox(Graphics2D graphics2D) {
        graphics2D.setColor(new Color(207, 40, 43));
        graphics2D.drawRect((int) (centerPos.getWorldX() - distanceToSide), (int) (centerPos.getWorldY() - distanceToSide),
                size, size);
    }


    public Vector2D getCenterPos() {
        return centerPos;
    }

    public float getDistanceToSide() {
        return distanceToSide;
    }
}
