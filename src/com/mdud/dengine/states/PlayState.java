package com.mdud.dengine.states;

import com.mdud.dengine.managers.EntityManager;
import com.mdud.dengine.managers.TileManager;
import com.mdud.dengine.managers.BlockManager;
import com.mdud.dengine.utility.input.KeyHandler;
import com.mdud.dengine.utility.input.MouseHandler;

import java.awt.*;


public class PlayState extends GameState {

    // Initializings
    private EntityManager entityManager;
    private BlockManager blockManager;
    private TileManager tileManager;

    public PlayState() {
        entityManager = new EntityManager();
        blockManager = new BlockManager();
        tileManager = new TileManager(blockManager);

    }

    @Override
    public void update() {
        entityManager.update();
        tileManager.update(entityManager.getEntities());
    }

    @Override
    public void input(MouseHandler mouseHandler, KeyHandler keyHandler) {
        entityManager.input(mouseHandler, keyHandler);
    }

    @Override
    public void render(Graphics2D graphics) {
        tileManager.draw(graphics);
        entityManager.render(graphics);
        tileManager.lateDraw(graphics);

    }
}
