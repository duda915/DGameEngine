package com.mdud.dengine.graphics;

public class Vector2D {
    private float x;
    private float y;

    public Vector2D() {
        x = 0;
        y = 0;
    }

    public Vector2D(int x, int y) {
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

    public void incrementX(int incrementValue) {
        this.x += incrementValue;
    }

    public void incrementY(int incrementValue) {
        this.y += incrementValue;
    }

    public void incrementPosition(int incrementX, int incrementY) {
        this.x += incrementX;
        this.y += incrementY;
    }

    public static Vector2D copyVector(Vector2D vector) {
        return new Vector2D(vector);
    }

    public Vector2D copyVector() {
        return new Vector2D(this);
    }
}
