package com.mdud.dengine.graphics;

public class Vector2D {
    private float x;
    private float y;

    private static float worldX = 0;
    private static float worldY = 0;

    public Vector2D() {
        x = 0;
        y = 0;
    }

    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D(Vector2D vector) {
        this.x = vector.getX();
        this.y = vector.getY();
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void incrementX(float incrementValue) {
        this.x += incrementValue;
    }

    public void incrementY(float incrementValue) {
        this.y += incrementValue;
    }

    public void incrementPosition(float incrementX, float incrementY) {
        this.x += incrementX;
        this.y += incrementY;
    }

    public static Vector2D copyVector(Vector2D vector) {
        return new Vector2D(vector);
    }

    public Vector2D copyVector() {
        return new Vector2D(this);
    }

    public float getWorldX() {
        return x - worldX;
    }

    public float getWorldY() {
        return y - worldY;
    }

    public void setWorldPos(float x, float y) {
        worldX = x;
        worldY = y;

    }

    public void offsetWorldPos(float xOffset, float yOffset) {
        worldX += xOffset;
        worldY += yOffset;
    }

    @Override
    public int hashCode() {
        return Float.valueOf(x).hashCode() + Float.valueOf(y).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Vector2D vec = (Vector2D) obj;
        if(x == vec.getX() && y == vec.getY())
            return true;
        return false;
    }
}
