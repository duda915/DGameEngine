package com.mdud.dengine.utility;

public class MotionTester {
    private static int posX = 0;
    private static int posY = 0;

    public static void updatePos(int boundX, int boundY) {
        posX += 1;
        posY += 1;

        if(posX > boundX)
            posX = 0;
        if(posY > boundY)
            posY = 0;
    }

    public static void updatePos(int xIncrement, int yIncrement, int boundX, int boundY) {
        posX += xIncrement;
        posY += yIncrement;

        if(posX > boundX || posX < 0)
            posX = 0;
        if(posY > boundY || posY < 0)
            posY = 0;
    }

    public static void setPos(int x, int y) {
        posX = x;
        posY = y;
    }

    public static int getPosX() {
        return posX;
    }

    public static int getPosY() {
        return posY;
    }
}
