package com.mdud.dengine.entity;

import com.mdud.dengine.utility.input.KeyHandler;
import com.mdud.dengine.utility.input.MouseHandler;

import java.awt.*;
import java.util.ArrayList;

public class EntityManager {
    private ArrayList<Entity> entities;
    private Player player;

    public EntityManager() {
        entities = new ArrayList<>();
        player = new Player();

        //Entity Configuration
        entities.add(player);
    }

    public void input(MouseHandler mouseHandler, KeyHandler keyHandler) {
        player.input(mouseHandler, keyHandler);
    }

    public void update() {
        for(Entity entity : entities)
            entity.update();
    }

    public void render(Graphics2D graphics) {
        for (Entity entity : entities)
            entity.render(graphics);
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }
}
