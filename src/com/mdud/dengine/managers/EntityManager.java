package com.mdud.dengine.managers;

import com.mdud.dengine.entity.Entity;
import com.mdud.dengine.entity.Player;
import com.mdud.dengine.entity.StandingDummy;
import com.mdud.dengine.entity.WalkingDummy;
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
        entities.add(new WalkingDummy());
        entities.add(new StandingDummy());
    }

    public void input(MouseHandler mouseHandler, KeyHandler keyHandler) {
        player.input(mouseHandler, keyHandler);
    }

    public void update() {

        for(Entity entity : entities) {
            entity.update();
            checkCollision(entity);
        }
    }

    public void render(Graphics2D graphics) {
        for (Entity entity : entities)
            entity.render(graphics);
    }

    //entity collision
    private void checkCollision(Entity entity) {
        for (Entity entity1 : entities) {
            if(entity != entity1 && entity1.getCollisionBox() != null)
                if (entity.getCollisionBox().collides(entity1.getCollisionBox()))
                    entity.handleCollision(entity1.getCollisionBox());
        }

        //Old collision
        //entity.commitPosition();
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }
    public ArrayList<Entity> getEntities() {
        return entities;
    }
}

