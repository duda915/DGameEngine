package com.mdud.dengine.states;

import com.mdud.dengine.utility.KeyHandler;
import com.mdud.dengine.utility.MouseHandler;

import java.awt.*;
import java.util.ArrayList;

public class GameStateManager {
    private ArrayList<GameState> gameStates;

    public GameStateManager() {
        gameStates = new ArrayList<>();
        gameStates.add(new PlayState());
    }

    public void update() {
        for (GameState gs : gameStates) {
            gs.update();
        }
    }

    public void input(MouseHandler mouseHandler, KeyHandler keyHandler) {
        for (GameState gs : gameStates) {
            gs.input(mouseHandler, keyHandler);
        }
    }

    public void render(Graphics2D graphics) {
        for (GameState gs: gameStates) {
            gs.render(graphics);
        }
    }
}
