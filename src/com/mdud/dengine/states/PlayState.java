package com.mdud.dengine.states;

import com.mdud.dengine.entity.EntityManager;
import com.mdud.dengine.utility.input.KeyHandler;
import com.mdud.dengine.utility.input.MouseHandler;

import java.awt.*;


public class PlayState extends GameState {

    // Initializings
    private EntityManager entityManager;

    public PlayState() {
        entityManager = new EntityManager();
    }

    @Override
    public void update() {
        entityManager.update();
    }

    @Override
    public void input(MouseHandler mouseHandler, KeyHandler keyHandler) {
        entityManager.input(mouseHandler, keyHandler);
    }

    @Override
    public void render(Graphics2D graphics) {
        entityManager.render(graphics);
    }
}
