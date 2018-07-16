package com.mdud.dengine.main;

public class GameLauncher {

    public GameLauncher() {
        new GameWindow();
    }

    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "true");
        new GameLauncher();
    }
}
